package injector;

import anotations.LabInject;
import exceptions.InjectException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;

public class Injector {

    private static String pathToConfiguration = "src\\main\\resources\\Configuration.json";

    public static <T> void inject(T object) throws InjectException {
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(LabInject.class)) {
                try {
                    String type = findType(field.getType().getName());
                    if (type != null) {
                        field.setAccessible(true);
                        field.set(object, Class.forName(type).newInstance());
                    } else {
                        throw new InjectException("Not find inject filed");
                    }
                } catch (Exception e) {
                    throw new InjectException(e);
                }
            }

        }
    }

    private static String findType(String type) throws IOException, ParseException {
        JSONObject obj = (JSONObject) new JSONParser().parse(new FileReader(pathToConfiguration));
        return  (String) obj.get(type);
        /*  try(FileReader reader = new FileReader(pathToConfiguration)) {
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(itype)) {
                    String[] m =  line.split("=");
                    type = m[1].replaceAll(";","");
                    type = type.replaceAll(" ","");
                }
            }
        }*/
    }
}
