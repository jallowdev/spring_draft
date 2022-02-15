package com.tdsi.spring_draft.security.repository;

import com.tdsi.spring_draft.security.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName(String roleName);
}
