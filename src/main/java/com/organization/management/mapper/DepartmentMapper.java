package com.organization.management.mapper;

import com.organization.management.dto.request.DepartmentRequestDTO;
import com.organization.management.dto.response.DepartmentResponseDTO;
import com.organization.management.entity.Department;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    Department requestToEntity(DepartmentRequestDTO request);

    DepartmentResponseDTO entityToResponse(Department department);

    List<DepartmentResponseDTO> entityListToResponseList(List<Department> departments);
}
