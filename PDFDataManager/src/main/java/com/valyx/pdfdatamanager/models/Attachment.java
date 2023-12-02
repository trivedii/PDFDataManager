package com.valyx.pdfdatamanager.models;

import com.google.api.services.gmail.model.MessagePart;

import java.util.List;

public class Attachment {
    private String messageId;
    public List<String> attachmentIds;

    public Attachment() {
    }

    public Attachment(List<String> attachmentIds, String messageId) {
        this.messageId = messageId;
        this.attachmentIds = attachmentIds;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public List<String> getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(List<String> attachmentIds) {
        this.attachmentIds = attachmentIds;
    }
}
