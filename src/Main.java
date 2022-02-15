import java.io.*;

public class Main {
    public static StringBuilder input = new StringBuilder();
    public static StringBuilder out = new StringBuilder();
    public static StringBuilder output = new StringBuilder();
    private static final int ln = 80;


    public static void main(String[] args) {
        readFile();
        decryptInput();
        decryptOut();
        writeFile(output.toString());
    }

    private static void readFile() {
        String pathname = "input.txt";
        try (FileReader reader = new FileReader(pathname);
             BufferedReader br = new BufferedReader(reader)
        ) {
            String line = br.readLine();
            while (line != null) {
                input.append(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final int SN = 23;
    private static void decryptInput() {
        int value;
        for (int i = 0; i < input.length(); i += 2) {
            value = Integer.parseInt(input.substring(i, i + 2));
            if (value == 20)
                out.append(" ");
            else {
                value = value + SN;
                out.append(decToAscii("" + value));
            }
        }
    }
    private static void decryptOut() {
        char outT, pun;
        for (int i = 0; i < out.length(); i ++) {
            outT = out.charAt(i);
            if (outT == '%') {
                pun = (char) Integer.parseInt(out.substring(i + 1, i + 3), 16);
                if (pun == ':') {
                    String pw = input.substring(input.length() - out.length() + i + 2, input.length());
                    output.append(": ").append(pw);

                    output = makeLineFeed(output.toString());
                    break;
                } else {
                    output.append(pun);
                    i += 2;
                }
            } else output.append(outT);
        }
    }

    private static String decToAscii(String decStr) {
        StringBuilder output = new StringBuilder();

        String hexStr = Integer.toHexString(Integer.parseInt(decStr));

        for (int i = 0; i < hexStr.length(); i += 2) {
            String str = hexStr.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }

        return output.toString();
    }

    private static StringBuilder makeLineFeed(String s) {
        String[] str = s.split(" ");
        StringBuilder buffer = new StringBuilder();
        int len = 0;
        for (String value : str) {
            len += value.length();
            if (len > ln) {
                buffer.append("\n").append(value).append(" ");
                len = value.length() + 1;
            } else {
                buffer.append(value).append(" ");
                len++;
            }
        }
        return buffer;
    }

    private static void writeFile(String fp) {
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
    }
}
