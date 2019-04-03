package repository;

import org.springframework.security.core.GrantedAuthority;

public enum RoleRepository implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}