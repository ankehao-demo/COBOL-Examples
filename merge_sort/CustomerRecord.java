import java.util.Objects;

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

    public static CustomerRecord parseFromLine(String line) {
        if (line == null) {
            throw new IllegalArgumentException("Line cannot be null");
        }
        
        if (line.length() < 135) {
            line = String.format("%-135s", line);
        }

        int customerId = Integer.parseInt(line.substring(0, 5).trim());
        String lastName = line.substring(5, 55).trim();
        String firstName = line.substring(55, 105).trim();
        int contractId = Integer.parseInt(line.substring(105, 110).trim());
        String comment = line.substring(110, 135).trim();

        return new CustomerRecord(customerId, lastName, firstName, contractId, comment);
    }

    public String toFixedWidthLine() {
        return String.format("%05d%-50s%-50s%05d%-25s",
                customerId,
                lastName,
                firstName,
                contractId,
                comment).stripTrailing();
    }

    @Override
    public String toString() {
        return "CustomerRecord{" +
                "customerId=" + customerId +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", contractId=" + contractId +
                ", comment='" + comment + '\'' +
                '}';
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
