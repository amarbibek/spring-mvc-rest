package com.mycodestuffs.springmvcrest.services;

import com.mycodestuffs.springmvcrest.api.v1.model.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);
}
