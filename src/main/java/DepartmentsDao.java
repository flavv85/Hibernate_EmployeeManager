/*
In aplicati anterioare de angajati vom putea adauga un deparment care are nume  cod si sef de department(un angajat).
Un department contine mai multi angajati. Un angajat aparine unui departent.
Sa putem alege departmentul  unui angajat atunci cand il adugam.Sa putem adauga un nou department.
Sa vizualizam deprtamentele, sa vedem aganjati din'tr-un departament. Sa putem alege seful de department.
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

public class DepartmentsDao {

    private SessionFactory sessionFactory;

    public DepartmentsDao() {

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

        configuration.addAnnotatedClass(DepartmentsModel.class);
        configuration.addAnnotatedClass(EmployeesModel.class);

        configuration.setProperties(properties);

        ServiceRegistry serviceRegistry =
                new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

    }

    // add department
public void addDepartment(DepartmentsModel departmentName) {
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();
    session.saveOrUpdate(departmentName);
    transaction.commit();
    System.out.println("\nDepartment " +  departmentName.getName() + " added successfully!");
    session.close();
}

    // view departments

    public List<DepartmentsModel> viewDepartments() {
        Session session = sessionFactory.openSession();
        List<DepartmentsModel> departmentsModelList = session.createQuery("from DepartmentsModel").getResultList();
        session.close();
        return departmentsModelList;
    }

    // find department by id

    public DepartmentsModel findById(int departmentId) {
        Session session = sessionFactory.openSession();
        DepartmentsModel departmentsModel = session.find(DepartmentsModel.class, departmentId);
        return departmentsModel;
    }

    public void changeDeptLeader (int departmentId, EmployeesModel newDeptLeader){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        DepartmentsModel departmentsModel = session.find(DepartmentsModel.class, departmentId);
        departmentsModel.setDepartmentLeader(newDeptLeader);
        session.saveOrUpdate(departmentsModel);
        transaction.commit();
        session.close();

    }

}
