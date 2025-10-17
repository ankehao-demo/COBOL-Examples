package com.cognition.mergesort;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSortProcessor {

    public static void mergeFiles(String inputFile1, String inputFile2, String outputFile) throws IOException {
        System.out.println("Merging and sorting files...");

        List<CustomerRecord> records1 = FileHandler.readRecords(inputFile1);
        List<CustomerRecord> records2 = FileHandler.readRecords(inputFile2);

        records1.sort(Comparator.comparingInt(CustomerRecord::getCustomerId));
        records2.sort(Comparator.comparingInt(CustomerRecord::getCustomerId));

        List<CustomerRecord> mergedRecords = new ArrayList<>();
        int i = 0, j = 0;

        while (i < records1.size() && j < records2.size()) {
            if (records1.get(i).getCustomerId() <= records2.get(j).getCustomerId()) {
                mergedRecords.add(records1.get(i));
                i++;
            } else {
                mergedRecords.add(records2.get(j));
                j++;
            }
        }

        while (i < records1.size()) {
            mergedRecords.add(records1.get(i));
            i++;
        }

        while (j < records2.size()) {
            mergedRecords.add(records2.get(j));
            j++;
        }

        FileHandler.writeRecords(outputFile, mergedRecords);

        System.out.println("Merged file created. Records:");
        for (CustomerRecord record : mergedRecords) {
            System.out.println(record);
        }
    }

    public static void sortFile(String inputFile, String outputFile) throws IOException {
        System.out.println("Sorting merged file on descending contract id...");

        List<CustomerRecord> records = FileHandler.readRecords(inputFile);

        records.sort(Comparator.comparingInt(CustomerRecord::getContractId).reversed());

        FileHandler.writeRecords(outputFile, records);

        System.out.println("Sorted file created. Records:");
        for (CustomerRecord record : records) {
            System.out.println(record);
        }
    }
}
