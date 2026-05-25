import java.util.*;
public class Decompressor {
    
    public static String decompress(String bytesFilePath, String lookupFilePath) {

        //Creates lookup table using HashMap
        Scanner sc1 = new Scanner(lookupFilePath);
        Map<String,String> lookupTable = new HashMap<>();
        while (sc1.hasNext()) {
            // String[] line = sc1.nextLine().split(",");
            // lookupTable.put(line[0],line[1]);
            char[] line = sc1.nextLine().toCharArray();
            String key = String.valueOf(line[0]);
            String value = "";
            for (int i = 2; i < line.length; i++) {
                value+=line[i];
            }
            lookupTable.put(key,value);
        }
        sc1.close();

        //Creates bitString out of the binary file
        Scanner sc2 = new Scanner(bytesFilePath);
        StringBuilder bitString = new StringBuilder();
        while (sc2.hasNext()) {
            bitString.append(sc2.nextInt());
            break;
        }
        sc2.close(); 

        //Decodes bitString
        StringBuilder decoded = new StringBuilder(); 
        String currentBits = ""; 
        for (int i = 0; i < bitString.length(); i++) {
            currentBits += bitString.charAt(i);  
            if (lookupTable.containsKey(currentBits)) {
                decoded.append(lookupTable.get(currentBits));
                currentBits = "";
            }
        }
        
        return decoded.toString();
    }
}
