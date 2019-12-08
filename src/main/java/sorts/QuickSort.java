package sorts;

import sorts.ISorter.ISorter;

import java.util.Comparator;

public class QuickSort implements ISorter {
    @Override
    public <T> void sort(T[] elements,int size, Comparator<T> comparator) {
        quickSort(elements,comparator,0,size - 1);
    }
    /**
     * Сортирует массив быстрой сортировкой по передаваемому компаратору
     *
     * @param elements    сортируемый массив
     * @param comparator к
     * @param low        левая граница массива
     * @param high       правая граница массива
     */
    private <T> void quickSort(T[] elements, Comparator<T> comparator, int low, int high) {
        if (elements.length == 0)
            return;//завершить выполнение если длина массива равна 0

        if (low >= high)
            return;//завершить выполнение если уже нечего делить

        // выбрать опорный элемент
        int middle = low + (high - low) / 2;
        T opora = elements[middle];

        // разделить на подмассивы, который больше и меньше опорного элемента
        int i = low, j = high;
        while (i <= j) {
            while (comparator.compare(elements[i], opora) < 0) {
                i++;
            }

            while (comparator.compare(elements[j], opora) > 0) {
                j--;
            }

            if (i <= j) {//меняем местами
                T temp = elements[i];
                elements[i] = elements[j];
                elements[j] = temp;
                i++;
                j--;
            }
        }

        // вызов рекурсии для сортировки левой и правой части
        if (low < j)
            quickSort(elements, comparator, low, j);

        if (high > i)
            quickSort(elements, comparator, i, high);
    }

}
