package atos.homework;
import java.util.*;

class Book {
    // creating the Book class. A book consists of title, year ans author; point 2 in the homework
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
    // creating the Library class; point 1 in the homework
    private Map<Integer, Book> booksInLibrary;
    private int numOfBooksAvailable;
    private int nextID;
    
    Library() {
        booksInLibrary = new HashMap<>();
        numOfBooksAvailable = 0;
        nextID = 0;
    }
    //method for adding a new book; piont 5 in the homework
    void addBook(String title, int year, String author) {
        Book newBook = new Book(title, year, author);
        booksInLibrary.put(nextID, newBook);
        nextID ++;
        numOfBooksAvailable ++;
    }
    // method for removing a book; point 6 in the homework
    void removeBook(Integer bookID) {
        if (!booksInLibrary.containsKey(bookID)) {
            System.out.println("The book with this ID doesn't exist.");
        }
        else if (!booksInLibrary.get(bookID).getIsLent()) {
                booksInLibrary.remove(bookID);
                numOfBooksAvailable --;
            }
        else if (booksInLibrary.get(bookID).getIsLent()) {
            System.out.println("The book with this ID is currently lent.");
            }
        else {
            System.out.println("Something went very wrong.");
        }
    }
    //method for listing all the books; point 7 in the homework
    void listAllBooks() {
        System.out.println("There are " + booksInLibrary.size() + " books in the library; " + numOfBooksAvailable + " books are available.");
        for (Map.Entry<Integer, Book> entry : booksInLibrary.entrySet()){
            Book currentBook = entry.getValue();          
            System.out.println("ID: " + entry.getKey() + 
                            ", title: " + currentBook.getTitle() +
                            ", year: " + currentBook.getYear() +
                            ", author: " + currentBook.getAuthor() +
                            (!currentBook.getIsLent() ? ", is available" : ", is not available")
                            );
        } 
        System.out.println();
    }
    // method for lending a book; point 9 in the homework
    void lendBook(Integer bookID, String whoLent) {
        if (!booksInLibrary.containsKey(bookID)) {
            System.out.println("The book with this ID doesn't exist.");
        }
        else if (!booksInLibrary.get(bookID).getIsLent()) {
                booksInLibrary.get(bookID).setIsLent(true);
                booksInLibrary.get(bookID).setWhoLent(whoLent);
                numOfBooksAvailable --;
            }
        else if (booksInLibrary.get(bookID).getIsLent()) {
            System.out.println("The book with this ID is currently lent.");
            }
        else {
            System.out.println("Something went very wrong.");
        }
    }
    //method for seeing the details of the book by ID; point 10 in the homework
    void seeBookByID(Integer bookID) {
        if (booksInLibrary.containsKey(bookID)){
            Book currentBook =  booksInLibrary.get(bookID);     
            System.out.println("ID: " + bookID + 
                            ", title: " + currentBook.getTitle() +
                            ", year: " + currentBook.getYear() +
                            ", author: " + currentBook.getAuthor() +
                            (!currentBook.getIsLent() ? ", is available" : ", is not available")
                            );
            if (currentBook.getIsLent()){
                System.out.println("Lent by " + currentBook.getWhoLent());
            }
        }
        else {
            System.out.println("The book with this ID doesn't exist.");
        }
    }
    // method for searching a book by title, year or author, or combinations e.g. year and author; point 8 in the homework 
    void searchBook(Map<String, Object> params) {
        boolean noMatchingResults = true;
        for (Map.Entry<Integer, Book> entry : booksInLibrary.entrySet()){
            boolean titleMatches = false;
            boolean yearMatches = false;
            boolean authorMatches = false;
            Book currentBook = entry.getValue();
            
            if (!params.containsKey("title")){
                titleMatches = true;
            }
            else {
                titleMatches = (currentBook.getTitle().toLowerCase().contains(params.get("title").toString().toLowerCase())); 
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
                authorMatches = (currentBook.getAuthor().toLowerCase().contains(params.get("author").toString().toLowerCase()));
            }
      
            if (titleMatches && yearMatches && authorMatches){
                System.out.println("ID: " + entry.getKey() + 
                            ", title: " + currentBook.getTitle() +
                            ", year: " + currentBook.getYear() +
                            ", author: " + currentBook.getAuthor() +
                            (!currentBook.getIsLent() ? ", is available" : ", is not available")
                            );
                if (currentBook.getIsLent()){
                    System.out.println("Lent by " + currentBook.getWhoLent());
                }
                noMatchingResults = false;
            }   
        }
        if (noMatchingResults) {
                System.out.println("No matching results.");
            }
        System.out.println();
    }
    // point 3 in missing in the instructions, method for returning a book to the library seems logical here
    void returnBook(int bookID) {
        if (!booksInLibrary.containsKey(bookID)) {
            System.out.println("The book with this ID doesn't exist.");
        }
        else if (booksInLibrary.get(bookID).getIsLent()) {
                booksInLibrary.get(bookID).setIsLent(false);
                booksInLibrary.get(bookID).setWhoLent(null);
                numOfBooksAvailable ++;
            }
        else if (!booksInLibrary.get(bookID).getIsLent()) {
            System.out.println("The book with this ID isn't currently lent.");
            }
        else {
            System.out.println("Something went very wrong.");
        }
    }
}
    
public class AtosHomework {
    public static void main(String[] args) {
        // as metioned in the instructions, no UII, just testing the methods
        
        // adding books to the library; piont 5 in the homework
        Library lib = new Library();
        lib.addBook("Alice in Wonderland", 1865, "Lewis Carroll");
        lib.addBook("The Light Fantastic", 1986, "Terry Pratchett");
        lib.addBook("The Righteous Mind", 2013, "Jonathan Haidt");
        lib.addBook("Moving Pictures", 1990, "Terry Pratchett");
        lib.addBook("Eric", 1990, "Terry Pratchett");
        lib.addBook("Men at Arms", 1993, "Terry Pratchett");
        lib.addBook("Anatomy of Love", 1993, "Helen Fisher");
        
        System.out.println("\nTesting the listAllBooksMethod:");
        lib.listAllBooks();
        
        System.out.println("\nTesting the lendBook method:");
        lib.lendBook(2, "Tom");
        lib.lendBook(4, "Alice");
        lib.lendBook(4, "Bob");
        lib.lendBook(100, "Bob");
        lib.listAllBooks();
        
        System.out.println("\nTesting the seeBookByID method:");
        lib.seeBookByID(2);
        lib.seeBookByID(0);
        lib.seeBookByID(100);
        lib.seeBookByID(4);
        
        System.out.println("\nTesting the returnBook method:"); 
        lib.seeBookByID(2);
        lib.returnBook(2);
        lib.seeBookByID(2);
        lib.returnBook(0);
        lib.returnBook(101);
        
        System.out.println("\nTesting the removeBook method:");
        lib.seeBookByID(1);
        lib.removeBook(1);
        lib.seeBookByID(1);
        lib.removeBook(4);
        lib.removeBook(1);
        lib.removeBook(101);
        
        System.out.println("\nAdding some books:");
        lib.addBook("Eric", 1990, "Terry Pratchett"); // there are two copies of this book in the library now
        lib.addBook("The Light Fantastic", 1986, "Terry Pratchett");
        lib.listAllBooks();
         
        System.out.println("\nTesting the searchBook method :");    
        HashMap<String, Object> toSearch = new HashMap<>();
        toSearch.put("title", "Alice ");
        toSearch.put("year", 1865);
        toSearch.put("author", "Lewis Carroll");
        lib.searchBook(toSearch);
        
        toSearch = new HashMap<>();
        toSearch.put("year", 1990);
        lib.searchBook(toSearch);
        
        toSearch = new HashMap<>();
        toSearch.put("year", 1993);
        lib.searchBook(toSearch);
        
        toSearch = new HashMap<>();
        toSearch.put("year", 1993);
        toSearch.put("author", "Helen");
        lib.searchBook(toSearch);
        
        toSearch = new HashMap<>();
        toSearch.put("title", "the");
        lib.searchBook(toSearch);
        
        toSearch = new HashMap<>();
        toSearch.put("title", "the");
        toSearch.put("author", "Haidt");
        lib.searchBook(toSearch);
        
        toSearch = new HashMap<>();
        toSearch.put("year", 1990);
        lib.searchBook(toSearch);
        
        toSearch = new HashMap<>();
        toSearch.put("title", "Eric");
        toSearch.put("year", 1990);
        lib.searchBook(toSearch);
        
        toSearch = new HashMap<>();
        toSearch.put("title", "Eric");
        toSearch.put("year", 2000);
        lib.searchBook(toSearch);
    }
}
