import java.util.*;

class Pair{
    int node, par;
    Pair(int node, int par){
        this.node = node;
        this.par = par;
    }
}

public class detectCycleInUndGraph {
    private int v;
    private List<List<Integer>> adjL;

    public detectCycleInUndGraph(int v){
        this.v = v;
        adjL = new ArrayList<>();
        for(int i=0; i<v; i++){
            adjL.add(new ArrayList<>());
        }
    }

    public static void main(String args[]){
        detectCycleInUndGraph g = new detectCycleInUndGraph(5);
        g.adjL.get(0).add(1);
        Collections.addAll(g.adjL.get(1), 0,2,4);
        Collections.addAll(g.adjL.get(2), 0,2,4);
        Collections.addAll(g.adjL.get(3), 0,2,4);
        Collections.addAll(g.adjL.get(4), 0,2,4);
        int v = g.adjL.size();
        boolean vis[] = new boolean[v];
        boolean flag = false;
        // for(int i=0; i<v; i++){
        //     if(!vis[i]){
        //         if(g.bfs(i, vis, g.adjL)){
        //             flag = true; break;
        //         }
        //     }
        // }
        // if(flag) System.out.println(true);
        // else System.out.println(false);

        for(int i=0; i<v; i++){
            if(!vis[i]){
                if(g.dfs(i, vis, g.adjL)){
                    flag = true; break;
                }
            }
        }
        if(flag) System.out.println(true);
        else System.out.println(false);
    }

    // TC-O(V+2E), SC-O(2V)
    // public static boolean bfs(int src, boolean vis[], List<List<Integer>> adjL){
    //     vis[src] = true;
    //     Queue<Pair> q = new LinkedList<>();
    //     q.add(new Pair(src, -1));
    //     while(!q.isEmpty()){
    //         int node = q.peek().node;
    //         int par = q.peek().par;
    //         q.remove();
    //         for(int nb : adjL.get(node)){
    //             if(!vis[nb]){
    //                 vis[nb] = true;
    //                 q.add(new Pair(nb, node));
    //             }else{
    //                 if(nb != par){
    //                     return true;
    //                 }
    //             }
    //         }
    //     }
    //     return false;
    // }
    
    // TC-O(V+2E), SC-O(2V)
    public static boolean dfs(int src, boolean vis[], List<List<Integer>> adjL){
        vis[src] = true;
        for(int nb : adjL.get(src)){
            if(!vis[nb]){
                if(dfs(nb, vis, adjL)){
                    return true;
                }
            }else{
                if(nb != src){
                    return true;
                }
            }
        }
        return false;
    }
}
