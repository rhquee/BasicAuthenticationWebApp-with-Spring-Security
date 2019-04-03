package repository;

import model.Role;
import model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Repository
public class RoleRepository{

    public Set<Role> adminRoles = defineAdminRoles();
    public Set<Role> userRoles = defineUserRoles();

    private Set<Role> defineAdminRoles() {
        Set<Role> defineUserRoles = new HashSet<>();
        defineUserRoles.add(new Role("ADMIN_ROLE"));
        return adminRoles;
    }


    private Set<Role> defineUserRoles() {
        Set<Role> defineUserRoles = new HashSet<>();
        defineUserRoles.add(new Role("USER_ROLE"));
        return defineUserRoles;
    }

}