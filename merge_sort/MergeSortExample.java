import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSortExample {
    
    private static final String TEST_FILE_1 = "test-file-1.txt";
    private static final String TEST_FILE_2 = "test-file-2.txt";
    private static final String MERGED_FILE = "merge-output.txt";
    private static final String SORTED_FILE = "sorted-contract-id.txt";

    public static void main(String[] args) {
        createTestData();
        mergeAndDisplayFiles();
        sortAndDisplayFile();
        System.out.println("Done.");
    }

    private static void createTestData() {
        System.out.println("Creating test data files...");
        
        List<CustomerRecord> file1Records = new ArrayList<>();
        file1Records.add(new CustomerRecord(1, "last-1", "first-1", 5423, "comment-1"));
        file1Records.add(new CustomerRecord(5, "last-5", "first-5", 12323, "comment-5"));
        file1Records.add(new CustomerRecord(10, "last-10", "first-10", 653, "comment-10"));
        file1Records.add(new CustomerRecord(50, "last-50", "first-50", 5050, "comment-50"));
        file1Records.add(new CustomerRecord(25, "last-25", "first-25", 7725, "comment-25"));
        file1Records.add(new CustomerRecord(75, "last-75", "first-75", 1175, "comment-75"));
        
        List<CustomerRecord> file2Records = new ArrayList<>();
        file2Records.add(new CustomerRecord(999, "last-999", "first-999", 1610, "comment-99"));
        file2Records.add(new CustomerRecord(3, "last-03", "first-03", 3331, "comment-03"));
        file2Records.add(new CustomerRecord(30, "last-30", "first-30", 8765, "comment-30"));
        file2Records.add(new CustomerRecord(85, "last-85", "first-85", 4567, "comment-85"));
        file2Records.add(new CustomerRecord(24, "last-24", "first-24", 247, "comment-24"));
        
        writeRecordsToFile(file1Records, TEST_FILE_1);
        writeRecordsToFile(file2Records, TEST_FILE_2);
    }

    private static void mergeAndDisplayFiles() {
        System.out.println("Merging and sorting files...");
        
        List<CustomerRecord> allRecords = new ArrayList<>();
        allRecords.addAll(readRecordsFromFile(TEST_FILE_1));
        allRecords.addAll(readRecordsFromFile(TEST_FILE_2));
        
        allRecords.sort(Comparator.comparingInt(CustomerRecord::getCustomerId));
        
        writeRecordsToFile(allRecords, MERGED_FILE);
        
        for (CustomerRecord record : allRecords) {
            System.out.println(record);
        }
    }

    private static void sortAndDisplayFile() {
        System.out.println("Sorting merged file on descending contract id....");
        
        List<CustomerRecord> records = readRecordsFromFile(MERGED_FILE);
        
        records.sort(Comparator.comparingInt(CustomerRecord::getContractId).reversed());
        
        writeRecordsToFile(records, SORTED_FILE);
        
        for (CustomerRecord record : records) {
            System.out.println(record);
        }
    }

    private static void writeRecordsToFile(List<CustomerRecord> records, String filename) {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(filename), StandardCharsets.UTF_8))) {
            for (CustomerRecord record : records) {
                writer.write(record.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file " + filename + ": " + e.getMessage());
            System.exit(1);
        }
    }

    private static List<CustomerRecord> readRecordsFromFile(String filename) {
        List<CustomerRecord> records = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
            for (String line : lines) {
                if (line.length() >= 135) {
                    int customerId = Integer.parseInt(line.substring(0, 5).trim());
                    String lastName = line.substring(5, 55);
                    String firstName = line.substring(55, 105);
                    int contractId = Integer.parseInt(line.substring(105, 110).trim());
                    String comment = line.substring(110, 135);
                    
                    records.add(new CustomerRecord(customerId, lastName, firstName, 
                                                   contractId, comment));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file " + filename + ": " + e.getMessage());
            System.exit(1);
        }
        return records;
    }
}
