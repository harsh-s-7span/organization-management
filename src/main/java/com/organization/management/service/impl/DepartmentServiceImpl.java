package com.organization.management.service.impl;

import com.organization.management.constant.Constant;
import com.organization.management.entity.Department;
import com.organization.management.enums.ResultCode;
import com.organization.management.exception.CustomException;
import com.organization.management.repository.DepartmentRepository;
import com.organization.management.service.DepartmentService;
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
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeService employeeService;
    private final MessageSource messageSource;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository,
                                 EmployeeService employeeService, MessageSource messageSource)
    {
        this.departmentRepository = departmentRepository;
        this.employeeService = employeeService;
        this.messageSource = messageSource;
    }

    @Override
    public Department save(Department newDepartment)
    {
        log.info("Saving department {}", newDepartment.getName());
        log.debug("Checking for the department having same name {}", newDepartment.getName());

        // Check for existed department
        Optional<Department> existedDepartment = departmentRepository.findByName(newDepartment.getName());

        // Updating other details instead of department name
        if(existedDepartment.isPresent() && (!Objects.equals(existedDepartment.get().getId(), newDepartment.getId())))
        {
            throw new CustomException(ResultCode.INVALID_OPERATION.getValue(),
                    messageSource.getMessage("api.error.already.exists", new String[] {newDepartment.getName()}, Locale.getDefault()));
        }


        Department savedDepartment = departmentRepository.save(newDepartment);
        log.info("{} Department saved successfully", savedDepartment.getName());
        return savedDepartment;
    }

    @Override
    public Department findById(long id)
    {
        log.info("Fetching department having id {}", id);
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        return departmentOptional.orElseThrow(() -> new CustomException(ResultCode.NOT_FOUND.getValue(),
                "Unable to find department having id " + id));
    }

    @Override
    public void deleteById(long id)
    {
        // count total employees under specific department
        int employeeCount = employeeService.countEmployeesByDepartmentId(id);
        log.debug("department having id {} has {} employees", id, employeeCount);

        // check if department has the employees
        if(employeeCount > 0)
            throw new CustomException(ResultCode.INVALID_OPERATION.getValue(),
                    messageSource.getMessage("api.error.department.has.employees", new String[] {}, Locale.getDefault()));

        departmentRepository.deleteById(id);
        log.info("Department having id {} deleted successfully", id);
    }

    @Override
    public Page<Department> findAll(int pageNumber, int pageSize)
    {
        log.info("Fetching list of of departments [Page:{}, Size:{}]", pageNumber, pageSize);
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return departmentRepository.findAll(pageable);
    }
}
