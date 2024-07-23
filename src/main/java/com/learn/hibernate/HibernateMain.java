package com.learn.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.learn.entity.Employee;
import com.learn.entity.Laptop;
import com.learn.entity.Student;
import com.learn.util.HibernateUtil;

/**
 * Hello world!
 *
 */
public class HibernateMain 
{
    public static void main( String[] args )
    {	
    	Student student = new Student("Ramesh", "Fadatare");
        Employee emp = new Employee("John", "Cena");
        Laptop lap = new Laptop("Dell");
        //emp.setLaptop(lap);
        Transaction transaction = null;
        
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.persist(student);
            session.persist(emp);
            session.persist(lap);
            //session.persist(lap1);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List < Student > students = session.createQuery("from Student", Student.class).list();
            students.forEach(s -> System.out.println(s));
            
            List < Employee > emps = session.createQuery("from Employee", Employee.class).list();
            emps.forEach(s -> System.out.println(s));
            
            List < Laptop > laptops = session.createQuery("from laptop", Laptop.class).list();
            laptops.forEach(s -> System.out.println(s));
        } catch (Exception e) {
        	e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            
        }
    }
}
