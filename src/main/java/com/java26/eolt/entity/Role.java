package com.java26.eolt.entity;

import com.java26.eolt.myEnum.EoltRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_Id")
    private Long roleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private EoltRole role;

    @ManyToOne
    @JoinColumn(name = "myUserRole_id")
    User myUserRole;
}
