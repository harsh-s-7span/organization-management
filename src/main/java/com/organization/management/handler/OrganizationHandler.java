package com.organization.management.handler;

import com.organization.management.constant.Constant;
import com.organization.management.dto.BaseResponse;
import com.organization.management.dto.request.OrganizationRequestDTO;
import com.organization.management.dto.response.OrganizationResponseDTO;
import com.organization.management.entity.Organization;
import com.organization.management.enums.ResultCode;
import com.organization.management.mapper.OrganizationMapper;
import com.organization.management.service.OrganizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@Slf4j
public class OrganizationHandler {

    private final OrganizationService organizationService;
    private final OrganizationMapper organizationMapper;
    private final MessageSource messageSource;

    public OrganizationHandler(OrganizationService organizationService,
                               OrganizationMapper organizationMapper,
                               MessageSource messageSource)
    {
        this.organizationService = organizationService;
        this.organizationMapper = organizationMapper;
        this.messageSource = messageSource;
    }

    public BaseResponse<OrganizationResponseDTO> save(OrganizationRequestDTO request)
    {
        Organization savedOrganization = organizationService.save(organizationMapper.requestToEntity(request));

        OrganizationResponseDTO responseDTO = organizationMapper.entityToResponse(savedOrganization);

        return new BaseResponse<>(
                ResultCode.SUCCESS.getValue(),
                messageSource.getMessage("api.successfully.saved.response", new String[] {}, Locale.getDefault()),
                responseDTO);

    }
}
