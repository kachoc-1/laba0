package entities;

import ru.vsu.lab.entities.IDivision;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.entities.enums.Gender;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Person implements IPerson {
    private Integer id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private BigDecimal salary;
    private LocalDate birthdate;
    private IDivision division;


    public Person(Integer id,String firstName, String lastName,
                  Gender gender,LocalDate birthdate,IDivision division ,BigDecimal salary ) {
        this.salary = salary;
        this.birthdate = birthdate;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.division = division;
        
    }

    public Person() {
    }

    @Override
    public LocalDate getBirthdate() {
        return birthdate;
    }

    @Override
    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public Gender getGender() {
        return gender;
    }

    @Override
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Возвращает возраст
     * @return
     */
    @Override
    public Integer getAge() {
        return LocalDate.now().getYear() - birthdate.getYear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                gender == person.gender &&
                Objects.equals(salary, person.salary) &&
                Objects.equals(birthdate, person.birthdate) &&
                Objects.equals(division, person.division);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, gender, salary, birthdate, division);
    }

    @Override
    public BigDecimal getSalary() {
        return salary;
    }

    @Override
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public Division getDivision() {
        return (Division) division;
    }

    @Override
    public void setDivision(IDivision division) {
        this.division = division;
    }
}
