package com.mamun.ecommerce.service;

import com.mamun.ecommerce.dao.RoleRepository;
import com.mamun.ecommerce.dao.UserRepository;
import com.mamun.ecommerce.entity.Role;
import com.mamun.ecommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public User createUser(User user){


        return  this.userRepository.save(user);

    }

    public void initUserDetails(){

        Role adminRole=new Role();
        adminRole.setRoleName("admin");
        adminRole.setRoleDescription("Admin Description");
        this.roleRepository.save(adminRole);

        Role userRole=new Role();
        userRole.setRoleName("naim");
        userRole.setRoleDescription("User desc");
        this.roleRepository.save(userRole);

        Set<Role> list=new HashSet<>();


        User adminUser=new User();
        adminUser.setUserName("mamun");
        adminUser.setUserFirstName("abdullah");
        adminUser.setUserLastName("mamun");
        adminUser.setEmail("mamun@gmail.com");
        adminUser.setPassword("12345");
        list.add(adminRole);
        adminUser.setRoleSet(list);

        this.userRepository.save(adminUser);



        User normalUser=new User();
        normalUser.setUserName("naim");
        normalUser.setUserFirstName("naim");
        normalUser.setUserLastName("naimur");
        normalUser.setEmail("naim@gmail.com");
        normalUser.setPassword("123");
        list.add(userRole);
        normalUser.setRoleSet(list);



        this.userRepository.save(normalUser);


    }
}
