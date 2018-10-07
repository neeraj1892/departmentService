package com.iris.department.controller;

import com.iris.department.handler.InvalidArgumentException;
import com.iris.department.manager.DepartmentManager;
import com.iris.department.model.Department;
import com.iris.department.validator.DepartmentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

import static com.iris.department.constants.DepartmentConstants.INVALID_ID;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentManager departmentManager;

    @Autowired
    DepartmentValidator departmentValidator;

    @Autowired
    MessageSource messageSource;

    @GetMapping("/{id}")
    public Department findById(@PathVariable long id){
        return departmentManager.findById(id);
    }

    @GetMapping
    public List<Department> getAllDepartments(){
        return departmentManager.getAllDepartments();
    }

    @PostMapping
    public Department createDepartment(@Valid @RequestBody Department department){
        return departmentManager.createDepartment(department);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartmentById(@PathVariable long id){
        departmentManager.deleteDepartment(id);
    }

    @PutMapping("/{id}")
    public void updateDepartment(@Valid @RequestBody Department department,
                                       @PathVariable Long id){

        if(departmentValidator.validateDepartmentId(id)){
            throw new InvalidArgumentException(getMessage(INVALID_ID));
        }

        departmentManager.updateDepartment(department, id);
    }

    private String getMessage(String messageKey){
        return messageSource.getMessage(messageKey, null, Locale.ENGLISH);
    }
}
