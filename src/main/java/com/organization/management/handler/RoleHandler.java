package com.organization.management.handler;

import com.organization.management.dto.BaseResponse;
import com.organization.management.dto.Metadata;
import com.organization.management.dto.request.RoleRequestDTO;
import com.organization.management.dto.response.RoleResponseDTO;
import com.organization.management.dto.response.RoleResponseDTOList;
import com.organization.management.entity.Role;
import com.organization.management.enums.ResultCode;
import com.organization.management.mapper.RoleMapper;
import com.organization.management.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Service
@Slf4j
public class RoleHandler {

    private final RoleMapper roleMapper;
    private final RoleService roleService;
    private final MessageSource messageSource;

    public RoleHandler(RoleMapper roleMapper, RoleService roleService,
                       MessageSource messageSource)
    {
        this.roleMapper = roleMapper;
        this.roleService = roleService;
        this.messageSource = messageSource;
    }

    public BaseResponse<RoleResponseDTO> save(RoleRequestDTO request)
    {
        Role savedRole = roleService.save(roleMapper.requestToEntity(request));
        RoleResponseDTO responseDTO = roleMapper.entityToResponse(savedRole);

        return new BaseResponse<>(ResultCode.SUCCESS.getValue(),
                messageSource.getMessage("api.successfully.saved.response", new String[] {}, Locale.getDefault()),
                responseDTO);
    }

    public BaseResponse<RoleResponseDTO> view(long id)
    {
        Role dbRole = roleService.findById(id);
        RoleResponseDTO responseDTO = roleMapper.entityToResponse(dbRole);

        return new BaseResponse<>(ResultCode.SUCCESS.getValue(),
                messageSource.getMessage("api.successfully.fetched.response", new String[] {}, Locale.getDefault()),
                responseDTO);
    }

    public BaseResponse<Void> deleteById(long id)
    {
        roleService.deleteById(id);
        return new BaseResponse<>(ResultCode.SUCCESS.getValue(),
                messageSource.getMessage("api.successfully.deleted.response", new String[] {}, Locale.getDefault()));
    }

    public BaseResponse<RoleResponseDTOList> findAll(int pageNumber, int pageSize)
    {
        Page<Role> rolePage = roleService.findAll(pageNumber, pageSize);

        RoleResponseDTOList response = new RoleResponseDTOList();
        Metadata metadata = Metadata.builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();

        if(rolePage.isEmpty())
        {
            response.setMetadata(metadata);
            response.setRoles(Collections.emptyList());

            return new BaseResponse<>(ResultCode.NO_DATA_FOUND.getValue(),
                    ResultCode.NO_DATA_FOUND.getName(),
                    response);
        }

        List<RoleResponseDTO> roleResponseDTOList = roleMapper.entityListToResponseList(rolePage.getContent());
        metadata.setTotalPages(rolePage.getTotalPages());
        metadata.setTotalRecords(rolePage.getTotalElements());

        response.setRoles(roleResponseDTOList);
        response.setMetadata(metadata);

        return new BaseResponse<>(ResultCode.SUCCESS.getValue(),
                messageSource.getMessage("api.successfully.fetched.response", new String[] {}, Locale.getDefault()),
                response);

    }
}
