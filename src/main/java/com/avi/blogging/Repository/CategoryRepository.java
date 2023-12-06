package com.avi.blogging.Repository;

import com.avi.blogging.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Locale;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
