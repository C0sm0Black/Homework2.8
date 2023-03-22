package pro.sky.homework28.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pro.sky.homework28.domain.Employee;
import pro.sky.homework28.exception.DepartmentException;
import pro.sky.homework28.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")

public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DepartmentException.class)
    public String handleException(DepartmentException e) {
        return String.format("%s %s", HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @GetMapping(path = "/max-salary")
    public Employee maxSalary(@RequestParam Integer departmentId) {
        return departmentService.employeeWithMaxSalaryByIdDepartment(departmentId);
    }

    @GetMapping(path = "/min-salary")
    public Employee minSalary(@RequestParam Integer departmentId) {
        return departmentService.employeeWithMinSalaryByIdDepartment(departmentId);
    }

    @GetMapping(path = "/all")
    public Map<String, List<Employee>> allByDepartmentId(@RequestParam(required = false) Integer departmentId) {
        return departmentService.getAll(departmentId);
    }

}
