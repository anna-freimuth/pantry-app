package anna.freimuth.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.HashMap;

@Service
public class PantryDeSerialization {
    @Value("${anna.freimuth.path}")
    private String path;


    public HashMap<String, LocalDate> simplePantryDeSerialization() {

        HashMap map = null;
        try {
            FileInputStream fis = new FileInputStream(path + "hashmap.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            map = (HashMap) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException e) {
            // do nothing
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
            e.printStackTrace();
        }

        return map == null ? new HashMap<>() : map;
    }
}
