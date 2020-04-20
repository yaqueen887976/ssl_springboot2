package com.yaqin.spring_boot_hw2.controller;

import com.yaqin.spring_boot_hw2.entity.Document;
import com.yaqin.spring_boot_hw2.exception.DocumentNotFoundException;
import com.yaqin.spring_boot_hw2.repository.DocumentRepository;
import com.yaqin.spring_boot_hw2.response.ResponseCreate;
import com.yaqin.spring_boot_hw2.response.ResponseGet;
import com.yaqin.spring_boot_hw2.service.DocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/storage/documents")
public class DocumentController {

    private final DocumentRepository documentRepository;
    private DocumentService documentService;

    DocumentController(DocumentRepository documentRepository){
        this.documentRepository = documentRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseCreate<String>>  newDocument(@RequestBody Document newDocument) throws InterruptedException{
        documentRepository.save(newDocument);
        return new ResponseEntity<>(new ResponseCreate<String>(HttpStatus.valueOf("CREATED").toString(),
                "text/plain;",
                newDocument.getContent()),
                HttpStatus.CREATED);
    }

    // Single item

    @GetMapping("/storage/documents/{docId}")
    ResponseEntity<ResponseGet> one(@PathVariable Long id) {
        documentRepository.findById(id)
                .orElseThrow(() -> new DocumentNotFoundException("Application Not Found"));

        return new ResponseEntity<>(new ResponseGet(HttpStatus.valueOf("OK").toString(),
                documentService.findDocument(id).getContent().length(),
                documentService.findDocument(id).getContent()),
                HttpStatus.CREATED);
    }

    @PutMapping("/storage/documents/{docId}")
    String replaceDocument(@RequestBody Document newDocument, @PathVariable Long id) {

        documentRepository.findById(id)
                .map(document -> {
                    document.setContent(newDocument.getContent());
                    return documentRepository.save(document);
                })
                .orElseGet(() -> {
                    newDocument.setId(id);
                    return documentRepository.save(newDocument);
                });

        return "204 No Content";
    }

    @DeleteMapping("/storage/documents/{docId}")
    String deleteDocument(@PathVariable Long id) {
        documentRepository.deleteById(id);
        return "204 No Content";
    }
}
