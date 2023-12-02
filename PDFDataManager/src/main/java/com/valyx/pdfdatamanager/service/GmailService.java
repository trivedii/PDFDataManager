package com.valyx.pdfdatamanager.service;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.*;

import com.valyx.pdfdatamanager.config.GmailConfig;
import com.valyx.pdfdatamanager.models.Attachment;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.*;
import java.security.GeneralSecurityException;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;


@Service
public class GmailService {

    @Autowired
    private PdfParserService pdfParserService;
    private static final String USER_ID = "me";
    private static final String SUBJECT = "subject:";
    private static final String PDF_TYPE = "application/pdf";
    Gmail service = GmailConfig.getService();

    public GmailService() throws GeneralSecurityException, IOException {
    }

   // To retrieve messageIds of all messages with particular sublect
    public List<String> getMessageIds(String query) throws IOException, BadRequestException {
            List<String> messageIds = new ArrayList<>();
            ListMessagesResponse response = service.users().messages().list(USER_ID)
                    .setQ(SUBJECT + query)
                    .execute();
            if(response.isEmpty() || ObjectUtils.isEmpty(response.getMessages())){
                throw new BadRequestException("No mails found for the given Subject");
            }
            for(Message message : response.getMessages()){
                messageIds.add(message.getId());
            }
            return messageIds;

    }

   // to retrieve all pdf attachments of a message id
    public List<String> getAttachments(String messageId) {
        List<String> attachmentIds = new ArrayList<>();
        try {
            Message message = service.users().messages().get(USER_ID, messageId).setFormat("full").execute();

            List<MessagePart> messagePartList = getAttachmentsRecursive(message);
            for(MessagePart messagePart : messagePartList){
                if(Objects.equals(messagePart.getMimeType(), PDF_TYPE)){
                    attachmentIds.add(messagePart.getBody().getAttachmentId());
                }
            }
            return attachmentIds;
        } catch (IOException e) {
            throw new RuntimeException("Error retrieving attachments", e);
        }
    }

    //helper function for above getAttachments
    private List<MessagePart> getAttachmentsRecursive(Message message) {
        List<MessagePart> attachments = message.getPayload().getParts();
        if (attachments != null && !attachments.isEmpty()) {
            return attachments;
        }
        return attachments;
    }

    //to get all attachment ids of all the message ids
    public List<Attachment> getAllAttachmentIds(String subject) throws IOException {

        List<Attachment> attachments = new ArrayList<Attachment>();
        for(String str: getMessageIds(subject)){
            Attachment attachment = new Attachment(getAttachments(str),str);
            attachments.add(attachment);
        }
        return attachments;
    }

    //decoding base64 to byte array
    public byte[] decodeBase64ToBytes( String messageId,String attachmentId ) throws IOException {
        // Replace this base64String with your actual Base64-encoded PDF data
        // Get the attachment
        MessagePartBody attachment = service.users().messages().attachments()
                .get(USER_ID, messageId, attachmentId)
                .execute();
        String base64UrlString = attachment.getData();
        base64UrlString = base64UrlString.replace('-', '+').replace('_', '/');
        int padding = 4 - base64UrlString.length() % 4;
        if (padding % 4 != 0) {
            base64UrlString += "=".repeat(padding);
        }

        return Base64.getDecoder().decode(base64UrlString);
    }
}
