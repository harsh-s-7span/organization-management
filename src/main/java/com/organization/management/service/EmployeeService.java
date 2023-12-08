package com.organization.management.service;

import com.organization.management.entity.Employee;
import org.springframework.data.domain.Page;

public interface EmployeeService {

    Employee save(Employee employee);

    Employee findById(long id);

    void deleteById(long id);

    Page<Employee> findAll(int pageNumber, int pageSize);

    int countEmployeesByRoleId(long roleId);

    int countEmployeesByDepartmentId(long departmentId);
}
