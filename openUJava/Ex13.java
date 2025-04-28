public class Ex13 {
    
    public static int get(int[] b , int k){
        // given b not null and represents the sum of values in [] a at the k value
        // and  0 < k < b length
        // returns the first value in b if k == 0
        // else returns the k value - the previous value
        // time: O(1)
        // storage: O(1)
        if(k == 0){
            return(b[k]);
        }else{
            return(b[k] - b[k - 1]);
        }
    }

    public static int find(int[] b, int x) {
        // binery search on b 
        // finds mid for hi and low each step based
        //  on the value of mid in raltion to x until finds x and then returns mid
        // and if x not found returns -1
        // time: O(log n)
        // storage: O(1)
        int n  = b.length;
        int lo = 0;
        int hi = n - 1;
    
        while (lo <= hi) {
            // get the mid index in b
            int mid = (lo + hi) >>> 1; 
            
            // a[mid] = b[mid]-b[mid-1] get the 'i' value in a[]            
            int val = get(b, mid);                 
            
            // returns in equals to x
            if (val == x) {
                return mid;
            // update lo to 1 above current mid if x > mid
            } else if (val < x) {
                lo = mid + 1;  
            // update hi to 1 below current mid if x < mid                       
            } else {
                hi = mid - 1;                      
            }
        }
        // not found x
        return -1;                                 
    }
    
    public static boolean superInc (int [] arr, int k){
        // use tabulation for maping combantions
        // stores them and looks for a combantion equals to k
        // Time: O(k*n)  
        // Space: O(k) 
        int n = arr.length;

        // Handle edge case: if target sum is negative.
        if (k < 0) {
            return false;
        }

        // Create a 1D boolean array to store the results for each sum from 0 - k
        // sumTab[j] will be true if a subset summing to j is possible.
        boolean[] sumTab = new boolean[k + 1];

        // true with an empty subset if k == 0.
        sumTab[0] = true;

        // Iterate through each element of the array
        for (int i = 0; i < n; i++) {
            int currentNum = arr[i];

            // Iterate backwards from the target sum k down to the current number.
            // We iterate backwards to ensure that we use each element at most once
            // per outer loop iteration. If we went forwards, sumTab[j - currentNum]
            // might have already been set using currentNum in the same pass.
            for (int j = k; j >= currentNum; j--) {
                // A sum j is possible if:
                // 1. It was already possible without using the current number (sumTab[j] was true).
                // 2. The sum (j - currentNum) was possible, and we now include currentNum.
                sumTab[j] = sumTab[j] || sumTab[j - currentNum];
            }
        }

        // The final answer is whether the target sum k is achievable.
        return sumTab[k];
    }

    public static int countEqualDiff (int [] arr, int diff){
        
    }
    public static void main(String[] args) {
        int[] arr1 = { 3, 34, 4, 12, 5, 2 };
        int k1 = 9;
        System.out.println("Array: [3, 34, 4, 12, 5, 2], Target Sum: " + k1);
        System.out.println("Subset sum exists: " + superInc(arr1, k1)); // Output: true (4 + 5)

        int[] arr2 = { 2, 3, 7, 8, 10 };
        int k2 = 11;
        System.out.println("\nArray: [2, 3, 7, 8, 10], Target Sum: " + k2);
        System.out.println("Subset sum exists: " + superInc(arr2, k2)); // Output: true (3 + 8)

        int[] arr3 = { 1, 2, 3, 7 };
        int k3 = 6;
        System.out.println("\nArray: [1, 2, 3, 7], Target Sum: " + k3);
        System.out.println("Subset sum exists: " + superInc(arr3, k3)); // Output: true (1 + 2 + 3)

        int[] arr4 = { 1, 2, 3, 4 };
        int k4 = 11;
        System.out.println("\nArray: [1, 2, 3, 4], Target Sum: " + k4);
        System.out.println("Subset sum exists: " + superInc(arr4, k4)); // Output: false
    }
}
