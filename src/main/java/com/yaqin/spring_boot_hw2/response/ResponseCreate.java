package com.yaqin.spring_boot_hw2.response;


public class ResponseCreate <T>{

    private String code;

    private String content_type;

    private T content;

    public ResponseCreate(String code, String content_type, T content) {
        this.code = code;
        this.content_type = content_type;
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContentType() {
        return content_type;
    }

    public void setContentType(String message) {
        this.content_type = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
