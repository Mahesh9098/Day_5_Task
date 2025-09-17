import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;

public class RobustFileProcessing {
    public static void main(String[] args) {
        String fileName = "C:\\Users\\91626\\IdeaProjects\\gujjar\\file_writer\\src\\product.csv";
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(",");
                    if (parts.length != 2) {
                        throw new InvalidProductDataException("Invalid row format: " + line);
                    }

                    String productName = parts[0].trim();
                    double productPrice = Double.parseDouble(parts[1].trim());

                    System.out.println("Product: " + productName + ", Price: " + productPrice);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid price in row: " + line);
                } catch (InvalidProductDataException e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("The file " + fileName + " was not found.");
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                    System.out.println("File closed successfully.");
                }
            } catch (IOException e) {
                System.err.println("Error closing the file.");
            }
        }
    }
}
