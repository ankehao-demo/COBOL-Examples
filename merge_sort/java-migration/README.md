# COBOL Merge Sort Java Migration

This Java application migrates the COBOL merge sort functionality from `merge_sort_test.cbl` to Java.

## Overview

This implementation replicates the functionality of the COBOL program that:
1. Creates test data in two input files
2. Merges the files by ascending customer ID
3. Sorts the merged result by descending contract ID

## Project Structure

```
src/com/cobolexamples/mergesort/
├── CustomerRecord.java       - Data model representing customer records
├── FileHandler.java          - I/O operations for reading/writing records
├── MergeSortProcessor.java   - Core merge and sort logic
├── TestDataGenerator.java    - Generates the test data files
└── Main.java                 - Application entry point
```

## Record Format

The application maintains COBOL fixed-width format compatibility:
- Customer ID: 5 digits (zero-padded)
- Last Name: 50 characters (space-padded)
- First Name: 50 characters (space-padded)
- Contract ID: 5 digits (zero-padded)
- Comment: 25 characters (space-padded)
- **Total record length: 135 characters**

## Test Data

**File 1** (`test-file-1.txt`) - 6 records:
- Customer IDs: 1, 5, 10, 50, 25, 75

**File 2** (`test-file-2.txt`) - 5 records:
- Customer IDs: 999, 3, 30, 85, 24

## Design Decisions

1. **File Format**: Uses COBOL fixed-width format (135 characters per record) for exact functional equivalence
2. **Input Handling**: Pre-sorts input files by customer ID before merging (true to COBOL MERGE semantics)
3. **Error Handling**: Uses Java exceptions with meaningful error messages
4. **Architecture**: Simple utility classes focused on the specific use case
5. **Performance**: In-memory sorting for simplicity

## How to Build and Run

### Compile

```bash
cd java-migration
javac -d bin src/com/cobolexamples/mergesort/*.java
```

### Run

```bash
cd java-migration
java -cp bin com.cobolexamples.mergesort.Main
```

### Expected Output

The program will:
1. Create `test-file-1.txt` and `test-file-2.txt` with test data
2. Display merged records (sorted by customer ID ascending)
3. Create `merge-output.txt` with merged data
4. Display final sorted records (sorted by contract ID descending)
5. Create `sorted-contract-id.txt` with final sorted data

## Output Files

- `test-file-1.txt` - First test data file
- `test-file-2.txt` - Second test data file
- `merge-output.txt` - Merged records sorted by customer ID (ascending)
- `sorted-contract-id.txt` - Final output sorted by contract ID (descending)

## Verification

To verify the Java implementation matches the COBOL behavior, you can compare the output files:

```bash
# Run COBOL version
cd ../
cobc -x merge_sort_test.cbl
./merge_sort_test

# Run Java version
cd java-migration
java -cp bin com.cobolexamples.mergesort.Main

# Compare outputs
diff merge-output.txt ../merge-output.txt
diff sorted-contract-id.txt ../sorted-contract-id.txt
```

## JIRA Reference

This implementation addresses **ANKEDEMO-1**: Migrate COBOL merge sort functionality to Java.

## Implementation Notes

- The Java code maintains the same workflow sequence as the COBOL program
- Fixed-width format ensures compatibility with the original COBOL file structure
- The merge operation pre-sorts both input files before merging (COBOL MERGE requirement)
- All test data values match the COBOL implementation exactly
- Error handling uses Java exceptions rather than COBOL file status codes
