import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Graph {

    // Instance variables
    private int time = 0;
    private List<Integer> traversalArray;
    private int v;
    private int e;
    private List<List<Integer>> graphList;
    private int[][] graphMatrix;

    private boolean[] visited;
    private int[] startTime;
    private int[] endTime;

    // Constructor to initialize the graph with v vertices
    public Graph(int v) {
        this.v = v;
        this.e = new Random().nextInt(45 - 9 + 1) + 9;
        this.traversalArray = new ArrayList<>();
        this.graphList = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            graphList.add(new ArrayList<>());
        }
        this.graphMatrix = new int[v][v];
    }

    // Function to create a random graph
    public void createRandomGraph() {
        Random rand = new Random();
        for (int i = 0; i < this.e; i++) {
            int src = rand.nextInt(this.v);
            int dest = rand.nextInt(this.v);

            while (src == dest || this.graphMatrix[src][dest] == 1) {
                src = rand.nextInt(this.v);
                dest = rand.nextInt(this.v);
            }

            this.graphList.get(src).add(dest);
            this.graphMatrix[src][dest] = 1;
        }
    }

    // Function to print adjacency list
    public void printGraphList() {
        System.out.println("Adjacency List Representation:");
        for (int i = 0; i < this.v; i++) {
            System.out.print(i + "-->");
            for (int j : this.graphList.get(i)) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Function to print adjacency matrix
    public void printGraphMatrix() {
        System.out.println("Adjacency Matrix Representation:");
        for (int i = 0; i < this.v; i++) {
            for (int j = 0; j < this.v; j++) {
                System.out.print(this.graphMatrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Function to get the number of edges
    public int numberOfEdges() {
        return this.e;
    }

    // Function for DFS
    public void dfs() {
        this.visited = new boolean[this.v];
        this.startTime = new int[this.v];
        this.endTime = new int[this.v];

        for (int node = 0; node < this.v; node++) {
            if (!this.visited[node]) {
                traverseDFS(node);
            }
        }

        System.out.println("\nDFS Traversal: " + this.traversalArray);
    }

    // Recursive helper function for DFS
    private void traverseDFS(int node) {
        this.visited[node] = true;
        this.traversalArray.add(node);
        this.startTime[node] = this.time++;
        
        for (int neighbour : this.graphList.get(node)) {
            if (!this.visited[neighbour]) {
                System.out.println("Tree Edge: " + node + "-->" + neighbour);
                traverseDFS(neighbour);
            } else {
                if (this.startTime[node] > this.startTime[neighbour] && this.endTime[node] < this.endTime[neighbour]) {
                    System.out.println("Back Edge: " + node + "-->" + neighbour);
                } else if (this.startTime[node] < this.startTime[neighbour] && this.endTime[node] > this.endTime[neighbour]) {
                    System.out.println("Forward Edge: " + node + "-->" + neighbour);
                } else if (this.startTime[node] > this.startTime[neighbour] && this.endTime[node] > this.endTime[neighbour]) {
                    System.out.println("Cross Edge: " + node + "-->" + neighbour);
                }
            }
        }
        this.endTime[node] = this.time++;
    }
}

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        int n = 10;
        Graph g = new Graph(n);
        g.createRandomGraph();
        g.printGraphList();
        g.printGraphMatrix();
        g.dfs();
    }
}
