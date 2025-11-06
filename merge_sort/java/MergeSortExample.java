import java.io.*;
import java.util.*;

public class MergeSortExample {
    
    public static void main(String[] args) {
        try {
            createTestData();
            mergeAndDisplayFiles();
            sortAndDisplayFile();
            System.out.println("Done.");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void createTestData() throws IOException {
        System.out.println("Creating test data files...");
        
        List<CustomerRecord> testFile1 = new ArrayList<>();
        testFile1.add(new CustomerRecord(1, "last-1", "first-1", 5423, "comment-1"));
        testFile1.add(new CustomerRecord(5, "last-5", "first-5", 12323, "comment-5"));
        testFile1.add(new CustomerRecord(10, "last-10", "first-10", 653, "comment-10"));
        testFile1.add(new CustomerRecord(50, "last-50", "first-50", 5050, "comment-50"));
        testFile1.add(new CustomerRecord(25, "last-25", "first-25", 7725, "comment-25"));
        testFile1.add(new CustomerRecord(75, "last-75", "first-75", 1175, "comment-75"));
        
        writeRecordsToFile(testFile1, "test-file-1.txt");
        
        List<CustomerRecord> testFile2 = new ArrayList<>();
        testFile2.add(new CustomerRecord(999, "last-999", "first-999", 1610, "comment-99"));
        testFile2.add(new CustomerRecord(3, "last-03", "first-03", 3331, "comment-03"));
        testFile2.add(new CustomerRecord(30, "last-30", "first-30", 8765, "comment-30"));
        testFile2.add(new CustomerRecord(85, "last-85", "first-85", 4567, "comment-85"));
        testFile2.add(new CustomerRecord(24, "last-24", "first-24", 247, "comment-24"));
        
        writeRecordsToFile(testFile2, "test-file-2.txt");
    }

    private static void mergeAndDisplayFiles() throws IOException {
        System.out.println("Merging and sorting files...");
        
        List<CustomerRecord> allRecords = new ArrayList<>();
        allRecords.addAll(readRecordsFromFile("test-file-1.txt"));
        allRecords.addAll(readRecordsFromFile("test-file-2.txt"));
        
        Collections.sort(allRecords, Comparator.comparingInt(CustomerRecord::getCustomerId));
        
        writeRecordsToFile(allRecords, "merge-output.txt");
        
        for (CustomerRecord record : allRecords) {
            System.out.println(record);
        }
    }

    private static void sortAndDisplayFile() throws IOException {
        System.out.println("Sorting merged file on descending contract id....");
        
        List<CustomerRecord> records = readRecordsFromFile("merge-output.txt");
        
        Collections.sort(records, 
            Comparator.comparingInt(CustomerRecord::getCustomerContractId).reversed());
        
        writeRecordsToFile(records, "sorted-contract-id.txt");
        
        for (CustomerRecord record : records) {
            System.out.println(record);
        }
    }

    private static List<CustomerRecord> readRecordsFromFile(String filename) throws IOException {
        List<CustomerRecord> records = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    records.add(CustomerRecord.fromFixedWidthString(line));
                }
            }
        }
        
        return records;
    }

    private static void writeRecordsToFile(List<CustomerRecord> records, String filename) 
            throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (CustomerRecord record : records) {
                writer.write(record.toFixedWidthString());
                writer.newLine();
            }
        }
    }
}
