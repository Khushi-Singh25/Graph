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

public class numOfOpToMakeNtwConnected {
    public static void main(String args[]){
        int n = 6;
        int[][] edges = {{0,1}, {0,2}, {0,3}, {1,2}, {1,3}};
        System.out.println(minOp(n, edges));
    }

    public static int minOp(int n, int[][] edges){
        DisjointSet ds = new DisjointSet(n);
        int extraE = 0;
        for(int[] ed : edges){
            int u = ed[0];
            int v = ed[1];
            if(ds.findUltPar(u) != ds.findUltPar(v)){
                ds.unionBySize(u, v);
            }else{
                extraE++;
            }
        }
        int cnt = 0;
        for(int i=0; i<n; i++){
            if(ds.par.get(i) == i){
                cnt++;
            }
        }
        if(extraE >= cnt-1){
            return cnt-1;
        }
        return -1;
    }
}
