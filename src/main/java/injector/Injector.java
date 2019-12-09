package injector;

import anotations.LabInject;
import exceptions.FileConfigNotFoundException;
import exceptions.InjectException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;

public class Injector {

    private static String pathToConfiguration = "src\\main\\resources\\Configuration.json";

    public static <T> void inject(T object) {
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(LabInject.class)) {
                try {
                    String type = findType(field.getType().getName());
                    if (type != null) {
                        field.setAccessible(true);
                        field.set(object, Class.forName(type).newInstance());
                    } else {
                        throw new InjectException("Not found inject filed");
                    }
                } catch (FileNotFoundException e) {
                    throw new FileConfigNotFoundException("Configuration file not found", e);

                } catch (ClassNotFoundException e) {
                    throw new InjectException("Not found class for dependency injection", e);

                } catch (InstantiationException | IOException | ParseException | IllegalAccessException e) {
                    throw new InjectException(e);
                }

            }
        }
    }

    private static String findType(String type) throws IOException, ParseException {
        JSONObject obj = (JSONObject) new JSONParser().parse(new FileReader(pathToConfiguration));
        return (String) obj.get(type);
    }
}
