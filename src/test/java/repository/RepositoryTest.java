package repository;

import entities.Person;
import org.junit.Assert;
import org.junit.Test;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.entities.enums.Gender;


import java.math.BigDecimal;

public class RepositoryTest {

    @Test(expected = IndexOutOfBoundsException.class)
    public void get() {
        Repository<Person> repository = new Repository<>();
        repository.add(new Person());
        repository.get(1);
    }

    @Test
    public void size() {
        Repository<IPerson> repository = new Repository<>();
        repository.add(new Person());
        repository.add(new Person());
        repository.add(new Person());
        repository.add(new Person());

        Assert.assertTrue(repository.size() == 4);
    }

    @Test
    public void isEmpty() {
        Repository<IPerson> repository = new Repository<>();
        repository.add(new Person());
        Assert.assertFalse(repository.isEmpty());
    }

    @Test
    public void contains() {
        Repository<IPerson> repository = new Repository<>();
        IPerson person = new Person();
        repository.add(person);
        Assert.assertTrue(repository.contains(person));
    }

    @Test
    public void add() {
        Repository<IPerson> repository = new Repository<>();
        repository.add(0, new Person());
        IPerson person = new Person();
        person.setSalary(new BigDecimal(1000000));
        repository.add(0, person);
        Assert.assertEquals(person,repository.get(0));
    }

    @Test
    public void delete() {
        Repository<IPerson> repository = new Repository<>();
        repository.add(new Person());
        repository.add(new Person());
        repository.add(new Person());
        IPerson person = new Person();
        person.setGender(Gender.MALE);
        repository.add(3,person);
        repository.delete(3);
        Assert.assertTrue(repository.size() == 3);

    }

    @Test
    public void testAdd() {
        Repository<IPerson> repository = new Repository<>();
        repository.add(new Person());
        repository.add(new Person());
        repository.add(new Person());

        Assert.assertTrue(repository.size() == 3);
    }

    @Test
    public void testDelete() {
        Repository<IPerson> repository = new Repository<>();
        repository.add(new Person());
        repository.add(new Person());
        repository.add(new Person());
        IPerson person = new Person();
        person.setGender(Gender.MALE);
        repository.add(person);

        repository.delete(person);
        Assert.assertFalse(repository.contains(person));

    }

    @Test
    public void indexOf() {
        Repository<IPerson> repository = new Repository<>();
        repository.add(new Person());
        repository.add(new Person());
        repository.add(new Person());
        repository.add(new Person());

        IPerson person = new Person();
        person.setGender(Gender.MALE);

        Assert.assertTrue(repository.indexOf(person) == -1);

    }

    @Test
    public void set() {
        Repository<IPerson> repository = new Repository<>();
        repository.add(new Person());
        repository.add(new Person());
        repository.add(new Person());
        repository.add(new Person());
        IPerson person = new Person();
        person.setGender(Gender.MALE);
        repository.set(0,person);
        Assert.assertEquals(person,repository.get(0));
    }
}