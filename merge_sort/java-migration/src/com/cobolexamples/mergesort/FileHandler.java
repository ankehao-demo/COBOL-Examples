package com.cobolexamples.mergesort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    
    public static List<CustomerRecord> readRecords(String filename) throws IOException {
        List<CustomerRecord> records = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    records.add(CustomerRecord.fromFixedWidthString(line));
                }
            }
        }
        
        return records;
    }
    
    public static void writeRecords(String filename, List<CustomerRecord> records) 
            throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (CustomerRecord record : records) {
                writer.write(record.toString());
                writer.newLine();
            }
        }
    }
    
    public static void displayRecords(List<CustomerRecord> records) {
        for (CustomerRecord record : records) {
            System.out.println(record.toString());
        }
    }
}
