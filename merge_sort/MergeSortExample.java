import java.io.*;
import java.util.*;

public class MergeSortExample {

    private static final String TEST_FILE_1 = "test-file-1.txt";
    private static final String TEST_FILE_2 = "test-file-2.txt";
    private static final String MERGE_OUTPUT = "merge-output.txt";
    private static final String SORTED_OUTPUT = "sorted-contract-id.txt";

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
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (CustomerRecord record : records) {
                writer.println(record.toString());
            }
        }
    }

    private static List<CustomerRecord> readRecordsFromFile(String filename) throws IOException {
        List<CustomerRecord> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(parseRecord(line));
            }
        }
        return records;
    }

    private static CustomerRecord parseRecord(String line) {
        int customerId = Integer.parseInt(line.substring(0, 5).trim());
        String lastName = line.substring(5, 55);
        String firstName = line.substring(55, 105);
        int contractId = Integer.parseInt(line.substring(105, 110).trim());
        String comment = line.substring(110, 135);

        return new CustomerRecord(customerId, lastName, firstName, contractId, comment);
    }

    private static void mergeAndDisplayFiles() throws IOException {
        System.out.println("Merging and sorting files...");

        List<CustomerRecord> file1Records = readRecordsFromFile(TEST_FILE_1);
        List<CustomerRecord> file2Records = readRecordsFromFile(TEST_FILE_2);

        List<CustomerRecord> mergedRecords = new ArrayList<>();
        mergedRecords.addAll(file1Records);
        mergedRecords.addAll(file2Records);

        Collections.sort(mergedRecords, Comparator.comparingInt(CustomerRecord::getCustomerId));

        writeRecordsToFile(MERGE_OUTPUT, mergedRecords);

        for (CustomerRecord record : mergedRecords) {
            System.out.println(record);
        }
    }

    private static void sortAndDisplayFile() throws IOException {
        System.out.println("Sorting merged file on descending contract id....");

        List<CustomerRecord> mergedRecords = readRecordsFromFile(MERGE_OUTPUT);

        Collections.sort(mergedRecords, Comparator.comparingInt(CustomerRecord::getContractId).reversed());

        writeRecordsToFile(SORTED_OUTPUT, mergedRecords);

        for (CustomerRecord record : mergedRecords) {
            System.out.println(record);
        }
    }
}
