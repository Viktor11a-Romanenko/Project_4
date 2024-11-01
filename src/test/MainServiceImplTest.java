package test;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.BookRepository;
import repository.BookRepositoryImpl;
import repository.UserRepository;
import repository.UserRepositoryImpl;
import service.MainServiceImpl;
import service.MainService;

import model.Book;
import utils.MyList;
import static org.junit.jupiter.api.Assertions.*;


class MainServiceImplTest {

    BookRepository br;
    UserRepository ur;
    MainService service;
    User activeUser;

    @BeforeEach
    void setUp() {
        br = new BookRepositoryImpl();
        ur =  new UserRepositoryImpl();
        service = new MainServiceImpl(br, ur);
        activeUser = new User("admin@gmail.com", "3");
        activeUser.setRole(Role.ADMIN);
    }

    @Test
    void addBook() {
        String nameBook = "Test Book";
        String authorBook = "Test Author";
        Book addedBook = service.addBook(nameBook, authorBook);

        assertEquals(nameBook, addedBook.getNameBook());
        assertEquals(authorBook, addedBook.getAuthorBook());
    }

    @Test
    void getAllBooks() {
        MyList<Book> allBooks = service.getAllBooks();
        assertNotNull(allBooks);
        assertEquals(8, allBooks.size());
    }

    @Test
    void getByName() {
        String name = "Война";
        MyList<Book> books = service.getByName(name);
        assertNotNull(books);
        assertEquals(1, books.size()); // Only "Война и мир"
        assertEquals("Лев Толстой", books.get(0).getAuthorBook());
        assertEquals("Война и мир", books.get(0).getNameBook());
        assertTrue(books.get(0).getNameBook().contains(name));
    }

    @Test
    void getByName_NonExistantName() {

        String name = "Карлсон";
        MyList<Book> books = service.getByName(name);
        assertNotNull(books);
        assertTrue(books.isEmpty());
    }

    @Test
    void getByAuthor_existingAuthor() {
        String authorBook = "Лев Толстой";
        MyList<Book> books = service.getByAuthor(authorBook);
        assertNotNull(books);
        assertEquals(1, books.size()); // Only "Война и мир"
        assertTrue(books.get(0).getAuthorBook().contains(authorBook));
    }

    @Test
    void getByAuthor_NonExistingAuthor() {

        String authorBook = "Дейл Карнеги";
        MyList<Book> books = service.getByAuthor(authorBook);
        assertNotNull(books);
        assertTrue(books.isEmpty());
    }

    @Test
    void getFreeBooks_OneBusy() {
        MyList<Book> allBooks = service.getAllBooks();
        if (allBooks.size() > 0) {
            allBooks.get(0).setBusy(true);
        }

        MyList<Book> freeBooks = service.getFreeBooks();
        assertNotNull(freeBooks);
        assertEquals(7, freeBooks.size());
    }

    @Test
    void getBusyBooks() {

        MyList<Book> allBooks = service.getAllBooks();
        if (allBooks.size() > 1) {
            allBooks.get(0).setBusy(true);
            allBooks.get(1).setBusy(true);
        }

        MyList<Book> busyBooks = service.getBusyBooks();
        assertNotNull(busyBooks);
        assertEquals(2, busyBooks.size());

    }

    @Test
    void updateEditBook() {
        String newBookAuthor = "Сергей Есенин Test";
        String newBookName = "СтихиUpdateEditBookTest";
        service.addBook(newBookName, newBookAuthor, 81);

        MyList<Book> books = service.getByAuthor(newBookName);
        assertEquals(1, books.size());

        Book addedBook = books.get(0);
        assertEquals(newBookAuthor, addedBook.getAuthorBook());
        assertEquals(newBookName, addedBook.getNameBook());

        String updatedBookName = "Странное новое имя киниги для теста";
        String updatedBookAuthor = "Группа 4 пишет проект";

        service.updateEditBook(updatedBookName, updatedBookAuthor, 81);


        books = service.getByAuthor(updatedBookName);
        assertEquals(1, books.size());

        Book updatedBook = books.get(0);
        assertEquals(newBookAuthor, updatedBook.getAuthorBook());
        assertEquals(newBookName, updatedBook.getNameBook());

    }

    @Test
    void takeBook() {
    }

    @Test
    void returnBook() {
    }

    @Test
    void deleteBook() {

        String nameBookToDelete =  "Сборник стихов";
        service.loginUser("1", "1");
        service.deleteBook(5);
        assertEquals(7, service.getAllBooks().size()); // На одну меньше
        assertEquals(0, service.getByName(nameBookToDelete).size());
    }

    @Test
    void registerUser() {
    }

    @Test
    void loginUser() {
        assertTrue(service.loginUser("1", "1"));
    }

    @Test
    void logout() {

    }
}