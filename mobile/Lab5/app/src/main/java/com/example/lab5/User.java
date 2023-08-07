package com.example.lab5;

public class User {
    private String _id, username, passwd, email, fullname;

    public User( String username, String passwd, String email, String fullname) {
        this._id = _id;
        this.username = username;
        this.passwd = passwd;
        this.email = email;
        this.fullname = fullname;
    }

    public User() {
    }
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "User{" +
                "_id='" + _id + '\'' +
                ", username='" + username + '\'' +
                ", passwd='" + passwd + '\'' +
                ", email='" + email + '\'' +
                ", fullname='" + fullname + '\'' +
                '}';
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }


}
