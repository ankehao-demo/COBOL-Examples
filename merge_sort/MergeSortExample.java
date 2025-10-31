import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class MergeSortExample {
    
    private static final String TEST_FILE_1 = "test-file-1.txt";
    private static final String TEST_FILE_2 = "test-file-2.txt";
    private static final String MERGED_FILE = "merge-output.txt";
    private static final String SORTED_FILE = "sorted-contract-id.txt";

    public static void main(String[] args) {
        try {
            createTestData();
            mergeAndDisplayFiles();
            sortAndDisplayFile();
            System.out.println("Done.");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
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
                writer.write(record.toFileString());
                writer.newLine();
            }
        }
    }

    private static List<CustomerRecord> readRecordsFromFile(String filename) throws IOException {
        List<CustomerRecord> records = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    records.add(CustomerRecord.fromFileString(line));
                }
            }
        }
        return records;
    }

    private static void mergeAndDisplayFiles() throws IOException {
        System.out.println("Merging and sorting files...");

        List<CustomerRecord> file1Records = readRecordsFromFile(TEST_FILE_1);
        List<CustomerRecord> file2Records = readRecordsFromFile(TEST_FILE_2);

        List<CustomerRecord> mergedRecords = new ArrayList<>();
        mergedRecords.addAll(file1Records);
        mergedRecords.addAll(file2Records);

        mergedRecords.sort(Comparator.comparingInt(CustomerRecord::getCustomerId));

        writeRecordsToFile(MERGED_FILE, mergedRecords);

        for (CustomerRecord record : mergedRecords) {
            System.out.println(record);
        }
    }

    private static void sortAndDisplayFile() throws IOException {
        System.out.println("Sorting merged file on descending contract id....");

        List<CustomerRecord> mergedRecords = readRecordsFromFile(MERGED_FILE);

        mergedRecords.sort(Comparator.comparingInt(CustomerRecord::getContractId).reversed());

        writeRecordsToFile(SORTED_FILE, mergedRecords);

        for (CustomerRecord record : mergedRecords) {
            System.out.println(record);
        }
    }
}
