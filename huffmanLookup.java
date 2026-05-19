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
