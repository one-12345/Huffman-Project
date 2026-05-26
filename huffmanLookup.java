import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class huffmanLookup {

    public static void main(String[] args) throws FileNotFoundException{
        File file = new File(args[0]);
        messageToBits("hello csc590", buildTable(file));
    }

    public static HashMap<String, String> buildTable(File tableFile) throws FileNotFoundException{
        HashMap<String, String> table = new HashMap<String, String>();
        Scanner scanner = new Scanner(tableFile);

        while(scanner.hasNextLine()){
            String[] nextLine = scanner.nextLine().split(" ");
            if(nextLine.length == 3){
                table.put(" ", nextLine[2]);
                System.out.println(" , " + nextLine[2]);
            }else{
                table.put(nextLine[0], nextLine[1]);
                System.out.println(nextLine[0] + ", " + nextLine[1]);
            }
            
        }
        scanner.close();
        return table;
    }
 public static String messageToBits(String s, HashMap<String, String> table) {
        String out = "";
        for(int i = 0; i< s.length(); i++) {
            String c = "";
            c += s.charAt(i);
            if(c == "E"){
                if(s.charAt(i+1) == 'O' && s.charAt(i) == 'F'){
                    return out;
                }
            }
            System.out.println(c);
            if(table.containsKey(c)){
                String plus = table.get(c);
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
