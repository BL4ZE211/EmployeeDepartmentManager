package org.example.DAO;

import org.example.entity.Department;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DepartmentDAO {
    public void createDepartment(Department department){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Department WHERE name=:name";
            Department existingDepartment = session.createQuery(hql,Department.class)
                    .setParameter("name",department.getName())
                    .uniqueResult();

            if(existingDepartment !=null){
                throw new RuntimeException("Already Employee exists");
            }

            Transaction tx = session.beginTransaction();
            session.persist(department);
            tx.commit();
        }
    }

    public List<Department> getAllDepartments(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String hql = "FROM Department";

            return session.createQuery(hql,Department.class)
                    .getResultList();
        }
    }

    public Department getDepartmentById(String id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String hql = "FROM Department WHERE id=:id";
            Department department = session.createQuery(hql,Department.class)
                    .setParameter("id",id)
                    .uniqueResult();

            if(department==null){
                throw new RuntimeException("No department with id : "+ id +" found.");
            }

            return department;
        }
    }

    public void deleteDepartment(String id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String hql = "FROM Department WHERE id=:id";
            Department department = session.createQuery(hql,Department.class)
                    .setParameter("id",id)
                    .uniqueResult();

            if(department==null){
                throw new RuntimeException("No department with id : "+ id +" found.");
            }
            Transaction tx = session.beginTransaction();
            session.remove(department);
            tx.commit();
        }
    }

    public  void updateDepartment(String id , Department department){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String hql = "FROM Department WHERE id=:id";
            Department existingDepartment = session.createQuery(hql,Department.class)
                    .setParameter("id",id)
                    .uniqueResult();

            if(existingDepartment ==null){
                throw new RuntimeException("No department with id : "+ id +" found.");
            }

            if(department.getLocation()!=null){
                existingDepartment.setLocation(department.getLocation());
            }
            if(department.getName()!=null){
                existingDepartment.setName(department.getName());
            }

            Transaction transaction = session.beginTransaction();
            session.merge(existingDepartment);
            transaction.commit();
        }
    }

}
