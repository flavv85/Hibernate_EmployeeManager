/*
In aplicati anterioare de angajati vom putea adauga un deparment care are nume  cod si sef de department(un angajat).
Un department contine mai multi angajati. Un angajat aparine unui departent.
Sa putem alege departmentul  unui angajat atunci cand il adugam.Sa putem adauga un nou department.
Sa vizualizam deprtamentele, sa vedem aganjati din'tr-un departament. Sa putem alege seful de department.
 */

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "departments")

public class DepartmentsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int code;

    @OneToMany(mappedBy = "departmentsModel", fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private List<EmployeesModel> employeesList;

    //     ideal e sa adaugam sef departament cand cream departamentul
    @OneToOne(cascade = CascadeType.ALL)
    private EmployeesModel departmentLeader;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<EmployeesModel> getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(List<EmployeesModel> employeesList) {
        this.employeesList = employeesList;
    }

    public EmployeesModel getDepartmentLeader() {
        return departmentLeader;
    }

    public void setDepartmentLeader(EmployeesModel departmentLeader) {
        this.departmentLeader = departmentLeader;
    }
}
