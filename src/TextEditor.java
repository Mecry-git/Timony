import java.io.*;

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
                sThread(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String lastNum = "";
    public static void sThread(String line) {
        int a = line.length();
        if ((a & 1) == 1) {
            if (lastNum.length() == 0) {
                lastNum = line.substring(line.length() - 1);
                line = line.substring(0, line.length() - 1);
            } else if (lastNum.length() == 1) {
                line += lastNum;
            } else System.exit(-5);
        }
        //line has been tided

        int sn = 23;

        int value;
        for (int i = 0; i < line.length(); i += 2) {
            value = Integer.parseInt(line.substring(i, i + 2));
            if (value == 20)
                output.append(" ");
            else {
                value = value + sn;
                output.append(decToStr("" + value));
            }
        }
        output.append("\n");
    }

    public static String decToStr(String ascii) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ascii.length() - 1; i += 2) {
            String h = ascii.substring(i, (i + 2));
            int decimal = Integer.parseInt(h, 10);
            sb.append((char) decimal);
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
