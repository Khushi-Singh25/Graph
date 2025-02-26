// PreRequisites Tasks, Course Scheduling

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class detectCycleInDirGraph {
    private int v;
    private List<List<Integer>> adjL;

    public detectCycleInDirGraph(int v){
        this.v = v;
        adjL = new ArrayList<>();
        for(int i=0; i<v; i++){
            adjL.add(new ArrayList<>());
        }
    }

    public static void main(String args[]){
        detectCycleInDirGraph g = new detectCycleInDirGraph(10);
        g.adjL.get(1).add(2);
        g.adjL.get(2).add(3);
        g.adjL.get(3).add(4);
        g.adjL.get(3).add(7);
        g.adjL.get(4).add(5);
        g.adjL.get(5).add(6);
        g.adjL.get(7).add(5);
        g.adjL.get(8).add(9);
        g.adjL.get(9).add(10);
        g.adjL.get(10).add(8);
        
        // int v = g.adjL.size();
        // boolean vis[] = new boolean[v+1];
        // boolean pathVis[] = new boolean[v+1];
        // boolean flag = false; 
        // for(int i=1; i<=v; i++){
        //     if(!vis[i]){
        //         if(g.dfs(i, vis, pathVis, g.adjL)){
        //             flag = true; break;
        //         }
        //     }
        // }
        // if(flag) System.out.println(true);
        // else System.out.println(false);

        int v = g.adjL.size();
        int vis[] = new int[v];
        int inDeg[] = new int[v];
        for(int i=0; i<v; i++){
            for(int node : g.adjL.get(i)){
                inDeg[node]++;
            }
        }
        System.out.println(g.bfs(vis, inDeg, g.adjL));
    }

    //TC-O(V+E), SC-O(2V)
    // public static boolean dfs(int node, boolean[] vis, boolean[] pathVis, List<List<Integer>> adjL){
    //     vis[node] = true;
    //     pathVis[node] = true;
    //     for(int nb : adjL.get(node)){
    //         if(!vis[nb]){
    //             if(dfs(nb, vis, pathVis, adjL)){
    //                 return true;
    //             }
    //         }else{
    //             if(pathVis[nb]) return true;
    //         }
    //     }
    //     pathVis[node] = false;
    //     return false;
    // }

    //TC-O(2V)+O(V+E), SC-O(3V)
    public static boolean bfs(int[] vis, int[] inDeg, List<List<Integer>> adjL){
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<adjL.size(); i++){
            if(inDeg[i]==0){
                q.add(i);
            }
        }
        int cnt = 0;
        while(!q.isEmpty()){
            int node = q.poll();
            cnt++;
            for(int nb : adjL.get(node)){
                inDeg[nb]--;
                if(inDeg[nb]==0){
                    q.add(nb);
                }
            }
        }
        if(cnt == adjL.size()) return true;
        return false;
    }
}
