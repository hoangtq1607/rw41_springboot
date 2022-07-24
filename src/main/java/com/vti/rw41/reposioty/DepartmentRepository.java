package com.vti.rw41.reposioty;

import com.vti.rw41.dto.DepartmentDto;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DepartmentRepository {

    private ArrayList<DepartmentDto> departments = new ArrayList<>();

    @PostConstruct
    void init() {
        for (int i = 0; i < 5; i++) {
            DepartmentDto dto = new DepartmentDto();
            dto.setId(i + 1);
            dto.setName("department_" + (i + 1));
            departments.add(dto);
        }
    }

    public List<DepartmentDto> getAllDepartment() {

        return departments;

    }

    public Optional<DepartmentDto> getDepartmentById(Integer id) {

        for (DepartmentDto department : departments) {
            if (id.equals(department.getId())) {
                return Optional.of(department);
            }
        }

        return Optional.empty();

    }

    public DepartmentDto addDepartment(DepartmentDto department) {
        departments.add(department);
        return department;
    }
}
