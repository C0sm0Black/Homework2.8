package pro.sky.homework28.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.homework28.domain.Employee;
import pro.sky.homework28.exception.DepartmentException;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    @Autowired
    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee employeeWithMinSalaryByIdDepartment(Integer departmentId) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment().getId() == departmentId)
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new DepartmentException("Данный депортамент не найден"));
    }

    public Employee employeeWithMaxSalaryByIdDepartment(Integer departmentId) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment().getId() == departmentId)
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new DepartmentException("Данный депортамент не найден"));
    }

    public Map<String, List<Employee>> getAll(Integer id) {
        return employeeService.getAll().stream()
                .filter(e -> id == null || e.getDepartment().getId() == id)
                .collect(Collectors.groupingBy(
                        employee -> employee.getDepartment().getName(),
                        Collectors.mapping(e -> e, Collectors.toList()))
                );

    }

}
