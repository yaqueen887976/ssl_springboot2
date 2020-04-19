package com.yaqin.spring_boot_hw2.exception;

public class DocumentNotFoundException extends RuntimeException{
    public DocumentNotFoundException(String exception) {
        super(exception);
    }
}
