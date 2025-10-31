# Merge Sort Example

This directory contains both COBOL and Java implementations of a merge and sort program that demonstrates file merging and sorting operations.

## COBOL Implementation

**File:** `merge_sort_test.cbl`

The COBOL program performs two main operations:

1. **Merges two sorted files**: Merges `test-file-1.txt` and `test-file-2.txt` into a single sorted output file using COBOL's `MERGE` statement, sorting by ascending `f-customer-id`.

2. **Sorts the merged file**: Sorts the merged file by descending `f-customer-contract-id` using COBOL's `SORT` statement.

### Running the COBOL Program

```bash
cd merge_sort
cobc -x merge_sort_test.cbl
./merge_sort_test
```

## Java Implementation

**Files:** `CustomerRecord.java`, `MergeSortExample.java`

The Java program replicates the COBOL functionality using Java's built-in sorting capabilities.

### CustomerRecord Class

Represents a customer record with five fields:
- `customerId` (integer)
- `customerLastName` (String)
- `customerFirstName` (String)
- `customerContractId` (integer)
- `customerComment` (String)

The class provides methods to:
- Convert to/from fixed-width string format (matching COBOL's record structure)
- Format: 5-digit customer ID + 50-char last name + 50-char first name + 5-digit contract ID + 25-char comment

### MergeSortExample Program

Replicates the COBOL program's behavior:

1. **Creates test data**: Generates the same hardcoded test records as the COBOL program
   - `test-file-1.txt`: 6 records with customer IDs: 1, 5, 10, 50, 25, 75
   - `test-file-2.txt`: 5 records with customer IDs: 999, 3, 30, 85, 24

2. **Merges files**: Reads both input files, combines them, and sorts by customer ID in ascending order using `Collections.sort()` with a custom `Comparator`

3. **Sorts merged file**: Sorts the merged records by contract ID in descending order

### Running the Java Program

```bash
cd merge_sort
javac CustomerRecord.java MergeSortExample.java
java MergeSortExample
```

### Output Files

Both implementations produce the following output files:
- `merge-output.txt`: Merged records sorted by ascending customer ID
- `sorted-contract-id.txt`: Records sorted by descending contract ID

## Comparison

The Java implementation demonstrates the same logical operations as the COBOL program but uses different approaches:

- **COBOL**: Uses built-in `MERGE` and `SORT` statements which handle file I/O and sorting together
- **Java**: Explicitly handles file reading, data structure management (List), sorting logic (Comparator), and file writing as separate steps

Both produce identical results with the same test data.
