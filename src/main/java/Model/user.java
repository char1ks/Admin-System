package Model;

import javax.validation.constraints.NotEmpty;

public class user {
    private int id;
    @NotEmpty(message = "User name is should not be empty!")
    private String name;
    @NotEmpty(message = "User birth date is should not be empty!")
    private String birthdate;

    public user() {
    }

    public user(int id, String name, String birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}
