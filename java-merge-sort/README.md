# Java Merge Sort Application

This Java application migrates the COBOL merge sort functionality from `merge_sort/merge_sort_test.cbl`.

## Overview

The application performs file-based merging and sorting of customer records:
1. Creates test data files with customer records
2. Merges two input files by ascending customer ID
3. Sorts the merged result by descending contract ID

## Architecture

The application consists of 5 Java classes:

### 1. `CustomerRecord.java`
Data model representing a customer record with:
- Customer ID (5 digits)
- Last Name (50 characters)
- First Name (50 characters)
- Contract ID (5 digits)
- Comment (25 characters)

### 2. `TestDataGenerator.java`
Generates test data matching the COBOL program:
- File 1: 6 records with customer IDs: 1, 5, 10, 50, 25, 75
- File 2: 5 records with customer IDs: 999, 3, 30, 85, 24

### 3. `FileHandler.java`
Handles file I/O operations:
- Reads/writes records in fixed-width format (135 characters per record)
- Maintains COBOL compatibility with space-padded fields
- Parses and formats customer records

### 4. `MergeSortProcessor.java`
Core business logic:
- `mergeFiles()`: Merges two files by ascending customer ID
- `sortFile()`: Sorts file by descending contract ID

### 5. `Main.java`
Orchestrates the workflow:
1. Create test data
2. Merge files
3. Sort merged result

## Design Decisions

### File Format
**Choice**: Fixed-width format (135 characters per record)
- Maintains compatibility with COBOL program
- Uses space-padding for string fields and zero-padding for numeric fields
- Can be easily modified to support other formats (CSV, JSON) if needed

### Input Handling
**Choice**: Pre-sort input files before merging
- Both input files are sorted by customer ID before merge operation
- Implements true merge semantics (COBOL MERGE requires pre-sorted inputs)
- More efficient than a full sort of unsorted data

### Error Handling
**Choice**: Java exceptions with meaningful error messages
- Uses try-catch blocks in Main class
- Throws IOException for file operations
- Prints stack traces for debugging

### Performance
**Choice**: In-memory sorting
- All records loaded into memory for sorting
- Simple and efficient for the test dataset (11 records)
- Can be extended for larger datasets if needed

## How to Run

### Compilation
```bash
cd java-merge-sort/src
javac com/cognition/mergesort/*.java
```

### Execution
```bash
cd java-merge-sort/src
java com.cognition.mergesort.Main
```

### Expected Output
The application will:
1. Create `test-file-1.txt` and `test-file-2.txt` with test data
2. Merge them into `merge-output.txt` (sorted by customer ID ascending)
3. Sort merged file into `sorted-contract-id.txt` (sorted by contract ID descending)
4. Print records to console after each operation

## Output Files

- `test-file-1.txt`: First input file (6 records)
- `test-file-2.txt`: Second input file (5 records)
- `merge-output.txt`: Merged result sorted by customer ID (ascending)
- `sorted-contract-id.txt`: Final result sorted by contract ID (descending)

## Verification

To verify the output matches the COBOL program:
1. Run the Java application
2. Run the COBOL program
3. Compare the output files

The record format is identical (135 characters per line), so files should match exactly.

## JIRA Ticket

This implementation addresses **ANKEDEMO-1**: Migrate COBOL merge sort functionality to Java.
