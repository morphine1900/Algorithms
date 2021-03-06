package com.pillow;

import java.util.ArrayList;
import java.util.List;

public class MedianSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int L1 = nums1.length, L2 = nums2.length;
        if (L1 > L2)
            return findMedianSortedArrays(nums2, nums1);

        int s1 = 0, e1 = L1, i = 0, j = 0;
        int leftMax = 0;
        while (s1 <= e1) {
            i = (s1 + e1) / 2;
            j = (L1 + L2 + 1) / 2 - i;
            //System.out.printf("i: %d, j: %d \n",
            //        i, j);
            if (i > 0 && nums1[i-1] > nums2[j]) {
                //System.out.printf("ni-1: %d > nj: %d \n",
                //        nums1[i-1], nums2[j]);
                e1 = i - 1;
            } else if (j > 0 && i < L1 && nums2[j-1] > nums1[i]) {
                //System.out.printf("nj-1: %d > ni: %d \n",
                //        nums2[j-1], nums1[i]);
                s1 = i + 1;
            } else {
                //  found it
                if (i == 0) {
                    leftMax = nums2[j-1];
                } else if (j == 0) {
                    leftMax = nums1[i-1];
                } else {
                    leftMax = Math.max(nums1[i-1], nums2[j-1]);
                }
                break;
            }
        }
        if ((L1 + L2) % 2 == 1)
            return leftMax;
        int rightMin = 0;
        if (i == L1)
            rightMin = nums2[j];
        else if (j == L2)
            rightMin = nums1[i];
        else
            rightMin = Math.min(nums1[i], nums2[j]);
        return (rightMin + leftMax) / 2.0;
    }

    public static void main(String[] args) {
        MedianSortedArrays s = new MedianSortedArrays();
        int[][] data = {
            {}, {1},
            {1,2},{3,4}
        };
        for (int i = 0; i < data.length; i += 2) {
            double d = s.findMedianSortedArrays(data[i], data[i+1]);
            System.out.println(d);
        }
    }
}
