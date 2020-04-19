package com.yaqin.spring_boot_hw2.entity;

import javax.persistence.*;

@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="document_id")
    private Long id;

    @Column(name = "document_content", nullable = false)
    private String content;

    public Document(){}

    public Document(String content){
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
