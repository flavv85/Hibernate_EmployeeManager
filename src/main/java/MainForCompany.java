import java.awt.*;
import java.util.List;
import java.util.Scanner;

public class MainForCompany {

    public static void main(String[] args) {

        EmployeesDao employee = new EmployeesDao();
        DepartmentsDao departmentsDao = new DepartmentsDao();

        Scanner scanner = new Scanner(System.in);

        int option = -1;

        while (option != 0) {
            System.out.println("----------------------------------------");
            System.out.println("Company employees management application");
            System.out.println("----------------------------------------");
            System.out.println(" 1. View employees");
            System.out.println(" 2. Add employee");
            System.out.println(" 3. Delete employee");
            System.out.println(" 4. Change employee's last name");
            System.out.println(" 5. Change employee's address");
            System.out.println(" 6. Find employee by id");
            System.out.println(" 7. View departments");
            System.out.println(" 8. Add departments");
            System.out.println(" 9. Find department by id");
            System.out.println("10. Find employees by department");
            System.out.println("11. Change department leader");

            System.out.println("\n 0. Exit");
            System.out.println();

            option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                List<EmployeesModel> employeesModelList = employee.viewEmployees();
                for (EmployeesModel x : employeesModelList
                ) {
                    DepartmentsModel departmentsModel = x.getDepartmentsModel();
                    if (departmentsModel == null) {
                        System.out.println(x.getId() + ". " + x.getName() + " " + x.getLastName() + " @ no assigned " +
                                "department." + " Age: " + x.getAge() + " ,lives " + "on " + x.getAddress() + ".");
                    } else {
                        System.out.println(x.getId() + ". " + x.getName() + " " + x.getLastName() + " @ " + x.getDepartmentsModel().getName() +
                                " department. Age: " + x.getAge() + " ,lives " +
                                "on " + x.getAddress() + ".");
                    }
                }
            }
            System.out.println();

            if (option == 2) {
                System.out.println("Name: ");
                String name = scanner.nextLine();
                System.out.println("Last name: ");
                String lastName = scanner.nextLine();
                System.out.println("Address: ");
                String address = scanner.nextLine();
//                System.out.println("Departament: ");
//                String department = scanner.nextLine();
                System.out.println("Age: ");
                int age = scanner.nextInt();
                scanner.nextLine();

                // ----------------- adaugam departamentul dorit -----------

                System.out.println("Enter the department for new employee");
                List<DepartmentsModel> departmentsModelList = departmentsDao.viewDepartments();
                for (DepartmentsModel x : departmentsModelList) {
                    System.out.println(x.getId() + ". " + x.getName());
                }

                System.out.println("Assign department id: ");
                int departmentId = scanner.nextInt();
                scanner.nextLine();

                DepartmentsModel chosenDepartment = departmentsDao.findById(departmentId);

                EmployeesModel employeesModel = new EmployeesModel();

                employeesModel.setName(name);
                employeesModel.setLastName(lastName);
                employeesModel.setAddress(address);
//                employeesModel.setDepartment(department);
                employeesModel.setDepartmentsModel(chosenDepartment);
                employeesModel.setAge(age);

                employee.addEmployee(employeesModel);
                System.out.println(name + " " + lastName + " added to employees DB");
                System.out.println();
            }

            if (option == 3) {
                System.out.println("Enter id of employee to delete: ");
                int employeeId = scanner.nextInt();
                scanner.nextLine();

                EmployeesModel employeeModel = employee.findById(employeeId);
                if (employeeModel != null) {
                }
                System.out.println("Delete employee at id: " + employeeModel.getId() + " ? (Y/N)");
                String answer = scanner.nextLine();
                if (answer.equals("y") || answer.equals("Y")) {
                    employee.deleteEmployee(employeeId);
                    System.out.println("Employee successfully deleted!");
                    System.out.println();
                }
            }

            if (option == 4) {
                System.out.println("Enter employee's id: ");
                int employeeId = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter new last name: ");
                String lastName = scanner.nextLine();
                employee.changeLastName(employeeId, lastName);
            }

            if (option == 5) {
                System.out.println("Enter employee's id: ");
                int employeeId = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter new address: ");
                String address = scanner.nextLine();
                employee.changeAddress(employeeId, address);
            }

            if (option == 6) {
                System.out.println("Enter employee's id: ");
                int employeeId = scanner.nextInt();
                scanner.nextLine();
                EmployeesModel employeeModel = employee.findById(employeeId);
                if (employeeModel == null) {
                    System.out.println("Employee id Not Found!");
                } else {
                    System.out.println("At id: " + employeeModel.getId() + " is " + employeeModel.getName() + " " + employeeModel.getLastName());
                }
            }

            if (option == 7) {
                List<DepartmentsModel> departmentsModelList = departmentsDao.viewDepartments();
                for (DepartmentsModel x : departmentsModelList) {
                    if (x.getDepartmentLeader() == null) {
                        System.out.println(x.getId() + ". " + x.getName() + " - code: " + x.getCode() + ". No leader " +
                                "assigned");
                    } else {
                        System.out.println(x.getId() + ". " + x.getName() + " - code: " + x.getCode() + ". Department " +
                                "Leader: " + x.getDepartmentLeader().getName() + " " + x.getDepartmentLeader().getLastName());
                    }
                }
            }

            if (option == 8) {
                System.out.println("Enter departments name: ");
                String departmentName = scanner.nextLine();
                System.out.println("Enter department code: ");
                int departmentCode = scanner.nextInt();
                scanner.nextLine();

                // --------atribuim un employee ca departmentLeader
                System.out.println("Choose the department leader");
                List<EmployeesModel> employeesModelList = employee.viewEmployees();
                for (EmployeesModel x : employeesModelList
                ) {
                    System.out.println(x.getId() + ". " + x.getName() + " " + x.getLastName());
                }

                System.out.println("Enter employee id: ");
                int departmentLeader = scanner.nextInt();
                scanner.nextLine();

                EmployeesModel employeeLeader = employee.findById(departmentLeader);

                DepartmentsModel department = new DepartmentsModel();
                department.setName(departmentName);
                department.setCode(departmentCode);
                department.setDepartmentLeader(employeeLeader);

                departmentsDao.addDepartment(department);

            }

            if (option == 9) {
                System.out.println("Enter department's id: ");
                int departmentId = scanner.nextInt();
                scanner.nextLine();
                DepartmentsModel department = departmentsDao.findById(departmentId);
                if (department == null) {
                    System.out.println("Invalid department name !");
                } else {
                    System.out.println("At id: " + department.getId() + " is " + department.getName() + " - code: " + department.getCode());
                }
            }

            if (option == 10) {
                List<DepartmentsModel> departmentsModelList = departmentsDao.viewDepartments();
                for (DepartmentsModel x : departmentsModelList) {
                    System.out.println(x.getId() + ". " + x.getName());
                }
                System.out.println("Enter department id to view assigned employees: ");
                int departmentId = scanner.nextInt();
                scanner.nextLine();

                DepartmentsModel departmentsModel = departmentsDao.findById(departmentId);
                List<EmployeesModel> employeesModelList = departmentsModel.getEmployeesList();

                if (employeesModelList != null) {
                    for (EmployeesModel x : employeesModelList
                    ) {
                        System.out.println(x.getId() + ".  " + x.getName() + " " + x.getLastName());
                    }
                }
            }

            if (option == 11){
                List<DepartmentsModel> departmentsModelList = departmentsDao.viewDepartments();
                for (DepartmentsModel x : departmentsModelList) {
                    System.out.println(x.getId() + ". " + x.getName());
                }
                System.out.println("\nChoose department id to reassign leader: ");
                int departmentId = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter new department leader");
                List<EmployeesModel> employeesModelList = employee.viewEmployees();
                for (EmployeesModel x : employeesModelList
                ) {
                    System.out.println(x.getId() + ". " + x.getName() + " " + x.getLastName());
                }
                System.out.println("Enter id of new leader: ");
                int newLeader = scanner.nextInt();
                scanner.nextLine();

                EmployeesModel newEmployeeLeader = employee.findById(newLeader);
                departmentsDao.changeDeptLeader(departmentId, newEmployeeLeader);
                System.out.println("Leader for " + departmentsDao.findById(departmentId).getName() + " department " +
                        "successfully updated!");

            }
        }
    }
}
