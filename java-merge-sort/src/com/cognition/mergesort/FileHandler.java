package com.cognition.mergesort;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final int CUSTOMER_ID_LENGTH = 5;
    private static final int LAST_NAME_LENGTH = 50;
    private static final int FIRST_NAME_LENGTH = 50;
    private static final int CONTRACT_ID_LENGTH = 5;
    private static final int COMMENT_LENGTH = 25;
    private static final int TOTAL_RECORD_LENGTH = 135;

    public static List<CustomerRecord> readRecords(String filename) throws IOException {
        List<CustomerRecord> records = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() >= TOTAL_RECORD_LENGTH) {
                    CustomerRecord record = parseRecord(line);
                    records.add(record);
                }
            }
        }
        
        return records;
    }

    public static void writeRecords(String filename, List<CustomerRecord> records) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (CustomerRecord record : records) {
                writer.write(formatRecord(record));
                writer.newLine();
            }
        }
    }

    private static CustomerRecord parseRecord(String line) {
        int offset = 0;
        
        String customerIdStr = line.substring(offset, offset + CUSTOMER_ID_LENGTH).trim();
        offset += CUSTOMER_ID_LENGTH;
        
        String lastName = line.substring(offset, offset + LAST_NAME_LENGTH).trim();
        offset += LAST_NAME_LENGTH;
        
        String firstName = line.substring(offset, offset + FIRST_NAME_LENGTH).trim();
        offset += FIRST_NAME_LENGTH;
        
        String contractIdStr = line.substring(offset, offset + CONTRACT_ID_LENGTH).trim();
        offset += CONTRACT_ID_LENGTH;
        
        String comment = line.substring(offset, offset + COMMENT_LENGTH).trim();
        
        int customerId = Integer.parseInt(customerIdStr);
        int contractId = Integer.parseInt(contractIdStr);
        
        return new CustomerRecord(customerId, lastName, firstName, contractId, comment);
    }

    private static String formatRecord(CustomerRecord record) {
        StringBuilder sb = new StringBuilder();
        
        sb.append(String.format("%0" + CUSTOMER_ID_LENGTH + "d", record.getCustomerId()));
        
        sb.append(padRight(record.getLastName(), LAST_NAME_LENGTH));
        
        sb.append(padRight(record.getFirstName(), FIRST_NAME_LENGTH));
        
        sb.append(String.format("%0" + CONTRACT_ID_LENGTH + "d", record.getContractId()));
        
        sb.append(padRight(record.getComment(), COMMENT_LENGTH));
        
        return sb.toString();
    }

    private static String padRight(String str, int length) {
        if (str.length() >= length) {
            return str.substring(0, length);
        }
        return String.format("%-" + length + "s", str);
    }
}
