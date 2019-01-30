package database;

import java.util.Date;

/**
 * Created by kfrak on 30.01.2019.
 */
public class Person {
    private long id;
    private String name;
    private String password;

    public Person() {
    }

    public Person(long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

