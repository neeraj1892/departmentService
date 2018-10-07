package com.iris.department.repository;

import com.iris.department.model.Department;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

import static com.iris.department.constants.DepartmentConstants.GET_ALL_DEPARTMENTS;

@Repository
@Transactional
public class DepartmentRepositoryImpl implements DepartmentRepository{

    @PersistenceContext
    EntityManager entityManager;

    public Department createDepartment(Department department){
         entityManager.persist(department);
         return department;
    }

    public Department findById(long id) {
        return entityManager.find(Department.class, id);
    }

    public void deleteDepartment(Department department){
        entityManager.remove(department);
    }


    public List<Department> getAllDepartments() {
        TypedQuery<Department> query = entityManager
                .createNamedQuery(GET_ALL_DEPARTMENTS, Department.class);

        return query.getResultList();
    }

    public void updateDepartment(Department department){
        entityManager.merge(department);
    }
}
