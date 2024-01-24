package com.example.entity_crud_1.controller;

import com.example.entity_crud_1.model.Post;
import com.example.entity_crud_1.reponsitory.PostReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostReponsitory postReponsitory;
    @GetMapping
    public ResponseEntity showList() {
        return new ResponseEntity(postReponsitory.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        return new ResponseEntity(postReponsitory.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Post post) {
        return new ResponseEntity(postReponsitory.save(post), HttpStatus.OK);
    }

    @PutMapping ("/{id}")
    public ResponseEntity save(@RequestBody Post post, @PathVariable Long id) {
        post.setId(id);
        return new ResponseEntity(postReponsitory.save(post), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        postReponsitory.deleteById(id);
        return new ResponseEntity("delete thanh cong", HttpStatus.OK);
    }

    @GetMapping("/newest")
    public ResponseEntity<List<Post>> getNewestPosts() {
        List<Post> newestPosts = postReponsitory.findAllByOrderByCreatedAtAsc();
        return new ResponseEntity<>(newestPosts, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Post>> searchPosts(@RequestParam String keyword) {
        List<Post> searchResult = postReponsitory.findByTitleContainingOrContentContaining(keyword, keyword);
        return new ResponseEntity<>(searchResult, HttpStatus.OK);
    }

}
