package com.mycodestuffs.springmvcrest.api.v1.mapper;

import com.mycodestuffs.springmvcrest.api.v1.model.CategoryDTO;
import com.mycodestuffs.springmvcrest.domain.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {
    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    void categoryToCategoryDTO() {
        //given
        Category category = new Category();
        category.setName("Joe");
        category.setId(1L);

        //when
        CategoryDTO categoryDTO= categoryMapper.categoryToCategoryDTO(category);

        //then
        Assertions.assertEquals(Long.valueOf(1L),categoryDTO.getId());
        Assertions.assertEquals("Joe",categoryDTO.getName());

    }
}