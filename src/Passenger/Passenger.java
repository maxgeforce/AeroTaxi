package Passenger;

import java.io.Serializable;
import java.util.ArrayList;

public class Passenger implements Serializable {
    private String name;
    private String LastName;
    private String Dni;
    private int id;
    private Integer age;

    public Passenger() {
    }

    public Passenger(String name, String lastName, String dni, Integer age) {
        this.name = name;
        LastName = lastName;
        Dni = dni;
        this.age = age;
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

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String dni) {
        Dni = dni;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Passenger id " + "-" + id + "-" + "\n" +
                "  name :    " + name + "\n" +
                "  LastName: " + LastName + "\n" +
                "  Dni:      " + Dni + "\n" +
                "  age:      " + age + "\n" + "\n" ;
    }
}
