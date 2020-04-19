package com.yaqin.spring_boot_hw2.controller;

import com.yaqin.spring_boot_hw2.entity.Document;
import com.yaqin.spring_boot_hw2.exception.DocumentNotFoundException;
import com.yaqin.spring_boot_hw2.repository.DocumentRepository;
import com.yaqin.spring_boot_hw2.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/document")
public class DocumentController {
    /*
    @Resource
    private DocumentService documentService;

    @Autowired
    public void setApplicationService(DocumentService documentService) {
        this.documentService = documentService;
    }


    @GetMapping("/documents")
    public ResponseEntity<List<Document>> getAllTickets() {
        List<Document> list = documentService.listDocuments();
        return new ResponseEntity<List<Document>>(list, HttpStatus.OK);
    }*/
    private final DocumentRepository documentRepository;
    DocumentController(DocumentRepository documentRepository){
        this.documentRepository = documentRepository;
    }

    @GetMapping("/storage/get_all_documents")
    List<Document> all() {
        return (List<Document>) documentRepository.findAll();
    }

    @PostMapping("/storage/documents")
    Document newEmployee(@RequestBody Document newEmployee) {
        return documentRepository.save(newEmployee);
    }

    // Single item

    @GetMapping("/storage/documents/{docId}")
    Document one(@PathVariable Long id) {
        return documentRepository.findById(id)
                .orElseThrow(() -> new DocumentNotFoundException("Application Not Found"));
    }

    @PutMapping("/storage/documents/{docId}")
    Document replaceDocument(@RequestBody Document newDocument, @PathVariable Long id) {

        return documentRepository.findById(id)
                .map(document -> {
                    document.setContent(newDocument.getContent());
                    return documentRepository.save(document);
                })
                .orElseGet(() -> {
                    newDocument.setId(id);
                    return documentRepository.save(newDocument);
                });
    }

    @DeleteMapping("/storage/documents/{docId}")
    void deleteDocument(@PathVariable Long id) {
        documentRepository.deleteById(id);
    }
}
