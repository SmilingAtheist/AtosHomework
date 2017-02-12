/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.homework;

import java.util.*;


/**
 *
 * @author SmilingAtheist
 */

class Book {
    
    
    
    final private String title;
    final private int year;
    final private String author;
    private boolean isLent;
    private String whoLent;
    
    Book(String startTitle, int startYear, String startAuthor) {
        title = startTitle;
        year = startYear;
        author = startAuthor;
        isLent = false;
    }
    
    
    String getTitle(){
        return title;
    }
    
    int getYear() {
        return year;
    }
    
    String getAuthor() {
        return author;
    }
    
    boolean getIsLent() {
        return isLent;
    }
      
    void setIsLent(boolean newIsLent) {
        isLent = newIsLent;
    }
    
    String getWhoLent() {
        return whoLent;
    }
    
    void setWhoLent(String newWhoLent) {
        whoLent = newWhoLent;
    }
    
    
}



class Library {
    
    private Map<Integer, Book> booksInLibrary = new HashMap<Integer, Book>();
    private int numOfBooksAvailable;
    private int nextID;
    
    Library() {
        numOfBooksAvailable = 0;
        nextID = 0;
    }
    
    void addBook(String title, int year, String author) {
        Book newBook = new Book(title, year, author);
        booksInLibrary.put(nextID, newBook);
        nextID ++;
        numOfBooksAvailable ++;
    }
    
    void removeBook(Integer bookID) {
        
        if (booksInLibrary.containsKey(bookID) && !booksInLibrary.get(bookID).getIsLent() ) {
                booksInLibrary.remove(bookID);
                numOfBooksAvailable --;
            }
        else if (booksInLibrary.get(bookID).getIsLent()) {
            System.out.println("The book with this ID is currently lent.");
        }
        else {
            System.out.println("The book with this ID doesn't exist.");
        }
    }
    
    
    void listAllBooks() {
        
        System.out.println("There are " + booksInLibrary.size() + " books in the library; " + numOfBooksAvailable + " books are available.");
        
        for (Map.Entry<Integer, Book> entry : booksInLibrary.entrySet()){
            Book currentBook = entry.getValue();
             
                    
            System.out.println("ID: " + entry.getKey() + 
                            ", title: " + currentBook.getTitle() +
                            ", year: " + currentBook.getYear() +
                            ", author: " + currentBook.getAuthor() +
                            ", is available?: " + !currentBook.getIsLent()
                            );
        } 
        System.out.println();
    }
    
    void lendBook(Integer bookID, String whoLent) {
        
        if (booksInLibrary.containsKey(bookID) && !booksInLibrary.get(bookID).getIsLent() ) {
                booksInLibrary.get(bookID).setIsLent(true);
                booksInLibrary.get(bookID).setWhoLent(whoLent);
                numOfBooksAvailable --;
            }
        else if (booksInLibrary.get(bookID).getIsLent()) {
            System.out.println("The book with this ID is currently lent");
        }
        else {
            System.out.println("The book with this ID doesn't exist");
        }
    }
    
    
    void seeBookByID(Integer bookID) {
        if (booksInLibrary.containsKey(bookID)){
            
            Book currentBook =  booksInLibrary.get(bookID);     
        
            System.out.println("ID: " + bookID + 
                            ", title: " + currentBook.getTitle() +
                            ", year: " + currentBook.getYear() +
                            ", author: " + currentBook.getAuthor() +
                            ", is available?: " + !currentBook.getIsLent()
                            );
        
            if (currentBook.getIsLent()){
                System.out.println("Lent by " + currentBook.getWhoLent());
            }
        }
        else {
            System.out.println("The book with this ID doesn't exist");
        }
    }
  
    void searchBook(Map<String, Object> params) {
        
        for (Map.Entry<Integer, Book> entry : booksInLibrary.entrySet()){
            
            boolean titleMatches = false;
            boolean yearMatches = false;
            boolean authorMatches = false;
            
            Book currentBook = entry.getValue();
             
            if (!params.containsKey("title")){
                titleMatches = true;
            }
            else {
                titleMatches = (params.get("title") == currentBook.getTitle());    
            }
            
            if (!params.containsKey("year")){
                yearMatches = true;
            }
            else {
                yearMatches = params.get("year").equals(currentBook.getYear());    
            }
            
            if (!params.containsKey("author")){
                authorMatches = true;
            }
            else {
                authorMatches = (params.get("author") == currentBook.getAuthor());    
            }
            
            if (titleMatches && yearMatches && authorMatches){
                System.out.println("ID: " + entry.getKey() + 
                            ", title: " + currentBook.getTitle() +
                            ", year: " + currentBook.getYear() +
                            ", author: " + currentBook.getAuthor() +
                            ", is available?: " + !currentBook.getIsLent()
                            );
        
                if (currentBook.getIsLent()){
                    System.out.println("Lent by " + currentBook.getWhoLent());
                }
            }
            
        } 
        
        
        
    }
    
    
}
    
    
        
    
    
    
    

        
        
        
        
        
public class AtosHomework {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Book book1 = new Book("Alice in Wonderland", 1865, "Lewis Carroll");
        Book book2 = new Book("The Light Fantastic", 1986, "Terry Pratchett");
        Book book3 = new Book("The Righteous Mind", 2013, "Jonathan Haidt");
        Book book4 = new Book("Why Him? Why Her?", 2010, "Helen Fisher");
        
        Library lib = new Library();
        lib.addBook("Alice in Wonderland", 1865, "Lewis Carroll");
        lib.addBook("The Light Fantastic", 1986, "Terry Pratchett");
        lib.addBook("The Righteous Mind", 2013, "Jonathan Haidt");
        lib.addBook("Why Him? Why Her?", 2010, "Helen Fisher");
        lib.listAllBooks();
        
        lib.lendBook(2, "Tom");    
        lib.removeBook(1);
        
        lib.listAllBooks();
        
        lib.seeBookByID(2);
        
        HashMap<String, Object> toSearch = new  HashMap<>();
        toSearch.put("title", "Alice in Wonderland");
        toSearch.put("year", 1865);
        toSearch.put("author", "Lewis Carroll");
        lib.searchBook(toSearch);
    }
    
       
    
}
