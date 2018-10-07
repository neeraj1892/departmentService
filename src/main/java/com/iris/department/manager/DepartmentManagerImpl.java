package com.iris.department.manager;

import com.iris.department.constants.DepartmentConstants;
import com.iris.department.handler.DepartmentNotFoundException;
import com.iris.department.model.Department;
import com.iris.department.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class DepartmentManagerImpl implements DepartmentManager {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    MessageSource messageSource;

    public Department findById(long id){

        Department department = departmentRepository.findById(id);

        if(department == null){
            throw new DepartmentNotFoundException(getMessage(DepartmentConstants.DEPARTMENT_NOT_FOUND));
        }

        return department;
    }

    public Department createDepartment(Department department){
        return departmentRepository.createDepartment(department);
    }

    public void deleteDepartment(long id){
        Department department = departmentRepository.findById(id);

        if(department == null){
            throw new DepartmentNotFoundException(getMessage(DepartmentConstants.DEPARTMENT_NOT_FOUND));
        }

        departmentRepository.deleteDepartment(department);
    }


    public List<Department> getAllDepartments() {
        return departmentRepository.getAllDepartments();
    }

    public void updateDepartment(Department department, long id){

        Department dept = findById(id);

        if(dept == null){
            throw new DepartmentNotFoundException(getMessage(DepartmentConstants.DEPARTMENT_NOT_FOUND));
        }

        department.setId(id);
        department.setCreatedOn(dept.getCreatedOn());

        departmentRepository.updateDepartment(department);
    }

    private String getMessage(String messageKey){
        return messageSource.getMessage(messageKey, null, Locale.ENGLISH);
    }
}
