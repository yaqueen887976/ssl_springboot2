package com.yaqin.spring_boot_hw2.response;

/*
* Response:
200 OK
Content-Length: 11
hello world*/

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseGet<T> {

    private Integer code;

    private String message;

    private T content;

    public ResponseGet(Integer code, String message, T content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }


}
