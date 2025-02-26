import java.util.*;

// unit weighted graph
public class shortestPathInUndGraph {
    public static void main(String args[]){
        int v = 9;
        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0; i<v; i++){
            adj.add(new ArrayList<>());
        }
        Collections.addAll(adj.get(0), 1,3);
        Collections.addAll(adj.get(1), 0,2,3);
        Collections.addAll(adj.get(2), 1,6);
        Collections.addAll(adj.get(3), 0,4);
        Collections.addAll(adj.get(4), 3,5);
        Collections.addAll(adj.get(5), 4,6);
        Collections.addAll(adj.get(6), 2,5,7,8);
        Collections.addAll(adj.get(7), 6,8);
        Collections.addAll(adj.get(8), 6,7);

        int[] dist = findDist(adj, 0);
        System.out.println(dist);
    }

    public static int[] findDist(List<List<Integer>> adj, int src){
        int v = adj.size();
        int dist[] = new int[v];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        while(!q.isEmpty()){
            int nd = q.poll();
            if(dist[nd] != Integer.MAX_VALUE){
                for(int nb : adj.get(nd)){
                    if(dist[nd] + 1 < dist[nb]){
                        dist[nb] = dist[nd] + 1;
                        q.add(nb);
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
}

