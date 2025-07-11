package org.example;

import org.example.DAO.DepartmentDAO;
import org.example.DAO.EmployeeDAO;
import org.example.entity.Department;
import org.example.entity.Employee;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        DepartmentDAO dao = new DepartmentDAO();

//       Department department = new Department();
//       department.setName("HR");
//       department.setLocation("Hyd");
//
//
//
//       dao.createDepartment(department);
//
//        Employee e1 = new Employee();
//        e1.setName("Ramesh");
//        e1.setEmail("ramesh@gmail.com");
//        e1.setDepartment(department);
//        e1.setSalary(35000.00);
//
//        employeeDAO.createEmployee(e1);
        System.out.println(employeeDAO.getAllEmployees());

//        System.out.println(employeeDAO.getEmployeeById("8b3bc751-e4d6-428b-8c07-d27e1bb29793"));
        employeeDAO.deleteEmployeeById("8b3bc751-e4d6-428b-8c07-d27e1bb29793");
        System.out.println(employeeDAO.getAllEmployees());
//        System.out.println( dao.getDepartmentById("a817ed35-1867-4f71-a7a2-d085894edfaf"));
//        dao.deleteDepartment("a817ed35-1867-4f71-a7a2-d085894edfaf");
//        System.out.println(dao.getAllDepartments());



    }
}