package com.vti.rw41.servcie;

import com.vti.rw41.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    List<Department> getAllDepartment();

    Optional<Department> getDepartmentById(Integer i);

    Department addDepartment(Department department);

    Optional<Department> deleteDepartmentById(Integer id);

    Optional<Department> updateDepartmentById(Integer id, Department department);

}
