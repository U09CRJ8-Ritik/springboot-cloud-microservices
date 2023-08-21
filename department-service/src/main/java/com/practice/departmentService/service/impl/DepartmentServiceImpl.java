package com.practice.departmentService.service.impl;

import com.practice.departmentService.entity.Department;
import com.practice.departmentService.exceptions.ResourceNotFoundException;
import com.practice.departmentService.model.DepartmentDto;
import com.practice.departmentService.repository.DepartmentRepository;
import com.practice.departmentService.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

        // Convert department dto to department JPA Entity
        Department department = new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode()
        );
        Optional<Department> optionalDepartment = departmentRepository.findByDepartmentCode(department.getDepartmentCode());


        if (optionalDepartment.isPresent()) {
            throw new ResourceNotFoundException("Department Code Already Exist");
        }

        departmentRepository.save(department);

        // convert department entity to Department DTO
        DepartmentDto savedDepartmentDto = new DepartmentDto(
                department.getId(),
                departmentDto.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()
        );

        return savedDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {

        Department department = departmentRepository.findByDepartmentCode(departmentCode).orElseThrow(()->new ResourceNotFoundException("Department Code doesn't exist"));

        DepartmentDto departmentDto = new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()
        );

        return departmentDto;

    }
}
