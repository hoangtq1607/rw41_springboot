package com.vti.rw41.controller;

import com.vti.rw41.dto.response.ProductDTO;
import com.vti.rw41.dto.request.ProductRequest;
import com.vti.rw41.servcie.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public Page<ProductDTO> productEntities(Pageable pageable) {
        return productService.getAllProduct(pageable);
    }

    @PostMapping
    public void receivedRequest(@RequestBody @Valid ProductRequest request) {
        System.out.println(request);
    }

}
