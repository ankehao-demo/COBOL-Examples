import java.util.Objects;

public class CustomerRecord {
    private int customerId;
    private String lastName;
    private String firstName;
    private int contractId;
    private String comment;

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

    public String toFileString() {
        return String.format("%05d%-50s%-50s%05d%-25s",
            customerId,
            padRight(lastName, 50),
            padRight(firstName, 50),
            contractId,
            padRight(comment, 25));
    }

    public static CustomerRecord fromFileString(String line) {
        if (line.length() < 135) {
            throw new IllegalArgumentException("Invalid record length: " + line.length());
        }
        
        int customerId = Integer.parseInt(line.substring(0, 5).trim());
        String lastName = line.substring(5, 55).trim();
        String firstName = line.substring(55, 105).trim();
        int contractId = Integer.parseInt(line.substring(105, 110).trim());
        String comment = line.substring(110, 135).trim();
        
        return new CustomerRecord(customerId, lastName, firstName, contractId, comment);
    }

    private static String padRight(String s, int length) {
        if (s.length() >= length) {
            return s.substring(0, length);
        }
        return String.format("%-" + length + "s", s);
    }

    @Override
    public String toString() {
        return toFileString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerRecord that = (CustomerRecord) o;
        return customerId == that.customerId &&
               contractId == that.contractId &&
               Objects.equals(lastName, that.lastName) &&
               Objects.equals(firstName, that.firstName) &&
               Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, lastName, firstName, contractId, comment);
    }
}
