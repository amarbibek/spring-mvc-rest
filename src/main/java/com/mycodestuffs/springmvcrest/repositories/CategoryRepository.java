package com.mycodestuffs.springmvcrest.repositories;

import com.mycodestuffs.springmvcrest.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}