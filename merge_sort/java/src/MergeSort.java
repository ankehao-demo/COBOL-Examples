import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSort {
    
    public static void mergeAndDisplay() throws IOException {
        System.out.println("Merging and sorting files...");
        
        List<CustomerRecord> records1 = new ArrayList<>();
        List<CustomerRecord> records2 = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader("test-file-1.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                records1.add(CustomerRecord.fromFixedWidthString(line));
            }
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader("test-file-2.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                records2.add(CustomerRecord.fromFixedWidthString(line));
            }
        }
        
        records1.sort(Comparator.comparing(CustomerRecord::getCustomerId));
        records2.sort(Comparator.comparing(CustomerRecord::getCustomerId));
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("merge-output.txt"))) {
            int i = 0, j = 0;
            
            while (i < records1.size() || j < records2.size()) {
                if (i >= records1.size()) {
                    writer.write(records2.get(j).toFixedWidthString());
                    writer.newLine();
                    j++;
                } else if (j >= records2.size()) {
                    writer.write(records1.get(i).toFixedWidthString());
                    writer.newLine();
                    i++;
                } else if (records1.get(i).getCustomerId() <= records2.get(j).getCustomerId()) {
                    writer.write(records1.get(i).toFixedWidthString());
                    writer.newLine();
                    i++;
                } else {
                    writer.write(records2.get(j).toFixedWidthString());
                    writer.newLine();
                    j++;
                }
            }
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader("merge-output.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error opening merged output file: " + e.getMessage());
            throw e;
        }
    }
    
    public static void sortAndDisplay() throws IOException {
        System.out.println("Sorting merged file on descending contract id....");
        
        List<CustomerRecord> records = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader("merge-output.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(CustomerRecord.fromFixedWidthString(line));
            }
        }
        
        records.sort(Comparator.comparing(CustomerRecord::getContractId).reversed());
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("sorted-contract-id.txt"))) {
            for (CustomerRecord record : records) {
                writer.write(record.toFixedWidthString());
                writer.newLine();
            }
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader("sorted-contract-id.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error opening sorted output file: " + e.getMessage());
            throw e;
        }
    }
}
