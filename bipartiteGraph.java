import java.util.*;

public class bipartiteGraph {
    private int v;
    private List<List<Integer>> adjL;

    public bipartiteGraph(int v){
        this.v = v;
        this.adjL = new ArrayList<>();
        for(int i=0; i<v; i++){
            adjL.add(new ArrayList<>());
        }
    }

    public static void main(String args[]){
        bipartiteGraph g = new bipartiteGraph(8);
        g.adjL.get(1).add(2);
        Collections.addAll(g.adjL.get(2), 1,3,6);
        Collections.addAll(g.adjL.get(3), 2,4);
        Collections.addAll(g.adjL.get(4), 3,5,7);
        Collections.addAll(g.adjL.get(5), 4,6);
        Collections.addAll(g.adjL.get(6), 2,5);
        Collections.addAll(g.adjL.get(7), 4,8);
        g.adjL.get(8).add(7);

        int color[] = new int[g.v];
        // color[1] = 1;
        // System.out.println(bfs(1, color, g.adjL));
        boolean flag = true;
        for(int i=0; i<g.v; i++){
            if(color[i]==0){
                if(dfs(i, 1, color, g.adjL)==false){
                    flag = false; break;
                } 
            }
        }
        if(flag) System.out.println(true);
        else System.out.println(false);
    }

    // public static boolean bfs(int src, int[] color, List<List<Integer>> adjL){
    //     Queue<Integer> q = new LinkedList<>();
    //     q.add(src);
    //     while(!q.isEmpty()){
    //         int node = q.poll();
    //         for(int nb : adjL.get(node)){
    //             if(color[nb]!=0 && color[nb]==color[node]){
    //                 return false;
    //             }
    //             else if(color[nb]==0){
    //                 color[nb] = color[node]==1 ? 2 : 1;
    //                 q.add(nb);
    //             }
    //         } 
    //     }
    //     return true;
    // }

    public static boolean dfs(int node, int clr, int[] color, List<List<Integer>> adjL){
        color[node] = clr;
        for(int nb : adjL.get(node)){
            if(color[nb]!=0 && color[nb]==color[node]){
                return false;
            }
            else if(color[nb]==0){
                int nbClr = color[node]==1 ? 2 : 1;
                if(dfs(nb, nbClr, color, adjL)==false){
                    return false;
                }
            }
        }
        return true;
    }
}
