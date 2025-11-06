# Java Implementation of COBOL Merge Sort

This is a Java implementation that replicates the functionality of the COBOL merge sort program (`merge_sort_test.cbl`).

## Overview

The program performs the following operations:

1. **Creates test data**: Generates two input files (`test-file-1.txt` and `test-file-2.txt`) with hardcoded customer records
2. **Merges and sorts**: Combines both input files and sorts by customer ID in ascending order, outputting to `merge-output.txt`
3. **Sorts by contract ID**: Sorts the merged file by contract ID in descending order, outputting to `sorted-contract-id.txt`

## Customer Record Structure

Each customer record contains five fields:
- **Customer ID** (integer): 5-digit customer identifier
- **Last Name** (String): Customer's last name (up to 50 characters)
- **First Name** (String): Customer's first name (up to 50 characters)
- **Contract ID** (integer): 5-digit contract identifier
- **Comment** (String): Additional comment (up to 25 characters)

Records are stored in fixed-width format (135 characters total).

## Compilation and Execution

```bash
# Compile the Java programs
javac CustomerRecord.java MergeSortExample.java

# Run the program
java MergeSortExample
```

## Output

The program displays:
1. Status messages for each operation
2. The merged and sorted records (sorted by customer ID ascending)
3. The final sorted records (sorted by contract ID descending)

## Files Generated

- `test-file-1.txt`: First test data file (6 records)
- `test-file-2.txt`: Second test data file (5 records)
- `merge-output.txt`: Merged file sorted by customer ID (11 records)
- `sorted-contract-id.txt`: Final sorted file by contract ID descending (11 records)

## Differences from COBOL Implementation

- Java uses `Collections.sort()` with custom `Comparator` implementations instead of COBOL's `MERGE` and `SORT` statements
- File I/O is handled explicitly in Java using `BufferedReader` and `BufferedWriter`
- The Java implementation uses object-oriented design with a `CustomerRecord` class
- Fixed-width formatting is handled through the `toFixedWidthString()` and `fromFixedWidthString()` methods
