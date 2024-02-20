package com.jacksspring.dscommerce.repository;

import com.jacksspring.dscommerce.dto.ProductDTO;
import com.jacksspring.dscommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;




public interface ProductRepository extends JpaRepository<Product, Long > {
    @Query("SELECT obj FROM Product obj " +
            "WHERE UPPER(obj.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<Product> searchByName1(String name, Pageable pageable);

    @Query("SELECT new com.jacksspring.dscommerce.dto.ProductDTO(obj) FROM Product obj " +
            "WHERE UPPER(obj.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<ProductDTO> searchByName(String name, Pageable pageable);
}
