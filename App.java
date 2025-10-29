package com.Infobeans.Assignment_65;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Scanner sc = new Scanner(System.in);
        
        
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session s = sessionFactory.openSession();
        
        System.out.println("welcome kk");
        while(true) {
        	System.out.println("1. Add Department with Employees\r\n"
        			+ "2. Add Employee to Department\r\n"
        			+ "3. View All Departments and Employees\r\n"
        			+ "4. Update Employee Salary\r\n"
        			+ "5. Update Department Name\r\n"
        			+ "6. Delete Employee\r\n"
        			+ "7. Delete Department\r\n"
        			+ "8. Find Employees by Department (HQL)\r\n"
        			+ "9. Find Department by Employee Name (HQL)\r\n"
        			+ "10. List Employees with Salary > given amount (HQL)\r\n"
        			+ "11. List Departments with more than N employees (HQL)\r\n"
        			+ "0. Exit");
        
        int choice = sc.nextInt();
        sc.nextLine();
        switch(choice) {
       
        case 1 : 
        	Transaction t1=s.beginTransaction();
        	System.out.println("enter employee id: ");
        	int impId = sc.nextInt();
        	System.out.println("enter Employee salary : ");
        	double salary  = sc.nextDouble();
        	sc.nextLine();
        	System.out.println("enter Employee name: ");
        	String name = sc.nextLine();
        	System.out.println("enter Employee designation : ");
        	String designation = sc.nextLine();
        	
//        	ArrayList<Employee> el = new ArrayList<Employee>();

        	Employee e = new Employee();
        	
        	e.setId(impId);
        	e.setName(name);
        	e.setDegination(designation);
        	e.setSalary(salary);
        	
//        	el.add(e);
        	
        	Department d = new Department();
        	System.out.println("enter Department id: ");
        	int depId = sc.nextInt();
        	sc.nextLine();
        	System.out.println("enter Department name: ");
        	String depName = sc.nextLine();
        	System.out.println("enter Department location : ");
        	String location = sc.nextLine();
        	d.setDeptId(depId);
        	d.setDeptName(depName);
        	d.setLocation(location);
        	d.getEmployee().add(e);
        	e.setDepartment(d);
        	s.persist(d);
        	t1.commit();
        	s.clear();
        	break;
        	
        case 2 : 
        	System.out.println("enter employee id: ");
        	int impId1 = sc.nextInt();
        	System.out.println("enter Employee salary : ");
        	double salary1  = sc.nextDouble();
        	sc.nextLine();
        	System.out.println("enter Employee name: ");
        	String name1 = sc.nextLine();
        	System.out.println("enter Employee designation : ");
        	String designation1 = sc.nextLine();
        	
        	Transaction t2=s.beginTransaction();

        	Employee e2 = new Employee();
        	
        	e2.setId(impId1);
        	e2.setName(name1);
        	e2.setDegination(designation1);
        	e2.setSalary(salary1);
        	        	
        	System.out.println("enter id which department you want to add this imployee ");
        	int depId2 = sc.nextInt();
        	sc.nextLine();

        	Department d1 = s.get(Department.class, depId2);
        	d1.getEmployee().add(e2);
        	e2.setDepartment(d1);
        	s.persist(e2);
        	t2.commit();
        	
        	break;
        	
        case 3 : 
        	List<Department> department = s.createQuery("from Department", Department.class).getResultList();      	
        	if(department != null) {
        		for(Department val: department) {
        			System.out.println(val + " "+val.getEmployee() );
        		}
        	} else {
        		System.out.println("list is empty may be");
        	}
        	break; 
        	
        case 4 : 
        	Transaction t3=s.beginTransaction();
        	System.out.println("enter employee id for update salary : ");
        	int id = sc.nextInt();
        	sc.nextLine();
        	System.out.println("enter employee new salary : ");
        	double newSalary = sc.nextDouble();
        	     	
        	Employee updateE = s.get(Employee.class, id);
        	if (updateE != null) {
            	updateE.setSalary(newSalary);
            	s.merge(updateE);
            	t3.commit();
                System.out.println("Salary updated successfully!");
        	} else {
        		System.out.println("Employee not found!");
                t3.rollback();

        	}
        	break;
        case 5 : 
        	Transaction t4=s.beginTransaction();
        	System.out.println("enter department id for update name : ");
        	int id1 = sc.nextInt();
        	sc.nextLine();
        	System.out.println("enter department new name : ");
        	String newNameD = sc.nextLine();
        	
        	Department updateD = s.get(Department.class, id1);
        	if (updateD != null) {
        		updateD.setDeptName(newNameD);
        		s.merge(updateD);
        		t4.commit();
        		System.out.println("name updated successfully!");
        	} else {
        		System.out.println("department not found!");
        		t4.rollback();
        		
        	}
        	break;
        	
        case 6 : 
        	Transaction t5=s.beginTransaction();
        	System.out.println("enter employee id for delete : ");
        	int id2 = sc.nextInt();
        	sc.nextLine();
        	
        	Employee deleteE = s.get(Employee.class, id2);
        	if (deleteE != null) {
        		s.remove(deleteE);
        		t5.commit();
        		System.out.println("employee delete successfully!");
        	} else {
        		System.out.println("employee not found!");
        		t5.rollback();
        		
        	}
        	break;
        	
        case 7 : 
        	Transaction t6=s.beginTransaction();
        	System.out.println("enter department id for delete : ");
        	int id3 = sc.nextInt();
        	sc.nextLine();
        	
        	Department deleteD = s.get(Department.class, id3);
        	if (deleteD != null) {
        		s.remove(deleteD);
        		t6.commit();
        		System.out.println("department delete successfully!");
        	} else {
        		System.out.println("department not found!");
        		t6.rollback();      		
        	}
        	break;
        	
        case 8 : 
        	System.out.println("enter emplyoee id for fatch : ");
        	int id4 = sc.nextInt();
        	sc.nextLine();
        	
        	Employee fatchD = s.createQuery("from Employee where id = :id", Employee.class).setParameter("id", id4).getSingleResult();
        	if (fatchD != null) {
        		System.out.println(fatchD.getDepartment());
        	} else {
        		System.out.println("department not found!");
        	}
        	break;    	
        case 9 : 
        	System.out.println("enter emplyoee name for fatch : ");
        	String Ename = sc.nextLine();
        	
        	List<Employee> fatchByname = s.createQuery("from Employee where name = :name", Employee.class).setParameter("name", Ename).getResultList();
        	if (fatchByname != null) {
        		for(Employee val:fatchByname) {
        			System.out.println(val.getDepartment());
        		}
        		
        	} else {
        		System.out.println("department not found!");
        	}
        	break;   
        	
        case 10 :     
        	
        	List<Employee> fatchBySalary = s.createQuery("from Employee where salary > 25000", Employee.class).getResultList();
        	if (fatchBySalary != null) {
        		for(Employee val:fatchBySalary) {
        			System.out.println(val);
        		}
        		
        	} else {
        		System.out.println("department not found!");
        	}
        	break; 
        case 11 :    
        	System.out.println("enter employe count in one department : ");
        	int count = sc.nextInt();
        	sc.nextLine();
        	List<Department> fatchECount = s.createQuery("from Department d where size(d.employee) >= :n", Department.class).setParameter("n", count).getResultList();
        	if (fatchECount != null) {
        		for(Department val:fatchECount) {
        			System.out.println(val);
        		}
        		
        	} else {
        		System.out.println("department not found!");
        	}
        	break; 
        	
        case 0 :    
        	s.close();
        	sessionFactory.close();
        	break; 
        
        default : System.out.println("you enter rongh choice .");	
        }
        
        
        }
    }
}