package com.neel.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neel.demo.Dao.FileStorageService;
import com.neel.demo.Models.UploadedFile;

@Service
public class FileUploadService {

	
	@Autowired
	private FileStorageService fservice;
	
	public UploadedFile uploadFile(UploadedFile file) {
		
		return fservice.save(file);
	
	}
	
	public UploadedFile findById(Long id) {
		return fservice.getById(id);
	}
	
	
	public List<UploadedFile> allfiles(){
	
		
		return fservice.findAll();
	}


}
