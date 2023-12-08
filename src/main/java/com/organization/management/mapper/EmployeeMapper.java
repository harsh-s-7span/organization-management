package com.organization.management.mapper;

import com.organization.management.dto.request.EmployeeRequestDTO;
import com.organization.management.dto.response.EmployeeResponseDTO;
import com.organization.management.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(source = "departmentId", target = "department.id")
    @Mapping(source = "roleId", target = "role.id")
    @Mapping(source = "subOrganizationId", target = "subOrganization.id")
    Employee requestToEntity(EmployeeRequestDTO request);

    @Mapping(source = "department.name", target = "departmentName")
    @Mapping(source = "role.name", target = "roleName")
    @Mapping(source = "subOrganization.name", target = "subOrganizationName")
    EmployeeResponseDTO entityToResponse(Employee employee);

    List<EmployeeResponseDTO> entityListToResponseList(List<Employee> employeeList);
}
