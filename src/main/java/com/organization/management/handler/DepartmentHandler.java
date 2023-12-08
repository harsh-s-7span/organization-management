package com.organization.management.handler;

import com.organization.management.constant.Constant;
import com.organization.management.dto.BaseResponse;
import com.organization.management.dto.Metadata;
import com.organization.management.dto.request.DepartmentRequestDTO;
import com.organization.management.dto.response.DepartmentResponseDTO;
import com.organization.management.dto.response.DepartmentResponseDTOList;
import com.organization.management.entity.Department;
import com.organization.management.enums.ResultCode;
import com.organization.management.mapper.DepartmentMapper;
import com.organization.management.service.DepartmentService;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Service
public class DepartmentHandler {

    private final DepartmentMapper departmentMapper;
    private final DepartmentService departmentService;
    private final MessageSource messageSource;

    public DepartmentHandler(DepartmentMapper departmentMapper,
                             DepartmentService departmentService,
                             MessageSource messageSource)
    {
        this.departmentMapper = departmentMapper;
        this.departmentService = departmentService;
        this.messageSource = messageSource;
    }

    public BaseResponse<DepartmentResponseDTO> save(DepartmentRequestDTO request)
    {
        Department savedDepartment = departmentService.save(departmentMapper.requestToEntity(request));
        DepartmentResponseDTO response = departmentMapper.entityToResponse(savedDepartment);

        return new BaseResponse<>(ResultCode.SUCCESS.getValue(),
                messageSource.getMessage("api.successfully.saved.response", new String[] {}, Locale.getDefault()), response);
    }

    public BaseResponse<DepartmentResponseDTO> view(long id)
    {
        DepartmentResponseDTO response = departmentMapper.entityToResponse(departmentService.findById(id));

        return new BaseResponse<>(ResultCode.SUCCESS.getValue(),
                messageSource.getMessage("api.successfully.fetched.response", new String[] {}, Locale.getDefault()), response);
    }

    public BaseResponse<Void> deleteById(long id)
    {
        departmentService.deleteById(id);
        return new BaseResponse<>(ResultCode.SUCCESS.getValue(),
                messageSource.getMessage("api.successfully.deleted.response", new String[] {}, Locale.getDefault()));
    }

    public BaseResponse<DepartmentResponseDTOList> findAll(int pageNumber, int pageSize)
    {
        Page<Department> departmentPage = departmentService.findAll(pageNumber, pageSize);

        DepartmentResponseDTOList response = new DepartmentResponseDTOList();
        Metadata metadata = Metadata.builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();

        if(departmentPage.isEmpty())
        {
            response.setMetadata(metadata);
            response.setDepartments(Collections.emptyList());

            return new BaseResponse<>(ResultCode.NO_DATA_FOUND.getValue(),
                    ResultCode.NO_DATA_FOUND.getName(),
                    response);
        }

        List<DepartmentResponseDTO> departmentResponseDTOList = departmentMapper.entityListToResponseList(departmentPage.getContent());
        metadata.setTotalPages(departmentPage.getTotalPages());
        metadata.setTotalRecords(departmentPage.getTotalElements());

        response.setDepartments(departmentResponseDTOList);
        response.setMetadata(metadata);

        return new BaseResponse<>(ResultCode.SUCCESS.getValue(),
                messageSource.getMessage("api.successfully.fetched.response", new String[] {}, Locale.getDefault()),
                response);

    }
}
