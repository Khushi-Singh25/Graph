import java.util.*;

class Pair{
    String str; int lev;
    Pair(String str, int lev){
        this.str = str;
        this.lev = lev;
    }
}
public class wordLadder1 {
    public static void main(String args[]){
        String words[] = {"hot", "dot", "dog", "lot", "log", "cog"};
        String start = "hit";
        String tar = "cog";
        System.out.println(wordLadderLength(start, tar, words));
    }

    //TC - O(n*str.length*26)
    public static int wordLadderLength(String start, String tar, String[] words){
        HashSet<String> set = new HashSet<>();
        for(String w : words) set.add(w);

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(start, 1));
        set.remove(start);
        
        while(!q.isEmpty()){
            String word = q.peek().str;
            int lev = q.peek().lev;
            q.poll();
            if(word.equals(tar)){
                return lev;
            }
            for(int i=0; i<word.length(); i++){
                for(char ch='a'; ch<='z'; ch++){
                    char arr[] = word.toCharArray();
                    arr[i] = ch;
                    String s = new String(arr);
                    if(set.contains(s)){
                        q.add(new Pair(s, lev+1));
                        set.remove(s);
                    }
                }
            }
        }
        return 0;
    } 
}
