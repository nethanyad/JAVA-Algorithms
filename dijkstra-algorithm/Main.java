/*
---------------------------------------------------------
Project:    Dijkstra's Algorithm (Graph Shortest Path)
Author:     Nethanya Dhaiphule
Language:   Java
Description:
    Implementation of Dijkstra's Algorithm using an
    adjacency list backed by singly linked lists.
    Supports graph loading from a file and interactive
    shortest path queries.
---------------------------------------------------------
*/

import java.io.*;
import java.util.*;

/**
 * Represents a single node in the adjacency list (linked list format).
 * Each node contains a destination vertex, the weight of the edge,
 * and a pointer to the next node in the list.
 */
class Node {
    String data;
    int weight;
    Node next;

    public Node(String data, int weight) {
        this.data = data;
        this.weight = weight;
        this.next = null;
    }
}

/**
 * Represents an edge in the graph, storing the destination vertex
 * and the weight of the edge.
 */
class Edge {
    String destination;
    int weight;

    public Edge(String destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }
}

/**
 * Singly linked list implementation for storing adjacency lists.
 * Edges are stored in ascending order of weight for neatness.
 */
class SinglyLinkedList {
    private Node head;

    /**
     * Inserts a new edge (destination, weight) into the list
     * while keeping it sorted by weight.
     */
    public void addSorted(String destination, int weight) {
        Node newNode = new Node(destination, weight);
        if (head == null || head.weight > weight) {
            newNode.next = head;
            head = newNode;
            return;
        }
        Node current = head;
        while (current.next != null && current.next.weight < weight) {
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
    }

    /**
     * Converts the linked list into a List<Edge> for easier processing.
     */
    public List<Edge> toList() {
        List<Edge> edges = new ArrayList<>();
        Node current = head;
        while (current != null) {
            edges.add(new Edge(current.data, current.weight));
            current = current.next;
        }
        return edges;
    }
}

/**
 * Graph class storing vertices and edges using an adjacency list.
 * Provides methods to load from a file and compute shortest paths.
 */
class Graph {
    private Map<String, SinglyLinkedList> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    /**
     * Adds a directed edge from source to destination with the given weight.
     */
    public void addEdge(String source, String destination, int weight) {
        adjacencyList.putIfAbsent(source, new SinglyLinkedList());
        adjacencyList.get(source).addSorted(destination, weight);
    }

    /**
     * Checks if the graph contains the specified node.
     */
    public boolean containsNode(String node) {
        return adjacencyList.containsKey(node);
    }

    /**
     * Implements Dijkstra's Algorithm to compute the shortest path
     * from the start node to all other nodes in the graph.
     * 
     * Complexity:
     * - O((V + E) log V) using a priority queue.
     */
    public void dijkstra(String start) {
        Map<String, Integer> distances = new HashMap<>();
        PriorityQueue<Map.Entry<String, Integer>> pq =
                new PriorityQueue<>(Map.Entry.comparingByValue());

        // Initialize distances to infinity
        for (String node : adjacencyList.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
        }

        // Distance to start node is zero
        distances.put(start, 0);
        pq.add(new AbstractMap.SimpleEntry<>(start, 0));

        while (!pq.isEmpty()) {
            Map.Entry<String, Integer> current = pq.poll();
            String currentNode = current.getKey();
            int currentDistance = current.getValue();

            // Skip if we already found a shorter path
            if (currentDistance > distances.get(currentNode)) continue;

            // Explore neighbors
            List<Edge> neighbors =
                    adjacencyList.getOrDefault(currentNode, new SinglyLinkedList()).toList();
            for (Edge edge : neighbors) {
                int newDist = currentDistance + edge.weight;
                int knownDistance = distances.getOrDefault(edge.destination, Integer.MAX_VALUE);

                if (newDist < knownDistance) {
                    distances.put(edge.destination, newDist);
                    pq.add(new AbstractMap.SimpleEntry<>(edge.destination, newDist));
                }
            }
        }

        // Display results
        for (String node : distances.keySet()) {
            int dist = distances.get(node);
            if (dist == Integer.MAX_VALUE) {
                System.out.println(start + " → " + node + ": No Path Available");
            } else {
                System.out.println(start + " → " + node + ": " + dist);
            }
        }
    }

    /**
     * Reads a graph from a file.
     * File format:
     *   Source Destination Weight
     * Lines starting with '#' are ignored.
     */
    public void loadGraphFromFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = br.readLine()) != null) {
            if (line.startsWith("#") || line.isBlank()) continue;

            String[] parts = line.split("\\s+");
            if (parts.length == 3) {
                addEdge(parts[0], parts[1], Integer.parseInt(parts[2]));
            }
        }
        br.close();
    }
}

/**
 * Main driver class for running the Dijkstra's Algorithm program.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Graph graph = new Graph();

        // Load graph from file
        System.out.print("Enter the graph file: ");
        String filename = scanner.nextLine();

        try {
            graph.loadGraphFromFile(filename);
            System.out.println("Graph loaded successfully.\n");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        // Query shortest paths
        while (true) {
            System.out.print("Enter the starting node (or type 'exit' to quit): ");
            String start = scanner.nextLine();

            if (start.equalsIgnoreCase("exit")) break;

            if (!graph.containsNode(start)) {
                System.out.println("Error: Node '" + start + "' not found in graph.");
                continue;
            }

            graph.dijkstra(start);
        }

        System.out.println("Program terminated.");
    }
}
