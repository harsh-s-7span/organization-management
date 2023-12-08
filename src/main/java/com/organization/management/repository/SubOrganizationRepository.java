package com.organization.management.repository;

import com.organization.management.entity.SubOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubOrganizationRepository extends JpaRepository<SubOrganization, Long> {

    Optional<SubOrganization> findByName(String subOrgName);
}
