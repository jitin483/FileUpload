package com.neel.demo.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.neel.demo.Models.UploadedFile;
import com.neel.demo.services.FileUploadService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class FileController {
	  @Autowired
	    private FileUploadService fus;
	  
	  
	
    @GetMapping("/upload")
    public String upload(Model model) {
        return "upload";
    }


  

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file ,HttpSession s ,Model m) {
        try {
          
            UploadedFile uploadedFile = new UploadedFile();
            uploadedFile.setFileName(file.getOriginalFilename());
            uploadedFile.setFileType(file.getContentType());
            java.util.Date currentDate = new java.util.Date();
            Date sqlDate = new Date(currentDate.getTime());
            uploadedFile.setTimestamp(sqlDate);
            byte[] data=file.getBytes();
            String path =s.getServletContext().getRealPath("/")+"uploadedFiles"+File.separator+file.getOriginalFilename();
            FileOutputStream fos=new FileOutputStream(path);
            fos.write(data);
            fus.uploadFile(uploadedFile);
            fos.close();
            return "redirect:/all";
        } catch (Exception e) {
            return "File upload failed: " + e.getMessage();
        }
    }

    
    
    
    
    

    
    @GetMapping("download/{fileName}")
    public void downloadFile(@PathVariable String fileName, HttpServletResponse response , HttpSession s) throws IOException {
    	String path=s.getServletContext().getRealPath("/")+"uploadedFiles"+File.separator+fileName;
    	File file = new File(path);

        if (file.exists()) {
            response.setContentType("application/octet-stream");
           response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.setHeader("Content-Length", String.valueOf(file.length()));
            try (InputStream inputStream = new FileInputStream(file)) {
                FileCopyUtils.copy(inputStream, response.getOutputStream());
                response.flushBuffer();
                
            }
        } else {
           
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
    
  
    @GetMapping("/all")
    public String listUploadedFiles(Model model,HttpSession s) {
    	List<UploadedFile> ListofFile = fus.allfiles();
    	model.addAttribute("ListofFile", ListofFile);
    	model.addAttribute("success","File uploaded successfully!");
        return "download";
    }
    
    
   
    
    @GetMapping("/view/{pdfFileName}")
    public String displayPdf(@PathVariable String pdfFileName,HttpSession s,Model model) {
    	String path=s.getServletContext().getRealPath("/")+"uploadedFiles"+File.separator+pdfFileName;
        try {
            Path filePath = Paths.get(path);
            byte[] fileBytes = Files.readAllBytes(filePath);
            String base64Content = Base64.getEncoder().encodeToString(fileBytes);
            model.addAttribute("fileData", base64Content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "pdfDisplay"; 
    }
    
}
