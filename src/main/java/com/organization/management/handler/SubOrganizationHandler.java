package com.organization.management.handler;

import com.organization.management.dto.BaseResponse;
import com.organization.management.dto.request.SubOrganizationRequestDTO;
import com.organization.management.dto.response.SubOrganizationResponseDTO;
import com.organization.management.entity.SubOrganization;
import com.organization.management.enums.ResultCode;
import com.organization.management.mapper.SubOrganizationMapper;
import com.organization.management.service.SubOrganizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@Slf4j
public class SubOrganizationHandler {

    private final SubOrganizationMapper subOrganizationMapper;
    private final SubOrganizationService subOrganizationService;
    private final MessageSource messageSource;

    public SubOrganizationHandler(SubOrganizationMapper subOrganizationMapper,
                                  SubOrganizationService subOrganizationService,
                                  MessageSource messageSource)
    {
        this.subOrganizationMapper = subOrganizationMapper;
        this.subOrganizationService = subOrganizationService;
        this.messageSource = messageSource;
    }

    public BaseResponse<SubOrganizationResponseDTO> save(SubOrganizationRequestDTO request)
    {
        SubOrganization savedSubOrganization = subOrganizationService.save(subOrganizationMapper.requestToEntity(request));

        SubOrganizationResponseDTO responseDTO = subOrganizationMapper.entityToResponse(savedSubOrganization);

        return new BaseResponse<>(ResultCode.SUCCESS.getValue(),
                messageSource.getMessage("api.successfully.saved.response", new String[] {}, Locale.getDefault()),
                responseDTO);
    }
}
