package com.organization.management.mapper;

import com.organization.management.dto.request.RoleRequestDTO;
import com.organization.management.dto.response.RoleResponseDTO;
import com.organization.management.entity.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role requestToEntity(RoleRequestDTO request);

    RoleResponseDTO entityToResponse(Role role);

    List<RoleResponseDTO> entityListToResponseList(List<Role> roleList);
}
