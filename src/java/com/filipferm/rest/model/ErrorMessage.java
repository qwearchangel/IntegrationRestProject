/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filipferm.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Filip
 */
@XmlRootElement
public class ErrorMessage {
   private String errorMessage;
   private int errorCode;
   private String documentaionLink;

    public ErrorMessage() {
    }

    public ErrorMessage(String errorMessage, int errorCode, String documentaionLink) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.documentaionLink = documentaionLink;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getDocumentaionLink() {
        return documentaionLink;
    }

    public void setDocumentaionLink(String documentaionLink) {
        this.documentaionLink = documentaionLink;
    }
   
   
}
