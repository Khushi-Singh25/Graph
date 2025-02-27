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

public class numOfIslands2 {
    public static void main(String args[]){
        int rows = 4;
        int cols = 5;
        int[][] queries = {{1,1}, {0,1}, {3,3}, {3,4}};
        System.out.println(func(rows, cols, queries));
    }

    public static List<Integer> func(int n, int m, int[][] queries){
        DisjointSet ds = new DisjointSet(n*m);
        List<Integer> ans = new ArrayList<>();
        int dx[] = {+1, -1, 0, 0};
        int dy[] = {0, 0, +1, -1};

        int[][] vis = new int[n][m];
        int cnt = 0;
        for(int i=0; i<queries.length; i++){
            int row = queries[i][0];
            int col = queries[i][1];
            if(vis[row][col] == 1){
                ans.add(cnt);
                continue;
            }
            vis[row][col] = 1;
            cnt++;
            for(int d=0; d<4; d++){
                int nr = row + dx[d];
                int nc = col + dy[d];
                if(nr>=0 && nr<n && nc>=0 && nc<m && vis[nr][nc]==1){
                    int nodeNum = row*m+col;
                    int adjnodeNum = nr*m+nc;
                    if(ds.findUltPar(nodeNum) != ds.findUltPar(adjnodeNum)){
                        cnt--;
                        ds.unionBySize(nodeNum, adjnodeNum);
                    }
                }
            }
            ans.add(cnt);
        }
        return ans;        
    }
}