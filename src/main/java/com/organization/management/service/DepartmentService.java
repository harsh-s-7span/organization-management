package com.organization.management.service;

import com.organization.management.entity.Department;
import org.springframework.data.domain.Page;

public interface DepartmentService {

    Department save(Department department);

    Department findById(long id);

    void deleteById(long id);

    Page<Department> findAll(int pageNumber, int pageSize);
}
