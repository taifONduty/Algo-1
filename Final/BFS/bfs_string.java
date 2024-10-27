package Implementation;
import java.io.File;
import java.util.*;

import Graph;

class Graph {
    private static int INT_MAX = (int) 1e9;
    private int v;
    private int e;
    private ArrayList<ArrayList<Character>> adj;
    private boolean[] visited;
    private int[] distance;
    private int[] color;
    private int[] d;
    private int[] f;
    private int[] prev;
    private int time;

    // Utility method to convert letter to index (A->0, B->1, etc.)
    private int letterToIndex(char letter) {
        return Character.toUpperCase(letter) - 'A';
    }

    // Utility method to convert index to letter (0->A, 1->B, etc.)
    private char indexToLetter(int index) {
        return (char) (index + 'A');
    }

    Graph(File file) {
        try {
            Scanner sc = new Scanner(file);
            v = sc.nextInt();
            e = sc.nextInt();
            adj = new ArrayList<ArrayList<Character>>(v);
            for (int i = 0; i < v; i++) {
                adj.add(new ArrayList<Character>());
            }

            while (sc.hasNext()) {
                char src = sc.next().charAt(0);
                char dest = sc.next().charAt(0);
                adj.get(letterToIndex(src)).add(dest);
                adj.get(letterToIndex(dest)).add(src);
            }
            sc.close();

            color = new int[26]; // Maximum 26 vertices (A-Z)
            d = new int[26];
            f = new int[26];
            prev = new int[26];

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void addVertex(int n) {
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Character>());
        }
        v += n;
    }

    void addEdge(char src, char dest) {
        int srcIndex = letterToIndex(src);
        int destIndex = letterToIndex(dest);
        if (srcIndex < v && destIndex < v) {
            adj.get(destIndex).add(src);
            adj.get(srcIndex).add(dest);
        } else {
            System.out.println("Invalid vertices");
        }
    }

    int getNumberOfVertices() {
        return v;
    }

    List<Character> getAdjacentVertices(char vertex) {
        int vertexIndex = letterToIndex(vertex);
        if (vertexIndex < v) {
            return adj.get(vertexIndex);
        } else {
            System.out.println("Invalid vertex");
            return new ArrayList<>();
        }
    }

    void printAdjacencyList() {
        for (int i = 0; i < v; i++) {
            System.out.print("Adjacency list of vertex " + indexToLetter(i) + ": ");
            for (char j : adj.get(i)) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    void bfsUtil(char s) {
        distance = new int[v];
        Arrays.fill(distance, -1);
        visited = new boolean[v];
        Queue<Character> q = new LinkedList<>();
        q.add(s);
        visited[letterToIndex(s)] = true;
        distance[letterToIndex(s)] = 0;

        while (!q.isEmpty()) {
            char u = q.poll();
            System.out.print(u + " ");
            for (char v : adj.get(letterToIndex(u))) {
                int vIndex = letterToIndex(v);
                if (!visited[vIndex]) {
                    visited[vIndex] = true;
                    distance[vIndex] = distance[letterToIndex(u)] + 1;
                    q.add(v);
                }
            }
        }

        System.out.println();
        System.out.println("Shortest distances from vertex " + s + ":");
        for (int i = 0; i < v; i++) {
            if (distance[i] != -1) {
                System.out.println("Vertex " + indexToLetter(i) + ": " + distance[i]);
            } else {
                System.out.println("Vertex " + indexToLetter(i) + ": Not reachable");
            }
        }
    }

    void bfs() {
        visited = new boolean[v];
        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                bfsUtil(indexToLetter(i));
            }
        } 
    }
}

public class bfs_string {
    public static void main(String[] args) {
        File input = new File("input.txt");
        Graph g = new Graph(input);
        g.printAdjacencyList();
        g.bfs();
    }
}