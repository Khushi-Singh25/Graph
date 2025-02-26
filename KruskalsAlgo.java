import java.util.*;

class DisjointSet {                        
    List<Integer> size = new ArrayList<>();
    List<Integer> par = new ArrayList<>();

    public DisjointSet(int n){     
        for(int i=0; i<n; i++){
            size.add(1);
            par.add(i);
        }
    }
    public int findUltPar(int node){
        if(node == par.get(node)){
            return node;
        }
        int ulp = findUltPar(par.get(node));
        par.set(node, ulp);
        return par.get(node);
    }
    public void unionBySize(int u, int v){
        int ulp_u = findUltPar(u);
        int ulp_v = findUltPar(v);
        if(ulp_u == ulp_v){
            return;
        }
        if(size.get(ulp_u) < size.get(ulp_v)){
            par.set(ulp_u, ulp_v);
            size.set(ulp_v, size.get(ulp_u) + size.get(ulp_v));
        }else{
            par.set(ulp_v, ulp_u);
            size.set(ulp_u, size.get(ulp_u) + size.get(ulp_v));
        }
    }
}

public class KruskalsAlgo {                    //TC - O(ElogE)
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

    public static int spanningTree(int n, int e, List<List<int[]>> adj) {
        DisjointSet ds = new DisjointSet(5);
        List<int[]> edges = new ArrayList<>();
        for(int i=0; i<n; i++){
            for(int[] neigh : adj.get(i)){
                int nb = neigh[0];
                int wt = neigh[1];
                edges.add(new int[]{i, nb, wt});
            }
        }
        Collections.sort(edges, (a,b) -> Integer.compare(a[2],b[2]));
        List<int[]> mst = new ArrayList<>();
        int minS = 0;

        for(int ed[] : edges){
            int u = ed[0];
            int v = ed[1];
            int wt = ed[2];
            if(ds.findUltPar(u) != ds.findUltPar(v)){
                minS += wt;
                mst.add(new int[]{u, v});
                ds.unionBySize(u, v);
            }
        }
        System.out.println(mst);
        return minS;
    }
}


