package com.organization.management.service.impl;

import com.organization.management.constant.Constant;
import com.organization.management.entity.Employee;
import com.organization.management.enums.ResultCode;
import com.organization.management.exception.CustomException;
import com.organization.management.repository.EmployeeRepository;
import com.organization.management.service.EmployeeService;
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
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final MessageSource messageSource;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, MessageSource messageSource)
    {
        this.employeeRepository = employeeRepository;
        this.messageSource = messageSource;
    }

    @Override
    public Employee save(Employee newEmployee)
    {
        log.info("Saving employee {} {}", newEmployee.getFirstName(), newEmployee.getLastName());
        log.debug("Checking for the duplicate email {}", newEmployee.getEmail());

        // Unique email address check
        Optional<Employee> sameEmailEmp = employeeRepository.findByEmail(newEmployee.getEmail());

        // Updating other details instead of department name
        if(sameEmailEmp.isPresent() && (!Objects.equals(sameEmailEmp.get().getId(), newEmployee.getId())))
        {
            throw new CustomException(ResultCode.INVALID_OPERATION.getValue(),
                    messageSource.getMessage("api.error.email.already.exists", new String[] {}, Locale.getDefault()));
        }

        Employee savedEmployee = employeeRepository.save(newEmployee);
        log.info("{} {} - A new employee saved successfully", savedEmployee.getFirstName(), savedEmployee.getLastName());
        return savedEmployee;
    }

    @Override
    public Employee findById(long id) {

        log.info("fetching employee having id {}", id);
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        return employeeOptional.orElseThrow(() -> new CustomException(ResultCode.NOT_FOUND.getValue(),
                "Unable to find employee having id " + id));
    }

    @Override
    public void deleteById(long id) {
        log.info("Deleting employee having id {}", id);
        employeeRepository.deleteById(id);
        log.info("Employee deleted successfully");
    }

    @Override
    public Page<Employee> findAll(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        log.info("Fetching list of employees [Page:{}, Size:{}]", pageNumber, pageSize);
        return employeeRepository.findAll(pageable);
    }

    @Override
    public int countEmployeesByRoleId(long roleId) {
        return employeeRepository.countByRoleId(roleId);
    }

    @Override
    public int countEmployeesByDepartmentId(long departmentId) {
        return employeeRepository.countByDepartmentId(departmentId);
    }
}
