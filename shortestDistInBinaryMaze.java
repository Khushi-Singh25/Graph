import java.util.*;

class Tuple{
    int dis, row, col;
    Tuple(int dis, int row, int col){
        this.dis = dis;
        this.row = row;
        this.col = col;
    }
}

public class shortestDistInBinaryMaze {
    public static void main(String args[]){
        int grid[][] = {{1, 1, 1, 1},
                        {1, 1, 0, 1},
                        {1, 1, 1, 1},
                        {1, 1, 0, 0},
                        {1, 0, 0, 1}};
        int src[] = {0,1};
        int des[] = {2,2};
        System.out.println(shortestPath(grid, src, des));
    }

    public static int shortestPath(int[][] grid, int[] src, int[] des){
        if(src[0]==des[0] && src[1]==des[1]){
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        Queue<Tuple> q = new LinkedList<>();
        q.add(new Tuple(0, src[0], src[1]));

        int[][] dist = new int[m][n];
        for(int[] d : dist){
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        dist[src[0]][src[1]] = 0;
        int dx[] = {+1, -1, 0, 0};
        int dy[] = {0, 0, +1, -1}; 

        while(!q.isEmpty()){
            int d = q.peek().dis;
            int r = q.peek().row;
            int c = q.poll().col;
            for(int a=0; a<4; a++){
                int nr = r + dx[a];
                int nc = c + dy[a];
                if(nr>=0 && nr<m && nc>=0 && nc<n && grid[nr][nc]==1 && d+1<dist[nr][nc]){
                    dist[nr][nc] = d+1;
                    if(nr==des[0] && nc==des[1]) return d+1;
                    q.add(new Tuple(d+1, nr, nc));
                }
            }
        }
        return -1;
    }
}
