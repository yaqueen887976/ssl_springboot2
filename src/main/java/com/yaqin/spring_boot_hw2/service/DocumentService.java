package com.yaqin.spring_boot_hw2.service;

import com.yaqin.spring_boot_hw2.entity.Document;

import java.util.List;

public interface DocumentService {
    List<Document> listDocuments();
    Document findDocument(long id);
    void save(Document document);
    void delete(long id);
    void update(Document document);

}
