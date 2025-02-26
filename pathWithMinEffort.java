import java.util.*;

class Tuple{
    int dif, row, col;
    Tuple(int dif, int row, int col){
        this.dif = dif;
        this.row = row;
        this.col = col;
    }
}

public class pathWithMinEffort {   //TC - O(ElogV) + O((n*m)*4*log(n*m))
    public static void main(String args[]){
        int ht[][] = {{1,2,2}, {3,8,2}, {5,3,5}};
        int row = 3;
        int col = 3;
        System.out.println(minEffort(row, col, ht));
    }

    public static int minEffort(int m, int n, int ht[][]){
        PriorityQueue<Tuple> pq = new PriorityQueue<>((a,b)->Integer.compare(a.dif, b.dif));
        pq.add(new Tuple(0, 0, 0));

        int[][] diff = new int[m][n];
        for(int[] d : diff){
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        diff[0][0] = 0;
        int dx[] = {+1, -1, 0, 0};
        int dy[] = {0, 0, +1, -1}; 

        while(!pq.isEmpty()){
            int d = pq.peek().dif;
            int r = pq.peek().row;
            int c = pq.poll().col;
            if(r==m-1 && c==n-1) return d;
            for(int a=0; a<4; a++){
                int nr = r + dx[a];
                int nc = c + dy[a];
                if(nr>=0 && nr<m && nc>=0 && nc<n){
                    int newEffort = Math.max(Math.abs(ht[nr][nc]-ht[r][c]), d);
                    if(newEffort < diff[nr][nc]){
                        diff[nr][nc] = newEffort;
                        pq.add(new Tuple(newEffort, nr, nc));
                    }
                }
            }
        }
        return 0;
    }
}
