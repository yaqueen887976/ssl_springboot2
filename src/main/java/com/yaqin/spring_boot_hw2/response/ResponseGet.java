package com.yaqin.spring_boot_hw2.response;

/*
* Response:
200 OK
Content-Length: 11
hello world*/

import com.fasterxml.jackson.annotation.JsonInclude;

/*Response:
200 OK
Content-Length: 11
hello world*/
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseGet {

    private String code;

    private int content_length;

    private String content;

    public ResponseGet(String code, int content_length, String content) {
        this.code = code;
        this.content_length = content_length;
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getContent_length() {
        return content_length;
    }

    public void setContent_length(int content_length) {
        this.content_length = content_length;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
