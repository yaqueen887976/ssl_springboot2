package com.yaqin.spring_boot_hw2.controller;

import com.yaqin.spring_boot_hw2.entity.Document;
import com.yaqin.spring_boot_hw2.exception.DocumentNotFoundException;
import com.yaqin.spring_boot_hw2.repository.DocumentRepository;
import com.yaqin.spring_boot_hw2.response.ResponseCreate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/storage/documents")
public class DocumentController {

    private final DocumentRepository documentRepository;


    DocumentController(DocumentRepository documentRepository){
        this.documentRepository = documentRepository;
    }


    @PostMapping
    //@PostMapping("/storage/documents")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseCreate<String>>  newDocument(@RequestBody Document newDocument) throws InterruptedException{
        documentRepository.save(newDocument);

        return new ResponseEntity<>(new ResponseCreate<String>(HttpStatus.valueOf("CREATED").toString(), "text/plain;", newDocument.getContent()), HttpStatus.CREATED);
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
