package sky.pro.hw210libraries.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sky.pro.hw210libraries.model.DepartmentService;
import sky.pro.hw210libraries.model.Employee;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    public final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee findEmployeeWithMaxSalary(@RequestParam("departmentId") int departmentId) {
        return departmentService.findEmployeeWithMaxSalary(departmentId);
    }

    @GetMapping("/min-salary")
    public Employee findEmployeeWithMinSalary(@RequestParam(value = "departmentId") int departmentId) {
        return departmentService.findEmployeeWithMinSalary(departmentId);
    }

    @GetMapping(value= "/all", params = "departmentId")
    public List<Employee> departmentEmployeeList(@RequestParam(value = "departmentId") int departmentId) {

        return departmentService.departmentEmployeeList(departmentId);

    }

    @GetMapping(value = "/all")
    public Map<Integer, Set<Employee>> allDepartmentEmployeeList() {

        return departmentService.allDepartmentsEmployeeList();

    }


}
