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

    @Override
    public String toString() {
        return String.format("%05d%-50s%-50s%05d%-25s",
                customerId,
                padRight(customerLastName, 50),
                padRight(customerFirstName, 50),
                customerContractId,
                padRight(customerComment, 25));
    }

    private static String padRight(String s, int length) {
        if (s.length() >= length) {
            return s.substring(0, length);
        }
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() < length) {
            sb.append(' ');
        }
        return sb.toString();
    }

    public static CustomerRecord fromLine(String line) {
        if (line.length() < 135) {
            throw new IllegalArgumentException("Invalid record line length");
        }
        
        int customerId = Integer.parseInt(line.substring(0, 5).trim());
        String lastName = line.substring(5, 55).trim();
        String firstName = line.substring(55, 105).trim();
        int contractId = Integer.parseInt(line.substring(105, 110).trim());
        String comment = line.substring(110, 135).trim();
        
        return new CustomerRecord(customerId, lastName, firstName, contractId, comment);
    }
}
