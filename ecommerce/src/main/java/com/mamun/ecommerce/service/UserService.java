package com.mamun.ecommerce.service;

import com.mamun.ecommerce.dao.RoleRepository;
import com.mamun.ecommerce.dao.UserRepository;
import com.mamun.ecommerce.entity.Role;
import com.mamun.ecommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User createUser(User user){

      Role role= this.roleRepository.findById("ROLE_NORMAL").get();

      Set<Role> roles= new HashSet<>();
      roles.add(role);
      user.setRoleSet(roles);

      user.setPassword(passwordEncoder.encode(user.getPassword()));

      return this.userRepository.save(user);


    }

    public List<User> getUsers(){
     List<User> users= this.userRepository.findAll();

        return users;
    }

    public void initUserDetails(){

        Role adminRole=new Role();
        adminRole.setRoleName("ROLE_ADMIN");
        adminRole.setRoleDescription("Admin Description");
        this.roleRepository.save(adminRole);

        Role userRole=new Role();
        userRole.setRoleName("ROLE_NORMAL");
        userRole.setRoleDescription("User desc");
        this.roleRepository.save(userRole);



        Set<Role> list1=new HashSet<>();
        User adminUser=new User();
        adminUser.setUserName("mamun");
        adminUser.setUserFirstName("abdullah");
        adminUser.setUserLastName("mamun");
        adminUser.setEmail("mamun@gmail.com");
        adminUser.setPassword(passwordEncoder.encode("123456"));
        list1.add(adminRole);
        adminUser.setRoleSet(list1);

        this.userRepository.save(adminUser);

//        Set<Role> list=new HashSet<>();
//
//        User normalUser=new User();
//        normalUser.setUserName("naim");
//        normalUser.setUserFirstName("naimur234");
//        normalUser.setUserLastName("naimur");
//        normalUser.setEmail("naim@gmail.com");
//        normalUser.setPassword(passwordEncoder.encode("1234"));
//        list.add(userRole);
//        normalUser.setRoleSet(list);
//
//
//
//        this.userRepository.save(normalUser);


    }
}
