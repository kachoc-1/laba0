package repository;

import entity.Person;

public interface ListPerson {
    int size();

    boolean isEmpty();

    boolean contains(Person person);

    boolean add(Person person);

    boolean remove(Person person);

    void add(int index, Person person);

    Person remove(int index);

    int indexOf(Person person);

    Person get(int index);

    Person set(int index, Person person);

    boolean equals(Object o);

    int hashCode();
}
