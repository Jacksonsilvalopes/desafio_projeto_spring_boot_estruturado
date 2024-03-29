package com.jacksspring.dscommerce.controller;

import com.jacksspring.dscommerce.dto.ProductDTO;


import com.jacksspring.dscommerce.dto.ProductMintDTO;
import com.jacksspring.dscommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long id) {
        ProductDTO productDTO = productService.findById(id);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping

    public ResponseEntity<Page<ProductMintDTO>> findAll(Pageable pageable) {
        Page<ProductMintDTO> result = productService.findAll(pageable);
        return ResponseEntity.ok(result);

    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")

    @PostMapping
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO productDTO) {
        ProductDTO result = productService.create(productDTO);
        //Boas Praticas
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(productDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(result);
        // return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {

        ProductDTO result = productService.update(id, productDTO);
        return ResponseEntity.ok(result);

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/search")
    public ResponseEntity<Page<ProductDTO>> search(@RequestParam(name = "name", defaultValue = "") String name, Pageable pageable) {
        Page<ProductDTO> result = productService.search(name, pageable);
        return ResponseEntity.ok(result);

    }


}
