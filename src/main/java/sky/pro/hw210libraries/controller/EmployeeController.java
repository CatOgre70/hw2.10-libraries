package sky.pro.hw210libraries.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sky.pro.hw210libraries.db.Employee;
import sky.pro.hw210libraries.service.EmployeeService;

import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    public final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam("firstname") String firstName,
                                @RequestParam("middlename") String middleName,
                                @RequestParam("lastname") String lastName,
                                @RequestParam("departmentId") int department,
                                @RequestParam("salary") double salary) {
        return employeeService.addEmployee(firstName, middleName, lastName, department, salary);
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam("firstname") String firstName,
                                   @RequestParam("middlename") String middleName,
                                   @RequestParam("lastname") String lastName) {
        return employeeService.removeEmployee(firstName, middleName, lastName);
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam("firstname") String firstName,
                                 @RequestParam("middlename") String middleName,
                                 @RequestParam("lastname") String lastName) {
        return employeeService.findEmployee(firstName, middleName, lastName);
    }

    @GetMapping("/list")
    public Map<String, Employee> employeeList() {
        return employeeService.allEmployeeList();
    }


}
