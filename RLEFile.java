import java.io.*;

public class RLEFile {
    // Compress method
    public static String compress(String input) {
        StringBuilder compressed = new StringBuilder();
        int i = 0;
        while (i < input.length()) {
            char current = input.charAt(i);
            int count = 1;
            while (i + 1 < input.length() && input.charAt(i + 1) == current) {
                count++;
                i++;
            }
            compressed.append(current).append(count);
            i++;
        }
        return compressed.toString();
    }

    // Decompress method
    public static String decompress(String input) {
        StringBuilder decompressed = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            i++;
            StringBuilder num = new StringBuilder();
            while (i < input.length() && Character.isDigit(input.charAt(i))) {
                num.append(input.charAt(i));
                i++;
            }
            i--;
            int count = Integer.parseInt(num.toString());
            for (int j = 0; j < count; j++) {
                decompressed.append(c);
            }
        }
        return decompressed.toString();
    }

    public static void main(String[] args) {
        try {
            // Read input.txt
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            StringBuilder data = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line);
            }
            reader.close();

            // Compress
            String compressed = compress(data.toString());

            // Write compressed.txt
            BufferedWriter writer = new BufferedWriter(new FileWriter("compressed.txt"));
            writer.write(compressed);
            writer.close();

            // Decompress
            String decompressed = decompress(compressed);

            // Write decompressed.txt
            BufferedWriter writer2 = new BufferedWriter(new FileWriter("decompressed.txt"));
            writer2.write(decompressed);
            writer2.close();

            System.out.println("Compression and Decompression complete!");
            System.out.println("Check compressed.txt and decompressed.txt");

        } catch (FileNotFoundException e) {
            System.out.println("Error: input.txt not found! Please create it first.");
        } catch (IOException e) {
            System.out.println("Error reading/writing file: " + e.getMessage());
        }
    }
}

