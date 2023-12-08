package com.organization.management.mapper;

import com.organization.management.dto.request.OrganizationRequestDTO;
import com.organization.management.dto.response.OrganizationResponseDTO;
import com.organization.management.entity.Organization;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {

    Organization requestToEntity(OrganizationRequestDTO request);

    OrganizationResponseDTO entityToResponse(Organization organization);
}
