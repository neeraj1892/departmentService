package com.iris.department.manager;

import com.iris.department.model.Department;

import java.util.List;

public interface DepartmentManager {

    Department findById(long id);

    Department createDepartment(Department department);

    void deleteDepartment(long id);

    List<Department> getAllDepartments();

    void updateDepartment(Department department, long id);
}
