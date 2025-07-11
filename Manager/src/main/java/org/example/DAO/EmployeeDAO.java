package org.example.DAO;

import org.example.entity.Department;
import org.example.entity.Employee;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeDAO {

    public void createEmployee(Employee employee){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Employee WHERE email=:email";
            Employee existingEmployee = session.createQuery(hql,Employee.class)
                    .setParameter("email",employee.getEmail())
                    .uniqueResult();

            if(existingEmployee!=null){
                throw new RuntimeException("Already Employee exists");
            }

            String name = employee.getDepartment().getName();

            String hql1 = "FROM Department WHERE name=:name";
            Department department = session.createQuery(hql1,Department.class)
                    .setParameter("name",name)
                    .uniqueResult();

            if(department==null){
                throw new RuntimeException("No department with name : "+ name +" found.");
            }

            Transaction tx = session.beginTransaction();
            session.persist(employee);
            tx.commit();

        }
    }

    public List<Employee> getAllEmployees(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String hql = "FROM Employee";
            List<Employee> allEmployees = session.createQuery(hql, Employee.class)
                    .getResultList();

            return allEmployees;
        }
    }

    public Employee getEmployeeById(String id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String hql = "FROM Employee WHERE id=:id";

            Employee employee = session.createQuery(hql, Employee.class)
                    .setParameter("id",id)
                    .uniqueResult();

            if(employee==null){
                throw new RuntimeException("No employee found");
            }

            return employee;
        }
    }

    public void deleteEmployeeById(String id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Employee WHERE id=:id";

            Employee employee = session.createQuery(hql, Employee.class)
                    .setParameter("id",id)
                    .uniqueResult();

            if(employee==null){
                throw new RuntimeException("No employee found");
            }

            Transaction tx = session.beginTransaction();
            session.remove(employee);
            tx.commit();
            }
        }

        public Employee updateEmployeeDetails(String id,Employee employee){
            try(Session session = HibernateUtil.getSessionFactory().openSession()){
                String hql = "FROM Employee WHERE id=:id";

                Employee existingEmployee = session.createQuery(hql, Employee.class)
                        .setParameter("id",id)
                        .uniqueResult();

                if(employee==null){
                    throw new RuntimeException("No employee found");
                }

                if(employee.getDepartment()!=null){
                    existingEmployee.setDepartment(employee.getDepartment());
                }

                if(employee.getEmail()!=null){
                    existingEmployee.setEmail(employee.getEmail());
                }

                if(employee.getSalary()!=0){
                    existingEmployee.setSalary(employee.getSalary());
                }

                if(employee.getName()!=null){
                    existingEmployee.setName(employee.getName());
                }

                Transaction tx = session.beginTransaction();
                session.merge(existingEmployee);
                tx.commit();

             }
        }
}

