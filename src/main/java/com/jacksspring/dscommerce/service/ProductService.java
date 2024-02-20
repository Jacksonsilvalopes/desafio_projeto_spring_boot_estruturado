package com.jacksspring.dscommerce.service;

import com.jacksspring.dscommerce.dto.ProductDTO;
import com.jacksspring.dscommerce.entity.Product;
import com.jacksspring.dscommerce.repository.ProductRepository;
import com.jacksspring.dscommerce.service.exceptions.DatabaseException;
import com.jacksspring.dscommerce.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso Não Encontrado")
        );
        return new ProductDTO(product);

    }

    @Transactional
    public Page<ProductDTO> search(String name, Pageable pageable) {
        Page<ProductDTO> result = productRepository.searchByName(name, pageable);
       // return result.map(x -> new ProductDTO(x));
        return result;
    }


    @Transactional
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> result = productRepository.findAll(pageable);
        return result.map(x -> new ProductDTO(x));
    }


    @Transactional
    public ProductDTO create(ProductDTO productDTO) {
        Product product = new Product();
        product = productRepository.save(product);
        copyDTOToEntity(productDTO, product);
        return new ProductDTO(product);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO productDTO) {


        try {
            Product product = productRepository.getReferenceById(id);
            copyDTOToEntity(productDTO, product);
            product = productRepository.save(product);
            return new ProductDTO(product);

        } catch (EntityNotFoundException e) {

            throw new ResourceNotFoundException("Recurso Não Encontrado");

        }

    }

    private void copyDTOToEntity(ProductDTO productDTO, Product product) {
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImgUrl(productDTO.getImgUrl());
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {

        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            productRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de Integridade Referencial");
        }


    }


}
