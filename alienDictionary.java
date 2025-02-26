import java.util.*;

public class alienDictionary {
    public static void main(String args[]){
        String words[] = {"baa", "abcd", "abca", "cab", "cad"};
        String order = findOrder(words);
        System.out.println(order.isEmpty() ? "false" : order);
    }

    public static String findOrder(String words[]){
        HashMap<Character, List<Character>> adjL = new HashMap<>();
        HashMap<Character, Integer> inDeg = new HashMap<>();
        for(String word : words){
            for(char ch : word.toCharArray()){
                adjL.putIfAbsent(ch, new ArrayList<>());
                inDeg.putIfAbsent(ch, 0);
            }
        }
        for(int i=0; i<words.length-1; i++){
            String s1 = words[i];
            String s2 = words[i+1];
            int len = Math.min(s1.length(), s2.length());
            boolean flag = false; 
            for(int j=0; j<len; j++){
                char ch1 = s1.charAt(j);
                char ch2 = s2.charAt(j);
                if(ch1 != ch2){
                    adjL.get(ch1).add(ch2);
                    inDeg.put(ch2, inDeg.get(ch2)+1);
                    flag = true; break;
                }
            }
            if(!flag && s1.length()>s2.length()){
                return "";
            }
        }
        StringBuilder alienO = new StringBuilder("");
        bfs(inDeg, adjL, alienO);
        return alienO.length()==inDeg.size() ? alienO.toString() : "";
    }

    public static void bfs(HashMap<Character,Integer> inDeg, HashMap<Character,List<Character>> adjL, StringBuilder alienO){
        Queue<Character> q = new LinkedList<>();
        for(char ch : inDeg.keySet()){
            if(inDeg.get(ch)==0){
                q.add(ch);
            }
        }
        while(!q.isEmpty()){
            char node = q.poll();
            alienO.append(node);
            for(char nb : adjL.get(node)){
                inDeg.put(nb, inDeg.get(nb)-1);
                if(inDeg.get(nb)==0){
                    q.add(nb);
                }
            }
        }
    }
}
