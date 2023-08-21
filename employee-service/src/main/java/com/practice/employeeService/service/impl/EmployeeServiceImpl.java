package com.practice.employeeService.service.impl;

import com.practice.employeeService.entity.Employee;
import com.practice.employeeService.exceptions.ResourceNotFoundException;
import com.practice.employeeService.model.APIResponseDto;
import com.practice.employeeService.model.DepartmentDto;
import com.practice.employeeService.model.EmployeeDto;
import com.practice.employeeService.repository.EmployeeRepository;
import com.practice.employeeService.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

//    private RestTemplate restTemplate;

//    private WebClient webClient;

    private APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode()
        );

        employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );

        return savedEmployeeDto;
    }

    @Override
    public APIResponseDto getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee Id doesn't exist"));

        // REST TEMPLATE

//        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(), DepartmentDto.class);
//
//        DepartmentDto departmentDto = responseEntity.getBody();

        // WEB CLIENT

//        DepartmentDto departmentDto = webClient.get()
//                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();

        // OPEN FEIGN

        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());


        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);

        return apiResponseDto;
    }
}
