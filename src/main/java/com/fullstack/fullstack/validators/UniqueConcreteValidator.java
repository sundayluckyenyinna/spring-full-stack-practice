package com.fullstack.fullstack.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.fullstack.fullstack.dao.database.JDBCRepository;
import com.fullstack.fullstack.dao.database.JdbcTemplateManager;
import com.fullstack.fullstack.dto.AuthenticationDTO;
import com.fullstack.fullstack.dto.ConcreteDTO;

/**
 * A component class to ensure that the email from the user is not already in use.
 */
@Component
public class UniqueConcreteValidator implements Validator {

    @Autowired
    private JDBCRepository jdbcRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return ConcreteDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // cast the object target to a ConcreteDTO
        ConcreteDTO dto = (ConcreteDTO) target;
        String userId = dto.getUserId();

        AuthenticationDTO auth = this.jdbcRepository.getAuthenticatedUser(userId);
        System.out.println( auth );
        if( auth != null ){
            errors.rejectValue("username", "username.exists", "Username already used");
        }
        
    }
    

}
