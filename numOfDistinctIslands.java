import java.util.*;
public class numOfDistinctIslands {    //TC - O(m*n)*(log(set(m*n)))+O((m*n)*4)
    private int[][] adjM;

    public numOfDistinctIslands( int[][] mat){     
        this.adjM = mat;
    }
    public static void main(String args[]){
        int[][] mat = {{1,1,0,1,1},
                       {1,0,0,0,0},
                       {0,0,0,0,1},
                       {1,1,0,1,1}};
        numOfDistinctIslands g = new numOfDistinctIslands(mat);
        int m = mat.length;
        int n = mat[0].length;
        boolean vis[][] = new boolean[m][n];
        HashSet<String> set = new HashSet<>();
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(mat[i][j]==1 && !vis[i][j]){
                    List<String> list = new ArrayList<>();
                    g.dfs(i, j, vis, mat, list, i, j);
                    set.add(list.toString());
                }
            } 
        }
        System.out.println(set.size());
    }

    public static String toString(int r, int c){
        return r + "," + c;
    }

    public static void dfs(int i, int j, boolean[][] vis, int[][] mat, List<String> list, int bi, int bj){
        vis[i][j] = true;
        list.add(toString(i-bi, j-bj));
        int m = mat.length;
        int n = mat[0].length;
        int dx[] = {+1, -1, 0, 0};
        int dy[] = {0, 0, +1, -1};
        for(int d=0; d<4; d++){
            int ni = i + dx[d];
            int nj = j + dy[d];
            if(ni>=0 && ni<m && nj>=0 && nj<n && !vis[ni][nj] && mat[ni][nj]==1){
                dfs(ni, nj, vis, mat, list, bi, bj);
            }
        }
    }
}
