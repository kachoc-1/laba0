import factory.LabFactory;
import injector.Injector;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.factory.ILabFactory;
import ru.vsu.lab.repository.IRepository;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ILabFactory factory = new LabFactory();

        IRepository<IPerson> persons = factory.<IPerson>createRepository(IPerson.class);
        String path = "src\\main\\resources\\persons.txt";

        Injector.inject(persons);
        CSVReader.read(persons, path);
        persons.sortBy(Comparator.comparing(IPerson::getAge));

        List<IPerson> iPeople = persons.toList();
//1
        HashMap iPeople1 = (HashMap) iPeople.stream().collect(
                Collectors.groupingBy((p) -> p.getDivision().getName(),
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                IPerson::getSalary,
                                BigDecimal::add)));
//2
        List<IPerson> iPeople2 = iPeople.stream().filter(itm ->
                itm.getAge() > 30
                        && itm.getFirstName().toLowerCase().contains("a")
                        && itm.getSalary().compareTo(new BigDecimal(30000)) < 0).collect(Collectors.toList());
//3
        List<IPerson> iPeople3 = iPeople.stream().filter(itm -> itm.getFirstName().toLowerCase().contains("aa")).collect(Collectors.toList());
//4
        HashMap iPeople4 = (HashMap) iPeople.stream().collect(Collectors.groupingBy(
                (p) -> p.getBirthdate().getYear()
                , Collectors.counting()));
    }
}
