# Java Migration of COBOL Merge Sort Program

This directory contains a Java implementation that replicates the functionality of the COBOL merge sort program from `merge_sort_test.cbl`.

## Overview

The program performs the following operations:
1. **Creates test data**: Generates two input files with hardcoded customer records
2. **Merges files**: Combines both input files and sorts by ascending customer ID
3. **Sorts merged file**: Sorts the merged output by descending contract ID

## Files

- `CustomerRecord.java` - Class representing a customer record with 5 fields
- `MergeSortExample.java` - Main program implementing merge and sort operations

## Customer Record Structure

Each customer record contains:
- `customerId` (integer, 5 digits)
- `customerLastName` (String, 50 characters)
- `customerFirstName` (String, 50 characters)
- `customerContractId` (integer, 5 digits)
- `customerComment` (String, 25 characters)

## Compilation and Execution

```bash
# Compile the Java files
cd ~/repos/COBOL-Examples/merge_sort/java/src
javac CustomerRecord.java MergeSortExample.java

# Run the program
java MergeSortExample
```

## Implementation Details

### Test Data Generation
The program creates the same test data as the COBOL version:
- **test-file-1.txt**: 6 records with customer IDs 1, 5, 10, 50, 25, 75
- **test-file-2.txt**: 5 records with customer IDs 999, 3, 30, 85, 24

### Merge Operation
Uses Java's `Collections.sort()` with a `Comparator` to sort by customer ID in ascending order:
```java
mergedRecords.sort(Comparator.comparingInt(CustomerRecord::getCustomerId));
```

### Sort Operation
Sorts the merged file by contract ID in descending order using a reversed comparator:
```java
records.sort(Comparator.comparingInt(CustomerRecord::getCustomerContractId).reversed());
```

## Output Files

The program generates three output files:
- `test-file-1.txt` - First input file
- `test-file-2.txt` - Second input file  
- `merge-output.txt` - Merged and sorted by customer ID (ascending)
- `sorted-contract-id.txt` - Sorted by contract ID (descending)

## Differences from COBOL

While the COBOL program uses built-in `MERGE` and `SORT` statements that handle file I/O and sorting together, the Java implementation:
- Explicitly handles file reading and writing
- Uses in-memory data structures (Lists)
- Implements sorting with Java's standard library comparators
- Separates file I/O, data structure management, and sorting logic into distinct steps

This approach is more verbose but provides greater flexibility and follows Java idioms.
