import java.util.*;


class Graph{
    ArrayList<ArrayList<Integer>> adj;
    Stack<Integer> stack;
    int v;
    boolean visited[];
    Graph(int vertex){
        adj = new ArrayList<ArrayList<Integer>>(vertex+1);
        this.v = vertex;
        for(int i=0;i<=vertex;i++){
            adj.add(new ArrayList<Integer>());
        }

        visited = new boolean[vertex+1];
        for(int i=0;i<=vertex;i++){
            visited[i] = false;
        }
    }
    
    void dfs(int src){
        // System.out.print(src + " ");
        visited[src] = true;
        for(int child: adj.get(src)){
            if(!visited[src]){
                dfs(src);
            }
        } 
        stack.push(src);
    }

    int[] topologicalSort(){
        stack = new Stack<Integer>();
        for(int i=1;i<=v;i++){
            if(!visited[i]){
                dfs(i);
            }
        }
        int ans[] = new int[v];
        int i = 0;
        while(!stack.isEmpty()){
            ans[i++] = stack.pop();
        }
        return ans;
    }

}

public class dfs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);    
        int vertex = sc.nextInt();
        int edge = sc.nextInt();
        Graph g = new Graph(vertex);
        for(int i=0;i<edge;i++){
            int src = sc.nextInt();
            int dest = sc.nextInt();
            g.adj.get(src).add(dest);
            // g.adj.get(dest).add(src);
        }

        for(int i=1;i<=vertex;i++){
            System.out.print(i+" -> ");
            for(int j=0;j<g.adj.get(i).size();j++){
                System.out.print(g.adj.get(i).get(j)+" ");
            }
            System.out.println();
        }


        int count = 0;
        for(int i=1;i<=vertex;i++){
            if(!g.visited[i]){
                g.dfs(i);
                System.out.println();
                count++;
            }
        } 
        System.out.println(count);
        
        System.out.println(Arrays.toString(g.topologicalSort()));
        sc.close();
    }
}
