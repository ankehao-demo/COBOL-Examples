public class CustomerRecord {
    private final int customerId;
    private final String lastName;
    private final String firstName;
    private final int contractId;
    private final String comment;

    public CustomerRecord(int customerId, String lastName, String firstName, 
                         int contractId, String comment) {
        this.customerId = customerId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.contractId = contractId;
        this.comment = comment;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getContractId() {
        return contractId;
    }

    public String getComment() {
        return comment;
    }

    public String toFixedWidthString() {
        return String.format("%05d%-50s%-50s%05d%-25s",
            customerId,
            lastName,
            firstName,
            contractId,
            comment
        );
    }

    public static CustomerRecord fromFixedWidthString(String line) {
        if (line.length() < 135) {
            line = String.format("%-135s", line);
        }
        
        int customerId = Integer.parseInt(line.substring(0, 5).trim());
        String lastName = line.substring(5, 55);
        String firstName = line.substring(55, 105);
        int contractId = Integer.parseInt(line.substring(105, 110).trim());
        String comment = line.substring(110, 135);
        
        return new CustomerRecord(customerId, lastName, firstName, contractId, comment);
    }

    @Override
    public String toString() {
        return toFixedWidthString();
    }
}
