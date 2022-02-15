package com.tdsi.spring_draft.security.repository;

import com.tdsi.spring_draft.security.models.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege,Long> {
}
