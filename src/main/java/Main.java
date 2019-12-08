import exceptions.InjectException;
import factory.LabFactory;
import injector.Injector;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.entities.enums.Gender;
import ru.vsu.lab.factory.ILabFactory;
import ru.vsu.lab.repository.IRepository;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ILabFactory factory = new LabFactory();

        IRepository<IPerson> persons =  factory.<IPerson>createRepository(IPerson.class);
        String path = "src\\main\\resources\\persons.txt";
        try {
            Injector.inject(persons);

            CSVReader.read(persons, path);
//            persons.sortBy(Comparator.comparing(IPerson::getAge,Comparator.nullsLast(Comparator.naturalOrder())));
            persons.sortBy(Comparator.comparing(IPerson::getAge));

            System.out.println(persons.get(0).getAge());

            IRepository<IPerson> repository = persons.searchBy(person -> person.getGender() == Gender.MALE);
            System.out.println(repository.get(0).getGender());

            List<IPerson> iPeople = persons.toList();
//1
         //   HashMap<String,BigDecimal> iPeople1 = iPeople.stream().filter(Comparator.naturalOrder().)
//2
            List<IPerson> iPeople2 = iPeople.stream().filter(itm ->
                    itm.getAge() > 30
                            && itm.getFirstName().toLowerCase().contains("a")
                            && itm.getSalary().compareTo(new BigDecimal(30000)) < 0).collect(Collectors.toList());
            System.out.println(iPeople2.size());
//
////3
//            List<IPerson>iPeople3 = iPeople.stream().filter(itm->
//                    itm.getFirstName().toLowerCase().matches(".*aa.*")).collect(Collectors.toList());
        } catch (InjectException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
