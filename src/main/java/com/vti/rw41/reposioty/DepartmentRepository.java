package com.vti.rw41.reposioty;

import com.vti.rw41.dto.DepartmentDto;
import com.vti.rw41.entity.Department;
import com.vti.rw41.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DepartmentRepository {

    public List<Department> getAllDepartment() {

        Session session = HibernateUtils.getSession();
        Query<Department> query = session.createQuery("FROM Department order by id desc", Department.class);
        return query.getResultList();

    }

    public Optional<Department> getDepartmentById(Integer id) {
        Session session = HibernateUtils.getSession();
        Query<Department> query = session.createQuery("FROM Department Where id = :deptId", Department.class);
        query.setParameter("deptId", id);
        return query.getResultStream().findFirst();

    }

    public Department addDepartment(Department dto) {

        Session session = HibernateUtils.getSession();
        session.beginTransaction();
        Department department = new Department();
        department.setDepartmentName(dto.getDepartmentName());
        session.save(department);
        session.getTransaction().commit();
        return department;
    }

    public Optional<Department> deleteDepartmentById(Integer id) {
        Optional<Department> departmentById = getDepartmentById(id);
        Session session = HibernateUtils.getSession();
        session.beginTransaction();
        session.createQuery("DELETE Department WHERE id = :id")
                .setParameter("id", id)
                .executeUpdate();
        session.getTransaction().commit();
        return departmentById;
    }

    public Optional<Department> updateDepartmentById(Integer id,
                                                     Department newDepartment) {
        Optional<Department> departmentById = getDepartmentById(id);
        Session session = HibernateUtils.getSession();
        if (departmentById.isPresent()) {
            Department oldDepartment = departmentById.get();
            oldDepartment.setDepartmentName(newDepartment.getDepartmentName());
            session.beginTransaction();
            session.update(oldDepartment);
            session.getTransaction().commit();
        }

        return departmentById;
    }
}
