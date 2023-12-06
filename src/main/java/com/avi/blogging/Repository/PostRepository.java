package com.avi.blogging.Repository;

import com.avi.blogging.Entity.Category;
import com.avi.blogging.Entity.Post;
import com.avi.blogging.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
  List<Post> findByCategory(Category category);

  Page<Post> findByUser(@Param("user") User user, Pageable pageable);

  Page<Post> findByCategory(@Param("category") Category category, Pageable pageable);

}
