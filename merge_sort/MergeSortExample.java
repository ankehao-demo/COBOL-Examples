import java.io.*;
import java.util.*;

class CustomerRecord {
    private int customerId;
    private String lastName;
    private String firstName;
    private int contractId;
    private String comment;

    public CustomerRecord(int customerId, String lastName, String firstName, 
                         int contractId, String comment) {
        this.customerId = customerId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.contractId = contractId;
        this.comment = comment;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getContractId() {
        return contractId;
    }

    public String getComment() {
        return comment;
    }

    public String toFileFormat() {
        return String.format("%05d%-50s%-50s%05d%-25s",
            customerId, lastName, firstName, contractId, comment);
    }

    public static CustomerRecord fromFileFormat(String line) {
        if (line.length() < 135) {
            line = String.format("%-135s", line);
        }
        
        int customerId = Integer.parseInt(line.substring(0, 5).trim());
        String lastName = line.substring(5, 55).trim();
        String firstName = line.substring(55, 105).trim();
        int contractId = Integer.parseInt(line.substring(105, 110).trim());
        String comment = line.substring(110, 135).trim();
        
        return new CustomerRecord(customerId, lastName, firstName, contractId, comment);
    }

    @Override
    public String toString() {
        return toFileFormat();
    }
}

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

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("test-file-1.txt"))) {
            writer.write(new CustomerRecord(1, "last-1", "first-1", 5423, "comment-1").toFileFormat());
            writer.newLine();
            writer.write(new CustomerRecord(5, "last-5", "first-5", 12323, "comment-5").toFileFormat());
            writer.newLine();
            writer.write(new CustomerRecord(10, "last-10", "first-10", 653, "comment-10").toFileFormat());
            writer.newLine();
            writer.write(new CustomerRecord(50, "last-50", "first-50", 5050, "comment-50").toFileFormat());
            writer.newLine();
            writer.write(new CustomerRecord(25, "last-25", "first-25", 7725, "comment-25").toFileFormat());
            writer.newLine();
            writer.write(new CustomerRecord(75, "last-75", "first-75", 1175, "comment-75").toFileFormat());
            writer.newLine();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("test-file-2.txt"))) {
            writer.write(new CustomerRecord(999, "last-999", "first-999", 1610, "comment-99").toFileFormat());
            writer.newLine();
            writer.write(new CustomerRecord(3, "last-03", "first-03", 3331, "comment-03").toFileFormat());
            writer.newLine();
            writer.write(new CustomerRecord(30, "last-30", "first-30", 8765, "comment-30").toFileFormat());
            writer.newLine();
            writer.write(new CustomerRecord(85, "last-85", "first-85", 4567, "comment-85").toFileFormat());
            writer.newLine();
            writer.write(new CustomerRecord(24, "last-24", "first-24", 247, "comment-24").toFileFormat());
            writer.newLine();
        }
    }

    private static void mergeAndDisplayFiles() throws IOException {
        System.out.println("Merging and sorting files...");

        List<CustomerRecord> allRecords = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("test-file-1.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                allRecords.add(CustomerRecord.fromFileFormat(line));
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("test-file-2.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                allRecords.add(CustomerRecord.fromFileFormat(line));
            }
        }

        Collections.sort(allRecords, Comparator.comparingInt(CustomerRecord::getCustomerId));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("merge-output.txt"))) {
            for (CustomerRecord record : allRecords) {
                writer.write(record.toFileFormat());
                writer.newLine();
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("merge-output.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    private static void sortAndDisplayFile() throws IOException {
        System.out.println("Sorting merged file on descending contract id....");

        List<CustomerRecord> records = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("merge-output.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(CustomerRecord.fromFileFormat(line));
            }
        }

        Collections.sort(records, Comparator.comparingInt(CustomerRecord::getContractId).reversed());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("sorted-contract-id.txt"))) {
            for (CustomerRecord record : records) {
                writer.write(record.toFileFormat());
                writer.newLine();
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("sorted-contract-id.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
