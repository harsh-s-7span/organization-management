package com.organization.management.dto.response;

import com.organization.management.dto.Metadata;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoleResponseDTOList {

    List<RoleResponseDTO> roles;

    Metadata metadata;

}
