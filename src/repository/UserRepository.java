package repository;

import model.User;
import model.Book;
import utils.MyList;

public interface UserRepository {
    User addUser(String email, String name);

    boolean isEmailExists(String email);

    User getUserByEmail(String email);


}
