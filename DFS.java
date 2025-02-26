import java.util.ArrayList;
import java.util.List;

public class DFS {                      //TC-O(V+2E), SC-O(2V)
    private int ver;
    private List<List<Integer>> adj;

    public DFS(int ver){
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
        DFS g = new DFS(9);
        g.addEdge(1,2);
        g.addEdge(1,6);
        g.addEdge(2,3);
        g.addEdge(2,4);
        g.addEdge(6,7);
        g.addEdge(6,9);
        g.addEdge(4,5);
        g.addEdge(7,8);
        g.addEdge(5,8);
        System.out.println(findDFS(g.ver, g.adj));
    }

    public static List<Integer> findDFS(int v, List<List<Integer>> adj){  
        List<Integer> dfs = new ArrayList<>();
        int vis[] = new int[v];
        vis[0] = 0;
        dfsTrav(0, vis, dfs, adj);
        return dfs;
    }

    public static void dfsTrav(int node, int[] vis, List<Integer> dfs, List<List<Integer>> adj){
        vis[node] = 1;
        dfs.add(node);
        for(int nb : adj.get(node)){
            if(vis[nb] == 0){
                dfsTrav(nb, vis, dfs, adj);
            }
        }
    }
}
