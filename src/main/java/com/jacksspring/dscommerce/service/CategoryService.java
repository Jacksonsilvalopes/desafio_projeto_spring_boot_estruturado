package com.jacksspring.dscommerce.service;

import com.jacksspring.dscommerce.dto.CategoryDTO;
import com.jacksspring.dscommerce.entity.Category;
import com.jacksspring.dscommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {


        @Autowired
        CategoryRepository categoryRepository;


        @Transactional
        public List<CategoryDTO> findAll() {
            List<Category> result = categoryRepository.findAll();
            return result.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
        }

}
