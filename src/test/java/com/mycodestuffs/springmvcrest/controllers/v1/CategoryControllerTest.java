package com.mycodestuffs.springmvcrest.controllers.v1;

import com.mycodestuffs.springmvcrest.api.v1.model.CategoryDTO;
import com.mycodestuffs.springmvcrest.domain.Category;
import com.mycodestuffs.springmvcrest.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class CategoryControllerTest {

    public static final String NAME = "Jim";

    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // we do not need this as mock in injected
//        categoryController= new CategoryController(categoryService);

        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();

    }

    @Test
    void testListCategories() throws Exception {
        CategoryDTO category1 = new CategoryDTO();
        category1.setId(1L);
        category1.setName(NAME);

        CategoryDTO category2 = new CategoryDTO();
        category1.setId(2L);
        category1.setName("Bob");

        List<CategoryDTO> categories = Arrays.asList(category1,category2);
//        List<CategoryDTO> categories = new ArrayList<CategoryDTO>(){{
//            add(category1);
//            add(category2);
//        }};

        when(categoryService.getAllCategories()).thenReturn(categories);

        mockMvc.perform(get("/api/v1/categories/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories",hasSize(2)));

    }

    @Test
    void testGetByNameCategories() throws Exception {
        CategoryDTO category1 = new CategoryDTO();
        category1.setName(NAME);
        category1.setId(2l);

        when(categoryService.getCategoryByName(anyString())).thenReturn(category1);

        mockMvc.perform(get("/api/v1/categories/Jim")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",equalTo(NAME)));


    }
}