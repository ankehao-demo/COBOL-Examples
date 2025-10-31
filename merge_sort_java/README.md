# COBOL Merge Sort - Java Migration

This is a Java implementation of the COBOL merge sort program from `merge_sort/merge_sort_test.cbl`.

## Overview

The program performs two main operations:

1. **Merges two sorted files**: It merges `test-file-1.txt` and `test-file-2.txt` into a single sorted output file, sorting by ascending customer ID.

2. **Sorts the merged file**: It then sorts the merged file by descending customer contract ID.

## Customer Record Structure

Each customer record contains five fields:
- Customer ID (integer, 5 digits)
- Customer Last Name (String, 50 characters)
- Customer First Name (String, 50 characters)
- Customer Contract ID (integer, 5 digits)
- Customer Comment (String, 25 characters)

## Implementation Details

### Classes

- **CustomerRecord**: Represents a customer record with the five fields described above. Includes methods for:
  - Converting to/from the fixed-width file format used by COBOL
  - Getters for all fields
  - String representation

- **MergeSortExample**: Main program that replicates the COBOL functionality:
  - Creates test data files with the same hardcoded records as the COBOL program
  - Reads both input files and merges them
  - Sorts by customer ID in ascending order using `Collections.sort()` with a custom `Comparator`
  - Sorts the merged file by contract ID in descending order
  - Writes output to files and displays results

## Building and Running

### Compile

```bash
cd merge_sort_java/src
javac *.java
```

### Run

```bash
java MergeSortExample
```

## Output Files

The program generates the following files:

- `test-file-1.txt`: First test file with 6 customer records
- `test-file-2.txt`: Second test file with 5 customer records
- `merge-output.txt`: Merged file sorted by ascending customer ID
- `sorted-contract-id.txt`: Final file sorted by descending contract ID

## Comparison with COBOL

The COBOL program uses COBOL's built-in `MERGE` and `SORT` statements, which are high-level declarative operations that handle file I/O and sorting together. In Java, these operations are implemented explicitly:

- File reading: `BufferedReader` with `Files.newBufferedReader()`
- Data structure: `List<CustomerRecord>`
- Sorting: `List.sort()` with `Comparator.comparingInt()`
- File writing: `BufferedWriter` with `Files.newBufferedWriter()`

The Java implementation maintains the same record structure and test data as the COBOL version, ensuring identical behavior.
