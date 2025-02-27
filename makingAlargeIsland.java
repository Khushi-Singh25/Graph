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

public class makingAlargeIsland {
    public static void main(String args[]){
        int[][] grid = {{1, 1, 0, 1, 1},
                        {1, 1, 0, 1, 1},
                        {1, 1, 0, 1, 1},
                        {0, 0, 1, 0, 0},
                        {0, 0, 1, 1, 1},
                        {0, 0, 1, 1, 1}};
        System.out.println(maxConnection(grid));
    }

    public static int maxConnection(int grid[][]) {
        int n = grid.length;
        DisjointSet ds = new DisjointSet(n*n);
        int dx[] = {+1, -1, 0, 0};
        int dy[] = {0, 0, +1, -1};
    
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 0) continue;
                for(int d=0; d<4; d++){
                    int ni = i + dx[d];
                    int nj = j + dy[d];
                    if(ni>=0 && ni<n && nj>=0 && nj<n && grid[ni][nj]==1){
                        int nodeNum = i*n+j;
                        int adjnodeNum = ni*n+nj;
                        ds.unionBySize(nodeNum, adjnodeNum);
                    }
                }
            }
        }
        int maxS = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 1) continue;
                HashSet<Integer> set = new HashSet<>();
                for(int d=0; d<4; d++){
                    int ni = i + dx[d];
                    int nj = j + dy[d];
                    if(ni>=0 && ni<n && nj>=0 && nj<n && grid[ni][nj]==1){
                        int adjnodeNum = ni*n+nj;
                        set.add(ds.findUltPar(adjnodeNum));
                    }
                }
                int totSize = 0;
                for(int par : set){
                    totSize += ds.size.get(par);
                }
                maxS = Math.max(maxS, totSize+1);
            }
        }
        for(int cell=0; cell<n*n; cell++){
            maxS = Math.max(maxS, ds.size.get(ds.findUltPar(cell)));
        }
        return maxS;
    }   
}

