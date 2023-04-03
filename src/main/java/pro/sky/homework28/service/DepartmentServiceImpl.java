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
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    @Autowired
    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee employeeWithMinSalaryByIdDepartment(Integer departmentId) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment().getId() == departmentId)
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new DepartmentException("Данный депортамент не найден"));
    }

    public String getMinSalaryByDepartment(Integer id) {

        Employee employee = employeeService.getAll().stream()
                .filter(e -> e.getDepartment().getId() == id)
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new DepartmentException("Данный депортамент не найден"));

        Double min = employee.getSalary();

        return min.toString();

    }

    public Employee employeeWithMaxSalaryByIdDepartment(Integer departmentId) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment().getId() == departmentId)
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new DepartmentException("Данный депортамент не найден"));
    }

    public String getMaxSalaryByDepartment(Integer id) {

        Employee employee = employeeService.getAll().stream()
                .filter(e -> e.getDepartment().getId() == id)
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new DepartmentException("Данный депортамент не найден"));

        Double max = employee.getSalary();

        return max.toString();
    }

    public Map<String, List<Employee>> getAll(Integer id) {
        return employeeService.getAll().stream()
                .filter(e -> id == null || e.getDepartment().getId() == id)
                .collect(Collectors.groupingBy(
                        employee -> employee.getDepartment().getName(),
                        Collectors.mapping(e -> e, Collectors.toList()))
                );

    }

    public String getSumSalaryByDepartment(Integer id) {

        final Double[] sum = {0.0};

        employeeService.getAll().stream()
                .filter(e -> e.getDepartment().getId() == id)
                .forEach(e -> {
                    sum[0] += e.getSalary();
                });

        return sum[0].toString();

    }

}
