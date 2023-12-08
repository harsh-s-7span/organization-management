package com.organization.management.service;

import com.organization.management.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {

    Role save(Role role);

    Role findById(long id);

    void deleteById(long id);

    Page<Role> findAll(int pageNumber, int pageSize);
}
