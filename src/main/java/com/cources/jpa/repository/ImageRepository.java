package com.cources.jpa.repository;

import com.cources.jpa.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findByHashId (String hashId);
}
