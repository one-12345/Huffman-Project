import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class huffmanLookup {

    public HashMap<Character, String> buildTable(File tableFile) throws FileNotFoundException{
        HashMap<Character, String> table = new HashMap();
        Scanner scanner = new Scanner(tableFile);

        while(scanner.hasNextLine()){
           char nextChar = scanner.next().toCharArray()[0];
           String byteString = scanner.next();
           table.put(nextChar, byteString);
        }
        return table;
    }
 public static String messageToBits(String s, HashMap table) {
        String out = "";
        for(int i = 0; i< s.length(); i++) {
            int c = s.charAt(i)-65;
            String plus = table.get(c).toString();
            out +=plus;
        }
        try(FileWriter fw = new FileWriter("messageToBits.txt")){
            fw.write(out);
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return out;

    }
}
