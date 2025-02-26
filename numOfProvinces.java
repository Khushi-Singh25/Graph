import java.util.*;

public class numOfProvinces {        //TC-O(V)+O(V+2E), SC-O(V)+O(V)
    private int v;
    private int[][] adjM;

    public numOfProvinces(int v, int[][] mat){
        this.v = v;
        this.adjM = mat;
    }
    public static void main(String args[]){
        int[][] mat = {{0,1,0,0,0,0,0,0},
                       {1,0,1,0,0,0,0,0},
                       {0,1,0,0,0,0,0,0},
                       {0,0,0,0,1,0,0,0},
                       {0,0,0,1,0,1,0,0},
                       {0,0,0,0,1,0,0,0},
                       {0,0,0,0,0,0,0,1},
                       {0,0,0,0,0,0,0,1}};
        numOfProvinces g = new numOfProvinces(8, mat);
        List<List<Integer>> adjL = new ArrayList<>();
        for(int i=0; i<g.v; i++){
            adjL.add(new ArrayList<>());
        }
        for(int i=0; i<g.v; i++){
            for(int j=0; j<g.v; j++){
                if(mat[i][j]==1 && i!=j){
                    adjL.get(i).add(j);
                    adjL.get(j).add(i);
                }
            } 
        }
        int cnt = 0;
        int vis[] = new int[g.v+1];                 //using DFS
        for(int i=0; i<g.v; i++){
            if(vis[i] == 0){
                cnt++;
                g.dfs(i, vis, adjL);
            }
        }
        System.out.println(cnt);

        // DisjointSet ds = new DisjointSet(8);           //using Disjoint Set
        // for(int i=0; i<g.v; i++){
        //     for(int j=0; j<g.v; j++){
        //         if(mat[i][j] == 1){
        //             ds.unionBySize(i, j);
        //         }
        //     }
        // }
        // int cnt = 0;
        // for(int i=0; i<g.v; i++){
        //     if(ds.par.get(i) == i){
        //         cnt++;
        //     }
        // }
        // System.out.println(cnt);
    }

    public void dfs(int node, int[] vis, List<List<Integer>> adjL){
        vis[node] = 1;
        for(int nb : adjL.get(node)){
            if(vis[nb] == 0){
                dfs(nb, vis, adjL);
            }
        }
    }
}

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
