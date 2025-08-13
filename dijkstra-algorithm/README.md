# Dijkstra's Algorithm (Java Implementation)

## Description
This project implements **Dijkstra's Shortest Path Algorithm** in Java using:
- An adjacency list representation of a directed, weighted graph
- A singly linked list for storing neighbors (sorted by weight)
- A priority queue for efficient shortest path computation

It supports **loading graph data from a text file** and allows users to **interactively query shortest paths**.

---

## Features
- Load graph data from a file (`source destination weight` format)
- Custom singly linked list for adjacency storage
- Priority queue–based Dijkstra's implementation
- Detects unreachable nodes
- Interactive console interface

---

## Input File Format
- Input file must be a **plain text** (`.txt`) file.
- Words should be separated by spaces, tabs, or punctuation.
- Example (`sample.txt`):
- Each line should represent a directed edge:
  A B 5
  A C 2
  B D 4
  C D 1


---


