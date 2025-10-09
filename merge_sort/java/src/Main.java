public class Main {
    public static void main(String[] args) {
        try {
            TestDataGenerator.generateTestFiles();
            
            MergeSort.mergeAndDisplay();
            
            MergeSort.sortAndDisplay();
            
            System.out.println("Done.");
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
