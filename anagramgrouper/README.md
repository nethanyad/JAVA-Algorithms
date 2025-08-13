## Anagram Grouper 

This Java program reads a text file, extracts all words, sorts them alphabetically using **Merge Sort**, and then groups them into anagrams stored in **singly linked lists**.  
The grouped anagrams are printed to the console and saved into a new output file.

---

## 📂 How It Works
1. **User Input**: The program asks for the name of a text file.
2. **Read File**: Reads all the contents of the file.
3. **Split into Words**: Separates the words using whitespace.
4. **Sort Words**: Uses a custom Merge Sort implementation to sort words alphabetically.
5. **Group Anagrams**: Groups words that are anagrams into singly linked lists using a canonical form (sorted characters).
6. **Output**:
   - Prints the grouped anagrams to the console.
   - Saves them in a file named `<original_filename>_grouped.txt`.

---

## 📝 Input File Requirements
- The input **must** be a plain text (`.txt`) file.
- Words should be separated by spaces, tabs, or newlines.
- Example file (`words.txt`):
