public class cityWithSmallestNumOfNbAtThres {
    public static void main(String args[]){
        int[][] edges = {{0, 1, 3},
                         {1, 2, 1}, 
                         {1, 3, 4},  
                         {2, 3, 1}};
        int n = 3;   //cities
        int m = 4;   //edges
        int th = 4;   //threshold
        System.out.println(findCity(m, n, edges, th));
    }

    public static int findCity(int m, int n, int[][] mat, int th){
        int dist[][] = new int[m][m];
        for(int i=0; i<m; i++){
            for(int j=0; j<m; j++){
                if(i == j) dist[i][j] = 0;
                else dist[i][j] = Integer.MAX_VALUE;
            }
        }
        for(int ed[] : mat){
            int u = ed[0];
            int v = ed[1];
            int wt = ed[2];
            dist[u][v] = wt; dist[v][u] = wt;
        }
        for(int k=0; k<m; k++){
            for(int i=0; i<m; i++){
                for(int j=0; j<m; j++){
                    if(dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE){
                        dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]);
                    }
                }
            }
        }
        int city = -1;
        int minCity = m;
        for(int i=0; i<m; i++){
            int cnt = 0;
            for(int j=0; j<m; j++){
                if(dist[i][j] <= th) cnt++;
            }
            if(cnt <= minCity){
                minCity = cnt;
                city = i;
            }
        }
        return city;
    }
}
