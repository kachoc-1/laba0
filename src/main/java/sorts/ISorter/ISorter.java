package sorts.ISorter;

import java.util.Comparator;

public interface ISorter {
    <T> void sort(T[] elements, int size, Comparator<T> comparator);
}
