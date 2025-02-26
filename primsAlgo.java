import java.util.*;

class Pair{
    int wt; int node; int par;
    Pair(int wt, int node, int par){
        this.wt = wt;
        this.node = node;
        this.par = par;
    }
}

public class primsAlgo {     //TC - O(ElogE + ElogE) -----> O(ElogE)
    public static void main(String args[]){
        int v = 5, e = 6;
        List<List<int[]>> adj = new ArrayList<>();
        for(int i=0; i<v; i++){
            adj.add(new ArrayList<>());
        }
        Collections.addAll(adj.get(0), new int[]{1,2}, new int[]{2,1});
        Collections.addAll(adj.get(1), new int[]{0,2}, new int[]{2,1});
        Collections.addAll(adj.get(2), new int[]{0,1}, new int[]{1,1}, new int[]{4,2}, new int[]{3,2});
        Collections.addAll(adj.get(3), new int[]{2,2}, new int[]{4,1});
        Collections.addAll(adj.get(4), new int[]{2,2}, new int[]{3,1});
        
        System.out.println(spanningTree(v, e, adj));
    }

    public static int spanningTree(int v, int e, List<List<int[]>> adj) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> Integer.compare(a.wt, b.wt));
        pq.add(new Pair(0,0,-1));
        
        boolean vis[] = new boolean[v];
        List<int[]> mst = new ArrayList<>();     // MST edges
        int minS = 0;                            // MST sum
        while(!pq.isEmpty()){    //------> E
            int node = pq.peek().node;
            int wt = pq.peek().node;
            int par = pq.poll().par;    //-------> logE
            if(!vis[node]){
                vis[node] = true;
                minS += wt;
                if(par!=-1){
                    mst.add(new int[]{node, par});
                }
                for(int[] neigh : adj.get(node)){    //-------> E
                    int nb = neigh[0];
                    int w = neigh[1];
                    if(!vis[nb]){
                        pq.add(new Pair(w, nb, node));    //-------> logE
                    }
                }
            }
        }
        System.out.println(mst);
        return minS;
    }
}
