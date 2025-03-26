import java.util.Map;

public class Fib {

    // Naive recursive solution
    public static int recfib(int n) {
        if (n < 0) throw new IllegalArgumentException("n must be non-negative");
        if (n == 0) return 0;
        if (n == 1) return 1;
        return recfib(n - 1) + recfib(n - 2);
    }

    // Dynamic programming with memoization
    public static int fib(int n, Map<Integer, Integer> map) {
        if (n < 0) throw new IllegalArgumentException("n must be non-negative");
        if (map == null) throw new IllegalArgumentException("map must not be null");

        if (n == 0) return 0;
        if (n == 1) return 1;

        if (map.containsKey(n)) return map.get(n);

        int fibN = fib(n - 1, map) + fib(n - 2, map);
        map.put(n, fibN);
        return fibN;
    }

    // Dynamic programming with tabulation
    public static int dfib(int n) {
        if (n < 0) throw new IllegalArgumentException("n must be non-negative");
        if (n == 0) return 0;
        if (n == 1) return 1;

        int[] memo = new int[n + 1];
        memo[0] = 0;
        memo[1] = 1;

        for (int i = 2; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        return memo[n];
    }
}