import java.io.*;
import java.util.*;

public class MergeSortExample {
    
    public static void main(String[] args) {
        System.out.println("Creating test data files...");
        createTestData();
        
        System.out.println("Merging and sorting files...");
        mergeAndDisplayFiles();
        
        System.out.println("Sorting merged file on descending contract id....");
        sortAndDisplayFile();
        
        System.out.println("Done.");
    }
    
    private static void createTestData() {
        List<CustomerRecord> file1Records = new ArrayList<>();
        file1Records.add(new CustomerRecord(1, "last-1", "first-1", 5423, "comment-1"));
        file1Records.add(new CustomerRecord(5, "last-5", "first-5", 12323, "comment-5"));
        file1Records.add(new CustomerRecord(10, "last-10", "first-10", 653, "comment-10"));
        file1Records.add(new CustomerRecord(50, "last-50", "first-50", 5050, "comment-50"));
        file1Records.add(new CustomerRecord(25, "last-25", "first-25", 7725, "comment-25"));
        file1Records.add(new CustomerRecord(75, "last-75", "first-75", 1175, "comment-75"));
        
        writeRecordsToFile("test-file-1.txt", file1Records);
        
        List<CustomerRecord> file2Records = new ArrayList<>();
        file2Records.add(new CustomerRecord(999, "last-999", "first-999", 1610, "comment-99"));
        file2Records.add(new CustomerRecord(3, "last-03", "first-03", 3331, "comment-03"));
        file2Records.add(new CustomerRecord(30, "last-30", "first-30", 8765, "comment-30"));
        file2Records.add(new CustomerRecord(85, "last-85", "first-85", 4567, "comment-85"));
        file2Records.add(new CustomerRecord(24, "last-24", "first-24", 247, "comment-24"));
        
        writeRecordsToFile("test-file-2.txt", file2Records);
    }
    
    private static void mergeAndDisplayFiles() {
        List<CustomerRecord> allRecords = new ArrayList<>();
        
        allRecords.addAll(readRecordsFromFile("test-file-1.txt"));
        allRecords.addAll(readRecordsFromFile("test-file-2.txt"));
        
        allRecords.sort(Comparator.comparingInt(CustomerRecord::getCustomerId));
        
        writeRecordsToFile("merge-output.txt", allRecords);
        
        for (CustomerRecord record : allRecords) {
            System.out.println(record);
        }
    }
    
    private static void sortAndDisplayFile() {
        List<CustomerRecord> records = readRecordsFromFile("merge-output.txt");
        
        records.sort(Comparator.comparingInt(CustomerRecord::getCustomerContractId).reversed());
        
        writeRecordsToFile("sorted-contract-id.txt", records);
        
        for (CustomerRecord record : records) {
            System.out.println(record);
        }
    }
    
    private static List<CustomerRecord> readRecordsFromFile(String filename) {
        List<CustomerRecord> records = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    records.add(CustomerRecord.fromFixedWidthString(line));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file " + filename + ": " + e.getMessage());
            System.exit(1);
        }
        
        return records;
    }
    
    private static void writeRecordsToFile(String filename, List<CustomerRecord> records) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (CustomerRecord record : records) {
                writer.println(record.toFixedWidthString());
            }
        } catch (IOException e) {
            System.err.println("Error writing file " + filename + ": " + e.getMessage());
            System.exit(1);
        }
    }
}
