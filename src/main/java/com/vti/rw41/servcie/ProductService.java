package com.vti.rw41.servcie;

import com.vti.rw41.dto.response.ProductDTO;
import com.vti.rw41.entity.ProductEntity;
import com.vti.rw41.reposioty.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Page<ProductDTO> getAllProduct(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productEntity -> {
                    ProductDTO dto = new ProductDTO();
                    dto.setId(productEntity.getId());
                    dto.setName(productEntity.getProductName());
                    dto.setCategory(productEntity.getCategory().getName());
                    dto.setPrice(productEntity.getPrice());
                    dto.setCreatedDate(productEntity.getCreatedDate());
                    return dto;
                });
    }
}
