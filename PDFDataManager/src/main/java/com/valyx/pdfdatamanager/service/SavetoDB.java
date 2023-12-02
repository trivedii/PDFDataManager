package com.valyx.pdfdatamanager.service;

import com.valyx.pdfdatamanager.dto.AttachmentResponse;
import com.valyx.pdfdatamanager.models.Attachment;
import com.valyx.pdfdatamanager.models.Transaction;
import com.valyx.pdfdatamanager.repository.Repository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SavetoDB {
    @Autowired
    private GmailService gmailService;
    @Autowired
    private PdfParserService pdfParserService;
    @Autowired
    private Repository repository;
    public AttachmentResponse save(String subject) throws IOException {
        AttachmentResponse attachmentResponse = new AttachmentResponse();
        try{
            List<Transaction> transactionDetails = new ArrayList<>();

            List<Attachment> attachments = gmailService.getAllAttachmentIds(subject);
            if(attachments.isEmpty()){
                throw new BadRequestException("No Attachments found for given subject! ");
            }
            for(Attachment attachment : attachments){
                String messageId = attachment.getMessageId();
                for(String str : attachment.getAttachmentIds()){
                    transactionDetails.addAll(pdfParserService.parseBankStatement(gmailService.decodeBase64ToBytes(messageId,str)));
                }
            }
            repository.saveAll(transactionDetails);
            attachmentResponse.setStatus("success");
            return attachmentResponse;
        }
        catch (Exception ex){
            attachmentResponse.setStatus("Failed");
            attachmentResponse.setDescription(ex.getMessage());
            attachmentResponse.setError("Bad Request");
            return attachmentResponse;
        }
    }
}
