import java.util.*;
// TC - O(n*m)+O((n*m)*4) ----> O(n*m)
// SC - O(n*m) ----> (Queue + vis[][])

class Pair{
    int row, col, tm;
    Pair(int r, int c, int tm){
        this.row = r; this.col = c; this.tm = tm;
    } 
}
public class rottenOranges {
    private int v;
    private int[][] adjM;

    public rottenOranges(int v, int[][] mat){     
        this.v = v;
        this.adjM = mat;
    }
    public static void main(String args[]){
        int[][] mat = {{0,1,2},
                       {0,1,1},
                       {2,1,1}};
        rottenOranges g = new rottenOranges(3, mat);
        g.bfs(g.adjM);
    }

    public void bfs(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;
        int vis[][] = new int[m][n];
        Queue<Pair> q = new LinkedList<>();
        int freshO = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j]==2){
                    q.add(new Pair(i,j,0));
                    vis[i][j] = 2;
                }else if(grid[i][j]==1){
                    freshO++;
                }
            }
        }
        int tm = 0;
        int dx[] = {+1, -1, 0, 0};
        int dy[] = {0, 0, +1, -1};
        int cntFresh = 0;
        while(!q.isEmpty()){
            int r = q.peek().row;
            int c = q.peek().col;
            int t = q.peek().tm;
            q.remove();
            tm = Math.max(tm, t);
            for(int d=0; d<4; d++){
                int nr = r + dx[d];
                int nc = c + dy[d];
                if(nr>=0 && nr<m && nc>=0 && nc<n && vis[nr][nc]==0 && grid[nr][nc]==1){
                    q.add(new Pair(nr, nc, t+1));
                    vis[nr][nc] = 2;
                    cntFresh++;
                }
            }
        }
        if(cntFresh != freshO) System.out.println(-1);
        else System.out.println(tm);
    }
}
