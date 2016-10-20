package main.java;

/**
 * Created by LethalLima on 10/18/16.
 */
public class LongestSubArray {

    public static void main(String[] args) {

        LongestSubArray longestSubArray = new LongestSubArray();

        int[] a = {1, 2, 3};
        int k = 6;

        System.out.println("Longest Subarray: " + longestSubArray.maxLength(a, k));

    }

    public int maxLength(int[] a, int k) {
        int longestSubArray = 0;
        for(int i = 0; i < a.length; i++) {
            // if index (i) is greater than k, then there is no reason to check the sub arrays at that index
            if(a[i] > k) {
                continue;
            }
            // check sub arrays at this index i
            for(int j = 1; i + j <= a.length; j++) {
                if(isSubArraySumWithinRange(a, i, i + j, k) && longestSubArray < j) {
                    longestSubArray = j;
                }
            }
        }

        return longestSubArray;
    }

    private boolean isSubArraySumWithinRange(int[] a, int index, int limit, int k) {
        int sum = 0;
        boolean flag = true;
        for(int i = index; flag && i < limit; i++) {
            if((sum += a[i]) > k)
                flag = false;
        }

        return flag;
    }
}

