package com.avi.blogging.services;

import com.avi.blogging.Payload.CategoryDto;

import java.util.List;

public interface CategoryService {
    //create category
    CategoryDto createCategory(CategoryDto categoryDto);
    //update category
    CategoryDto updateCategory(CategoryDto categoryDto,Long categoryID);
    //get category
    CategoryDto getCategoryById(Long categoryId);
    //getAllCategory
    List<CategoryDto> getAllCategories();
    //deleteCategory
    void deleteCategory(Long categoryId);
}
