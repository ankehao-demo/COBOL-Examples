# Java Migration of COBOL Merge/Sort Program

This directory contains a Java implementation that replicates the functionality of the COBOL `merge_sort_test.cbl` program.

## Overview

The program demonstrates:
1. **Test data generation** - Creates two files with customer records
2. **Merge operation** - Merges two files by ascending customer ID
3. **Sort operation** - Sorts the merged file by descending contract ID

## Architecture

### Classes

#### `CustomerRecord.java`
Data model representing a customer record with fixed-width serialization:
- `customerId` (5 digits)
- `lastName` (50 characters)
- `firstName` (50 characters)
- `contractId` (5 digits)
- `comment` (25 characters)

Methods:
- `toFixedWidthString()` - Serializes record to fixed-width format
- `fromFixedWidthString(String)` - Deserializes from fixed-width format

#### `TestDataGenerator.java`
Generates test data files matching the COBOL implementation:
- `test-file-1.txt` - 6 records with customer IDs: 1, 5, 10, 50, 25, 75
- `test-file-2.txt` - 5 records with customer IDs: 999, 3, 30, 85, 24

#### `MergeSort.java`
Core merge and sort operations:

**`mergeAndDisplay()`**:
- Reads both input files into memory
- Sorts each file by customer ID (ascending)
- Performs two-pointer merge algorithm
- Writes merged output to `merge-output.txt`
- Displays merged results

**`sortAndDisplay()`**:
- Reads merged file
- Sorts by contract ID (descending)
- Writes to `sorted-contract-id.txt`
- Displays sorted results

#### `Main.java`
Entry point that orchestrates the workflow:
1. Generate test data
2. Merge and display
3. Sort and display
4. Print "Done."

## Building and Running

### Compile
```bash
cd src
javac *.java
```

### Run
```bash
java Main
```

### Expected Output
```
Creating test data files...
Merging and sorting files...
[11 merged records sorted by customer ID ascending]
Sorting merged file on descending contract id....
[11 records sorted by contract ID descending]
Done.
```

## Key Differences from COBOL

### COBOL `MERGE` Statement
The COBOL `MERGE` statement automatically sorts input files before merging. In Java, this requires:
1. Reading both files into memory
2. Sorting each collection by the merge key
3. Performing a two-pointer merge algorithm

### COBOL `SORT` Statement
The COBOL `SORT` statement is declarative. In Java, we use `Collections.sort()` with a `Comparator`.

### File Handling
- **COBOL**: Uses file status codes (`ws-fs-status-*`) for error checking
- **Java**: Uses try-with-resources and exception handling

### Work Files
- **COBOL**: Uses Sort Description (SD) `fd-sorting-file` as a work file
- **Java**: Works with in-memory `List<CustomerRecord>` collections

## Output Files

- `test-file-1.txt` - First test data file (6 records)
- `test-file-2.txt` - Second test data file (5 records)
- `merge-output.txt` - Merged results sorted by customer ID ascending
- `sorted-contract-id.txt` - Final output sorted by contract ID descending
