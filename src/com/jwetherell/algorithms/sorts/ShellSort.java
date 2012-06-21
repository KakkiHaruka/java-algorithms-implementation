package com.jwetherell.algorithms.sorts;

import java.util.ArrayList;
import java.util.List;


/**
 * Shellsort, also known as Shell sort or Shell's method, is an in-place comparison sort. It generalizes an 
 * exchanging sort, such as insertion or bubble sort, by starting the comparison and exchange of elements with 
 * elements that are far apart before finishing with neighboring elements. Starting with far apart elements 
 * can move some out-of-place elements into position faster than a simple nearest neighbor exchange.
 * Family: Exchanging.
 * Space: In-place.
 * Stable: False.
 * 
 * Average case = depends on the gap
 * Worst case = O(n * log^2 n)
 * Best case = O(n)
 * 
 * http://en.wikipedia.org/wiki/Shell_sort
 * 
 * @author Justin Wetherell <phishman3579@gmail.com>
 */
public abstract class ShellSort<T extends Comparable<T>> {

    private ShellSort() { }


    public static <T extends Comparable<T>> T[] sort(int[] shells, T[] unsorted) {
        List<List<T>> subarrays = null;
        for (int gap : shells) {
            //Allocate arrays
            subarrays = new ArrayList<List<T>>(gap);
            for (int i=0; i<gap; i++) {
                subarrays.add(new ArrayList<T>(10));
            }
            //Populate sub-arrays
            T v = null;
            for (int i=0; i<unsorted.length; i++) {
                for (int j=0; j<gap; j++) {
                    if (i>=unsorted.length) continue;
                    v = unsorted[i++];
                    List<T> list = subarrays.get(j);
                    list.add(v);
                }
            }
            //Sort all sub-arrays
            sortSubarrays(subarrays);
            //Push the sub-arrays into the int array
            int k=0;
            int iter = 0;
            while (k<unsorted.length) {
                for (int j=0; j<gap; j++) {
                    if (k>=unsorted.length) continue;
                    unsorted[k++] = subarrays.get(j).get(iter);
                }
                iter++;
            }
        }
        return unsorted;
    }

    private static <T extends Comparable<T>> void sortSubarrays(List<List<T>> lists) {
        for (List<T> list : lists) {
            sort(list);
        }
    }

    /**
     * Insertion sort
     * 
     * @param list List to be sorted.
     */
    private static <T extends Comparable<T>> void sort(List<T> list) {
        for (int i=1; i<list.size(); i++) {
            T a = null;
            T b = null;
            for (int j=i; j>0; j--) {
                a = list.get(j);
                b = list.get(j-1);
                if (a.compareTo(b)<0) {
                    list.set(j-1, a);
                    list.set(j, b);
                } else {
                    break;
                }
            }
        }
    }
}
