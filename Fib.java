package java;
import java.util.Map;

public class Fib{
    public int recfib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return recfib(n-1) + recfib(n-2);
    }

// Dynamic programming solution using memoization

    public static int fib(int n, Map<Integer,Integer> map){

        if(n ==0){
            return 0;
        }

        if(n ==1){
            return 1;
        }

        if(map.containsKey(n)){
            return map.get(n);
        }

        Integer fibForN = fib(n-1,map) + fib(n-2,map);
        map.put(n, fibForN);

        return fibForN; 

    }   
// Dynamic programming solution using tabulation

    public int dfib(int n) {
        int[] memo = new int[n+1];
        memo[0] = 0;
        memo[1] = 1;
        for (int i = 2; i <= n; i++) {
            memo[i] = memo[i-1] + memo[i-2];
        }
        return memo[n];
    }
}
