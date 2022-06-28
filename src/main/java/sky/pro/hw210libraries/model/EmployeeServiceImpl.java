package sky.pro.hw210libraries.model;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final Map<String, Employee> eBook;

    public EmployeeServiceImpl() {

        eBook = new HashMap<>();

        eBook.put("ИванИвановичИванцов", new Employee("Иван", "Иванович", "Иванцов", 0, 50_000));
        eBook.put("ПетрИннокентьевичПетров", new Employee("Петр", "Иннокентьевич", "Петров", 0, 150_000));
        eBook.put("СеменСеменовичГорбунков", new Employee("Семен", "Семенович", "Горбунков", 1, 101_000));
        eBook.put("ИннокентийБорисовичЧулков", new Employee("Иннокентий", "Борисович", "Чулков", 1, 120_000));
        eBook.put("СергейАнуфриевичЯбеда", new Employee("Сергей", "Ануфриевич", "Ябеда", 2, 40_000));
        eBook.put("ИосифВахтанговичГришин", new Employee("Иосиф", "Вахтангович", "Гришин", 2, 91_000));
        eBook.put("ИванИвановичИванов", new Employee("Иван", "Иванович", "Иванов", 3, 50_000));
        eBook.put("АлибекДжунгаровичХомяков", new Employee("Алибек", "Джунгарович", "Хомяков", 3, 42_000));
        eBook.put("РустамИбрагимовичСулейбеков", new Employee("Рустам", "Ибрагимович", "Сулейбеков", 4, 22_000));
        eBook.put("ЗухраПетровнаДжугашвилли", new Employee("Зухра", "Петровна", "Джугашвилли", 4, 173_000));
        Employee e = new Employee("Казбек", "Дмитриевич", "Светлый", 5, 17_500);
        String key = e.getFirstName() + e.getMiddleName() + e.getLastName();
        eBook.put(key, e);
        eBook.put("СулейманМыколовичБеспамятный", new Employee("Сулейман", "Мыколович", "Беспамятный", 5, 23_700));
        eBook.put("МихайлоДмитриевичГлавко", new Employee("Михайло", "Дмитриевич", "Главко", 6, 110_300));
        eBook.put("ЕленаАрменовнаАкопян", new Employee("Елена", "Арменовна", "Акопян", 6, 199_999.99));
        eBook.put("ГюльчатайЗурабовнаСухова", new Employee("Гюльчатай", "Зурабовна", "Сухова", 7, 69_000));
        eBook.put("НатальяАльбертовнаРабинович", new Employee("Наталья", "Альбертовна", "Рабинович", 7, 29_794));

    }


    @Override
    public Employee addEmployee(String firstName, String middleName, String lastName, int department, double salary) {
        Employee e = new Employee(firstName, middleName, lastName, department, salary);
        String key = e.getFirstName() + e.getMiddleName() + e.getLastName();
        if(eBook.containsKey(key)){
            throw new EmployeeAlreadyAddedException("Employee with such first, middle and last names already added into the database");
        } else {
            eBook.put(key, e);
            return eBook.get(key);
        }
    }

    @Override
    public Employee removeEmployee(String firstName, String middleName, String lastName) {
        String key = firstName + middleName + lastName;
        if(!eBook.containsKey(key)) {
            throw new EmployeeNotFoundException("Employee with such firstName, middleName and lastName is not found in the database");
        } else {
            return eBook.remove(key);
        }
    }

    @Override
    public Employee findEmployee(String firstName, String middleName, String lastName) {
        String key = firstName + middleName + lastName;
        if(!eBook.containsKey(key)) {
            throw new EmployeeNotFoundException("Employee with such firstName, middleName and lastName is not found in the database");
        } else {
            return eBook.get(key);
        }
    }

    public Map<String, Employee> allEmployeeList() {
        return eBook;
    }


}
