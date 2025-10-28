package com.cognition.mergesort;

public class Main {
    public static void main(String[] args) {
        try {
            TestDataGenerator.createTestData();

            MergeSortProcessor.mergeFiles("test-file-1.txt", "test-file-2.txt", "merge-output.txt");

            MergeSortProcessor.sortFile("merge-output.txt", "sorted-contract-id.txt");

            System.out.println("Done.");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
