import java.io.*;
import java.util.*;

public class MergeSortExample {

    public static void main(String[] args) {
        MergeSortExample example = new MergeSortExample();
        
        try {
            example.createTestData();
            example.mergeAndDisplayFiles();
            example.sortAndDisplayFile();
            System.out.println("Done.");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void createTestData() throws IOException {
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

        writeRecordsToFile("test-file-1.txt", testFile1Records);
        writeRecordsToFile("test-file-2.txt", testFile2Records);
    }

    private void mergeAndDisplayFiles() throws IOException {
        System.out.println("Merging and sorting files...");

        List<CustomerRecord> allRecords = new ArrayList<>();
        
        allRecords.addAll(readRecordsFromFile("test-file-1.txt"));
        allRecords.addAll(readRecordsFromFile("test-file-2.txt"));

        Collections.sort(allRecords, Comparator.comparingInt(CustomerRecord::getCustomerId));

        writeRecordsToFile("merge-output.txt", allRecords);

        for (CustomerRecord record : allRecords) {
            System.out.println(record);
        }
    }

    private void sortAndDisplayFile() throws IOException {
        System.out.println("Sorting merged file on descending contract id....");

        List<CustomerRecord> records = readRecordsFromFile("merge-output.txt");

        Collections.sort(records, 
            Comparator.comparingInt(CustomerRecord::getContractId).reversed());

        writeRecordsToFile("sorted-contract-id.txt", records);

        for (CustomerRecord record : records) {
            System.out.println(record);
        }
    }

    private void writeRecordsToFile(String filename, List<CustomerRecord> records) 
            throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (CustomerRecord record : records) {
                writer.write(record.toString());
                writer.newLine();
            }
        }
    }

    private List<CustomerRecord> readRecordsFromFile(String filename) throws IOException {
        List<CustomerRecord> records = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() >= 135) {
                    int customerId = Integer.parseInt(line.substring(0, 5).trim());
                    String lastName = line.substring(5, 55).trim();
                    String firstName = line.substring(55, 105).trim();
                    int contractId = Integer.parseInt(line.substring(105, 110).trim());
                    String comment = line.substring(110, 135).trim();
                    
                    records.add(new CustomerRecord(customerId, lastName, firstName, 
                                                   contractId, comment));
                }
            }
        }
        
        return records;
    }
}
