import java.io.IOException;

public class CustomerRecord {
    private Integer customerId;
    private String lastName;
    private String firstName;
    private Integer contractId;
    private String comment;

    public CustomerRecord(Integer customerId, String lastName, String firstName, 
                         Integer contractId, String comment) {
        this.customerId = customerId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.contractId = contractId;
        this.comment = comment;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Integer getContractId() {
        return contractId;
    }

    public String getComment() {
        return comment;
    }

    public String toFixedWidthString() {
        return String.format("%05d%-50s%-50s%05d%-25s",
            customerId,
            padRight(lastName, 50),
            padRight(firstName, 50),
            contractId,
            padRight(comment, 25));
    }

    public static CustomerRecord fromFixedWidthString(String line) throws IOException {
        if (line.length() < 135) {
            throw new IOException("Invalid record length: " + line.length());
        }

        try {
            Integer customerId = Integer.parseInt(line.substring(0, 5).trim());
            String lastName = line.substring(5, 55).trim();
            String firstName = line.substring(55, 105).trim();
            Integer contractId = Integer.parseInt(line.substring(105, 110).trim());
            String comment = line.substring(110, 135).trim();

            return new CustomerRecord(customerId, lastName, firstName, contractId, comment);
        } catch (NumberFormatException e) {
            throw new IOException("Error parsing numeric fields: " + e.getMessage(), e);
        }
    }

    private static String padRight(String str, int length) {
        if (str.length() >= length) {
            return str.substring(0, length);
        }
        return String.format("%-" + length + "s", str);
    }

    @Override
    public String toString() {
        return toFixedWidthString();
    }
}
