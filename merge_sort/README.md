# Merge Sort Example - Java Implementation

This directory contains both COBOL and Java implementations of a merge sort program that demonstrates merging and sorting operations on customer records.

## Overview

The program performs two main operations:
1. **Merges two sorted files**: Merges `test-file-1.txt` and `test-file-2.txt` into a single sorted output file, sorting by ascending customer ID
2. **Sorts the merged file**: Sorts the merged file by descending contract ID

## Customer Record Structure

Each customer record contains five fields:
- Customer ID (5-digit integer)
- Last Name (50 characters)
- First Name (50 characters)
- Contract ID (5-digit integer)
- Comment (25 characters)

Records are stored in fixed-width format (135 characters per line).

## COBOL Implementation

### Compilation
```bash
cd merge_sort
cobc -x merge_sort_test.cbl
```

### Running
```bash
./merge_sort_test
```

The COBOL program uses built-in `MERGE` and `SORT` statements to handle the operations.

## Java Implementation

### Compilation
```bash
cd merge_sort
javac MergeSortExample.java CustomerRecord.java
```

### Running
```bash
java MergeSortExample
```

The Java implementation:
- Creates a `CustomerRecord` class to represent customer data
- Handles fixed-width file I/O to match COBOL's line sequential format
- Uses `Collections.sort()` with custom `Comparator` implementations for sorting

## Test Data

Both implementations generate the same test data:

**test-file-1.txt** (6 records):
- ID=1, last="last-1", first="first-1", contractID=5423, comment="comment-1"
- ID=5, last="last-5", first="first-5", contractID=12323, comment="comment-5"
- ID=10, last="last-10", first="first-10", contractID=653, comment="comment-10"
- ID=50, last="last-50", first="first-50", contractID=5050, comment="comment-50"
- ID=25, last="last-25", first="first-25", contractID=7725, comment="comment-25"
- ID=75, last="last-75", first="first-75", contractID=1175, comment="comment-75"

**test-file-2.txt** (5 records):
- ID=999, last="last-999", first="first-999", contractID=1610, comment="comment-99"
- ID=3, last="last-03", first="first-03", contractID=3331, comment="comment-03"
- ID=30, last="last-30", first="first-30", contractID=8765, comment="comment-30"
- ID=85, last="last-85", first="first-85", contractID=4567, comment="comment-85"
- ID=24, last="last-24", first="first-24", contractID=247, comment="comment-24"

## Output Files

- `merge-output.txt`: Merged records sorted by ascending customer ID
- `sorted-contract-id.txt`: Records sorted by descending contract ID

## Key Differences

- **COBOL**: Uses declarative `MERGE` and `SORT` statements that handle file I/O and sorting together
- **Java**: Explicitly handles file reading, data structure management, sorting logic, and file writing as separate steps
- Both implementations produce identical output and file formats
