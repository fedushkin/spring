package com.cources.jpa.service;

import com.cources.jpa.domain.Image;
import com.cources.jpa.domain.ImageStatus;
import com.cources.jpa.repository.ImageRepository;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final Hashids hashids;

    @Value("${upload.img.path}")
    private String uploadPath;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
        this.hashids = new Hashids(getClass().getName(), 9);
    }

    public void upload (MultipartFile multipartFile) {
        Image image = new Image();
        image.setFileName(multipartFile.getOriginalFilename());
        image.setContentType(multipartFile.getContentType());
        image.setImageStatus(ImageStatus.DRAFT);
        image.setSize(multipartFile.getSize());
        image.setExtension(getExtension(multipartFile.getOriginalFilename()));
        imageRepository.save(image);

        LocalDate now = LocalDate.now();
        File uploadPath = new File(String.format("%s/%d/%d/%d",
                this.uploadPath, now.getYear(), now.getMonthValue(), now.getDayOfMonth()));

        if (!uploadPath.exists() && uploadPath.mkdirs()) {
            System.out.println("files created");
        }

        image.setHashId(hashids.encode(image.getId()));
        image.setUploadPath(String.format("%s%d/%d/%d/%s.%s", this.uploadPath,
                now.getYear(), now.getMonthValue(), now.getDayOfMonth(),
                image.getHashId(), image.getExtension()));
        imageRepository.save(image);

        uploadPath = uploadPath.getAbsoluteFile();
        File file = new File(uploadPath, String.format("%s.%s", image.getHashId(), image.getExtension()));
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.getMessage();
        }
    }


    public Image findByHashId (String hashId) {
        return imageRepository.findByHashId(hashId);
    }

    public Optional<Image> findById (Long id) {
        return imageRepository.findById(id);
    }

    public List<Image> findAll () {
        return imageRepository.findAll();
    }

    public String getExtension (String fileName) {
        String ext = null;
        if (fileName != null && !fileName.isEmpty()) {
            int dot = fileName.lastIndexOf('.');
            if (dot > 0 && dot <= fileName.length() - 2) {
                ext = fileName.substring(dot + 1);
            }
        }
        return ext;
    }
}