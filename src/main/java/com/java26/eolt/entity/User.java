package com.java26.eolt.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String password;

   /* @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "role_Id",referencedColumnName = "user_id"))*/


    @OneToMany(mappedBy = "myUserRole", fetch = FetchType.EAGER)
    private Set<Role> roles;

    public User() {

    }

    public User(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.roles = user.getRoles();
    }

}
