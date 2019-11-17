package repository;

import entities.IPerson;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public interface IRepository {
    int size();

    boolean isEmpty();

    boolean contains(IPerson person);

    boolean add(IPerson person);

    boolean delete(IPerson person);

    void add(int index, IPerson person);

    IPerson delete (int index);

    int indexOf(IPerson person);

    IPerson get(int index);

    IPerson set(int index, IPerson person);

    public List<IPerson> toList();

    //Should not use toList method
    public void sortBy(Comparator<IPerson> comparator );

    //Should not use toList method
    public IRepository searchBy(Predicate<IPerson> condition);

    boolean equals(Object o);

    int hashCode();
}
