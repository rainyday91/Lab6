package src.client.model;

import java.time.LocalDate;
import java.io.Serializable;

public class Person implements Serializable {
    private String name; // не null, не пустой
    private LocalDate birthday; // не null
    private double height; // > 0
    private long weight; // > 0
    private String passportID; // не null, не пустой

    public Person(String name, LocalDate birthday, double height, long weight, String passportID) {
        this.name = name;
        this.birthday = birthday;
        this.height = height;
        this.weight = weight;
        this.passportID = passportID;
    }

    public String getName() { return name; }
    public LocalDate getBirthday() { return birthday; }
    public double getHeight() { return height; }
    public long getWeight() { return weight; }
    public String getPassportID() { return passportID; }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                ", height=" + height +
                ", weight=" + weight +
                ", passportID='" + passportID + '\'' +
                '}';
    }
}