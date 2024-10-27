import java.util.*;



class Graph{
    private int v;
    private ArrayList<ArrayList<Integer>> adj;
    private int dist[];
    private int parent[];
    private boolean visited[];

    Graph(int vertext){
        this.v = vertext;
        adj = new ArrayList<ArrayList<Integer>>();
        
        for(int i=0;i<vertext;i++){
            adj.add(new ArrayList<Integer>());
        }
    }

    void addEdge(int v, int u){
        adj.get(v).add(u);
        adj.get(u).add(v); // for undirected graph
    }

    void addVertex(int n){
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<Integer>());
        }
        v+=n;
    }

    void bfs(){
        visited = new boolean[v];
        dist = new int[v];
        parent = new int[v];
        
        Queue<Integer> q = new LinkedList<Integer>();

        for(int i=0;i<v;i++){
            parent[i] = -1;
            visited[i] = false;
            dist[i] = Integer.MAX_VALUE;
        }

        for(int i=0;i<v;i++){
            if(!visited[i]){
                q.add(i);
                visited[i] = true;
                dist[i] = 0;
                while(!q.isEmpty()){
                    int u = q.poll();
                    System.out.print(u+" ");
                    for(int v: adj.get(u)){
                        if(!visited[v]){
                            q.add(v);
                            visited[v] = true;
                            dist[v] = dist[u]+1;
                            parent[v] = u;
                        }
                    }
                }
            }
        }

        System.out.println();
        for(int i=0;i<v;i++){
            System.out.print(dist[i]+" ");
        }

    }
}

public class bfs{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();
        Graph g = new Graph(n);

        for(int i=0;i<e;i++){
            int src = sc.nextInt();
            int dest = sc.nextInt();
            g.addEdge(src, dest);
        }

        g.bfs();
        sc.close();
    }
}