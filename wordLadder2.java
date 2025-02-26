import java.util.*;

public class wordLadder2 {
    public static void main(String args[]){
        String words[] = {"pat", "bot", "pot", "poz", "coz"};
        String start = "bat";
        String tar = "coz";
        System.out.println(wordLadderSeq(start, tar, words));
    }

    //TC - O(n*str.length*26)
    public static ArrayList<ArrayList<String>> wordLadderSeq(String start, String tar, String[] words){
        HashSet<String> set = new HashSet<>();
        for(String w : words) set.add(w);
        
        Queue<ArrayList<String>> q = new LinkedList<>();
        ArrayList<String> list = new ArrayList<>();
        list.add(start);
        q.add(list);
        
        ArrayList<String> usedOnLev = new ArrayList<>();
        usedOnLev.add(start);
        ArrayList<ArrayList<String>> ans = new ArrayList<>();
        int lev = 0;
        
        while(!q.isEmpty()){
            ArrayList<String> vec = q.poll();
            if(vec.size() > lev){
                lev++;
                for(String it : usedOnLev){
                    set.remove(it);
                }
                usedOnLev.clear();
            }
            String str = vec.get(vec.size()-1);
            if(str.equals(tar)){
                if(ans.size()==0) ans.add(vec);
                else if(ans.get(0).size()==vec.size()) ans.add(vec);
            }
            for(int i=0; i<str.length(); i++){
                for(char ch='a'; ch<='z'; ch++){
                    char arr[] = str.toCharArray();
                    arr[i] = ch;
                    String s = new String(arr);
                    if(set.contains(s)){
                        vec.add(s);
                        ArrayList<String> temp = new ArrayList<>(vec);
                        q.add(temp);
                        usedOnLev.add(s);
                        vec.remove(vec.size()-1);
                    }
                }
            }
        }
        return ans;
    } 
}
