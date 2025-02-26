import java.util.*;

class Pair{
    int node, wt;
    Pair(int node, int wt){
        this.node = node;
        this.wt = wt;
    }
}

public class printShortestPath {
    public static void main(String args[]){
        int[][] edges = {{1,2,2}, {2,5,5}, {2,3,4}, {1,4,1}, {4,3,3}, {3,5,1}};
        int n = 5;
        int m = 6;
        System.out.println(shortestPath(n, m, edges));
    }

    public static List<Integer> shortestPath(int n, int m, int[][] edges){
        List<List<Pair>> adj = new ArrayList<>();
        for(int i=0; i<=n; i++){
            adj.add(new ArrayList<>());
        }
        for(int[] ed : edges){
            adj.get(ed[0]).add(new Pair(ed[1], ed[2]));
            adj.get(ed[1]).add(new Pair(ed[0], ed[2]));
        }
        int par[] = new int[n+1];
        int dist[] = new int[n+1];
        for(int i=1; i<=n; i++){
            dist[i] = Integer.MAX_VALUE;
            par[i] = i;
        } 
        dist[1] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[0],b[0]));
        pq.add(new int[]{0,1});
        while(!pq.isEmpty()){
            int node = pq.peek()[1];
            int dis = pq.poll()[0];
            for(Pair neigh : adj.get(node)){
                int nb = neigh.node;
                int wt = neigh.wt;
                if(dis + wt < dist[nb]){
                    dist[nb] = dis + wt;
                    pq.add(new int[]{dist[nb], nb});
                    par[nb] = node;
                }
            }
        }
        List<Integer> path = new ArrayList<>();
        for(int i=1; i<=n; i++){
            if(dist[i] == Integer.MAX_VALUE){
                path.add(-1);
                return path;
            }
        }
        int node = n;
        while(par[node] != node){
            path.add(node);
            node = par[node];
        }
        path.add(1);
        Collections.reverse(path);
        return path;
    }
}