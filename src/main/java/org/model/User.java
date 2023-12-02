package org.model;

public class User {
    private int userId;
    private String name;
    private int age;
    private boolean graduated;

    public User(){}
    public User(int userId, String name, int age, boolean graduated) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.graduated = graduated;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public boolean getGraduated(){
        return graduated;
    }
    public void setGraduated(boolean graduated) {
        this.graduated = graduated;
    }

    public boolean equals(User user) {
        if(user == null) return false;
        return userId==user.userId &&
                name.equals(user.name) &&
                age==user.age &&
                graduated==user.graduated;
    }
}
