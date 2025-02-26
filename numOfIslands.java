// TC - O(n^2)+O((n^2)*9) ----> O(n^2)
// SC - O(n^2) ----> (DFS rec stack + vis[][])

public class numOfIslands{
    private int v;
    private int[][] adjM;

    public numOfIslands(int v, int[][] mat){     
        this.v = v;
        this.adjM = mat;
    }
    public static void main(String args[]){
        int[][] mat = {{0,1,1,0,0},
                       {0,1,1,0,0},
                       {0,0,1,0,0},
                       {0,0,0,0,0},
                       {0,0,0,0,0},
                       {1,1,0,0,1}};
        numOfIslands g = new numOfIslands(5, mat);
        int cnt = 0;
        boolean vis[][] = new boolean[g.v][g.v];
        for(int i=0; i<g.v; i++){
            for(int j=0; j<g.v; j++){
                if(g.adjM[i][j]==1 && !vis[i][j]){
                    cnt++;
                    g.dfs(i, j, vis, g.adjM);
                }
            } 
        }
        System.out.println(cnt);
    }

    public void dfs(int i, int j, boolean[][] vis, int[][] adjM){
        if(i<0 || i>=adjM.length || j<0 || j>=adjM[0].length || vis[i][j] || adjM[i][j]==0){
            return;
        }
        vis[i][j] = true;
        for(int x=-1; x<=1; x++){
            for(int y=-1; y<=1; y++){
                dfs(i+x, j+y, vis, adjM);
            }
        }
    }
}
