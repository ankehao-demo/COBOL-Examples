import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class MergeSortExample {
    
    private static final String TEST_FILE_1 = "test-file-1.txt";
    private static final String TEST_FILE_2 = "test-file-2.txt";
    private static final String MERGED_OUTPUT = "merge-output.txt";
    private static final String SORTED_CONTRACT_OUTPUT = "sorted-contract-id.txt";

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

        List<CustomerRecord> testFile1Records = Arrays.asList(
            new CustomerRecord(1, "last-1", "first-1", 5423, "comment-1"),
            new CustomerRecord(5, "last-5", "first-5", 12323, "comment-5"),
            new CustomerRecord(10, "last-10", "first-10", 653, "comment-10"),
            new CustomerRecord(50, "last-50", "first-50", 5050, "comment-50"),
            new CustomerRecord(25, "last-25", "first-25", 7725, "comment-25"),
            new CustomerRecord(75, "last-75", "first-75", 1175, "comment-75")
        );

        List<CustomerRecord> testFile2Records = Arrays.asList(
            new CustomerRecord(999, "last-999", "first-999", 1610, "comment-99"),
            new CustomerRecord(3, "last-03", "first-03", 3331, "comment-03"),
            new CustomerRecord(30, "last-30", "first-30", 8765, "comment-30"),
            new CustomerRecord(85, "last-85", "first-85", 4567, "comment-85"),
            new CustomerRecord(24, "last-24", "first-24", 247, "comment-24")
        );

        writeRecordsToFile(TEST_FILE_1, testFile1Records);
        writeRecordsToFile(TEST_FILE_2, testFile2Records);
    }

    private static void writeRecordsToFile(String filename, List<CustomerRecord> records) 
            throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename))) {
            for (CustomerRecord record : records) {
                writer.write(record.toString());
                writer.newLine();
            }
        }
    }

    private static void mergeAndDisplayFiles() throws IOException {
        System.out.println("Merging and sorting files...");

        List<CustomerRecord> allRecords = new ArrayList<>();
        allRecords.addAll(readRecordsFromFile(TEST_FILE_1));
        allRecords.addAll(readRecordsFromFile(TEST_FILE_2));

        allRecords.sort(Comparator.comparingInt(CustomerRecord::getCustomerId));

        writeRecordsToFile(MERGED_OUTPUT, allRecords);

        for (CustomerRecord record : allRecords) {
            System.out.println(record);
        }
    }

    private static void sortAndDisplayFile() throws IOException {
        System.out.println("Sorting merged file on descending contract id....");

        List<CustomerRecord> records = readRecordsFromFile(MERGED_OUTPUT);

        records.sort(Comparator.comparingInt(CustomerRecord::getContractId).reversed());

        writeRecordsToFile(SORTED_CONTRACT_OUTPUT, records);

        for (CustomerRecord record : records) {
            System.out.println(record);
        }
    }

    private static List<CustomerRecord> readRecordsFromFile(String filename) 
            throws IOException {
        List<CustomerRecord> records = new ArrayList<>();
        
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
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
        }
        
        return records;
    }
}
