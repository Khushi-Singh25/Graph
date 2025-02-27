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

public class mostStonesRemoved {
    public static void main(String args[]){
        int[][] stones = {{0,0}, {0,1}, {1,0}, {1,2}, {2,1}, {2,2}};
        int n = stones.length;
        System.out.println(maxRemove(stones, n));
    }

    public static int maxRemove(int[][] stones, int n) {
        int maxR = 0;
        int maxC = 0;
        for(int s[] : stones){
            maxR = Math.max(maxR, s[0]);
            maxC = Math.max(maxC, s[1]);
        }
        DisjointSet ds = new DisjointSet(maxR + maxC);
        HashMap<Integer,Integer> hm = new HashMap<>();
        for(int s[] : stones){
            int nodeR = s[0];
            int nodeC = s[1] + maxR + 1;
            ds.unionBySize(nodeR, nodeC);
            hm.put(nodeR, 1);
            hm.put(nodeC, 1);
        }
        int cnt = 0;
        for(int key : hm.keySet()){
            if(ds.findUltPar(key) == key){
                cnt++;
            }
        }
        return n - cnt;           //No. of stones - Connected components
    }
}
