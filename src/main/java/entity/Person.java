package entity;

import org.joda.time.DateTime;

import java.util.Objects;

public class Person {
    private int id;
    private DateTime dateTime;
    private String name;
    private String surName;
    private String middleName;
    private Gender gender;
    private static int GID;

    public Person(DateTime dateTime, String name, String surName, String middleName, Gender gender) {
        this.id = GID++;
        this.dateTime = dateTime;
        this.name = name;
        this.surName = surName;
        this.middleName = middleName;
        this.gender = gender;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return DateTime.now().year().get() - dateTime.year().get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                Objects.equals(dateTime, person.dateTime) &&
                Objects.equals(name, person.name) &&
                Objects.equals(surName, person.surName) &&
                Objects.equals(middleName, person.middleName) &&
                gender == person.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateTime, name, surName, middleName, gender);
    }
}
