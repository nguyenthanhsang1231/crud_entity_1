package com.example.entity_crud_1.reponsitory;

import com.example.entity_crud_1.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostReponsitory extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedAtAsc();
    List<Post> findAllByTitleContainingIgnoreCase(String name);

}
