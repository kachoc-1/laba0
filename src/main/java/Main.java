
import entity.*;
import org.joda.time.DateTime;
import repository.*;


public class Main {
    public static void main(String[] args) {
        ListPerson persons = new ArrayPersons();
        for (int i = 0; i < 20; i++) {
            persons.add(new Person(DateTime.now(),"Илья" + i,"Akulov" + i,"--",Gender.MAN));
        }

        for (int i = 0; i < persons.size(); i++) {
            System.out.println(persons.get(i).getName() + " ");
        }

        persons.remove(5);
        for (int i = 0; i < persons.size(); i++) {
            System.out.println(persons.get(i).getName() + " ");
        }
        System.out.println(persons.indexOf(persons.get(5)));
    }

}
