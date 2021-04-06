package anna.freimuth.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.HashMap;

@Service
public class PantrySerialization {

    @Value("${anna.freimuth.path}")
    private String path;

    public void pantrySerialization(HashMap<String, LocalDate> map) {
        try {
            FileOutputStream fos = new FileOutputStream(path + "hashmap.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(map);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
