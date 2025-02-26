import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class eventualSafeStates {
    private int v;
    private List<List<Integer>> adjL;

    public eventualSafeStates(int v){
        this.v = v;
        adjL = new ArrayList<>();
        for(int i=0; i<v; i++){
            adjL.add(new ArrayList<>());
        }
    }

    public static void main(String args[]){
        eventualSafeStates g = new eventualSafeStates(8);
        Collections.addAll(g.adjL.get(0), 1,2);
        Collections.addAll(g.adjL.get(1), 2,3);
        g.adjL.get(2).add(5);
        g.adjL.get(3).add(0);
        g.adjL.get(4).add(5);
        g.adjL.get(7).add(1);

        // int v = g.adjL.size();
        // int vis[] = new int[v];
        // int pathVis[] = new int[v];
        // int checkSafe[] = new int[v];
        // for(int i=0; i<v; i++){
        //     if(vis[i]==0){
        //         g.dfs(i, vis, pathVis, checkSafe, g.adjL);
        //     }
        // }
        // List<Integer> safeNodes = new ArrayList<>();
        // for(int i=0; i<v; i++){
        //     if(checkSafe[i]==1){
        //         safeNodes.add(i);
        //     }
        // }
        // System.out.println(safeNodes);

        int v = g.adjL.size();
        int vis[] = new int[v];
        int inDeg[] = new int[v];
        List<List<Integer>> adjRev = new ArrayList<>();   //Reverse the edges
        for(int i=0; i<v; i++){
            adjRev.add(new ArrayList<>());
        }
        for(int i=0; i<v; i++){
            for(int nb : g.adjL.get(i)){
                adjRev.get(nb).add(i);
                inDeg[i]++;
            }
        }
        List<Integer> safeNodes = new ArrayList<>();
        g.bfs(vis, inDeg, g.adjL, safeNodes);
        Collections.sort(safeNodes);                     //Sort the safeNodes 
        System.out.println(safeNodes);
    }

    // public static boolean dfs(int node, int[] vis, int[] pathVis, int[] checkSafe, List<List<Integer>> adjL){
    //     vis[node] = 1;
    //     pathVis[node] = 1;
    //     for(int nb : adjL.get(node)){
    //         if(vis[nb]==0){
    //             if(dfs(nb, vis, pathVis, checkSafe, adjL)){
    //                 return true;
    //             }
    //         }else{
    //             if(pathVis[nb]==1){
    //                 return true;
    //             }
    //         }
    //     }
    //     pathVis[node] = 0;
    //     checkSafe[node] = 1;
    //     return false;
    // }

    public static void bfs(int[] vis, int[] inDeg, List<List<Integer>> adjL, List<Integer> safeNodes){
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<adjL.size(); i++){
            if(inDeg[i]==0){
                q.add(i);
            }
        }
        while(!q.isEmpty()){
            int node = q.poll();
            safeNodes.add(node);
            for(int nb : adjL.get(node)){
                inDeg[nb]--;
                if(inDeg[nb]==0){
                    q.add(nb);
                }
            }
        }
    }
}
