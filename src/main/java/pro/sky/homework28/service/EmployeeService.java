package pro.sky.homework28.service;

import org.springframework.stereotype.Service;
import pro.sky.homework28.domain.Employee;
import pro.sky.homework28.exception.EmployeeAlreadyAddedException;
import pro.sky.homework28.exception.EmployeeNotFoundException;
import pro.sky.homework28.exception.EmployeeStorageIsFullException;


import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private final int MAX_COUNT_EMPLOYEES = 5;
    private final List<Employee> employees = new ArrayList<>();

    public Employee add(String firstName, String lastName) {

        if (employees.size() > MAX_COUNT_EMPLOYEES - 1) {
            throw new EmployeeStorageIsFullException("Массив сотрудников переполнен");
        }

        Employee employee = new Employee(firstName, lastName);

        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("В массиве уже есть такой сотрудник");
        }

        employees.add(employee);
        return employee;

    }

    public Employee find(String firstName, String lastName) {

        Employee employee = null;

        for (Employee employee1 : employees) {

            if (employee1 != null && firstName.equals(employee1.getFirstName())
                    && lastName.equals(employee1.getLastName())) {
                employee = employee1;
            }

        }

        if (employee == null) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }

        return employee;

    }

    public Employee remove(String firstName, String lastName) {

        Employee employee = find(firstName, lastName);
        int x = -1;


        for (int i = 0; i < employees.size(); i++) {

            if (employees.get(i).equals(employee)) {

                x = i;
                employee = employees.get(i);

            }

        }

        employees.remove(x);
        return employee;

    }

    public List<Employee> getAll() {
        return employees;
    }

}
