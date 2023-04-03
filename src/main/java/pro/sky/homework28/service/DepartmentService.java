package pro.sky.homework28.service;

import pro.sky.homework28.domain.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    Employee employeeWithMinSalaryByIdDepartment(Integer departmentId);
    Employee employeeWithMaxSalaryByIdDepartment(Integer departmentId);
    Map<String, List<Employee>> getAll(Integer id);
    String getSumSalaryByDepartment(Integer id);
    String getMaxSalaryByDepartment(Integer id);
    String getMinSalaryByDepartment(Integer id);

}
