public class FloydWarshallAlgo {
    public static void main(String args[]){
        int[][] mat = {{0,1,43}, {1,0,6}, {-1,-1,0}};
        bellmanFord(mat);
        System.out.println(mat);
    }

    //TC - O(N^3), If we Dijkstra for each node then TC - O(V*ElogV)
    public static void bellmanFord(int[][] mat){
        int n = mat.length;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(mat[i][j] == -1){
                    mat[i][j] = Integer.MAX_VALUE;
                }
                if(i == j) mat[i][j]=0;
            }
        }
        for(int k=0; k<n; k++){
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    mat[i][j] = Math.min(mat[i][j], mat[i][k]+mat[k][j]);
                }
            }
        }
        // for(int i=0; i<n; i++){
        //     if(mat[i][i] < 0){
        //         "-ve cycle"
        //     } 
        // }
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(mat[i][j] == Integer.MAX_VALUE){
                    mat[i][j] = -1;
                }
            }
        }
    }
}
