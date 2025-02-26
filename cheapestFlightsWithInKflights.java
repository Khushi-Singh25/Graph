import java.util.*;

class Pair{
    int node, cost;
    Pair(int node, int cost){
        this.node = node;
        this.cost = cost;
    }
}
class Tuple{
    int stops, node, dis;
    Tuple(int stops, int node, int dis){
        this.stops = stops;
        this.node = node;
        this.dis = dis;
    }
}
public class cheapestFlightsWithInKflights {
    public static void main(String args[]){
        int[][] flights = {{0,1,100}, {1,2,100}, {2,0,100}, {1,3,600}, {2,3,200}};
        int src = 0, des = 3;
        int n = 4, k = 1;
        System.out.println(cheapestFlight(n, flights, src, des, k));
    }

    public static int cheapestFlight(int n, int[][] flights, int src, int des, int k){
        List<List<Pair>> adj = new ArrayList<>();
        for(int i=0; i<n; i++){
            adj.add(new ArrayList<>());
        }
        for(int[] f : flights){
            adj.get(f[0]).add(new Pair(f[1], f[2]));
        }
        Queue<Tuple> q = new LinkedList<>();
        q.add(new Tuple(0, src, 0));

        int dist[] = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        while(!q.isEmpty()){
            int stops = q.peek().stops;
            int node = q.peek().node;
            int dis = q.poll().dis;
            for(Pair neigh : adj.get(node)){
                int nb = neigh.node;
                int cost = neigh.cost;
                if(dis+cost < dist[nb] && stops+1<=k+1){
                    dist[nb] = dis+cost;
                    q.add(new Tuple(stops+1, nb, dis+cost));
                }
            }
        }
        for(int i=0; i<n; i++){
            if(dist[i] == Integer.MAX_VALUE){
                dist[i] = -1;
            }
        }
        return dist[des];
    }
}
