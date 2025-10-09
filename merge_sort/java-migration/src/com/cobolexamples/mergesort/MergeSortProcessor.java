package com.cobolexamples.mergesort;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSortProcessor {
    
    public static void mergeFiles(String file1, String file2, String outputFile) 
            throws IOException {
        System.out.println("Merging and sorting files...");
        
        List<CustomerRecord> records1 = FileHandler.readRecords(file1);
        List<CustomerRecord> records2 = FileHandler.readRecords(file2);
        
        records1.sort(Comparator.comparingInt(CustomerRecord::getCustomerId));
        records2.sort(Comparator.comparingInt(CustomerRecord::getCustomerId));
        
        List<CustomerRecord> mergedRecords = merge(records1, records2);
        
        FileHandler.writeRecords(outputFile, mergedRecords);
        
        FileHandler.displayRecords(mergedRecords);
    }
    
    private static List<CustomerRecord> merge(List<CustomerRecord> list1, 
                                              List<CustomerRecord> list2) {
        List<CustomerRecord> result = new ArrayList<>();
        int i = 0, j = 0;
        
        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i).getCustomerId() <= list2.get(j).getCustomerId()) {
                result.add(list1.get(i));
                i++;
            } else {
                result.add(list2.get(j));
                j++;
            }
        }
        
        while (i < list1.size()) {
            result.add(list1.get(i));
            i++;
        }
        
        while (j < list2.size()) {
            result.add(list2.get(j));
            j++;
        }
        
        return result;
    }
    
    public static void sortFile(String inputFile, String outputFile) throws IOException {
        System.out.println("Sorting merged file on descending contract id....");
        
        List<CustomerRecord> records = FileHandler.readRecords(inputFile);
        
        records.sort(Comparator.comparingInt(CustomerRecord::getContractId).reversed());
        
        FileHandler.writeRecords(outputFile, records);
        
        FileHandler.displayRecords(records);
    }
}
