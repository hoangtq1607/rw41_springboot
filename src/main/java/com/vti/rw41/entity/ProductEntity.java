package com.vti.rw41.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "product") // -> SQL: select * from product
public class ProductEntity extends BaseEntity { // -> from ProductEntity

    @Id
    @GeneratedValue(generator = "productIdSeq") // -> su sung seq
    @SequenceGenerator( // khai bao seq
            sequenceName = "productIdSeq", //-> ten cua sequence
            name = "productIdSeq",//-> ten cua sequence
            initialValue = 1, //gia tri dau tien
            allocationSize = 2) // buoc nhay
    private Integer id;

    @Column(name = "name")
    private String productName;

    private Double price;

    @ManyToOne
    private CategoryEntity category;

}
