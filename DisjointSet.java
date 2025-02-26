import java.util.*;

public class DisjointSet {                        //TC-O(4)
    List<Integer> rank = new ArrayList<>();
    List<Integer> size = new ArrayList<>();
    List<Integer> par = new ArrayList<>();

    public DisjointSet(int n){     
        for(int i=0; i<=n; i++){
            rank.add(0);
            size.add(1);
            par.add(i);
        }
    }

    public int findUltPar(int node){
        if(node == par.get(node)){
            return node;
        }
        int ulp = findUltPar(par.get(node));
        par.set(node, ulp);
        return par.get(node);
    }

    public void unionByRank(int u, int v){      //Rank is distorted
        int ulp_u = findUltPar(u);
        int ulp_v = findUltPar(v);
        if(ulp_u == ulp_v){
            return;
        }
        if(rank.get(ulp_u) < rank.get(ulp_v)){
            par.set(ulp_u, ulp_v);
        }else if(rank.get(ulp_v) < rank.get(ulp_u)){
            par.set(ulp_v, ulp_u);
        }else{
            par.set(ulp_v, ulp_u);
            int r = rank.get(ulp_u);
            rank.set(ulp_u, r+1);
        }
    }

    public void unionBySize(int u, int v){
        int ulp_u = findUltPar(u);
        int ulp_v = findUltPar(v);
        if(ulp_u == ulp_v){
            return;
        }
        if(size.get(ulp_u) < size.get(ulp_v)){
            par.set(ulp_u, ulp_v);
            size.set(ulp_v, size.get(ulp_u) + size.get(ulp_v));
        }else{
            par.set(ulp_v, ulp_u);
            size.set(ulp_u, size.get(ulp_u) + size.get(ulp_v));
        }
    }

    public static void main(String args[]){
        // DisjointSet ds = new DisjointSet(7);
        // ds.unionByRank(1, 2);
        // ds.unionByRank(2, 3);
        // ds.unionByRank(4, 5);
        // ds.unionByRank(6, 7);
        // ds.unionByRank(5, 6);

        // // Are 3 and 7 in the same component ?
        // if(ds.findUltPar(3) == ds.findUltPar(7)){
        //     System.out.println("Yes");
        // }else{
        //     System.out.println("No");
        // }

        // ds.unionByRank(3, 7);
        // // Now, are 3 and 7 in the same component ?
        // if(ds.findUltPar(3) == ds.findUltPar(7)){
        //     System.out.println("Yes");
        // }else{
        //     System.out.println("No");
        // }

        DisjointSet ds = new DisjointSet(7);
        ds.unionBySize(1, 2);
        ds.unionBySize(2, 3);
        ds.unionBySize(4, 5);
        ds.unionBySize(6, 7);
        ds.unionBySize(5, 6);

        // Are 3 and 7 in the same component ?
        if(ds.findUltPar(3) == ds.findUltPar(7)){
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }

        ds.unionBySize(3, 7);
        // Now, are 3 and 7 in the same component ?
        if(ds.findUltPar(3) == ds.findUltPar(7)){
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }
    }
}


