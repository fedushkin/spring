package com.cources.jpa.service;

import com.cources.jpa.domain.Blog;
import com.cources.jpa.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<Blog> getAll () {
        return blogRepository.findAllByOrderByIdDesc();
    }

    public Optional<Blog> getById (Long id) {
        return blogRepository.findById(id);
    }

    public Blog addBlog (Blog blog) {
        return blogRepository.save(blog);
    }

    public void deleteBlog(Blog blog) {
        blogRepository.delete(blog);
    }

    public List<Blog> searchByTitle (String place) {
        return blogRepository.findByTitleIgnoreCaseContaining(place);
    }
}