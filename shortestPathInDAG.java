import java.util.*;

class Pair{
    int node, wt;
    Pair(int node, int wt){
        this.node = node;
        this.wt = wt;
    }
}
public class shortestPathInDAG {
    public static void main(String args[]){
        int[][] edges = {{0,1,2}, {0,4,1}, {4,5,4}, {4,2,2}, {1,2,3}, {2,3,6}, {5,3,1}};
        int e = edges.length;
        int v = 6;
        int[] dist = findDist(v, e, edges);
        System.out.println(dist);
    }

    public static int[] findDist(int v, int e, int[][] edges){
        List<List<Pair>> adj = new ArrayList<>();
        for(int i=0; i<v; i++){
            adj.add(new ArrayList<>());
        }
        for(int[] ed : edges){
            adj.get(ed[0]).add(new Pair(ed[1], ed[2]));
        }
        int[] vis = new int[v];
        Stack<Integer> s = new Stack<>();
        for(int i=0; i<v; i++){
            if(vis[i]==0){
                dfs(i, vis, s, adj);
            }
        }
        int dist[] = new int[v];
        for(int i=0; i<v; i++) dist[i] = Integer.MAX_VALUE;
        dist[0] = 0;
        while(!s.isEmpty()){                                  //TC-O(V+E)
            int nd = s.pop();
            if(dist[nd] != Integer.MAX_VALUE){
                for(Pair nb : adj.get(nd)){
                    if(dist[nd] + nb.wt < dist[nb.node]){
                        dist[nb.node] = dist[nd] + nb.wt;
                    }
                }
            } 
        }
        for (int i=0; i<v; i++) {
            if(dist[i] == Integer.MAX_VALUE){
                dist[i] = -1;
            }
        }
        return dist;
    }

    // TC-O(V+E)
    public static void dfs(int src, int[] vis, Stack<Integer> s, List<List<Pair>> adj){
        vis[src] = 1;
        for(Pair nb : adj.get(src)){
            if(vis[nb.node]==0){
                dfs(nb.node, vis, s, adj);
            }
        }
        s.push(src);
    }
}
