package Sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort implements SortingFormula {

    @Override
    public <T extends Comparable> void sort(T[] array) {
        this.sort(array, 0, array.length - 1);
    }

    private <T extends Comparable> void sort(T[] array, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            this.sort(array, l, m);
            this.sort(array, m + 1, r);
            this.merge(array, l, m, r);
        }
    }

    private <T extends Comparable> void merge(T[] array, int l, int m, int r) {

        //Cannot create a generic Array because the size is unknown, so list is required
        List<T> left = new ArrayList<>(m - l + 1);
        List<T> right = new ArrayList<>(r - m);

        left.addAll(Arrays.asList(array).subList(l, m + 1));
        right.addAll(Arrays.asList(array).subList(m + 1, r + 1));

        int i = 0;
        int j = 0;
        int k = 0;

        while (i != (m - l + 1) && j != (r - m)) {
            if (left.get(i).compareTo(right.get(j)) < 0) {
                array[l + k] = left.get(i);
                i++;
            } else {
                array[l + k] = right.get(j);
                j++;
            }
            k++;
        }

        while (i != (m - l + 1)) {
            array[l + k] = left.get(i);
            i++;
            k++;
        }

        while (j != (r - m)) {
            array[l + k] = right.get(j);
            j++;
            k++;
        }
    }
}