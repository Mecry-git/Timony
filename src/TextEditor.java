import java.io.*;
import java.math.BigInteger;

public class TextEditor {
    public static StringBuilder output = new StringBuilder();

    public static void main(String[] args) {
        readFile();
        writeFile(output.toString());
    }

    public static void readFile() {
        String pathname = "input.txt";
        try (FileReader reader = new FileReader(pathname);
             BufferedReader br = new BufferedReader(reader)
        ) {
            String line = br.readLine();
            while (line != null) {
                throllow(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String lastNum = "";
    public static void throllow(String line) {
        int a = line.length();
        if ((a & 1) == 1) {
            if (lastNum.length() == 0) {
                lastNum = line.substring(line.length() - 1);
                line = line.substring(0, line.length() - 1);
            } else if (lastNum.length() == 1) {
                line += lastNum;
            } else System.exit(-5);
        }
        //line has been traded
        output.append("\n\n").append(line).append("\n");

        BigInteger bigintSN = new BigInteger("23", 16);
        int sn = bigintSN.intValue();
        BigInteger bigintNSN = new BigInteger("0", 16);
        int nsn = bigintNSN.intValue();

        StringBuilder pb = new StringBuilder();
        for (int i = 0; i < line.length(); i += 2) {
            Integer value = Integer.parseInt(line.substring(i, i + 2));
            if (value == 20)
                value = value + sn - nsn;
            pb.append(value);
        }
        output.append("sn(hex) = ").append(Integer.toHexString(sn)).append(":\n");
        output.append(pb).append("\n");
        System.out.println(convertHexToString(pb.toString()));
        output.append(convertHexToString(pb.toString())).append("\n");
        output.append(convertStringToHex("Hello")).append("\n");
    }

    public static String convertStringToHex(String str){

        char[] chars = str.toCharArray();

        StringBuilder hex = new StringBuilder();
        for (char aChar : chars) {
            hex.append(Integer.toHexString((int) aChar));
        }

        return hex.toString();
    }
    public static String convertHexToString(String hex){

        StringBuilder sb = new StringBuilder();
        //StringBuilder temp = new StringBuilder();

        //49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for( int i=0; i<hex.length()-1; i+=2 ){

            //grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            //convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            //convert the decimal to character
            sb.append((char)decimal);

            //temp.append(decimal);
        }

        return sb.toString();
    }

    public static void writeFile(String fp) {
        System.out.println("Writing file...");
        try {
            File writeName = new File("output.txt");
            writeName.createNewFile();
            try (FileWriter writer = new FileWriter(writeName);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                out.write(fp);
                out.flush();
            }
        } catch (IOException e) {
            System.out.println("Writing file false!");
            e.printStackTrace();
        }
        System.out.println("Completed!");
    }
}
