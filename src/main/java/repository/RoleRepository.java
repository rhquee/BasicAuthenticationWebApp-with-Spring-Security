package repository;

import model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Repository
public class RoleRepository{

    private Set<String> adminRoles = defineAdminRoles();
    private Set<String> userRoles = defineUserRoles();

    private Set<String> defineAdminRoles() {
        Set<String> definedAdminRoles = new HashSet<>();
        definedAdminRoles.add("ADMIN_ROLE");
        return definedAdminRoles;
    }

    @PostConstruct
    private Set<String> defineUserRoles() {
        Set<String> defineUserRoles = new HashSet<>();
        defineUserRoles.add("USER_ROLE");
        return defineUserRoles;
    }

}