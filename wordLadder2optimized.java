import java.util.*;

public class wordLadder2optimized {
    static String beg;
    static HashMap<String,Integer> map;
    static ArrayList<ArrayList<String>> ans;
    public static void main(String args[]){
        String words[] = {"pat", "bot", "pot", "poz", "coz"};
        String start = "bat";
        String end = "coz";
        System.out.println(wordLadderSeq(start, end, words));
    }

    public static ArrayList<ArrayList<String>> wordLadderSeq(String start, String end, String[] words){
        HashSet<String> set = new HashSet<>();
        for(String w: words) set.add(w);

        Queue<String> q = new LinkedList<>();
        beg = start;
        q.add(start);

        map = new HashMap<>();
        map.put(start, 1);
        set.remove(start);

        while(!q.isEmpty()){
            String str = q.poll();
            int lev = map.get(str);
            if(str.equals(end)){
                break;
            }
            for(int i=0; i<str.length(); i++){
                for(char ch='a'; ch<='z'; ch++){
                    char arr[] = str.toCharArray();
                    arr[i] = ch;
                    String s = new String(arr);
                    if(set.contains(s)){
                        q.add(s);
                        map.put(s, lev+1);
                        set.remove(s);
                    }
                }
            }
        }
        ans = new ArrayList<>();
        if(map.containsKey(end)){
            ArrayList<String> list = new ArrayList<>();
            list.add(end);
            dfs(end, list);
        }
        return ans;
    }

    public static void dfs(String str, ArrayList<String> list){
        if(str.equals(beg)){
            ArrayList<String> temp = new ArrayList<>(list);
            Collections.reverse(temp);
            ans.add(temp);
            return;
        }
        int lev = map.get(str);
        for(int i=0; i<str.length(); i++){
            for(char ch='a'; ch<='z'; ch++){
                char arr[] = str.toCharArray();
                arr[i] = ch;
                String s = new String(arr);
                if(map.containsKey(s) && map.get(s)+1==lev){
                    list.add(s);
                    dfs(s, list);
                    list.remove(list.size()-1);
                }
            }
        }
    }
}
