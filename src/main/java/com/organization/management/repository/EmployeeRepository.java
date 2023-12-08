package com.organization.management.repository;

import com.organization.management.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    int countByRoleId(long id);

    int countByDepartmentId(long id);

    Optional<Employee> findByEmail(String email);
}