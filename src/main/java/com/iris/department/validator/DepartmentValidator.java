package com.iris.department.validator;

import org.springframework.stereotype.Component;

@Component
public class DepartmentValidator {

    public boolean validateDepartmentId(Long id){
        return id == null  || id < 0;
    }

}
