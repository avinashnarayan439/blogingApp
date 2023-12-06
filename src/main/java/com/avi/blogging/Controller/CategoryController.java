package com.avi.blogging.Controller;

import com.avi.blogging.Payload.CategoryDto;
import com.avi.blogging.Payload.UserDto;
import com.avi.blogging.Repository.CategoryRepository;
import com.avi.blogging.apiResponse.ApiResponse;
import com.avi.blogging.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto categoryDtoCreated = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(categoryDtoCreated, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable Long categoryId){
        CategoryDto updatedCategoryDto=this.categoryService.updateCategory(categoryDto,categoryId);
        return ResponseEntity.ok(updatedCategoryDto);
    }
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long categoryId){
        this.categoryService.deleteCategory(categoryId);
        ApiResponse apiResponse=new ApiResponse("category Deleted Sucessfully",true);
        return  new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategory() {

        return ResponseEntity.ok(this.categoryService.getAllCategories());
    }
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable Long categoryId){
        return ResponseEntity.ok(this.categoryService.getCategoryById(categoryId));
    }

}

