package com.java26.eolt.repository;

import com.java26.eolt.entity.Role;
import com.java26.eolt.myEnum.EoltRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByRole(EoltRole eoltRole);
}
