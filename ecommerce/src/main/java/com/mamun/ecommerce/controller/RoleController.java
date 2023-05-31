package com.mamun.ecommerce.controller;

import com.mamun.ecommerce.entity.Role;
import com.mamun.ecommerce.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/createRole")
    public Role createRole(@RequestBody Role role){

      Role newRole=  this.roleService.createRole(role);
      return  newRole;

    }

}
