import java.util.*;
import java.io.*;
import java.nio.file.*;
public class Decompressor {

    public static void main(String[] Args) {
        System.out.println(decompress("messageToBits.txt","encode_input.txt"));
    }
    
    public static String decompress(String bytesFilePath, String lookupFilePath) {

        //Creates lookup table using HashMap
        Scanner sc1 = new Scanner(lookupFilePath);
        Map<String,String> lookupTable = new HashMap<>();
        while (sc1.hasNext()) {
            // String[] line = sc1.nextLine().split(",");
            // lookupTable.put(line[0],line[1]);
            char[] line = sc1.nextLine().toCharArray();
            String key = "";
            int ind = 0;
            if (line[0] == ' ') {
                key = " ";
                ind = 1;
            }
            else {
                while (line[ind] != ' ' && ind < line.length - 1) {
                    key+=line[ind];
                    ind++;
                }
            }
            String value = "";
            for (int i = ind+1; i < line.length; i++) {
                value+=line[i];
            }
            lookupTable.put(key,value);
        }
        sc1.close();

        //Creates bitString out of the binary
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(Paths.get(lookupFilePath));
        } catch (IOException e) { e.printStackTrace(); }

        //Decodes bitString
        StringBuilder decoded = new StringBuilder(); 
        byte current = 0;
        for (int i = 0; i < bytes.length; i++) {
            for (int j = 0; j < 8; j++) {
                current = (byte) (current << 1);
                if ((bytes[i] << j) == 1) {
                    current = (byte) (current | 1);
                }
            }
            if (lookupTable.containsKey(current)) {
                decoded.append(lookupTable.get(current));
                current = 0;
            }
        }
        return decoded.toString();
    }
}
