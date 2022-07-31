package com.vti.rw41.controller;

import com.vti.rw41.dto.ProductRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    @PostMapping
    public void receivedRequest(@RequestBody @Valid ProductRequest request) {
        System.out.println(request);
    }

}
