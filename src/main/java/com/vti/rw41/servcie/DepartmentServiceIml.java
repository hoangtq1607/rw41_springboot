package com.vti.rw41.servcie;

import com.vti.rw41.dto.request.DepartmentRequest;
import com.vti.rw41.entity.Department;
import com.vti.rw41.exeption.ApiException;
import com.vti.rw41.reposioty.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceIml implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAllDepartment() {
        return departmentRepository.findAll(Sort.by("id").descending());

    }

    @Override
    public Optional<Department> getDepartmentById(Integer id) {

        Department byId = departmentRepository.findById(id)
                .orElseThrow(() -> new ApiException("department.not.exists"));

        return Optional.of(byId);

    }

    @Override
    public Department addDepartment(DepartmentRequest department) {
        Department d = new Department();
        d.setDepartmentName(department.getDepartmentName());
        return departmentRepository.save(d);
    }

    @Override
    public Optional<Department> deleteDepartmentById(Integer id) {
        Optional<Department> department = departmentRepository.findById(id);
        department.ifPresent(d -> departmentRepository.delete(d));
        return department;
    }

    @Override
    public Department updateDepartmentById(Integer id, DepartmentRequest department) {

        Department oldDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new ApiException("department.not.exists"));

        oldDepartment.setDepartmentName(department.getDepartmentName());
        departmentRepository.save(oldDepartment);

        return oldDepartment;
    }
}
