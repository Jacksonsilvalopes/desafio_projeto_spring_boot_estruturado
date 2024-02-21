package com.jacksspring.dscommerce.service;

import com.jacksspring.dscommerce.entity.User;
import com.jacksspring.dscommerce.service.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    public void validateSelfOrAdmin(Long userId){

        User me = userService.authenticated();
        if(!me.hasRole("ROLE_ADMIN") && !me.getId().equals(userId)){
            throw new ForbiddenException(" Usuario não autorizado");
        }



    }
}
