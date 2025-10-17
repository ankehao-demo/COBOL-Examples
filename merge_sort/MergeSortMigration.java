import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MergeSortMigration {

    private static final String TEST_FILE_1 = "test-file-1.txt";
    private static final String TEST_FILE_2 = "test-file-2.txt";
    private static final String MERGE_OUTPUT = "merge-output.txt";
    private static final String SORTED_CONTRACT_ID = "sorted-contract-id.txt";

    public static void main(String[] args) {
        try {
            createTestData();
            mergeFiles();
            sortMergedFile();
            System.out.println("Done.");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void createTestData() throws IOException {
        System.out.println("Creating test data files...");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILE_1))) {
            writer.write(new CustomerRecord(1, "last-1", "first-1", 5423, "comment-1").toFixedWidthLine());
            writer.newLine();
            writer.write(new CustomerRecord(5, "last-5", "first-5", 12323, "comment-5").toFixedWidthLine());
            writer.newLine();
            writer.write(new CustomerRecord(10, "last-10", "first-10", 653, "comment-10").toFixedWidthLine());
            writer.newLine();
            writer.write(new CustomerRecord(50, "last-50", "first-50", 5050, "comment-50").toFixedWidthLine());
            writer.newLine();
            writer.write(new CustomerRecord(25, "last-25", "first-25", 7725, "comment-25").toFixedWidthLine());
            writer.newLine();
            writer.write(new CustomerRecord(75, "last-75", "first-75", 1175, "comment-75").toFixedWidthLine());
            writer.newLine();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILE_2))) {
            writer.write(new CustomerRecord(999, "last-999", "first-999", 1610, "comment-99").toFixedWidthLine());
            writer.newLine();
            writer.write(new CustomerRecord(3, "last-03", "first-03", 3331, "comment-03").toFixedWidthLine());
            writer.newLine();
            writer.write(new CustomerRecord(30, "last-30", "first-30", 8765, "comment-30").toFixedWidthLine());
            writer.newLine();
            writer.write(new CustomerRecord(85, "last-85", "first-85", 4567, "comment-85").toFixedWidthLine());
            writer.newLine();
            writer.write(new CustomerRecord(24, "last-24", "first-24", 247, "comment-24").toFixedWidthLine());
            writer.newLine();
        }
    }

    private static void mergeFiles() throws IOException {
        System.out.println("Merging and sorting files...");

        List<CustomerRecord> allRecords = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(TEST_FILE_1))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    allRecords.add(CustomerRecord.parseFromLine(line));
                }
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(TEST_FILE_2))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    allRecords.add(CustomerRecord.parseFromLine(line));
                }
            }
        }

        Collections.sort(allRecords, Comparator.comparingInt(CustomerRecord::getCustomerId));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MERGE_OUTPUT))) {
            for (CustomerRecord record : allRecords) {
                System.out.println(record.toFixedWidthLine());
                writer.write(record.toFixedWidthLine());
                writer.newLine();
            }
        }
    }

    private static void sortMergedFile() throws IOException {
        System.out.println("Sorting merged file on descending contract id....");

        List<CustomerRecord> records = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(MERGE_OUTPUT))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    records.add(CustomerRecord.parseFromLine(line));
                }
            }
        }

        Collections.sort(records, Comparator.comparingInt(CustomerRecord::getContractId).reversed());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SORTED_CONTRACT_ID))) {
            for (CustomerRecord record : records) {
                System.out.println(record.toFixedWidthLine());
                writer.write(record.toFixedWidthLine());
                writer.newLine();
            }
        }
    }
}
