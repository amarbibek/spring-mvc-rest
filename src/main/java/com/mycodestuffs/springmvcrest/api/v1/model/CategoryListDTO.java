package com.mycodestuffs.springmvcrest.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Data
@AllArgsConstructor
//@RequestMapping("/api/v1/categories/")
public class CategoryListDTO {

    List<CategoryDTO> categories;
}
