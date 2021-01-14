package com.mycodestuffs.springmvcrest.services;

import com.mycodestuffs.springmvcrest.api.v1.mapper.CategoryMapper;
import com.mycodestuffs.springmvcrest.api.v1.model.CategoryDTO;
import com.mycodestuffs.springmvcrest.domain.Category;
import com.mycodestuffs.springmvcrest.repositories.CategoryRepository;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CategoryServiceTest {
    public static final Long ID=2L;
    public static final String NAME="Jimmy";

    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
    }

    @Test
    void getAllCategories() {
        //given
        List<Category> categories = new ArrayList<Category>(){{
            add(new Category());
            add(new Category());
            add(new Category());
        }};

        when(categoryRepository.findAll()).thenReturn(categories);

        //when
        List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();

        //then
        Assertions.assertEquals(3,categoryDTOS.size());
    }

    @Test
    void getCategoryByName() {

        //given
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        when(categoryRepository.findByName(anyString())).thenReturn(category);

        //when
        CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);

        //then
        Assertions.assertEquals(ID,categoryDTO.getId());
        Assertions.assertEquals(NAME,categoryDTO.getName());
    }
}