import java.util.*;

class iPair{
    int first, second;
    iPair(int first, int second){
        this.first = first;
        this.second = second;
    }
}

// We can use only Queue also but it will give unnecessary iterations
public class DijkstrasAlgoUsingPQ {                  //TC-O(ElogV)
    public static void main(String args[]){
        int v = 6;
        ArrayList<ArrayList<iPair>> adjL = new ArrayList<>();
        for(int i=0; i<v; i++){
            adjL.add(new ArrayList<>());
        }
        int src = 0;
        Collections.addAll(adjL.get(0), new iPair(1,4), new iPair(2,4));
        Collections.addAll(adjL.get(1), new iPair(0,4), new iPair(2,2));
        Collections.addAll(adjL.get(2), new iPair(0,4), new iPair(1,2), 
        new iPair(3,3), new iPair(4,1), new iPair(5,6));
        Collections.addAll(adjL.get(3), new iPair(2,3), new iPair(5,2));
        Collections.addAll(adjL.get(4), new iPair(2,1), new iPair(5,3));
        Collections.addAll(adjL.get(5), new iPair(2,6), new iPair(3,2),
        new iPair(4,3));

        int dist[] = new int[v];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[0],b[0]));
        pq.add(new int[]{0,src});

        while(!pq.isEmpty()){  //-----------> V
            int node = pq.peek()[1];
            int dis = pq.poll()[0];  //------------> log(heap size)
            for(iPair neigh : adjL.get(node)){  //----------> V-1 (total no.of edges)
                int nb = neigh.first;
                int wt = neigh.second;
                if(dis + wt < dist[nb]){
                    dist[nb] = dis + wt;
                    pq.add(new int[]{dist[nb], nb});  //-----------> log(heap size)
                }
            }
        }
        for(int i=0; i<v; i++){
            if(dist[i] == Integer.MAX_VALUE){
                dist[i] = -1;
            }
        }
        System.out.println(dist);
    }
}

// Overall time complexity -------->
// O(V * (log(heap size) + ne * (log(heap size))))
// O(V * (log(heap size)(ne + 1)))
// O(V * (log(heap size)(V-1 +1)))
// O(V^2 * (log(heap size)))
// O(V^2 * log(V^2))                becoz at worst case every node will push other all nodes 
// O(E * 2logV)                     becoz each node V having V-1 edges = V^2 = E
// O(ElogV)
