package com.cources.jpa.web.rest;

import com.cources.jpa.domain.Image;
import com.cources.jpa.domain.View;
import com.cources.jpa.exception.ObjectNotFoundException;
import com.cources.jpa.service.ImageService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class ImageController {
    private final ImageService imageService;

    @Value("${upload.img.path}")
    private String uploadPath;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<String> upload (@RequestParam("file") MultipartFile multipartFile) {
        imageService.upload(multipartFile);
        return new ResponseEntity<String>(multipartFile.getOriginalFilename() + " is uploaded", HttpStatus.CREATED);
    }

    @JsonView(View.Entity.class)
    @GetMapping(value = "/preview")
    public ResponseEntity<List<Image>> previews () {
        List<Image> images = imageService.findAll();
        return new ResponseEntity<List<Image>>(images, HttpStatus.OK);
    }

    @JsonView(View.EntityData.class)
    @GetMapping(value = "/preview/{id}")
    public ResponseEntity<Image> preview (@PathVariable Long id) {
        Image image = imageService.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Blog not found :: " + id));
        return new ResponseEntity<Image>(image, HttpStatus.OK);
    }

    @GetMapping(value = "/download/{hashId}")
    public ResponseEntity<FileInputStream> view (@PathVariable String hashId) throws MalformedURLException, FileNotFoundException {
        Image image = imageService.findByHashId(hashId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + URLEncoder.encode(image.getFileName()))
                .contentLength(image.getSize())
                .contentType(MediaType.IMAGE_JPEG)
                .body(new FileInputStream(image.getUploadPath()));
    }
}
