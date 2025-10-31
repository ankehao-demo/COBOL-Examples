public class CustomerRecord {
    private int customerId;
    private String customerLastName;
    private String customerFirstName;
    private int customerContractId;
    private String customerComment;

    public CustomerRecord(int customerId, String customerLastName, String customerFirstName, 
                         int customerContractId, String customerComment) {
        this.customerId = customerId;
        this.customerLastName = customerLastName;
        this.customerFirstName = customerFirstName;
        this.customerContractId = customerContractId;
        this.customerComment = customerComment;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public int getCustomerContractId() {
        return customerContractId;
    }

    public String getCustomerComment() {
        return customerComment;
    }

    public String toFixedWidthString() {
        return String.format("%05d%-50s%-50s%05d%-25s",
            customerId,
            customerLastName,
            customerFirstName,
            customerContractId,
            customerComment);
    }

    public static CustomerRecord fromFixedWidthString(String line) {
        if (line.length() < 135) {
            line = String.format("%-135s", line);
        }
        
        int customerId = Integer.parseInt(line.substring(0, 5).trim());
        String customerLastName = line.substring(5, 55).trim();
        String customerFirstName = line.substring(55, 105).trim();
        int customerContractId = Integer.parseInt(line.substring(105, 110).trim());
        String customerComment = line.substring(110, 135).trim();
        
        return new CustomerRecord(customerId, customerLastName, customerFirstName,
                                 customerContractId, customerComment);
    }

    @Override
    public String toString() {
        return toFixedWidthString();
    }
}
