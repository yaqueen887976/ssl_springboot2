package com.yaqin.spring_boot_hw2.service;

import com.yaqin.spring_boot_hw2.entity.Document;
import com.yaqin.spring_boot_hw2.exception.DocumentNotFoundException;
import com.yaqin.spring_boot_hw2.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    @Resource
    private DocumentRepository documentRepository;

    @Override
    public List<Document> listDocuments() {
        return (List<Document>) documentRepository.findAll();
    }

    @Override
    public Document findDocument(long id) {
        //return documentRepository.findById(id);

        Optional<Document> optionalDocument =  documentRepository.findById(id);

        if(optionalDocument.isPresent())
            return optionalDocument.get();
        else
            throw new DocumentNotFoundException("Application Not Found");


    }

    @Override
    public void save(Document document) {
        documentRepository.save(document);
    }

    @Override
    @Transactional
    public void delete(long id) {
        Optional<Document> optionalDocument =  documentRepository.findById(id);

        if(optionalDocument.isPresent())
            documentRepository.delete(optionalDocument.get());
        else
            throw new DocumentNotFoundException("Application Not Found");


    }

    @Override
    @Transactional
    public void update(Document document) {
        document.setContent(document.getContent());
    }
}