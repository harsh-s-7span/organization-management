package com.organization.management.controller;

import com.organization.management.dto.BaseResponse;
import com.organization.management.dto.request.DepartmentRequestDTO;
import com.organization.management.dto.response.DepartmentResponseDTO;
import com.organization.management.dto.response.DepartmentResponseDTOList;
import com.organization.management.handler.DepartmentHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentHandler departmentHandler;

    public DepartmentController(DepartmentHandler departmentHandler)
    {
        this.departmentHandler = departmentHandler;
    }

    @PostMapping
    public BaseResponse<DepartmentResponseDTO> save(@Valid @RequestBody DepartmentRequestDTO request)
    {
        return departmentHandler.save(request);
    }

    @GetMapping("/{id}")
    public BaseResponse<DepartmentResponseDTO> view(@PathVariable long id)
    {
        return departmentHandler.view(id);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteById(@PathVariable long id)
    {
        return departmentHandler.deleteById(id);
    }

    @GetMapping
    public BaseResponse<DepartmentResponseDTOList> findAll(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                                           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize)
    {
        return departmentHandler.findAll(pageNumber, pageSize);
    }

}
