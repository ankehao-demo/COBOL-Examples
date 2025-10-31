# Merge Sort Example - Java Migration

This directory contains both the original COBOL merge sort program and its Java equivalent.

## Files

- `merge_sort_test.cbl` - Original COBOL program
- `CustomerRecord.java` - Java class representing a customer record
- `MergeSortExample.java` - Java program that replicates the COBOL functionality

## What the Programs Do

Both programs perform the following operations:

1. **Create Test Data**: Generate two test files with customer records:
   - `test-file-1.txt`: 6 records (customer IDs: 1, 5, 10, 50, 25, 75)
   - `test-file-2.txt`: 5 records (customer IDs: 999, 3, 30, 85, 24)

2. **Merge and Sort**: Merge both files and sort by customer ID in ascending order
   - Output: `merge-output.txt`
   - Display merged records to console

3. **Sort by Contract ID**: Sort the merged file by contract ID in descending order
   - Output: `sorted-contract-id.txt`
   - Display sorted records to console

## Customer Record Structure

Each record contains five fields:
- Customer ID (5-digit integer)
- Last Name (50 characters)
- First Name (50 characters)
- Contract ID (5-digit integer)
- Comment (25 characters)

## Running the COBOL Program

```bash
cobc -x merge_sort_test.cbl
./merge_sort_test
```

## Running the Java Program

```bash
javac CustomerRecord.java MergeSortExample.java
java MergeSortExample
```

## Implementation Details

### COBOL Implementation
- Uses COBOL's built-in `MERGE` and `SORT` statements
- These are high-level declarative operations that handle file I/O and sorting together
- File format: line sequential with fixed-width fields

### Java Implementation
- Explicit file reading/writing using `BufferedReader`/`BufferedWriter`
- Sorting using `Collections.sort()` with `Comparator` implementations
- Uses `Comparator.comparingInt()` for ascending sort
- Uses `Comparator.comparingInt().reversed()` for descending sort
- Maintains the same fixed-width field format as COBOL for file compatibility

## Output Files

After running either program, the following files will be created:
- `test-file-1.txt` - First test data file
- `test-file-2.txt` - Second test data file
- `merge-output.txt` - Merged and sorted by customer ID (ascending)
- `sorted-contract-id.txt` - Sorted by contract ID (descending)
- `work-temp.txt` - Temporary work file (COBOL only)
