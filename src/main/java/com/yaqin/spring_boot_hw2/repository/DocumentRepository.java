package com.yaqin.spring_boot_hw2.repository;

import com.yaqin.spring_boot_hw2.entity.Document;
import org.springframework.data.repository.CrudRepository;

public interface DocumentRepository extends CrudRepository<Document, Long>{
}
