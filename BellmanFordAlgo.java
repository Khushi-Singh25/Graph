import java.util.*;

public class BellmanFordAlgo {
    public static void main(String args[]){
        int[][] edges = {{0,1,5}, {1,0,3}, {1,2,-1}, {2,0,1}};
        int n = 3;
        int src = 2;
        System.out.println(bellmanFord(n, edges, src));
    }

    //TC - O(V*E)
    public static int[] bellmanFord(int n, int[][] edges, int src){
        int[] dist = new int[n];
        Arrays.fill(dist, (int)1e8);
        dist[src] = 0;
        for(int i=0; i<n-1; i++){
            for(int[] ed : edges){
                int u = ed[0];
                int v = ed[1];
                int wt = ed[2];
                if(dist[u]!=(int)1e8 && dist[u]+wt<dist[v]){
                    dist[v] = dist[u] + wt;
                }
            }
        }
        for(int[] ed : edges){
            int u = ed[0];
            int v = ed[1];
            int wt = ed[2];
            if(dist[u]!=(int)1e8 && dist[u]+wt<dist[v]){
                return new int[]{-1};
            }
        }
        return dist;
    }
}
