package repository;

import entity.Person;

public class ArrayPersons implements ListPerson {

    private Person[] elementPerson;
    private int size;

    private final int defaultCapacity = 10;

    public ArrayPersons(int initialCapacity) {
        if (initialCapacity > 0)
            elementPerson = new Person[initialCapacity];
        else
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
    }

    public ArrayPersons() {
        elementPerson = new Person[defaultCapacity];
    }

    public int size() {
        return size;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size);
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size);
    }

    private void ensureCapacityInternal(int minCapacity) {
        if (minCapacity > elementPerson.length)
            grow();
    }

    private void copyOf(int newCapacity) {
        Person[] oldElementPerson = elementPerson;
        elementPerson = new Person[newCapacity];
        for (int i = 0; i < size; i++) {
            elementPerson[i] = oldElementPerson[i];
        }
    }

    private void grow() {
        int oldCapacity = elementPerson.length;
        int newCapacity = oldCapacity + (oldCapacity / 2 + 1);
        copyOf(newCapacity);
    }

    private void shiftLeftArray(int index) {
        for (int i = index; i < size - 1; i++) {
            elementPerson[i] = elementPerson[i + 1];
        }
    }

    private void shiftRightArray(int index) {
        for (int i = size - 1; i > index; i--) {
            elementPerson[i] = elementPerson[i - 1];
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Person person) {
        return indexOf(person) >= 0;
    }

    public boolean add(Person person) {
        ensureCapacityInternal(size + 1);
        elementPerson[size++] = person;
        return true;
    }

    public boolean remove(Person person) {
        int index = indexOf(person);
        if (index >= 0) {
            shiftLeftArray(index);
            size--;
            return true;
        }
        return false;
    }

    public void add(int index, Person person) {
        rangeCheckForAdd(index);
        ensureCapacityInternal(size + 1);
        shiftRightArray(index);
        size++;

    }

    public Person remove(int index) {
        rangeCheck(index);
        Person value = elementPerson[index];
        shiftLeftArray(index);
        elementPerson[--size] = null;
        return value;
    }

    public int indexOf(Person person) {
        if (person == null) {
            for (int i = 0; i < size; i++) {
                if (elementPerson[i] == null)
                    return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (person.equals(elementPerson[i]))
                    return i;
            }
        }
        return -1;

    }

    public Person get(int index) {
        rangeCheck(index);

        return elementPerson[index];
    }

    public Person set(int index, Person person) {
        rangeCheck(index);
        Person oldValue = elementPerson[index];
        elementPerson[index] = person;
        return oldValue;
    }
}
