package repository;

import model.Role;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Repository
public class UserRepository {

    @Autowired
    RoleRepository roleRepository;

    private Map<String, User> users = defineUserInRepository();

    @PostConstruct
    private Map<String, User> defineUserInRepository() {
        Map<String, User> usersMap = new HashMap<>();
        usersMap.put("user", new User("User", "user", roleRepository.userRoles));
        usersMap.put("admin", new User("Admin", "admin", roleRepository.adminRoles));
        return usersMap;
    }


    public User findByUsername(String username) {
        return users.get(username);
    }
}
