package service;

import model.Book;
import model.User;
import utils.MyList;

public interface MainService {

    //Creat
    void addBook (String nameBook, String authorBook, int idBook);

    //Read
    MyList<Book> getAllBooks();

    MyList<Book> getBooksByName (String nameBook);

    MyList<Book> getBooksByAuthor (String authorName);

    MyList<Book> getFreeBooks();

    MyList<Book> getAllBooksInUser();


    //Update

    boolean updateEditBook(String nameBook, String authorName, int idBook);

    boolean takeBook(int idBook);

    boolean returnBook(int idBook);

    //Delet

    Book deleteBook (int idBook);

    User registerUser(String email, String password);
    boolean loginUser (String email, String password);
    void logout();








}
