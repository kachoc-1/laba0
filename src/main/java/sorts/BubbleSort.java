package sorts;

import sorts.ISorter.ISorter;

import java.util.Comparator;

public class BubbleSort implements ISorter {
    @Override
    public <T> void sort(T[] elements,int size, Comparator<T> comparator) {
        bubbleSort(elements,size,comparator);
    }

    /**
     * @param elements    сортируемый массив
     * @param comparator к
     *                   Сортирует Пузырьком по передаваемому компаратору
     */
    private <T> void bubbleSort(T[] elements,int size, Comparator<T> comparator) {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (comparator.compare(elements[j], elements[j + 1]) > 0) {
                    T temp = elements[j];
                    elements[j] = elements[j + 1];
                    elements[j + 1] = temp;
                }
            }
        }
    }
}
