package com.vti.rw41.servcie;

import com.vti.rw41.entity.Department;
import com.vti.rw41.reposioty.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    public List<Department> getAllDepartment() {

        return departmentRepository.getAllDepartment();
    }

    public Optional<Department> getDepartmentById(Integer id) {

        return departmentRepository.getDepartmentById(id);
    }

    public Department addDepartment(Department department) {
        return departmentRepository.addDepartment(department);
    }

    public Optional<Department> deleteDepartmentById(Integer id) {
        return departmentRepository.deleteDepartmentById(id);
    }

    public Optional<Department> updateDepartmentById(Integer id, Department department) {

        return departmentRepository.updateDepartmentById(id, department);
    }
}
