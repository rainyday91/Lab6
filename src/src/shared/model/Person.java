package src.shared.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class Person implements Serializable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private LocalDate birthday; //Поле не может быть null
    private double height; //Значение поля должно быть больше 0
    private long weight; //Значение поля должно быть больше 0
    private String passportID; //Поле не может быть null
    @Serial
    private static final long serialVersionUID = 1L;
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

    public String getName() {
        return name;
    }

    public double getHeight() {
        return height;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public long getWeight() {
        return weight;
    }

    public String getPassportID() {
        return passportID;
    }

    public Person(String name, LocalDate birthday, double height, long weight, String passportID) {
        this.name = name;
        this.birthday = birthday;
        this.height = height;
        this.weight = weight;
        this.passportID = passportID;
    }
}