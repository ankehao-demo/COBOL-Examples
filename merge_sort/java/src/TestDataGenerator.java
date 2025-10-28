import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TestDataGenerator {
    
    public static void generateTestFiles() throws IOException {
        System.out.println("Creating test data files...");
        
        createTestFile1();
        createTestFile2();
    }
    
    private static void createTestFile1() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("test-file-1.txt"))) {
            CustomerRecord[] records = {
                new CustomerRecord(1, "last-1", "first-1", 5423, "comment-1"),
                new CustomerRecord(5, "last-5", "first-5", 12323, "comment-5"),
                new CustomerRecord(10, "last-10", "first-10", 653, "comment-10"),
                new CustomerRecord(50, "last-50", "first-50", 5050, "comment-50"),
                new CustomerRecord(25, "last-25", "first-25", 7725, "comment-25"),
                new CustomerRecord(75, "last-75", "first-75", 1175, "comment-75")
            };
            
            for (CustomerRecord record : records) {
                writer.write(record.toFixedWidthString());
                writer.newLine();
            }
        }
    }
    
    private static void createTestFile2() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("test-file-2.txt"))) {
            CustomerRecord[] records = {
                new CustomerRecord(999, "last-999", "first-999", 1610, "comment-99"),
                new CustomerRecord(3, "last-03", "first-03", 3331, "comment-03"),
                new CustomerRecord(30, "last-30", "first-30", 8765, "comment-30"),
                new CustomerRecord(85, "last-85", "first-85", 4567, "comment-85"),
                new CustomerRecord(24, "last-24", "first-24", 247, "comment-24")
            };
            
            for (CustomerRecord record : records) {
                writer.write(record.toFixedWidthString());
                writer.newLine();
            }
        }
    }
}
