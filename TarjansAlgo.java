import java.util.*;

public class TarjansAlgo {           //TC-O(V+2E), SC-O(V+2E)+O(3N) 
    static int timer = 1;
    public static void main(String args[]){
        int n = 4;
        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0; i<n; i++){
            adj.add(new ArrayList<>());
        }
        adj.get(0).add(1);
        adj.get(1).add(2);
        adj.get(2).add(0);
        adj.get(1).add(3);
        System.out.println(criticalConnections(n, adj));
    }

    public static List<List<Integer>> criticalConnections(int n, List<List<Integer>> adj) {
        List<List<Integer>> bridges = new ArrayList<>();
        int[] vis = new int[n];
        int[] tin = new int[n];
        int[] low = new int[n];
        dfs(0, -1, vis, tin, low, adj, bridges);
        return bridges;
    }

    public static void dfs(int node, int par, int[] vis, int[] tin, int[] low, List<List<Integer>> adj, List<List<Integer>> bridges){
        vis[node] = 1;
        tin[node] = timer;
        timer++;
        for(int nb : adj.get(node)){
            if(nb == par) continue;
            if(vis[nb] == 0){
                dfs(nb, node, vis, tin, low, adj, bridges);
                low[node] = Math.min(low[node], low[nb]);
                if(low[nb] > tin[node]){
                    bridges.add(Arrays.asList(nb, node));
                }
            }else{
                low[node] = Math.min(low[node], low[nb]);
            }
        }
    }
}
