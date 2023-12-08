package com.organization.management.controller;

import com.organization.management.dto.BaseResponse;
import com.organization.management.dto.request.RoleRequestDTO;
import com.organization.management.dto.response.RoleResponseDTO;
import com.organization.management.dto.response.RoleResponseDTOList;
import com.organization.management.handler.RoleHandler;
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
@RequestMapping("/role")
public class RoleController {

    private final RoleHandler roleHandler;

    public RoleController(RoleHandler roleHandler)
    {
        this.roleHandler = roleHandler;
    }

    @PostMapping
    public BaseResponse<RoleResponseDTO> save(@Valid @RequestBody RoleRequestDTO request)
    {
        return roleHandler.save(request);
    }

    @GetMapping("/{id}")
    public BaseResponse<RoleResponseDTO> view(@PathVariable long id)
    {
        return roleHandler.view(id);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteById(@PathVariable Long id)
    {
        return roleHandler.deleteById(id);
    }

    @GetMapping
    public BaseResponse<RoleResponseDTOList> findAll(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                                     @RequestParam(value = "pageSize", defaultValue = "10") int pageSize)
    {
        return roleHandler.findAll(pageNumber, pageSize);
    }

}
