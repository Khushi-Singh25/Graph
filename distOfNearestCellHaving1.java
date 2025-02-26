import java.util.*;
class Pair{
    int row, col, dis;
    Pair(int r, int c, int d){
        this.row = r; this.col = c; this.dis = d;
    }
}

public class distOfNearestCellHaving1 {
    private int v;
    private int[][] adjM;

    public distOfNearestCellHaving1(int v, int[][] mat){
        this.v = v;
        this.adjM = mat;
    }

    public static void main(String args[]){
        int[][] mat = {{0,0,0},
                       {0,1,0},
                       {1,0,1}};
        distOfNearestCellHaving1 g = new distOfNearestCellHaving1(3, mat);
        boolean[][] vis = new boolean[g.v][g.v];
        int[][] dist = new int[g.v][g.v];
        bfs(vis, dist, g.adjM);
        System.out.println(dist);
    }

    public static void bfs(boolean[][] vis, int[][] dist, int[][] grid){
        int m = grid.length;
        int n = grid[0].length;
        Queue<Pair> q = new LinkedList<>();
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 1){
                    q.add(new Pair(i, j, 0));
                    vis[i][j] = true;
                }
            }
        }
        int dx[] = {+1, -1, 0, 0};
        int dy[] = {0, 0, +1, -1};
        while(!q.isEmpty()){
            int i = q.peek().row;
            int j = q.peek().col;
            int d = q.peek().dis;
            q.remove();
            dist[i][j] = d;
            for(int x=0; x<4; x++){
                int ni = i + dx[x];
                int nj = j + dy[x];
                if(ni>=0 && ni<m && nj>=0 && nj<n && !vis[ni][nj]){
                    q.add(new Pair(ni, nj, d+1));
                    vis[ni][nj] = true;
                }
            } 
        }
    }
}
