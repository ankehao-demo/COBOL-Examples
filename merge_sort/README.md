# Merge Sort Example

This directory contains both COBOL and Java implementations of a program that demonstrates file merging and sorting operations.

## What the Programs Do

Both programs perform the same operations:

1. **Create Test Data**: Generate two test files with customer records
   - `test-file-1.txt`: 6 customer records
   - `test-file-2.txt`: 5 customer records

2. **Merge Operation**: Merge the two files and sort by customer ID (ascending)
   - Reads both input files
   - Combines them into a single list
   - Sorts by customer ID in ascending order
   - Writes to `merge-output.txt`
   - Displays the merged records

3. **Sort Operation**: Sort the merged file by contract ID (descending)
   - Reads the merged output file
   - Sorts by contract ID in descending order
   - Writes to `sorted-contract-id.txt`
   - Displays the sorted records

## Customer Record Structure

Each customer record contains 5 fields with fixed widths (total 135 characters):

| Field | Type | Width | Format |
|-------|------|-------|--------|
| Customer ID | Integer | 5 | Zero-padded (e.g., "00001") |
| Last Name | String | 50 | Space-padded right |
| First Name | String | 50 | Space-padded right |
| Contract ID | Integer | 5 | Zero-padded (e.g., "05423") |
| Comment | String | 25 | Space-padded right |

## COBOL Version

### Compile and Run

```bash
cd ~/repos/COBOL-Examples/merge_sort
cobc -x merge_sort_test.cbl -o merge_sort_test
./merge_sort_test
```

### COBOL Features Demonstrated

- `MERGE` statement: Declarative file merging with sorting
- `SORT` statement: Declarative file sorting
- Line sequential file I/O
- Fixed-length record formats

## Java Version

### Compile and Run

```bash
cd ~/repos/COBOL-Examples/merge_sort
javac CustomerRecord.java MergeSortExample.java
java MergeSortExample
```

### Java Implementation Details

- **CustomerRecord.java**: Represents a customer record with:
  - Five fields matching the COBOL structure
  - `toFixedWidthString()`: Formats record as 135-character fixed-width string
  - `fromFixedWidthString()`: Parses a line into a CustomerRecord object

- **MergeSortExample.java**: Main program with three methods:
  - `createTestData()`: Generates test files with hardcoded data
  - `mergeAndDisplayFiles()`: Merges and sorts by customer ID (ascending)
  - `sortAndDisplayFile()`: Sorts by contract ID (descending)

Uses Java's `Collections.sort()` with custom `Comparator` implementations instead of implementing sort algorithms from scratch.

## Test Data

### File 1 (test-file-1.txt)
- Customer ID 1, Contract ID 5423
- Customer ID 5, Contract ID 12323
- Customer ID 10, Contract ID 653
- Customer ID 50, Contract ID 5050
- Customer ID 25, Contract ID 7725
- Customer ID 75, Contract ID 1175

### File 2 (test-file-2.txt)
- Customer ID 999, Contract ID 1610
- Customer ID 3, Contract ID 3331
- Customer ID 30, Contract ID 8765
- Customer ID 85, Contract ID 4567
- Customer ID 24, Contract ID 247

## Expected Output

### After Merge (sorted by customer ID ascending):
1, 3, 5, 10, 24, 25, 30, 50, 75, 85, 999

### After Sort (sorted by contract ID descending):
12323, 8765, 7725, 5423, 5050, 4567, 3331, 1610, 1175, 653, 247

## Files Created

- `test-file-1.txt` - First input file
- `test-file-2.txt` - Second input file
- `merge-output.txt` - Merged and sorted by customer ID
- `sorted-contract-id.txt` - Final output sorted by contract ID
