package com.cognition.mergesort;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestDataGenerator {

    public static void createTestData() throws IOException {
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

        FileHandler.writeRecords("test-file-1.txt", file1Records);
        FileHandler.writeRecords("test-file-2.txt", file2Records);
    }
}
