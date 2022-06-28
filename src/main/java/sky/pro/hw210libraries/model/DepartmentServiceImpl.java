package sky.pro.hw210libraries.model;

import org.springframework.stereotype.Service;

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
        return employeeService.allEmployeeList().keySet().stream()
                .filter(s -> employeeService.allEmployeeList().get(s).getDepartment() == department)
                .map(employeeService.allEmployeeList()::get)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new RuntimeException("Wrong department number in the " +
                        "DepartmentService.findEmployeeWithMaxSalary() method argument!"));
    }

    @Override
    public Employee findEmployeeWithMinSalary(int department) {
        return employeeService.allEmployeeList().keySet().stream()
                .filter(s -> employeeService.allEmployeeList().get(s).getDepartment() == department)
                .map(employeeService.allEmployeeList()::get)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new RuntimeException("Wrong department number in the " +
                        "DepartmentService.findEmployeeWithMaxSalary() method argument!"));
    }

    @Override
    public List<Employee> departmentEmployeeList(int department) {
        return employeeService.allEmployeeList().keySet().stream()
                .filter(s -> employeeService.allEmployeeList().get(s).getDepartment() == department)
                .map(employeeService.allEmployeeList()::get)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, Set<Employee>> allDepartmentsEmployeeList() {
        return employeeService.allEmployeeList().keySet().stream()
                .map(employeeService.allEmployeeList()::get)
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.toSet()));
    }

}
