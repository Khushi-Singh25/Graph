import java.util.*;

public class KosarajuAlgo {
    public static void main(String[] args){
        int v = 5;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0; i<5; i++){
            adj.add(new ArrayList<>());
        }
        adj.get(0).add(2);
        adj.get(0).add(3);
        adj.get(1).add(0);
        adj.get(2).add(1);
        adj.get(3).add(4);
        System.out.println(adj);
    }

    public static int kosaraju(ArrayList<ArrayList<Integer>> adj) {
        int v = adj.size();
        int vis[] = new int[v];
        Stack<Integer> s = new Stack<>();
        for(int i=0; i<v; i++){
            if(vis[i] == 0){
                dfs(i, vis, adj, s);
            }
        }
        ArrayList<ArrayList<Integer>> adjR = new ArrayList<>();
        for(int i=0; i<5; i++){
            adjR.add(new ArrayList<>());
        }
        for(int i=0; i<v; i++){
            vis[i] = 0;
            for(int nb : adj.get(i)){
                adjR.get(nb).add(i);
            }
        }
        int scc = 0;
        ArrayList<ArrayList<Integer>> printSCC = new ArrayList<>();
        while(!s.isEmpty()){
            int node = s.pop();
            if(vis[node] == 0){
                scc++;
                ArrayList<Integer> temp = new ArrayList<>();
                dfs2(node, vis, adjR, temp);
                printSCC.add(temp);
            }
        }
        System.out.println(printSCC);
        return scc;
    }

    public static void dfs(int node, int[] vis, ArrayList<ArrayList<Integer>> adj, Stack<Integer> s){
        vis[node] = 1;
        for(int nb : adj.get(node)){
            if(vis[nb] == 0){
                dfs(nb, vis, adj, s);
            }
        }
        s.push(node);
    }

    public static void dfs2(int node, int[] vis, ArrayList<ArrayList<Integer>> adjR, ArrayList<Integer> temp){
        vis[node] = 1;
        temp.add(node);
        for(int nb : adjR.get(node)){
            if(vis[nb] == 0){
                dfs2(nb, vis, adjR, temp);
            }
        }
    }
}
