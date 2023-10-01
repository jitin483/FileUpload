package com.neel.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neel.demo.Models.UploadedFile;


@Repository
public interface FileStorageService extends JpaRepository<UploadedFile, Long> {
    // Custom query methods can be defined here if needed
}
