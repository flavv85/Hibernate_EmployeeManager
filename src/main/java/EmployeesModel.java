import javax.persistence.*;

@Entity
@Table(name = "employees")

public class EmployeesModel {
    //    id nume prenume adresa varsta si departament
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 45)
    private String name;
    @Column(length = 45)
    private String lastName;
    @Column(length = 45)
    private String address;
    private int age;
//    @Column(length = 45)
//    private String department;

    @ManyToOne
    private DepartmentsModel departmentsModel;

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

//    public String getDepartment() {
//        return department;
//    }
//
//    public void setDepartment(String department) {
//        this.department = department;
//    }

    public DepartmentsModel getDepartmentsModel() {
        return departmentsModel;
    }

    public void setDepartmentsModel(DepartmentsModel departmentsModel) {
        this.departmentsModel = departmentsModel;
    }
}
