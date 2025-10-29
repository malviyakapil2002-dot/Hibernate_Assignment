package com.Infobeans.assignment_66_ques_1;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.internal.build.AllowSysOut;



public class App 
{
    public static void main( String[] args )
    {
    	 System.out.println( "enter in new world" );
         Scanner sc = new Scanner(System.in);
         StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
         SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
         Session session = sessionFactory.openSession();
         
         while(true) {
         	System.out.println("1. Add Library with Books\r\n"
         			+ "2. Add Book to Library\r\n"
         			+ "3. View All Libraries and Books\r\n"
         			+ "4. Update Book Price\r\n"
         			+ "5. Update Library Name\r\n"
         			+ "6. Delete Book\r\n"
         			+ "7. Delete Library\r\n"
         			+ "8. Find Books by Library (HQL)\r\n"
         			+ "9. Find Library by Book Title (HQL)\r\n"
         			+ "10.  List Books with Price > given amount (HQL)\r\n"
         			+ "11. List Libraries with more than N books (HQL)\r\n"
         			+ "Exit\r\n"
         			+ "");
             int choice = sc.nextInt();
             sc.nextLine();
             switch(choice) {
             case 1 : 
            	Transaction ts = session.beginTransaction();
               	System.out.println("enter bookId ");
               	int bookId = sc.nextInt();
                sc.nextLine();
             	System.out.println("enter title ");
             	String title = sc.nextLine();
             	System.out.println("enter author ");
             	String author = sc.nextLine();
             	System.out.println("enter price ");
             	int price = sc.nextInt();
             	sc.nextLine();
             	Books b = new Books();
             	b.setAuthor(author);
             	b.setBookId(bookId);
             	b.setPrice(price);
             	b.setTitle(title);
             	
              	System.out.println("enter libraryId ");
              	int libraryId = sc.nextInt();
              	 sc.nextLine();
             	System.out.println("enter libraryName ");
             	String libraryName = sc.nextLine();
             	System.out.println("enter location ");
             	String location = sc.nextLine();
             	
             	Library l = new Library();
             	l.setLibraryId(libraryId);
             	l.setLibraryName(libraryName);
             	l.setLocation(location);
             	l.getBooks().add(b);
             	b.setLibrary(l); 
             	session.persist(l);
             	ts.commit();
                break;
             
             case 2 : 
             	Transaction ts2 = session.beginTransaction();
               	System.out.println("enter bookId ");
               	int bookId2 = sc.nextInt();
                sc.nextLine();
             	System.out.println("enter title ");
             	String title2 = sc.nextLine();
             	System.out.println("enter author ");
             	String author2 = sc.nextLine();
             	System.out.println("enter price ");
             	int price2 = sc.nextInt();
             	sc.nextLine();
             	Books b2 = new Books();
             	b2.setAuthor(author2);
             	b2.setBookId(bookId2);
             	b2.setPrice(price2);
             	b2.setTitle(title2);
             	
             	System.out.println("enter library Id ");
             	int libId = sc.nextInt();
             	sc.nextLine();
             	Library lib = session.get(Library.class, libId );
             	lib.getBooks().add(b2);
             	b2.setLibrary(lib);
             	session.persist(b2);
             	ts2.commit();

             	break;
             	
             case 3 :
            	 List<Library> library = session.createQuery("from Library", Library.class).getResultList();
            	 if(library != null) {
            		 for(Library val:library) {
            			 System.out.println(val +" "+val.getBooks());
            		 }
            	 } else {
            		 System.out.println("not found!!");
            	 }
             	
             	break;
             	
             case 4 :
              	Transaction ts3 = session.beginTransaction();

             	System.out.println("enter id for update book price : ");
             	int updatePrice = sc.nextInt();
             	System.out.println("enter new price.");
             	int newPrice = sc.nextInt();
             	sc.nextLine();
             	
             	Books booksU = session.get(Books.class, updatePrice);
             	if(booksU != null) {
                 	booksU.setPrice(newPrice);
                 	session.merge(booksU);
                 	ts3.commit();
             	} else {
             		System.out.println("not found!!!");
             	}
             	break;
             case 5 :
            	 Transaction ts4 = session.beginTransaction();
            	 
            	 System.out.println("enter id for update library name : ");
            	 int updateName = sc.nextInt();
            	 sc.nextLine();
            	 System.out.println("enter new name.");
            	 String newName = sc.nextLine();
            	 
            	 Library LibraryU = session.get(Library.class, updateName);
            	 if(LibraryU != null) {
            		 LibraryU.setLibraryName(newName);
            		 session.merge(LibraryU);
            		 ts4.commit();
            	 } else {
            		 System.out.println("not found!!!");
            	 }
            	 break;
             case 6 :
            	 Transaction ts5 = session.beginTransaction();
            	 
            	 System.out.println("enter id for delete book : ");
            	 int deleteBook = sc.nextInt();
            	 sc.nextLine();
            	 
            	 Books BookD = session.get(Books.class, deleteBook);
            	 if(BookD != null) {
            		 session.remove(BookD);
            		 ts5.commit();
            	 } else {
            		 System.out.println("not found!!!");
            	 }
            	 break;
            	 
             case 7 :
            	 Transaction ts6 = session.beginTransaction();
            	 
            	 System.out.println("enter id for delete Library : ");
            	 int deleteLibrary = sc.nextInt();
            	 sc.nextLine();
            	 
            	 Library LibraryD = session.get(Library.class, deleteLibrary);
            	 if(LibraryD != null) {
            		 session.remove(LibraryD);
            		 ts6.commit();
            	 } else {
            		 System.out.println("not found!!!");
            	 }
            	 break;
            	 
             case 8 :            	 
            	 System.out.println("enter book title for fatch Library : ");
            	 String booktitle = sc.nextLine();
            	 
            	 Books booksF = session.createQuery("from Books where title = :title", Books.class).setParameter("title", booktitle).getSingleResult(); 
            	 if(booksF != null) {
            		 System.out.println(booksF.getLibrary());
            	 } else {
            		 System.out.println("not found!!!");
            	 }
            	 break;
             case 9 :            	 
            	 System.out.println("enter Library name for fatch books : ");
            	 String libraryNames = sc.nextLine();
            	 
            	 List<Library> LibraryF = session.createQuery("from Library where libraryName = :name", Library.class).setParameter("name", libraryNames).getResultList(); 
            	 if(LibraryF != null) {
            		 for(Library val:LibraryF) {
            			 System.out.println(val.getBooks());
            		 }
            	 } else {
            		 System.out.println("not found!!!");
            	 }
            	 break;
             case 10 :            	 
            	 System.out.println("list library with more then n books enter n : ");
            	 int n = sc.nextInt();
            	 sc.nextLine();
            	 
            	 List<Library> LibraryN = session.createQuery("from Library l where size(l.books) >= :n", Library.class).setParameter("n", n).getResultList(); 
            	 if(LibraryN != null) {
            		 for(Library val:LibraryN) {
            			 System.out.println(val);
            		 }
            	 } else {
            		 System.out.println("not found!!!");
            	 }
            	 break;
            	     	
             case 11 : 
             	System.out.println("exit successfully.");	
                 session.close();
                 sessionFactory.close();
                 return;
              
             default : System.out.println("enter valid case .");     
             	
             }
         }         
    }
}