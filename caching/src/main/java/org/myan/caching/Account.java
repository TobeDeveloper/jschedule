package org.myan.caching;

/**
 * Created by myan on 11/20/2017.
 * Intellij IDEA
 */
public class Account {
    private int id;
    private String name;

    public Account(String name) {
        this.name = name;
    }

    public Account(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
