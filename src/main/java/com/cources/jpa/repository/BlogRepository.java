package com.cources.jpa.repository;

import com.cources.jpa.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findAllByOrderByIdDesc();

    List<Blog> findByTitleIgnoreCaseContaining(String place);
}
