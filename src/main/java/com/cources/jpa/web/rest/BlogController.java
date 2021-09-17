package com.cources.jpa.web.rest;

import com.cources.jpa.domain.Blog;
import com.cources.jpa.domain.View;
import com.cources.jpa.exception.ObjectNotFoundException;
import com.cources.jpa.service.BlogService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/")
public class BlogController {
    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @JsonView(View.Entity.class)
    @GetMapping(value = "/blogs")
    public ResponseEntity<List<Blog>> getBlogs (
            @RequestParam(name = "search", required = false, value = "") String place) {
        if (blogService.getAll().isEmpty()) {
            throw new ObjectNotFoundException("Blogs not exist");
        }
        List<Blog> blogs = blogService.getAll();
        if (place != null && !place.isEmpty()) {
            blogs = blogService.searchByTitle(place);
        }

        return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
    }

    @JsonView(View.EntityData.class)
    @GetMapping(value = "/blog/{id}")
    public ResponseEntity<Blog> getBlog (@PathVariable Long id) {
        Blog blog = blogService.getById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Blog not found :: " + id));
        return new ResponseEntity<Blog>(blog, HttpStatus.OK);
    }

    @PostMapping(value = "/blog")
    public ResponseEntity<Blog> createBlog (@RequestBody Blog blog) {
        return new ResponseEntity<Blog>(blogService.addBlog(blog), HttpStatus.CREATED);
    }

    @PutMapping(value = "/blog/{id}")
    public ResponseEntity<Blog> updateBlog (@PathVariable Long id, @RequestBody Blog blog) {
        Blog editBlog = blogService.getById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Blog not found :: " + id));
        editBlog.setTitle(blog.getTitle());
        editBlog.setText(blog.getText());

        return new ResponseEntity<Blog>(blogService.addBlog(editBlog), HttpStatus.OK);
    }

    @DeleteMapping(value = "/blog/{id}")
    public void deleteBlog (@PathVariable Long id) {
        Blog blog = blogService.getById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Blog not found :: " + id));
        blogService.deleteBlog(blog);
    }
}