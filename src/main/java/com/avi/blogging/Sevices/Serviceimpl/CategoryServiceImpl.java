package com.avi.blogging.Sevices.Serviceimpl;

import com.avi.blogging.Entity.Category;
import com.avi.blogging.Exceptation.ResourceNotFoundException;
import com.avi.blogging.Payload.CategoryDto;
import com.avi.blogging.Repository.CategoryRepository;
import com.avi.blogging.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category=this.modelMapper.map(categoryDto,Category.class);
        return this.modelMapper.map(categoryRepository.save(category),CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
       Category category=this.categoryRepository.findById(categoryId)
               .orElseThrow(()->new ResourceNotFoundException("category","categoryId",categoryId));
       category.setCategoryTitle(categoryDto.getCategoryTitle());
       category.setCategoryDescription(categoryDto.getCategoryDescription());
       return this.modelMapper.map(categoryRepository.save(category),CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
         Category category=categoryRepository.findById(categoryId)
                 .orElseThrow(()-> new ResourceNotFoundException("category","categoryId",categoryId));
        return this.modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categoryList=this.categoryRepository.findAll();
        List<CategoryDto> categoryDtoList=new ArrayList<>();
        for(Category cat:categoryList){
           CategoryDto categoryDto=this.modelMapper.map(cat,CategoryDto.class);
            categoryDtoList.add(categoryDto);
        }
        return categoryDtoList;
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category=categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("category","categoryId",categoryId));
        this.categoryRepository.delete(category);

    }

}
