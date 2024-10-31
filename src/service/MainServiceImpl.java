package service;

import model.Book;
import model.Role;
import model.User;
import repository.BookRepository;
import repository.UserRepository;
import utils.MyList;
import utils.PersonValidator;

public class MainServiceImpl implements MainService {

    private final BookRepository repositoryBook;
    private final UserRepository repositoryUser;
    private User activeUser;


    public MainServiceImpl(BookRepository repositoryBook, UserRepository repositoryUser) {
        this.repositoryBook = repositoryBook;
        this.repositoryUser = repositoryUser;
    }

    @Override
    public void addBook(String nameBook, String authorBook, int idBook) {
        if (activeUser == null || activeUser.getRole() != Role.ADMIN) {
            System.out.println("Добавление новой книги невозможно.");
            return;
        }
        if (nameBook == null || authorBook == null || idBook <= 0) {
            System.out.println("Добавление книги невозможно");
            return;
        }

        Book book = new Book(idBook, authorBook, nameBook);
        // todo нужна база данных
        //books.add(book);
    }

    @Override
    public MyList<Book> getAllBooks() {
        return repositoryBook.getAllBook();
    }

    // todo
    @Override
    public MyList<Book> getBooksByName(String nameBook) {
        if (nameBook == null || nameBook.isEmpty()) {
            System.out.println("Ошибка: название книги не может быть пустым.\"");
            return null;
        }
        return repositoryBook.getBooksByName(nameBook);
    }

    @Override
    public MyList<Book> getBooksByAuthor(String authorName) {
        if (authorName == null || authorName.isEmpty()){
            System.out.println("Ошибка: имя автора не может быть пустым.");
            return null;
        }
        return repositoryBook.getBooksByAuthor(authorName);

    }

    @Override
    public MyList<Book> getFreeBooks() {
        return repositoryBook.getFreeBooks;
    }

    @Override
    public MyList<Book> getAllBooksInUser() {
        return repositoryBook.getAllBooksInUser;
    }


    @Override
    public boolean updateEditBook(String nameBook, String authorName, int idBook) {
        if (activeUser == null || activeUser.getRole() != Role.ADMIN){
            System.out.println("Изменить книгу может только администратор");
            return false;
        }
        // todo
        Book book = repositoryBook.getById(idBook);
        if (book == null|| idBook <= 0) return  false;
        book.setSeries(idBook); // todo переписать

        book.repositoryBook.getBookByName(nameBook);
        if (nameBook == null || nameBook.isEmpty()){
//            return false;
//        } else (book.getById() != idBook){
//            return  false;
        }

        book.setTitle(nameBook);

        book.repositoryBook.getBookByAuthorName(authorName);
        if (authorName == null || authorName.isEmpty()) return false;
        book.setAuthor(authorName);
        return  true;

    }

    @Override
    public boolean takeBook(int idBook) {
        if (activeUser == null){
            System.out.println("Выполните вход в систему.");
            return false;
        }

        Book book = repositoryBook.getById(idBook);
        if (book == null) return false;
        if (!book.isBusy()){
            System.out.println("Книга уже занята.");
            return false;
        }
        book.setBusy(false);
        System.out.println("Книга успешно взята.");
        return true;
    }

    @Override
    public boolean returnBook(int idBook) {
        if (activeUser == null){
            System.out.println("Выполните вход в систему.");
            return false;
        }

        Book book = repositoryBook.getById(idBook);
        if (book == null) return false;
        if (book.isBusy()){
            System.out.println("Книга уже свободна.");
            return false;
        }
        book.setBusy(true);
        System.out.println("Книга успешно возвращена.");
        return true;
    }

    @Override
    public Book deleteBook(int idBook) {
        if (activeUser == null || activeUser.getRole() != Role.ADMIN){
            System.out.println("Доступно только Администратору");
            return null;
        }
        Book book = repositoryBook.getById(idBook);
        if (book == null) return null;

        repositoryBook.deletBook(book);
        return book;

    }

    @Override
    public User registerUser(String email, String password) {
        if (!PersonValidator.isEmailValid(email)){
            System.out.println("Email не прошел проверку!");
            return null;
        }

        if (!PersonValidator.isPasswordValid(password)){
            System.out.println("Password не прошел проверку!");
            return null;
        }

        if (repositoryUser.isPasswordValid(password)){
            System.out.println("Email уже используется!");
            return null;
        }

        User user = repositoryUser.addUser(email,password);
        return user;
    }

    @Override
    public boolean loginUser(String email, String password) {
        User user = repositoryUser.getUserByEmail(email);
        if (user == null){
            System.out.println("Неверный email или password.");
            return false;
        }
        if (!user.getPassword().equals(password)){
            System.out.println("Неверный email или password.");
            return false;
        }

        activeUser = user;
        return true;
    }

    @Override
    public void logout() {
        activeUser = null;
    }
}
