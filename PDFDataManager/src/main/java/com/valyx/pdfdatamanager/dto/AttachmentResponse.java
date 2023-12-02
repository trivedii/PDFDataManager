package com.valyx.pdfdatamanager.dto;

public class AttachmentResponse extends BaseError{
    private String Status;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
