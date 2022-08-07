package com.vti.rw41.servcie;

import com.vti.rw41.dto.response.ProductDTO;
import com.vti.rw41.reposioty.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Page<ProductDTO> getAllProduct(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productEntity -> {

                    ProductDTO dto = new ProductDTO();
                    BeanUtils.copyProperties(productEntity, dto);
                    if (productEntity.getCategory() != null) {
                        dto.setCategory(productEntity.getCategory().getName());
                    }
                    return dto;
                });
    }
}
