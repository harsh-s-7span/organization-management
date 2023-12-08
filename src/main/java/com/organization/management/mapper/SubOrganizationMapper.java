package com.organization.management.mapper;

import com.organization.management.dto.request.SubOrganizationRequestDTO;
import com.organization.management.dto.response.SubOrganizationResponseDTO;
import com.organization.management.entity.SubOrganization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubOrganizationMapper {

    @Mapping(source = "organizationId", target = "organization.id")
    SubOrganization requestToEntity(SubOrganizationRequestDTO request);

    SubOrganizationResponseDTO entityToResponse(SubOrganization subOrganization);
}
