//package src.client;
//
//import java.io.*;
//import java.nio.ByteBuffer;
//
//public class ObjectDeserializer {
//
//    public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
//        try (ByteArrayInputStream bis = new ByteArrayInputStream(data);
//             ObjectInputStream ois = new ObjectInputStream(bis)) {
//            return ois.readObject();
//        }
//    }
//}
