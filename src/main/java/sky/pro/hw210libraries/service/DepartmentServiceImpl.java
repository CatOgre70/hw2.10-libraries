package sky.pro.hw210libraries.service;

import org.springframework.stereotype.Service;
import sky.pro.hw210libraries.db.Employee;
import sky.pro.hw210libraries.exception.EmployeeNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {

        this.employeeService = employeeService;

    }

    @Override
    public Employee findEmployeeWithMaxSalary(int department) {

        return employeeService.allEmployeeList().values().stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Wrong department number in the " +
                        "DepartmentService.findEmployeeWithMaxSalary() method argument!"));

    }

    @Override
    public Employee findEmployeeWithMinSalary(int department) {

        return employeeService.allEmployeeList().values().stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Wrong department number in the " +
                        "DepartmentService.findEmployeeWithMaxSalary() method argument!"));

    }

    @Override
    public List<Employee> departmentEmployeeList(int department) {
        return employeeService.allEmployeeList().values().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, Set<Employee>> allDepartmentsEmployeeList() {
        return employeeService.allEmployeeList().values().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.toSet()));
    }

}
