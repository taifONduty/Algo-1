import java.util.*;

class Graph {
    private int v;
    ArrayList<ArrayList<Integer>> adj;
    private Stack<Integer> stack;
    private boolean visited[];

    Graph(int vertex) {
        this.v = vertex;
        adj = new ArrayList<ArrayList<Integer>>(vertex);
        for(int i = 0; i < vertex; i++) {
            adj.add(new ArrayList<Integer>());
        }

        visited = new boolean[vertex];
        for(int i = 0; i < vertex; i++) {
            visited[i] = false;
        }
    }

    void dfs(int src) {
        visited[src] = true;
        for(int child: adj.get(src)) {
            if(!visited[child]) {
                dfs(child);
            }
        }
        stack.push(src);
    }

    int[] topologicalSort() {
        stack = new Stack<Integer>();
        for(int i = 0; i < v; i++) {
            if(!visited[i]) {
                dfs(i);
            }
        }
        int ans[] = new int[v];
        int i = 0;
        while(!stack.isEmpty()) {
            ans[i++] = stack.pop();
        }
        return ans;
    }

    // Modified to return transposed graph and find SCCs
    ArrayList<ArrayList<Integer>> adjTranspose() {
        ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>(v);
        for(int i = 0; i < v; i++) {
            temp.add(new ArrayList<Integer>());
        }
        for(int i = 0; i < v; i++) {
            for(int child: adj.get(i)) {
                temp.get(child).add(i);
            }
        }
        return temp;
    }

    // Helper method for second DFS
    void DFSUtil(ArrayList<ArrayList<Integer>> gr, int v, boolean[] visited, ArrayList<Integer> component) {
        visited[v] = true;
        component.add(v);
        
        for(int child: gr.get(v)) {
            if(!visited[child]) {
                DFSUtil(gr, child, visited, component);
            }
        }
    }

    // Method to find and return all SCCs
    ArrayList<ArrayList<Integer>> findSCCs() {
        // Step 1: Get vertices in order of finishing time (topological sort)
        int[] order = topologicalSort();
        
        // Step 2: Get transpose of graph
        ArrayList<ArrayList<Integer>> transposed = adjTranspose();
        
        // Reset visited array for second DFS
        Arrays.fill(visited, false);
        
        // Step 3: Do DFS of transposed graph in order of finishing times
        ArrayList<ArrayList<Integer>> sccs = new ArrayList<>();
        
        for(int i = 0; i < v; i++) {
            int vertex = order[i];
            if(!visited[vertex]) {
                ArrayList<Integer> component = new ArrayList<>();
                DFSUtil(transposed, vertex, visited, component);
                sccs.add(component);
            }
        }
        
        return sccs;
    }

    // Method to get number of SCCs
    int countSCCs() {
        return findSCCs().size();
    }
}

public class scc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int vertex = sc.nextInt();
        int edge = sc.nextInt();
        Graph g = new Graph(vertex);
        for(int i = 0; i < edge; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            g.adj.get(src).add(dest);
        }

        // Find and print SCCs
        ArrayList<ArrayList<Integer>> sccs = g.findSCCs();
        System.out.println("Number of Strongly Connected Components: " + g.countSCCs());
        System.out.println("Strongly Connected Components are:");
        for(ArrayList<Integer> component : sccs) {
            System.out.println(component);
        }
    }
}