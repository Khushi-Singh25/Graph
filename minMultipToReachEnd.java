import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Pair{
    int st, num;
    Pair(int st, int num){
        this.st = st;
        this.num = num;
    }
}
public class minMultipToReachEnd {
    public static void main(String args[]){
        int arr[] = {3, 4, 65};
        int start = 7;
        int end = 66175;
        System.out.println(minimumMultiplications(arr, start, end));
    }

    public static int minimumMultiplications(int[] arr, int start, int end) {
        if(start == end) return 0;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0, start));
        
        int steps[] = new int[100000];
        Arrays.fill(steps, Integer.MAX_VALUE);
        steps[start] = 0;
        
        while(!q.isEmpty()){
            int st = q.peek().st;
            int num = q.poll().num;
            for(int i=0; i<arr.length; i++){
                int newNum = (num*arr[i]) % 100000;
                if(newNum == end){
                    return st+1;
                }
                if(st+1 < steps[newNum]){
                    steps[newNum] = st+1;
                    q.add(new Pair(st+1, newNum));
                }
            }
        }
        return -1;
    }
}
