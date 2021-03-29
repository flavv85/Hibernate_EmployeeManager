/*
Individual work  : O aplicatie in hibernate in care putem adauga angajati unei firme , afisa angajati, a modifica numele
de familie a angajatilor, a modifica adresa angajatilor. A sterge angajatul, a gasi angajatul dupa id.
Angjatul va avea  id nume prenume adresa varsta si departament.
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.List;
import java.util.Properties;

public class EmployeesDao {

    private SessionFactory sessionFactory;

    public EmployeesDao() {

        Configuration configuration = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
        properties.put(Environment.URL, "jdbc:mysql://localhost:3306/company");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "12345");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        properties.put(Environment.SHOW_SQL, false);
        //create table
        properties.put(Environment.HBM2DDL_AUTO, "update");
        // register class corespondent with table
        configuration.addAnnotatedClass(EmployeesModel.class);
        configuration.addAnnotatedClass(DepartmentsModel.class);

        configuration.setProperties(properties);

        ServiceRegistry serviceRegistry =
                new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    //    add employee
    public void addEmployee(EmployeesModel employeesModel) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(employeesModel);
        transaction.commit();
        session.close();
    }

    //    view employees
    public List<EmployeesModel> viewEmployees() {
        Session session = sessionFactory.openSession();
        List<EmployeesModel> employeesModelList = session.createQuery("from EmployeesModel").getResultList();
        session.close();
        return employeesModelList;
    }

    //    modify lastName
    public void changeLastName(int employeeId, String lastName) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        EmployeesModel employeeModel = session.find(EmployeesModel.class, employeeId);
        employeeModel.setLastName(lastName);
        session.saveOrUpdate(employeeModel);
        transaction.commit();
        session.close();
    }

    //    modify address
    public void changeAddress(int employeeId, String newAddress) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        EmployeesModel employeeModel = session.find(EmployeesModel.class, employeeId);
        employeeModel.setAddress(newAddress);
        session.saveOrUpdate(employeeModel);
        transaction.commit();
        session.close();
    }

    //    delete employee
    public void deleteEmployee(int employeeId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        EmployeesModel employeeModel = session.find(EmployeesModel.class, employeeId);
        session.delete(employeeModel);
        transaction.commit();
        session.close();
    }

    //    find employee by id
    public EmployeesModel findById(int employeeId) {
        Session session = sessionFactory.openSession();
        EmployeesModel employeeModel = session.find(EmployeesModel.class, employeeId);
        session.close();
        return employeeModel;
    }


}
