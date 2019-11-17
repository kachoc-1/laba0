import entities.Division;
import entities.IPerson;
import entities.Person;
import enums.Gender;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import repository.IRepository;
import repository.Repository;

import java.io.FileReader;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy");
        IRepository persons = new Repository();
        try (FileReader reader = new FileReader("C:\\Users\\braty\\laba0\\src\\main\\resources\\persons.txt")) {
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                String[] mass = s.split(";");

                persons.add(new Person(new Integer(mass[0]), mass[1], " ",
                        Gender.valueOf(mass[2]), LocalDate.parse(mass[3], formatter),
                        new Division(mass[4]), new BigDecimal(mass[5])));
            }

        } catch (Exception e) {
            System.out.println("Ex " + e.getMessage());
        }
        persons.sortBy(Comparator.comparingInt(IPerson::getAge));
        System.out.println(persons.get(1).getAge());
        System.out.println(persons.get(persons.size() - 1).getAge());

        IRepository repository = persons.searchBy(person -> person.getAge() == 19);
        System.out.println(repository.size());
        System.out.println(repository.get(0));

    }

}
