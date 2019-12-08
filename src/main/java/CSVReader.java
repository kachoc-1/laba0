import factory.LabFactory;
import ru.vsu.lab.entities.IDivision;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.entities.enums.Gender;
import ru.vsu.lab.factory.ILabFactory;
import ru.vsu.lab.repository.IRepository;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;

public class CSVReader {
    private static final int ZERO = 0, ONE = 1, TWO = 2, TREE = 3, FOUR = 4, FIVE = 5;

    public static void read(IRepository<IPerson> repository, String path) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        HashMap<String, IDivision> divisionHashMap = new HashMap<>();
        ILabFactory factory = new LabFactory();

        try (FileReader reader = new FileReader(path)) {
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                String[] mass = s.split(";");
                String key = mass[FOUR];
                IDivision division = divisionHashMap.get(key);
                if (division == null) {
                    division = factory.createDivision();
                    division.setName(key);
                    divisionHashMap.put(key, division);
                }
                IPerson person = factory.createPerson();

                person.setId(Integer.valueOf(mass[ZERO]));
                person.setFirstName(mass[ONE]);
                person.setLastName("");
                person.setGender(Gender.valueOf(mass[TWO].toUpperCase()));
                person.setBirthdate(LocalDate.parse(mass[TREE], formatter));
                person.setDivision(division);
                person.setSalary(new BigDecimal(mass[FIVE]));

                repository.add(person);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
