import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
public class huffmanLookup {

    public static void main(String[] args) throws FileNotFoundException{
        File file = new File(args[0]);
        stringToBytes(messageToBits("Beyond syntax, efficiency is key. Standard dictionary lookups run in O(1) average time complexity because Python utilizes a highly optimized hash table under the hood. This makes dictionary operations incredibly fast, even when scaling to millions of entries. By mastering these built-in tools and understanding their performance benefits, you can write Pythonic code that is both highly efficient and exceptionally easy for other developers to read, maintain, and debug over time.", buildTable(file)));
    }

    public static HashMap<String, String> buildTable(File tableFile) throws FileNotFoundException{
        HashMap<String, String> table = new HashMap<String, String>();
        Scanner scanner = new Scanner(tableFile);

        while(scanner.hasNextLine()){
            String[] nextLine = scanner.nextLine().split(" ");
            if(nextLine.length == 3){
                table.put(" ", nextLine[2]);
            }else{
                table.put(nextLine[0], nextLine[1]);
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
            if(table.containsKey(c)){
                String plus = table.get(c);
                out += plus;
            }
        }

        try(FileWriter fw = new FileWriter("messageToBits.txt")){
            fw.write(out);
            System.out.println("Compression complete.");
        }

        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return out;

    }


    public static void stringToBytes(String input) throws FileNotFoundException{
        OutputStream outputStream = new FileOutputStream("messageToBits.bin");

        char[] array = input.toCharArray();
        BitSet bits = new BitSet(array.length);
        for(int i = 0; i < array.length; i++){  
            if(array[i] == '1'){
                bits.set(i);
            }else{
                bits.clear(i);
            }
        }

        try {
            outputStream.write(bits.toByteArray());
            System.out.println("Binary file created.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
