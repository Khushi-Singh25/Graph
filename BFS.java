import java.util.*;

public class BFS {
    private int ver;
    private List<List<Integer>> adj;

    public BFS(int ver){                 //TC-O(V+2E), SC-O(2V)
        this.ver = ver;
        adj = new ArrayList<>();
        for(int i=0; i<=ver; i++){
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v){
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
    public static void main(String args[]){
        BFS g = new BFS(9);
        g.addEdge(1,2);
        g.addEdge(1,6);
        g.addEdge(2,3);
        g.addEdge(2,4);
        g.addEdge(6,7);
        g.addEdge(6,9);
        g.addEdge(4,5);
        g.addEdge(7,8);
        g.addEdge(5,8);
        System.out.println(findBFS(g.ver, g.adj));
    }

    public static List<Integer> findBFS(int v, List<List<Integer>> adj){  
        List<Integer> bfs = new ArrayList<>();
        int vis[] = new int[v+1];
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        vis[1] = 1;
        while(!q.isEmpty()){
            int node = q.poll();
            bfs.add(node);
            for(int nb : adj.get(node)){
                if(vis[nb] == 0){
                    q.add(nb);
                    vis[nb] = 1;
                }
            }
        }
        return bfs;
    }
}
