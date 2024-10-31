package repository;

import model.Book;
import utils.MyArrayList;
import utils.MyList;

import java.util.concurrent.atomic.AtomicInteger;

public class BookRepositoryImpl implements BookRepository {

    private final MyList<Book> books;
    private final AtomicInteger currentId = new AtomicInteger(1);

    public BookRepositoryImpl() {
        this.books = new MyArrayList<>();
        addBooks();
    }

    private void addBooks(){
        books.addAll(
                new Book(currentId.getAndIncrement(),"Александр Пушкин","Евгений Онегин"),
                new Book(currentId.getAndIncrement(),"Юрий Лермонтов","Бородино"),
                new Book(currentId.getAndIncrement(),"Лев Толстой","Война и мир"),
                new Book(currentId.getAndIncrement(),"Михаил Булгаков","Собачье сердце"),
                new Book(currentId.getAndIncrement(),"Марина Цветаева","Сборник стихов"),
                new Book(currentId.getAndIncrement(),"Джейми Оливер","Сборник лучших блюд английской кухни"),
                new Book(currentId.getAndIncrement(),"Алексей Толстой","Петр 1"),
                new Book(currentId.getAndIncrement(),"Михаил Шолохов","Тихий Дон")
        );
    }

    @Override
    public void addBook(String author, String name) {
        Book book=new Book(currentId.getAndIncrement(),author,name);
        books.add(book);
    }

    @Override
    public MyList<Book> getAllBooks() {
        return books;
    }

    @Override
    public Book getById(int id) {
        for (Book book : books) {
            if (book.getIdBook() == id) return book;
        }
        return null;
    }

    @Override
    public MyList<Book> getByName(String name) {
        MyList<Book> result = new MyArrayList<>();
        for (Book book : books) {
            if (book.getNameBook().contains(name)) result.add(book);
        }
        return result;
    }

    @Override
    public MyList<Book> getByAuthor(String author) {
        MyList<Book> result = new MyArrayList<>();
        for (Book book : books) {
            if (book.getAuthorBook().contains(author)) result.add(book);
        }
        return result;
    }

    @Override
    public MyList<Book> getFreeBooks() {
        MyList<Book> result = new MyArrayList<>();
        
        for (Book book : books) {
            if (!book.isBusy()) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public MyList<Book> getBusyBooks() {
        MyList<Book> result = new MyArrayList<>();

        for (Book book : books) {
            if (book.isBusy()) result.add(book);
        }
        return result;
    }

    @Override
    public void deleteBook(Book book) {
        books.remove(book);
    }
}
