package com.mamun.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class User {

    @Id
    private String userName;
    private String userFirstName;
    private String userLastName;
    @Column(unique = true)
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name="USER_ROLE",

               joinColumns ={
                  @JoinColumn(name="USER_ID")

    },
            inverseJoinColumns = {
                    @JoinColumn(name="ROLE_ID")
            }

    )


    private Set<Role> roleSet;

}
