package com.vti.rw41.controller;

import com.vti.rw41.dto.DepartmentDto;
import com.vti.rw41.servcie.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping
    public List<DepartmentDto> getAllDepartment() {
        return departmentService.getAllDepartment();
    }

    //    @PostMapping
    @RequestMapping(value = "", method = RequestMethod.POST)
    public DepartmentDto addDepartment(@RequestBody DepartmentDto department) {
        return departmentService.addDepartment(department);
    }

    //    @GetMapping("/{id}")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<DepartmentDto> getDepartmentById(@PathVariable Integer id) {

        log.info("getDepartmentById={}", id);

        // log
        return departmentService.getDepartmentById(id);
    }

    //bean container, bean
}
