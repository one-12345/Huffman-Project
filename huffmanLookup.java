import java.util.*;
public class huffmanLookup {
    public static String messageToBits(String s, HashMap table) {
        String out = "";

        for(int i = 0; i< s.length(); i++) {
            int c = s.charAt(i)-65;
            String plus = table.get(c).toString();
            out +=plus;
        }
        return out;
    }
}
