import java.util.*;

class Pair{
    int node, tm;
    Pair(int node, int tm){
        this.node = node;
        this.tm = tm;
    }
}
class Pair2{
    long tm; int node; 
    Pair2(long tm, int node){
        this.tm = tm;
        this.node = node;
    }
}
public class numOfWaysToArriveAtDes {
    static int mod = 1000000000+7;
    public static void main(String args[]){
        int[][] roads = {{0,6,7},{0,1,2},{1,2,3},{1,3,3},{6,3,3},{3,5,1},{6,5,1},{2,5,1},{0,4,5},{4,6,2}};
        int n = 7;
        int m = 10;
        System.out.println(numOfWays(n, m, roads));
    }

    public static int numOfWays(int n, int m, int[][] roads){
        List<List<Pair>> adj = new ArrayList<>();
        for(int i=0; i<n; i++){
            adj.add(new ArrayList<>());
        }
        for(int[] r : roads){
            adj.get(r[0]).add(new Pair(r[1], r[2]));
            adj.get(r[1]).add(new Pair(r[0], r[2]));
        }
        PriorityQueue<Pair2> pq = new PriorityQueue<>((a,b)->Long.compare(a.tm, b.tm));
        pq.add(new Pair2(0,0));

        int ways[] = new int[n];
        ways[0] = 1;
        long time[] = new long[n];
        Arrays.fill(time, Long.MAX_VALUE);
        time[0] = 0;

        while(!pq.isEmpty()){
            int nd = pq.peek().node;
            long tm = pq.poll().tm;
            for(Pair neigh : adj.get(nd)){
                int nb = neigh.node;
                long nbTm = neigh.tm;
                if(tm + nbTm < time[nb]){
                    time[nb] = tm + nbTm;
                    ways[nb] = ways[nd];
                    pq.add(new Pair2(tm + nbTm, nb));
                }
                else if(tm + nbTm == time[nb]){
                    ways[nb] = (ways[nb] + ways[nd]) % mod;
                }
            }
        }
        return ways[n-1];
    }
}
