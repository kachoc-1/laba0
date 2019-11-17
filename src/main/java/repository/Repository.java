package repository;

import entities.IPerson;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Repository implements IRepository {

    private IPerson[] elementPerson;
    private int size;
    private final int defaultCapacity = 10;

    public Repository(int initialCapacity) {
        if (initialCapacity > 0)
            elementPerson = new IPerson[initialCapacity];
        else
            throw new IllegalArgumentException("Illegal Capacity: "
                    + initialCapacity);
    }

    public Repository() {
        elementPerson = new IPerson[defaultCapacity];
    }

    /**
     * Возвращает размер коллекции
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Выбрасывает исключение если индес вне границ массива
     *
     * @param index
     * @throws IndexOutOfBoundsException
     */
    private void rangeCheck(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size);
    }

    /**
     * Выбрасывает исключение при добавлении элемента по индексу
     * если индес вне границ массива
     *
     * @param index
     * @throws IndexOutOfBoundsException
     */
    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size);
    }

    /**
     * Расширяет коллекцию
     *
     * @param minCapacity заполненность внутреннего массива
     */
    private void ensureCapacityInternal(int minCapacity) {
        if (minCapacity > elementPerson.length)
            grow();
    }

    /**
     * Пересоздает внуттренний массив с новым размером
     *
     * @param newCapacity новый максимальный размер коллекции
     */
    private void copyOf(int newCapacity) {
        IPerson[] oldElementPerson = elementPerson;
        elementPerson = new IPerson[newCapacity];
        for (int i = 0; i < size; i++) {
            elementPerson[i] = oldElementPerson[i];
        }
    }

    /**
     * Расширяет коллекцию в 1.5 раза
     */
    private void grow() {
        int oldCapacity = elementPerson.length;
        int newCapacity = oldCapacity + (oldCapacity / 2 + 1);
        copyOf(newCapacity);
    }

    /**
     * С определенного индеска сдвигает каждый элементв массива влево
     *
     * @param index
     */
    private void shiftLeftArray(int index) {
        for (int i = index; i < size - 1; i++) {
            elementPerson[i] = elementPerson[i + 1];
        }
    }

    /**
     * С определенного индеска сдвигает каждый элементв массива Вправо
     *
     * @param index
     */
    private void shiftRightArray(int index) {
        for (int i = size - 1; i > index; i--) {
            elementPerson[i] = elementPerson[i - 1];
        }
    }

    /**
     * Проверяет коллекцию на пустоту
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Проверяет принадлежность person коллекции
     *
     * @param person
     * @return true если person принадлежит коллекции
     */
    @Override
    public boolean contains(IPerson person) {
        return indexOf(person) >= 0;
    }

    /**
     * Добавялет элмент в коллекцию
     *
     * @param person добавялемый элемент
     * @return true если добвление возможно
     */
    @Override
    public boolean add(IPerson person) {
        ensureCapacityInternal(size + 1);
        elementPerson[size++] = person;
        return true;
    }

    /**
     * Удаляет элемент из коллекции
     *
     * @param person удаляемый элемент
     * @return true если элемент принадлежал коллекции
     */
    @Override
    public boolean delete(IPerson person) {
        int index = indexOf(person);
        if (index >= 0) {
            shiftLeftArray(index);
            size--;
            return true;
        }
        return false;
    }

    /**
     * Добавялет элемент по определенному индексу
     *
     * @param index  индекс добавялемого элемента
     * @param person добавляемый элемент
     */
    @Override
    public void add(int index, IPerson person) {
        rangeCheckForAdd(index);
        ensureCapacityInternal(size + 1);
        shiftRightArray(index);
        size++;

    }

    /**
     * Удалаяет элемент по индексу
     *
     * @param index индекс удаляемого элемента
     * @return удаляемый элемент
     */
    @Override
    public IPerson delete(int index) {
        rangeCheck(index);
        IPerson value = elementPerson[index];
        shiftLeftArray(index);
        elementPerson[--size] = null;
        return value;
    }

    /**
     * Возвращает индекс элемента
     *
     * @param person
     * @return индекс элемента если не найдет то -1
     */
    @Override
    public int indexOf(IPerson person) {
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

    /**
     * Получает элемент по индексу
     *
     * @param index индекс получаемого элемента
     * @return элемент с индексом index
     */
    @Override
    public IPerson get(int index) {
        rangeCheck(index);

        return elementPerson[index];
    }

    /**
     * Заменет элемент с индексом index на person
     *
     * @param index  индекс
     * @param person добавялемый элемент
     * @return добавленный элемент
     */
    @Override
    public IPerson set(int index, IPerson person) {
        rangeCheck(index);
        IPerson oldValue = elementPerson[index];
        elementPerson[index] = person;
        return oldValue;
    }

    /**
     * Преобразует коллекцию в List<IPerson>
     */
    @Override
    public List<IPerson> toList() {
        return Arrays.asList(elementPerson);
    }


    /**
     * Сортирует по передаваемому компаратору
     */
    @Override
    public void sortBy(Comparator<IPerson> comparator) {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (comparator.compare(elementPerson[j], elementPerson[j + 1]) > 0) {
                    IPerson temp = elementPerson[j];
                    elementPerson[j] = elementPerson[j + 1];
                    elementPerson[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Возвращает репозиторий объектов удовлетворябщих условию
     */
    @Override
    public IRepository searchBy(Predicate<IPerson> condition) {
        Repository repository = new Repository();
        for (int i = 0; i < size; i++) {
            if (condition.test(elementPerson[i])) {
                repository.add(elementPerson[i]);
            }
        }
        return repository;
    }
}
