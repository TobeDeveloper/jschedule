package org.myan.jschedule.web.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by myan on 11/17/2017.
 * Intellij IDEA
 */
public class UserEntity {
    private int id;
    private String name;
    private String email;
    private String password;
    private Date lastLogin;

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
}
