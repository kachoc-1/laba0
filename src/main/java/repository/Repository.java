package repository;

import anotations.LabInject;
import ru.vsu.lab.repository.IRepository;
import sorts.ISorter.ISorter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Repository<T> implements IRepository<T> {

    /**
     * Массив данных
     */
    private T[] elements;
    /**
     * Текущий размер
     */
    private int size;
    /**
     * Размер по умолчанию
     */
    private final int defaultCapacity = 10;
    /**
     * Класс для сортировки массива
     */
    @LabInject
    private ISorter sorter;

    public Repository(int initialCapacity) {
        if (initialCapacity > 0)
            elements = (T[]) new Object[defaultCapacity];
        else
            throw new IllegalArgumentException("Illegal Capacity: "
                    + initialCapacity);
    }

    public Repository() {
        elements = (T[]) new Object[defaultCapacity];
    }

    /**
     * Возвращает размер коллекции
     */
    public int size() {
        return size;
    }

    /**
     * Выбрасывает исключение если индес вне границ массива
     *
     * @param index индекс элемента
     * @throws IndexOutOfBoundsException )
     */
    private void rangeCheck(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size);
    }

    /**
     * Выбрасывает исключение при добавлении элемента по индексу
     * если индес вне границ массива
     *
     * @param index индекс
     * @throws IndexOutOfBoundsException искл
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
        if (minCapacity > elements.length)
            grow();
    }

    /**
     * Пересоздает внуттренний массив с новым размером
     *
     * @param newCapacity новый максимальный размер коллекции
     */
    private void copyOf(int newCapacity) {
        T[] oldElementPerson = elements;
        elements = (T[])new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            elements[i] = oldElementPerson[i];
        }
    }

    /**
     * Расширяет коллекцию в 1.5 раза
     */
    private void grow() {
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity + (oldCapacity / 2 + 1);
        copyOf(newCapacity);
    }

    /**
     * С определенного индеска сдвигает каждый элементв массива влево
     *
     * @param index индекс
     */
    private void shiftLeftArray(int index) {
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
    }

    /**
     * С определенного индеска сдвигает каждый элементв массива Вправо
     *
     * @param index индекс
     */
    private void shiftRightArray(int index) {
        for (int i = size - 1; i > index; i--) {
            elements[i] = elements[i - 1];
        }
    }

    /**
     * Проверяет коллекцию на пустоту
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Проверяет принадлежность element коллекции
     *
     * @param element
     * @return true если element принадлежит коллекции
     */
    public boolean contains(T element) {
        return indexOf(element) >= 0;
    }

    /**
     * Добавялет элмент в коллекцию
     *
     * @param element добавялемый элемент
     */
    @Override
    public void add(T element) {
        ensureCapacityInternal(size + 1);
        elements[size++] = element;
    }

    /**
     * Удаляет элемент из коллекции
     *
     * @param element удаляемый элемент
     * @return true если элемент принадлежал коллекции
     */
    public boolean delete(T element) {
        int index = indexOf(element);
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
     * @param element добавляемый элемент
     */
    @Override
    public void add(int index, T element) {
        rangeCheckForAdd(index);
        ensureCapacityInternal(size + 1);
        shiftRightArray(index);
        elements[index] = element;
        size++;

    }

    /**
     * Удалаяет элемент по индексу
     *
     * @param index индекс удаляемого элемента
     * @return удаляемый элемент
     */
    @Override
    public T delete(int index) {
        rangeCheck(index);
        T value = elements[index];
        shiftLeftArray(index);
        elements[--size] = null;
        return value;
    }

    /**
     * Возвращает индекс элемента
     *
     * @param element искомый элемент
     * @return индекс элемента если не найдет то -1
     */
    public int indexOf(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null)
                    return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i]))
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
    public T get(int index) {
        rangeCheck(index);

        return elements[index];
    }

    /**
     * Заменет элемент с индексом index на element
     *
     * @param index  индекс
     * @param element добавялемый элемент
     * @return добавленный элемент
     */
    @Override
    public T set(int index, T element) {
        rangeCheck(index);
        T oldValue = elements[index];
        elements[index] = element;
        return oldValue;
    }

    /**
     * Преобразует коллекцию в List<T>
     */
    @Override
    public List<T> toList() {
        T[] returnElements = (T[]) new Object[size];
        for (int i = 0; i < size; i++) {
            returnElements[i] = elements[i];
        }
        return Arrays.asList(returnElements);
    }

    @Override
    public void sortBy(Comparator<T> comparator) {
        sorter.sort(elements,size,comparator);
    }

    /**
     * Возвращает репозиторий объектов удовлетворябщих условию
     */
    @Override
    public IRepository<T> searchBy(Predicate<T> condition) {
        Repository repository = new Repository();
        for (int i = 0; i < size; i++) {
            if (condition.test(elements[i])) {
                repository.add(elements[i]);
            }
        }
        return repository;
    }


}

