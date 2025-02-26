public class numOfEnclaves {
    private int[][] adjM;

    public numOfEnclaves(int[][] mat){
        this.adjM = mat;
    }

    public static void main(String args[]){
        int[][] mat = {{0, 0, 0, 0},
                        {1, 0, 1, 0},
                        {0, 1, 1, 0},
                        {0, 0, 0, 0}};
        numOfEnclaves g = new numOfEnclaves(mat);
        int m = mat.length;
        int n = mat[0].length;
        int dx[] = {+1, -1, 0, 0};
        int dy[] = {0, 0, +1, -1};
        boolean[][] vis = new boolean[m][n];
        for(int j=0; j<n; j++){
            if(!vis[0][j] && mat[0][j]==1){
                dfs(0, j, vis, mat, dx, dy);
            }
            if(!vis[m-1][j] && mat[m-1][j]==1){
                dfs(m-1, j, vis, mat, dx, dy);
            }
        }
        for(int i=0; i<m; i++){
            if(!vis[i][0] && mat[i][0]==1){
                dfs(i, 0, vis, mat, dx, dy);
            }
            if(!vis[i][n-1] && mat[i][n-1]==1){
                dfs(i, n-1, vis, mat, dx, dy);
            }
        }
        int cnt = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(!vis[i][j] && mat[i][j]==1){
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }

    public static void dfs(int i, int j, boolean[][] vis, int[][] mat, int[] dx, int[] dy){
        int m = mat.length;
        int n = mat[0].length;
        vis[i][j] = true;
        for(int d=0; d<4; d++){
            int ni = i + dx[d];
            int nj = j + dy[d];
            if(ni>=0 && ni<m && nj>=0 && nj<n && !vis[ni][nj] && mat[ni][nj]==1){
                dfs(ni, nj, vis, mat, dx, dy);
            }
        }
    }
}
