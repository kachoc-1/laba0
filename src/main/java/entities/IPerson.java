package entities;

import enums.Gender;
import org.joda.time.LocalDate;

import java.math.BigDecimal;
//import java.time.LocalDate;


public interface IPerson {

    Integer getId();

    void setId(Integer id);

    String getFirstName();

     void setFirstName(String firstName);

     String getLastName();

     void setLastName(String firstName);

     org.joda.time.LocalDate getBirthdate();

     void setBirthdate(LocalDate birthdate);

     Integer getAge();

     Gender getGender();

     void setGender(Gender gender);

     IDivision getDivision();

    public void setDivision(IDivision division);

    public BigDecimal getSalary();

    public void setSalary(BigDecimal salary);


}
