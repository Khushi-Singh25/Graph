import java.util.*;

class iPair{
    int first, second;
    iPair(int first, int second){
        this.first = first;
        this.second = second;
    }
}

// In set, the removal of element will take logN time which will save the future iterations
// So, it depends on the graph to use which DS.
public class DijkstrasAlgoUsingSet {                
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

        TreeSet<int[]> set = new TreeSet<>(Comparator.comparingInt(a->a[0]));
        set.add(new int[]{0,src});

        while(!set.isEmpty()){
            int[] top = set.pollFirst();
            int node = top[1];
            int dis = top[0];
            for(iPair neigh : adjL.get(node)){
                int nb = neigh.first;
                int wt = neigh.second;
                if(dis + wt < dist[nb]){
                    if(dist[nb] != Integer.MAX_VALUE){
                        set.remove(new int[]{dist[nb], nb});
                    }
                    dist[nb] = dis + wt;
                    set.add(new int[]{dist[nb], nb});
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
