package com.mamun.ecommerce.service;

import com.mamun.ecommerce.dao.RoleRepository;
import com.mamun.ecommerce.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    public Role createRole(Role role){

       return this.roleRepository.save(role);

    }
}
