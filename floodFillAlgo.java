// TC - O(n^2)+O((n^2)*4) ----> O(n^2)
// SC - O(n^2) ----> (DFS rec stack + vis[][])

public class floodFillAlgo {
    private int v;
    private int sr, sc, newClr;
    private int[][] adjM;

    public floodFillAlgo(int v, int sr ,int sc, int newClr, int[][] mat){     
        this.v = v;
        this.sr = sr; this.sc = sc;
        this.newClr = newClr;
        this.adjM = mat;
    }
    public static void main(String args[]){
        int[][] mat = {{1,1,0},
                       {2,2,0},
                       {2,2,2}};
        floodFillAlgo g = new floodFillAlgo(3, 2, 0 ,3, mat);
        boolean vis[][] = new boolean[g.v][g.v];

        int inClr = g.adjM[g.sr][g.sc];
        g.dfs(g.sr, g.sc, inClr, g.newClr, vis, g.adjM);
    }

    public void dfs(int i, int j, int inClr, int newClr, boolean[][] vis, int[][] grid){
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || vis[i][j] || grid[i][j]!=inClr){
            return;
        }
        grid[i][j] = newClr;
        vis[i][j] = true;
        int dx[] = {+1, -1, 0, 0};
        int dy[] = {0, 0, +1, -1};
        for(int d=0; d<4; d++){
            int ni = i + dx[d];
            int nj = j + dy[d];
            dfs(ni, nj, inClr, newClr, vis, grid);
        }
    }
}
