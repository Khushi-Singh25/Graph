import java.util.*;

public class topologicalSortAlgo {
    private int v;
    private List<List<Integer>> adjL;

    public topologicalSortAlgo(int v){
        this.v = v;
        adjL = new ArrayList<>();
        for(int i=0; i<v; i++){
            adjL.add(new ArrayList<>());
        }
    }

    public static void main(String args[]){
        topologicalSortAlgo g = new topologicalSortAlgo(8);
        g.adjL.get(2).add(3);
        g.adjL.get(3).add(1);
        Collections.addAll(g.adjL.get(4), 0,1);
        Collections.addAll(g.adjL.get(5), 0,2);

        // int v = g.adjL.size();
        // int vis[] = new int[v];
        // Stack<Integer> s = new Stack<>();
        // for(int i=0; i<v; i++){
        //     if(vis[i]==0){
        //         g.dfs(i, vis, s, g.adjL);
        //     }
        // }
        // List<Integer> ans = new ArrayList<>();
        // while(!s.isEmpty()){
        //     ans.add(s.pop());
        // }
        // System.out.println(ans);

        int v = g.adjL.size();
        int vis[] = new int[v];
        int inDeg[] = new int[v];
        for(int i=0; i<v; i++){
            for(int node : g.adjL.get(i)){
                inDeg[node]++;
            }
        }
        List<Integer> ans = new ArrayList<>();
        g.bfs(vis, inDeg, g.adjL, ans);
        System.out.println(ans);
    }

    // // using DFS
    // public static void dfs(int node, int[] vis, Stack<Integer> s, List<List<Integer>> adjL){
    //     vis[node] = 1;
    //     for(int nb : adjL.get(node)){
    //         if(vis[nb]==0){
    //             dfs(nb, vis, s, adjL);
    //         }
    //     }
    //     s.push(node);
    // }

    // using BFS - also known as Kahn's Algo
    public static void bfs(int[] vis, int[] inDeg, List<List<Integer>> adjL, List<Integer> ans){
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<adjL.size(); i++){
            if(inDeg[i]==0){
                q.add(i);
            }
        }
        while(!q.isEmpty()){
            int node = q.poll();
            ans.add(node);
            for(int nb : adjL.get(node)){
                inDeg[nb]--;
                if(inDeg[nb]==0){
                    q.add(nb);
                }
            }
        }
    }
}
