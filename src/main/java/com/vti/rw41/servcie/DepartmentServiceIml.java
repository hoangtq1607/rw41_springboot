package com.vti.rw41.servcie;

import com.vti.rw41.entity.Department;
import com.vti.rw41.reposioty.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service //singleton
//@Primary
public class DepartmentServiceIml implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @PostConstruct
    void init() {
        System.out.println("DepartmentServiceIml created");
    }

    @Override
    public List<Department> getAllDepartment() {

        return departmentRepository.getAllDepartment();
    }

    @Override
    public Optional<Department> getDepartmentById(Integer id) {

        return departmentRepository.getDepartmentById(id);
    }

    @Override
    public Department addDepartment(Department department) {
        return departmentRepository.addDepartment(department);
    }

    @Override
    public Optional<Department> deleteDepartmentById(Integer id) {
        return departmentRepository.deleteDepartmentById(id);
    }

    @Override
    public Optional<Department> updateDepartmentById(Integer id, Department department) {

        return departmentRepository.updateDepartmentById(id, department);
    }
}
