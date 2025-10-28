import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MergeSortExample {

    public static void main(String[] args) {
        try {
            System.out.println("Creating test data files...");
            createTestData();
            
            System.out.println("Merging and sorting files...");
            mergeAndDisplayFiles();
            
            System.out.println("Sorting merged file on descending contract id....");
            sortAndDisplayFile();
            
            System.out.println("Done.");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void createTestData() throws IOException {
        try (BufferedWriter writer1 = new BufferedWriter(new FileWriter("test-file-1.txt"))) {
            writer1.write(new CustomerRecord(1, "last-1", "first-1", 5423, "comment-1").toFixedWidthString());
            writer1.newLine();
            
            writer1.write(new CustomerRecord(5, "last-5", "first-5", 12323, "comment-5").toFixedWidthString());
            writer1.newLine();
            
            writer1.write(new CustomerRecord(10, "last-10", "first-10", 653, "comment-10").toFixedWidthString());
            writer1.newLine();
            
            writer1.write(new CustomerRecord(50, "last-50", "first-50", 5050, "comment-50").toFixedWidthString());
            writer1.newLine();
            
            writer1.write(new CustomerRecord(25, "last-25", "first-25", 7725, "comment-25").toFixedWidthString());
            writer1.newLine();
            
            writer1.write(new CustomerRecord(75, "last-75", "first-75", 1175, "comment-75").toFixedWidthString());
            writer1.newLine();
        }

        try (BufferedWriter writer2 = new BufferedWriter(new FileWriter("test-file-2.txt"))) {
            writer2.write(new CustomerRecord(999, "last-999", "first-999", 1610, "comment-99").toFixedWidthString());
            writer2.newLine();
            
            writer2.write(new CustomerRecord(3, "last-03", "first-03", 3331, "comment-03").toFixedWidthString());
            writer2.newLine();
            
            writer2.write(new CustomerRecord(30, "last-30", "first-30", 8765, "comment-30").toFixedWidthString());
            writer2.newLine();
            
            writer2.write(new CustomerRecord(85, "last-85", "first-85", 4567, "comment-85").toFixedWidthString());
            writer2.newLine();
            
            writer2.write(new CustomerRecord(24, "last-24", "first-24", 247, "comment-24").toFixedWidthString());
            writer2.newLine();
        }
    }

    private static void mergeAndDisplayFiles() throws IOException {
        List<CustomerRecord> records = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader("test-file-1.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(CustomerRecord.fromFixedWidthString(line));
            }
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader("test-file-2.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(CustomerRecord.fromFixedWidthString(line));
            }
        }
        
        Collections.sort(records, Comparator.comparing(CustomerRecord::getCustomerId));
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("merge-output.txt"))) {
            for (CustomerRecord record : records) {
                writer.write(record.toFixedWidthString());
                writer.newLine();
                System.out.println(record.toFixedWidthString());
            }
        }
    }

    private static void sortAndDisplayFile() throws IOException {
        List<CustomerRecord> records = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader("merge-output.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(CustomerRecord.fromFixedWidthString(line));
            }
        }
        
        Collections.sort(records, Comparator.comparing(CustomerRecord::getContractId).reversed());
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("sorted-contract-id.txt"))) {
            for (CustomerRecord record : records) {
                writer.write(record.toFixedWidthString());
                writer.newLine();
                System.out.println(record.toFixedWidthString());
            }
        }
    }
}
