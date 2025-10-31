import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

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
        }
    }

    private static void createTestData() throws IOException {
        System.out.println("Creating test data files...");

        List<CustomerRecord> file1Records = Arrays.asList(
            new CustomerRecord(1, "last-1", "first-1", 5423, "comment-1"),
            new CustomerRecord(5, "last-5", "first-5", 12323, "comment-5"),
            new CustomerRecord(10, "last-10", "first-10", 653, "comment-10"),
            new CustomerRecord(50, "last-50", "first-50", 5050, "comment-50"),
            new CustomerRecord(25, "last-25", "first-25", 7725, "comment-25"),
            new CustomerRecord(75, "last-75", "first-75", 1175, "comment-75")
        );

        List<CustomerRecord> file2Records = Arrays.asList(
            new CustomerRecord(999, "last-999", "first-999", 1610, "comment-99"),
            new CustomerRecord(3, "last-03", "first-03", 3331, "comment-03"),
            new CustomerRecord(30, "last-30", "first-30", 8765, "comment-30"),
            new CustomerRecord(85, "last-85", "first-85", 4567, "comment-85"),
            new CustomerRecord(24, "last-24", "first-24", 247, "comment-24")
        );

        writeRecordsToFile(TEST_FILE_1, file1Records);
        writeRecordsToFile(TEST_FILE_2, file2Records);
    }

    private static void writeRecordsToFile(String filename, List<CustomerRecord> records) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename))) {
            for (CustomerRecord record : records) {
                writer.write(record.toString());
                writer.newLine();
            }
        }
    }

    private static List<CustomerRecord> readRecordsFromFile(String filename) throws IOException {
        return Files.lines(Paths.get(filename))
                .map(CustomerRecord::fromLine)
                .collect(Collectors.toList());
    }

    private static void mergeAndDisplayFiles() throws IOException {
        System.out.println("Merging and sorting files...");

        List<CustomerRecord> records1 = readRecordsFromFile(TEST_FILE_1);
        List<CustomerRecord> records2 = readRecordsFromFile(TEST_FILE_2);

        List<CustomerRecord> mergedRecords = new ArrayList<>();
        mergedRecords.addAll(records1);
        mergedRecords.addAll(records2);

        mergedRecords.sort(Comparator.comparingInt(CustomerRecord::getCustomerId));

        writeRecordsToFile(MERGED_FILE, mergedRecords);

        for (CustomerRecord record : mergedRecords) {
            System.out.println(record);
        }
    }

    private static void sortAndDisplayFile() throws IOException {
        System.out.println("Sorting merged file on descending contract id....");

        List<CustomerRecord> records = readRecordsFromFile(MERGED_FILE);

        records.sort(Comparator.comparingInt(CustomerRecord::getCustomerContractId).reversed());

        writeRecordsToFile(SORTED_FILE, records);

        for (CustomerRecord record : records) {
            System.out.println(record);
        }
    }
}
