import java.util.*;

class DisjointSet {                        
    List<Integer> size = new ArrayList<>();
    List<Integer> par = new ArrayList<>();

    public DisjointSet(int n){     
        for(int i=0; i<n; i++){
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
}

public class accountsMerge {
    public static void main(String args[]){
        int n = 4;
        ArrayList<ArrayList<String>> acc = new ArrayList<>();
        for(int i=0; i<n; i++){
            acc.add(new ArrayList<>());
        }
        Collections.addAll(acc.get(0), "John", "johnsmith@mail.com", "john_newyork@mail.com");
        Collections.addAll(acc.get(1), "John", "johnsmith@mail.com", "john00@mail.com");
        Collections.addAll(acc.get(2), "Mary", "mary@mail.com");
        Collections.addAll(acc.get(3), "John", "johnnybravo@mail.com");

        System.out.println(accMerge(acc));
    }

    public static ArrayList<ArrayList<String>> accMerge(ArrayList<ArrayList<String>> acc){
        int n = acc.size();
        DisjointSet ds = new DisjointSet(n);
        HashMap<String, Integer> hm = new HashMap<>();
        for(int i=0; i<n; i++){
            for(int j=1; j<acc.get(i).size(); j++){
                String mail = acc.get(i).get(j);
                if(!hm.containsKey(mail)){
                    hm.put(mail, i);
                }else{
                    ds.unionBySize(i, hm.get(mail));
                }
            }
        }
        ArrayList<ArrayList<String>> mergeM = new ArrayList<>();
        for(int i=0; i<n; i++){
            mergeM.add(new ArrayList<>());
        }
        for(String mail : hm.keySet()){
            int node = ds.findUltPar(hm.get(mail));
            mergeM.get(node).add(mail);
        }

        ArrayList<ArrayList<String>> ans = new ArrayList<>();
        for(int i=0; i<n; i++){
            if(mergeM.get(i).size() == 0){
                continue;
            }
            Collections.sort(mergeM.get(i));
            ArrayList<String> temp = new ArrayList<>();
            temp.add(acc.get(i).get(0));
            temp.addAll(mergeM.get(i));
            ans.add(temp);
        }
        return ans;
    } 
}
