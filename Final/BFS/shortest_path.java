import java.util.*;

class Graph{


    int bfs(String s, String t){
        int sourcX = s.charAt(0)-'a';
        int sourcY = s.charAt(1)-'1';
        int destX = t.charAt(0)-'a';
        int destY = t.charAt(1)-'1';

        Queue<Integer> q = new LinkedList<Integer>();
        boolean visited[][] = new boolean[8][8];
        int dist[][] = new int[8][8];
        int dx[] = {-2,-2,-1,-1,1,1,2,2};
        int dy[] = {-1,1,-2,2,-2,2,-1,1};
        q.add(sourcX*8+sourcY);
        visited[sourcX][sourcY] = true;
        dist[sourcX][sourcY] = 0;
        while(!q.isEmpty()){
            int u = q.poll();
            int x = u/8;
            int y = u%8;
            if(x == destX && y == destY){
                return dist[x][y];
            }
            for(int i=0;i<8;i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
                if(nx>=0 && nx<8 && ny>=0 && ny<8 && !visited[nx][ny]){
                    q.add(nx*8+ny);
                    visited[nx][ny] = true;
                    dist[nx][ny] = dist[x][y]+1;
                }
            }
        }

        return 0;
    }
}


public class shortest_path {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Graph g = new Graph();
        while(n-- > 0){
            String s = sc.next();
            String t = sc.next();
            System.out.println(g.bfs(s,t));
        }
    }
}
