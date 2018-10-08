package com.iris.department.controller;

import com.iris.department.handler.InvalidArgumentException;
import com.iris.department.manager.DepartmentManager;
import com.iris.department.model.Department;
import com.iris.department.validator.DepartmentValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

import static com.iris.department.constants.DepartmentConstants.INVALID_ID;

@RestController
public class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    DepartmentManager departmentManager;

    @Autowired
    DepartmentValidator departmentValidator;

    @Autowired
    MessageSource messageSource;

    /**
     * Find Department by Id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Department findById(@PathVariable Long id){

        checkIfValidId(id);

        Department department = departmentManager.findById(id);
        LOGGER.info("Department find : {}", department);
        return department;
    }

    /**
     * Fetch the list of all available departments.
     * @return
     */
    @GetMapping
    public List<Department> getAllDepartments(){
        LOGGER.info("Finding All Departments");
        return departmentManager.getAllDepartments();
    }

    /**
     * Create and save new Department object into DB.
     * @param department
     * @return
     */
    @PostMapping
    public Department createDepartment(@Valid @RequestBody Department department){
        LOGGER.info("Create Department : {}", department.getDeptName());
        return departmentManager.createDepartment(department);
    }

    /**
     * Deletes a specific department based on it's Id.
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deleteDepartmentById(@PathVariable long id){

        checkIfValidId(id);

        LOGGER.info("Delete department with Id : {}", id);
        departmentManager.deleteDepartment(id);
    }

    /**
     * Updates a specific department based on it's Id.
     * @param department
     * @param id
     */
    @PutMapping("/{id}")
    public void updateDepartment(@Valid @RequestBody Department department,
                                       @PathVariable Long id){

        checkIfValidId(id);

        LOGGER.info("Update department with Id : {}", id);
        departmentManager.updateDepartment(department, id);
    }

    /**
     * Checks if a received Id is valid or not
     * If received Id is invalid then throw error.
     * @param id
     */
    private void checkIfValidId(Long id) {
        if(departmentValidator.validateDepartmentId(id)){
            LOGGER.error("Invalid Id {} received.", id);
            throw new InvalidArgumentException(getMessage(INVALID_ID));
        }
    }

    private String getMessage(String messageKey){
        return messageSource.getMessage(messageKey, null, Locale.ENGLISH);
    }

}
