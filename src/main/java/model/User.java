package model;

import java.util.Set;

public class User {

    private String username;
    private String password;
    private Set<Role> userRoles;

    public User(String username, String password, Set<Role> userRoles) {
        this.username = username;
        this.password = password;
        this.userRoles = userRoles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<Role> userRoles) {
        this.userRoles = userRoles;
    }
}
