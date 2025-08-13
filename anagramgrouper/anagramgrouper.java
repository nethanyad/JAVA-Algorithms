/* 
---------------------------------------------------------
Project:    Anagram Grouper
Author:     Nethanya Dhaiphule
Language:   Java
Description:
       This program reads an input text file, extracts words, 
	   sorts them alphabetically using Merge Sort, groups them 
	   into anagrams using a singly linked list, and saves the 
	   grouped anagrams to an output file.
---------------------------------------------------------
*/

import java.io.*;
import java.util.*;

public class anagramgrouper {

    public static String[] words;

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter input file name:");
            String inputFileName = scanner.nextLine();

            StringBuilder fileContent = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(inputFileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    fileContent.append(line).append("\n");
                }
            } catch (IOException e) {
                System.out.println("Error: Cannot read the file.");
                return;
            }

            if (fileContent.length() == 0) {
                System.out.println("Error: File is empty.");
                return;
            }

            words = fileContent.toString().trim().split("\\s+");

            MergeSort.mergeSort(words, 0, words.length - 1);

            Map<String, SinglyLinkedList> anagramGroups = AnagramGrouper.groupAnagrams(words);

            printFinalOutput(anagramGroups, inputFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printFinalOutput(Map<String, SinglyLinkedList> groups, String inputFileName) {
        System.out.println("===========================================");
        System.out.println("Grouped Anagrams from File: " + inputFileName);
        System.out.println("===========================================");

        int groupCounter = 1;
        StringBuilder formattedOutput = new StringBuilder();

        for (SinglyLinkedList group : groups.values()) {
            if (group != null && !group.isEmpty()) {
                String formattedGroup = "Group " + groupCounter + ": " + group.toString();
                System.out.println(formattedGroup);
                formattedOutput.append(formattedGroup).append("\n");
                groupCounter++;
            }
        }

        String outputFileName = inputFileName.replaceFirst("[.][^.]+$", "") + "_grouped.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            writer.write(formattedOutput.toString());
            System.out.println("Grouped anagrams saved to: " + outputFileName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}

class MergeSort {
    public static void mergeSort(String[] array, int left, int right) {
        if (left < right) {
            int middle = left + (right - left) / 2;
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);
            merge(array, left, middle, right);
        }
    }

    private static void merge(String[] array, int left, int mid, int right) {
        int leftSize = mid - left + 1;
        int rightSize = right - mid;

        String[] leftArray = new String[leftSize];
        String[] rightArray = new String[rightSize];

        manualCopy(array, left, leftArray, 0, leftSize);
        manualCopy(array, mid + 1, rightArray, 0, rightSize);

        int i = 0, j = 0, k = left;
        while (i < leftSize && j < rightSize) {
            if (leftArray[i].compareTo(rightArray[j]) <= 0) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }

        while (i < leftSize) array[k++] = leftArray[i++];
        while (j < rightSize) array[k++] = rightArray[j++];
    }

    private static void manualCopy(String[] source, int sourceStart, String[] destination, int destStart, int length) {
        for (int i = 0; i < length; i++) {
            destination[destStart + i] = source[sourceStart + i];
        }
    }
}

class SinglyLinkedList {
    private Node head;

    private static class Node {
        String data;
        Node next;
        public Node(String data) {
            this.data = data;
            this.next = null;
        }
    }

    public SinglyLinkedList() {
        head = null;
    }

    public void addSorted(String word) {
        Node newNode = new Node(word);
        if (head == null || word.compareTo(head.data) < 0) {
            newNode.next = head;
            head = newNode;
            return;
        }

        Node current = head;
        while (current.next != null && word.compareTo(current.next.data) > 0) {
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
    }

    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node current = head;
        while (current != null) {
            result.append(current.data);
            if (current.next != null) result.append(" ");
            current = current.next;
        }
        return result.toString();
    }
}

class AnagramGrouper {
    public static Map<String, SinglyLinkedList> groupAnagrams(String[] words) {
        Map<String, SinglyLinkedList> map = new LinkedHashMap<>();
        if (words == null || words.length == 0) return map;

        for (String word : words) {
            String canonicalForm = computeCanonicalForm(word);
            map.putIfAbsent(canonicalForm, new SinglyLinkedList());
            map.get(canonicalForm).addSorted(word);
        }
        return map;
    }

    private static String computeCanonicalForm(String word) {
        char[] charArray = word.toCharArray();
        insertionSort(charArray);
        return new String(charArray);
    }

    private static void insertionSort(char[] arr) {
        for (int i = 1; i < arr.length; i++) {
            char key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}
