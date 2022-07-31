package com.vti.rw41.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DepartmentID")
    private Integer id;

    @Column(name = "DepartmentName")
    private String departmentName;
}
