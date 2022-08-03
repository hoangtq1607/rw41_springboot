package com.vti.rw41.controller;

import com.vti.rw41.dto.request.DepartmentRequest;
import com.vti.rw41.entity.Department;
import com.vti.rw41.servcie.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/departments")
public class DepartmentController {

    // [R] GET /departments -> lay tat ca departments
    // [R] GET /departments/{id} -> lay department theo id
    // [C] POST /departments -> tao moi departments
    // [U] PUT /departments/{id} -> update department theo id
    // [D] DELETE /departments/{id} -> delete department theo id

    //    @Setter
    @Autowired
    DepartmentService departmentService; //inject

//
//    public DepartmentController(DepartmentService departmentService) {
//        this.departmentService = departmentService;
//    }

    @GetMapping
    public List<Department> getAllDepartment() {
        return departmentService.getAllDepartment();
    }

    //    @PostMapping
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Department addDepartment(@Valid @RequestBody DepartmentRequest department) {
        return departmentService.addDepartment(department);
    }

    //    @GetMapping("/{id}")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Department> getDepartmentById(@PathVariable Integer id) {

        log.info("getDepartmentById={}", id);
        // log
        return departmentService.getDepartmentById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Optional<Department> deleteDepartmentById(@PathVariable Integer id) {

        log.info("getDepartmentById={}", id);
        // log
        return departmentService.deleteDepartmentById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Optional<Department> updateDepartmentById(@PathVariable Integer id,
                                                     @RequestBody DepartmentRequest department) {

        log.info("getDepartmentById={}", id);
        // log
        return departmentService.updateDepartmentById(id, department);
    }

    //bean container, bean
}
