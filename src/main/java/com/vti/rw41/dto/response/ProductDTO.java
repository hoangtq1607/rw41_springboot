package com.vti.rw41.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductDTO {

    private Integer id;
    private String name;
    private Double price;
    private LocalDateTime createdDate;
    private String category;

}
