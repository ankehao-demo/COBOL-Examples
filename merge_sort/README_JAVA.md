# Java Migration of COBOL Merge Sort Program

This is a Java implementation that replicates the functionality of the COBOL merge sort program (`merge_sort_test.cbl`).

## Overview

The program demonstrates:
1. **Test Data Generation**: Creates two test files with customer records
2. **Merge Operation**: Merges `test-file-1.txt` and `test-file-2.txt`, sorting by customer ID (ascending)
3. **Sort Operation**: Sorts the merged file by contract ID (descending)

## Files

- `CustomerRecord.java` - Represents a customer record with 5 fields
- `MergeSortProgram.java` - Main program implementing merge and sort operations
- `README_JAVA.md` - This file

## Customer Record Structure

Each customer record contains:
- **Customer ID** (integer, 5 digits)
- **Last Name** (String, 50 characters)
- **First Name** (String, 50 characters)
- **Contract ID** (integer, 5 digits)
- **Comment** (String, 25 characters)

## Compilation and Execution

```bash
# Compile
javac CustomerRecord.java MergeSortProgram.java

# Run
java MergeSortProgram
```

## Expected Output

The program will:
1. Create `test-file-1.txt` with 6 customer records
2. Create `test-file-2.txt` with 5 customer records
3. Merge both files into `merge-output.txt`, sorted by customer ID (ascending)
4. Display the merged records
5. Sort `merge-output.txt` by contract ID (descending) into `sorted-contract-id.txt`
6. Display the sorted records

## Test Data

### File 1 Records:
- Customer IDs: 1, 5, 10, 50, 25, 75

### File 2 Records:
- Customer IDs: 999, 3, 30, 85, 24

### Merged Output (sorted by customer ID):
- Customer IDs: 1, 3, 5, 10, 24, 25, 30, 50, 75, 85, 999

### Final Output (sorted by contract ID descending):
- Contract IDs: 12323, 8765, 7725, 5423, 5050, 4567, 3331, 1610, 1175, 653, 247

## Differences from COBOL

While the Java implementation produces identical output, the approach differs:
- **COBOL**: Uses built-in `MERGE` and `SORT` statements that handle file I/O automatically
- **Java**: Explicitly handles file reading, in-memory sorting using `Collections.sort()` with `Comparator`, and file writing

## Comparison with COBOL

Run both versions to verify identical behavior:

```bash
# COBOL version
cobc -x merge_sort_test.cbl
./merge_sort_test

# Java version
javac CustomerRecord.java MergeSortProgram.java
java MergeSortProgram
```

Both programs should produce the same output and create identical output files.
