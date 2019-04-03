package repository;

import model.Role;
import model.User;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Thinking of Java:
 * Zalecane rzutowanie kontenera na typ w górę (stosowanie ogólniejszego interfejsu)
 * w celu zostawienia furtki dla ewentualnej przyszłej zmiany implementacji, np.:
 * List<Apple> a = new ArrayList<Apple>(); możliwa zamiana na:
 * List<Apple> a = new LinkedList<Apple>();
 */

@Repository
public class UserRepository {

    private Map<String, User> users = defineUserInRepository();

    @PostConstruct
    private Map<String, User> defineUserInRepository() {
        Map<String, User> usersMap = new HashMap<>();
        usersMap.put("user", new User("User", "user", ));
        usersMap.put("admin", new User("Admin", "admin", ));
        return usersMap;
    }


    public User findByUsername(String username) {
        return users.get(username);
    }
}
