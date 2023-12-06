package com.avi.blogging.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.id.factory.internal.AutoGenerationTypeStrategy;
import org.modelmapper.internal.bytebuddy.dynamic.loading.InjectionClassLoader;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long categoryId;
    @NotEmpty
    private String categoryTitle;
    @NotEmpty
    private String categoryDescription;
    @OneToMany(mappedBy = "category",cascade =CascadeType.ALL)
    private List<Post> post=new ArrayList<>();

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public Category() {
    }
}
