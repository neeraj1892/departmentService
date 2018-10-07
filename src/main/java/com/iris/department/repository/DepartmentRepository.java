package com.iris.department.repository;

import com.iris.department.model.Department;

import java.util.List;

public interface DepartmentRepository{

    Department createDepartment(Department department);
    Department findById(long id);
    void deleteDepartment(Department department);
    List<Department> getAllDepartments();
    void updateDepartment(Department department);
}
