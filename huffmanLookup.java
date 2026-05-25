import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class huffmanLookup {

    public static void main(String[] args) throws FileNotFoundException{
        File file = new File(args[0]);
        messageToBits("a b c d e f g h i j k l m n o", buildTable(file));
    }

    public static HashMap<Character, String> buildTable(File tableFile) throws FileNotFoundException{
        HashMap<Character, String> table = new HashMap<Character, String>();
        Scanner scanner = new Scanner(tableFile);

        while(scanner.hasNextLine()){
           char nextChar = scanner.next().toCharArray()[0];
           String byteString = scanner.next();
           table.put(nextChar, byteString);
           System.out.println(nextChar + ", " + byteString);
        }
        scanner.close();
        return table;
    }
 public static String messageToBits(String s, HashMap<Character, String> table) {
        String out = "";
        for(int i = 0; i< s.length(); i++) {
            int c = s.charAt(i);
            if(!table.containsKey((char)c)){
                out += 11100;
            }else{
                System.out.println(c);
                String plus = table.get((char)c);
                out += plus;
                System.out.println(plus);
                System.out.println(out);
            }
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
