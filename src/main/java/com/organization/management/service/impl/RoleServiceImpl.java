package com.organization.management.service.impl;

import com.organization.management.entity.Role;
import com.organization.management.enums.ResultCode;
import com.organization.management.exception.CustomException;
import com.organization.management.repository.RoleRepository;
import com.organization.management.service.EmployeeService;
import com.organization.management.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final EmployeeService employeeService;
    private final MessageSource messageSource;

    public RoleServiceImpl(RoleRepository roleRepository,
                           EmployeeService employeeService, MessageSource messageSource)
    {
        this.roleRepository = roleRepository;
        this.employeeService = employeeService;
        this.messageSource = messageSource;
    }

    @Override
    public Role save(Role newRole) {
        log.info("Saving role : {}", newRole.getName());

        log.debug("Checking for the role having same name {}", newRole.getName());

        // Check for existed role
        Optional<Role> existedRole = roleRepository.findByName(newRole.getName());
        // Update role scenario - if other details are changed instead of role name
        if(existedRole.isPresent() && (!Objects.equals(existedRole.get().getId(), newRole.getId())))
        {
            throw new CustomException(ResultCode.INVALID_OPERATION.getValue(),
                    messageSource.getMessage("api.error.already.exists", new String[] {newRole.getName()}, Locale.getDefault()));
        }


        Role savedRole = roleRepository.save(newRole);
        log.info("Role {} saved successfully", savedRole.getName());
        return savedRole;
    }

    @Override
    public Role findById(long id) {
        log.info("Fetching role having id {}", id);
        Optional<Role> roleOptional = roleRepository.findById(id);
        return roleOptional.orElseThrow(() -> new CustomException(ResultCode.NOT_FOUND.getValue(),
                "Role not found for given id " + id));
    }

    @Override
    public void deleteById(long id) {

        // count total employees under specific department
        int employeeCount = employeeService.countEmployeesByRoleId(id);
        log.debug("Role having id {} is assigned to {} employees", id, employeeCount);

        // check if department has the employees
        if(employeeCount > 0)
            throw new CustomException(ResultCode.INVALID_OPERATION.getValue(),
                    messageSource.getMessage("api.error.assigned.role.delete", new String[] {}, Locale.getDefault()));

        roleRepository.deleteById(id);
    }

    @Override
    public Page<Role> findAll(int pageNumber, int pageSize) {
        log.info("Fetching list of of roles [Page:{}, Size:{}]", pageNumber, pageSize);
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return roleRepository.findAll(pageable);
    }


}
