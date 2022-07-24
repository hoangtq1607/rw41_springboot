package com.vti.rw41.servcie;

import com.vti.rw41.dto.DepartmentDto;
import com.vti.rw41.reposioty.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    public List<DepartmentDto> getAllDepartment() {

        return departmentRepository.getAllDepartment();
    }

    public Optional<DepartmentDto> getDepartmentById(Integer id) {

        return departmentRepository.getDepartmentById(id);
    }

    public DepartmentDto addDepartment(DepartmentDto department) {
        return departmentRepository.addDepartment(department);
    }
}
