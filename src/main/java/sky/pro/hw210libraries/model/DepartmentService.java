package sky.pro.hw210libraries.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface DepartmentService {

    Employee findEmployeeWithMaxSalary(int department);

    Employee findEmployeeWithMinSalary(int department);

    List<Employee> departmentEmployeeList(int department);

    Map<Integer, Set<Employee>> allDepartmentsEmployeeList();

}
