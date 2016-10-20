package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by LethalLima on 10/18/16.
 * Challenge: Longest Subarray
 * Goal: Return the length of the longest subarray having elements that sum to a number than
 * or equal to k. You CANNOT reorder the array's elements.
 * Subarrays of array a = [1, 2, 3] are [1], [1, 2], [1, 2, 3], [2], [2, 3], and [3].
 */
public class LongestSubArray {

    public static void main(String[] args) throws FileNotFoundException {

        URL filePath = LongestSubArray.class.getResource("../resources/LongestSubArray.txt");

        if(filePath != null) {
            Scanner scan = new Scanner(new File(filePath.getFile()));

            int N = scan.nextInt();
            int[] a = new int[N];

            int i = 0;
            while(i < N) {
                a[i++] = scan.nextInt();
            }

            int k = scan.nextInt();
            scan.close();

            LongestSubArray longestSubArray = new LongestSubArray();
            System.out.println("Longest Subarray: " + longestSubArray.maxLength(a, k));

        } else {
            System.out.println("Could not find file.");
        }

    }

    public int maxLength(int[] a, int k) {
        int longestSubArray = 0;
        for(int i = 0; i < a.length; i++) {
            // if int at index i is greater than k, then there is no reason to check the sub arrays at that index
            if(a[i] > k) {
                continue;
            }
            // check sub arrays for each index i unless longestSubArray is
            // already larger than current subarray we're checking
            // i + j <= a.length must be true to prevent ArrayIndexOutOfBoundsException
            for(int j = 1; i + j <= a.length; j++) {
                // there is no reason to test whether sub array is less than equal to k
                // if that size sub array or less is already computed and stored, then continue
                if(longestSubArray >= j)
                    continue;

                if(isSubArraySumWithinRange(a, i, i + j, k)) {
                    longestSubArray = j;
                }
            }
        }

        return longestSubArray;
    }

    /*
        sub array sum must be less than or equal to k to return true
     */
    private boolean isSubArraySumWithinRange(int[] a, int index, int limit, int k) {
        int sum = 0;
        boolean isWithinRange = true;
        for(int i = index; isWithinRange && i < limit; i++) {
            if((sum += a[i]) > k)
                isWithinRange = false;
        }

        return isWithinRange;
    }
}

