public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure nums1 is the smaller array for optimization
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        
        int m = nums1.length;
        int n = nums2.length;
        int totalLeft = (m + n + 1) / 2; // Number of elements on the left side
        
        // Binary search on the smaller array
        int left = 0, right = m;
        
        while (left <= right) {
            // Partition indices
            int cut1 = (left + right) / 2;  // Elements from nums1 on left side
            int cut2 = totalLeft - cut1;    // Elements from nums2 on left side
            
            // Left and right elements around the cuts
            int left1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int left2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1];
            int right1 = (cut1 == m) ? Integer.MAX_VALUE : nums1[cut1];
            int right2 = (cut2 == n) ? Integer.MAX_VALUE : nums2[cut2];
            
            // Check if we found the correct partition
            if (left1 <= right2 && left2 <= right1) {
                // Perfect partition found
                if ((m + n) % 2 == 0) {
                    // Even total length - median is average of two middle elements
                    return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
                } else {
                    // Odd total length - median is the maximum of left elements
                    return Math.max(left1, left2);
                }
            }
            // Adjust binary search bounds
            else if (left1 > right2) {
                // Too many elements from nums1 on left side
                right = cut1 - 1;
            } else {
                // Too few elements from nums1 on left side  
                left = cut1 + 1;
            }
        }
        
        return -1; // Should never reach here with valid input
    }
    
    // Alternative approach: Merge and find median (O(m+n) - for comparison)
    public double findMedianSortedArraysBruteForce(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[] merged = new int[m + n];
        int i = 0, j = 0, k = 0;
        
        // Merge the arrays
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                merged[k++] = nums1[i++];
            } else {
                merged[k++] = nums2[j++];
            }
        }
        
        while (i < m) merged[k++] = nums1[i++];
        while (j < n) merged[k++] = nums2[j++];
        
        // Find median
        int total = m + n;
        if (total % 2 == 0) {
            return (merged[total/2 - 1] + merged[total/2]) / 2.0;
        } else {
            return merged[total/2];
        }
    }
    
    // Test method
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test cases
        int[][] nums1Tests = {{1,3}, {1,2}, {0,0}, {}, {2}};
        int[][] nums2Tests = {{2}, {3,4}, {0,0}, {1}, {}};
        double[] expected = {2.0, 2.5, 0.0, 1.0, 2.0};
        
        System.out.println("Testing Binary Search Approach (O(log(m+n))):");
        for (int i = 0; i < nums1Tests.length; i++) {
            double result = solution.findMedianSortedArrays(nums1Tests[i], nums2Tests[i]);
            System.out.printf("nums1=%s, nums2=%s -> %.5f (Expected: %.5f) %s%n",
                java.util.Arrays.toString(nums1Tests[i]),
                java.util.Arrays.toString(nums2Tests[i]),
                result, expected[i],
                Math.abs(result - expected[i]) < 1e-5 ? "✓" : "✗");
        }
        
        System.out.println("\nTesting Brute Force Approach (O(m+n)):");
        for (int i = 0; i < nums1Tests.length; i++) {
            double result = solution.findMedianSortedArraysBruteForce(nums1Tests[i], nums2Tests[i]);
            System.out.printf("nums1=%s, nums2=%s -> %.5f (Expected: %.5f) %s%n",
                java.util.Arrays.toString(nums1Tests[i]),
                java.util.Arrays.toString(nums2Tests[i]),
                result, expected[i],
                Math.abs(result - expected[i]) < 1e-5 ? "✓" : "✗");
        }
    }
}