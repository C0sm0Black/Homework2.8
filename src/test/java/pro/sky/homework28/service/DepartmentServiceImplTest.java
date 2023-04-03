package pro.sky.homework28.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pro.sky.homework28.domain.Employee;
import pro.sky.homework28.exception.DepartmentException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static pro.sky.homework28.domain.Department.DEPARTMENT_BY_ID;

@ContextConfiguration(classes = {DepartmentServiceImpl.class})
@ExtendWith(SpringExtension.class)
class DepartmentServiceImplTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void employeeWithMinSalaryByIdDepartment_success() {

        //Подготовка входных данных

        Integer id = 1;
        Double maxSalary = 63_000d;
        Double minSalary = 48_150d;

        Employee emp1 = new Employee("Виктория", "Гончарова", maxSalary, DEPARTMENT_BY_ID.get(id));
        Employee emp2 = new Employee("Олег", "Коновалов", minSalary, DEPARTMENT_BY_ID.get(id));

        List<Employee> employees = List.of(emp1, emp2);

        //Подготовка ожидаемого результата

        when(employeeService.getAll()).thenReturn(employees);

        //Начало теста

        Employee actual = departmentService.employeeWithMinSalaryByIdDepartment(id);
        assertEquals(emp2, actual);
        assertTrue(minSalary < maxSalary);

    }

    @Test
    void employeeWithMinSalaryByIdDepartment_withDepartmentException() {

        //Подготовка входных данных

        Integer id = 1;

        //Подготовка ожидаемого результата

        when(employeeService.getAll()).thenReturn(Collections.emptyList());
        String exceptedMessage = "Данный депортамент не найден";

        //Начало теста

        Exception exception = assertThrows(DepartmentException.class,
                () -> departmentService.employeeWithMinSalaryByIdDepartment(id));

        assertEquals(exceptedMessage , exception.getMessage());

    }

    @Test
    void getMinSalaryByDepartment_success() {

        //Подготовка входных данных

        Integer id = 1;
        Double maxSalary = 77_000d;
        Double minSalary = 15_150d;

        Employee emp1 = new Employee("Виктория", "Гончарова", maxSalary, DEPARTMENT_BY_ID.get(id));
        Employee emp2 = new Employee("Олег", "Коновалов", minSalary, DEPARTMENT_BY_ID.get(id));

        List<Employee> employees = List.of(emp1, emp2);

        String expected = minSalary.toString();

        //Подготовка ожидаемого результата

        when(employeeService.getAll()).thenReturn(employees);

        //Начало теста

        String actual = departmentService.getMinSalaryByDepartment(id);
        assertEquals(expected, actual);
        assertTrue(minSalary < maxSalary);

    }

    @Test
    void getMinSalaryByDepartment_withDepartmentException() {

        //Подготовка входных данных

        Integer id = 1;

        //Подготовка ожидаемого результата

        when(employeeService.getAll()).thenReturn(Collections.emptyList());
        String expectedMessage = "Данный депортамент не найден";

        //Начало теста

        Exception exception = assertThrows(DepartmentException.class,
                () -> departmentService.getMinSalaryByDepartment(id));

        assertEquals(expectedMessage, exception.getMessage());

    }

    @Test
    void employeeWithMaxSalaryByIdDepartment_success() {

        //Подготовка входных данных

        Integer id = 2;
        Double maxSalary = 78_045d;
        Double minSalary = 55_000d;

        Employee emp1 = new Employee("Анна", "Комаева", minSalary, DEPARTMENT_BY_ID.get(id));
        Employee emp2 = new Employee("Андрей", "Рак", maxSalary, DEPARTMENT_BY_ID.get(id));

        List<Employee> employees = List.of(emp1, emp2);

        //Подготовка ожидаемого результата

        when(employeeService.getAll()).thenReturn(employees);

        //Начало теста

        Employee actual = departmentService.employeeWithMaxSalaryByIdDepartment(id);
        assertEquals(emp2, actual);
        assertTrue(minSalary < maxSalary);

    }

    @Test
    void employeeWithMaxSalaryByIdDepartment_withDepartmentException() {

        //Подготовка входных данных

        Integer id = 2;

        //Подготовка ожидаемого результата

        when(employeeService.getAll()).thenReturn(Collections.emptyList());
        String expectedMessage = "Данный депортамент не найден";

        //Начало теста

        Exception exception = assertThrows(DepartmentException.class,
                () -> departmentService.employeeWithMaxSalaryByIdDepartment(id));

        assertEquals(expectedMessage, exception.getMessage());

    }

    @Test
    void getMaxSalaryByDepartment_success() {

        //Подготовка входных данных

        Integer id = 2;
        Double maxSalary = 23_045d;
        Double minSalary = 15_000d;

        Employee emp1 = new Employee("Анна", "Комаева", minSalary, DEPARTMENT_BY_ID.get(id));
        Employee emp2 = new Employee("Андрей", "Рак", maxSalary, DEPARTMENT_BY_ID.get(id));

        List<Employee> employees = List.of(emp1, emp2);

        String expected = maxSalary.toString();

        //Подготовка ожидаемого результата

        when(employeeService.getAll()).thenReturn(employees);

        //Начало теста

        String actual = departmentService.getMaxSalaryByDepartment(id);
        assertEquals(expected, actual);
        assertTrue(minSalary < maxSalary);

    }

    @Test
    void getMaxSalaryByDepartment_withDepartmentException() {

        //Подготовка входных данных

        Integer id = 2;

        //Подготовка ожидаемого результата

        when(employeeService.getAll()).thenReturn(Collections.emptyList());
        String expectedMessage = "Данный депортамент не найден";

        //Начало теста

        Exception exception = assertThrows(DepartmentException.class,
                () -> departmentService.getMaxSalaryByDepartment(id));

        assertEquals(expectedMessage, exception.getMessage());

    }

    @Test
    void getAll_success() {

        //Подготовка входных данных

        Integer id = 3;
        List<Employee> employees = new ArrayList<>();


        Employee emp1 = new Employee("Виктория", "Гончарова", 63_000, DEPARTMENT_BY_ID.get(1));
        Employee emp2 = new Employee("Олег", "Коновалов", 48_150, DEPARTMENT_BY_ID.get(1));

        Employee emp3 = new Employee("Анна", "Комаева", 55_000, DEPARTMENT_BY_ID.get(2));
        Employee emp4 = new Employee("Андрей", "Рак", 78_045, DEPARTMENT_BY_ID.get(2));

        Employee emp5 = new Employee("Рустам", "Магамедов", 71_060, DEPARTMENT_BY_ID.get(3));
        Employee emp6 = new Employee("Василиса", "Евклидовна", 46_005, DEPARTMENT_BY_ID.get(3));

        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);
        employees.add(emp4);
        employees.add(emp5);
        employees.add(emp6);

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(employees);

        Map<String, List<Employee>> expected = new HashMap<>();
        String departmentName = DEPARTMENT_BY_ID.get(id).getName();

        expected.put(departmentName, List.of(emp5, emp6));

        //Начало теста

        Map<String, List<Employee>> actual = departmentService.getAll(id);
        assertEquals(expected, actual);

    }

    @Test
    void getAll_departmentIdIsNull() {

        //Подготовка входных данных

        Integer id = null;
        List<Employee> employees = new ArrayList<>();


        Employee emp1 = new Employee("Виктория", "Гончарова", 63_000, DEPARTMENT_BY_ID.get(1));
        Employee emp2 = new Employee("Олег", "Коновалов", 48_150, DEPARTMENT_BY_ID.get(1));

        Employee emp3 = new Employee("Анна", "Комаева", 55_000, DEPARTMENT_BY_ID.get(2));
        Employee emp4 = new Employee("Андрей", "Рак", 78_045, DEPARTMENT_BY_ID.get(2));

        Employee emp5 = new Employee("Рустам", "Магамедов", 71_060, DEPARTMENT_BY_ID.get(3));
        Employee emp6 = new Employee("Василиса", "Евклидовна", 46_005, DEPARTMENT_BY_ID.get(3));

        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);
        employees.add(emp4);
        employees.add(emp5);
        employees.add(emp6);

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(employees);

        Map<String, List<Employee>> expected = new HashMap<>();

        expected.put("Отдел 1", List.of(emp1, emp2));
        expected.put("Отдел 2", List.of(emp3, emp4));
        expected.put("Отдел 3", List.of(emp5, emp6));

        //Начало теста

        Map<String, List<Employee>> actual = departmentService.getAll(id);
        assertEquals(expected, actual);

    }

    @Test
    void getAll_empty() {

        //Подготовка входных данных

        Integer id = 3;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(Collections.emptyList());

        //Начало теста

        Map<String, List<Employee>> actual = departmentService.getAll(id);
        assertTrue(actual.isEmpty());

    }

}