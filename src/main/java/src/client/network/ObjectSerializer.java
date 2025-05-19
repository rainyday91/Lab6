package src.client.network;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public class ObjectSerializer {
    public static byte[] serialize(Object obj) throws Exception {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(obj);
            return bos.toByteArray();
        }
    }
}