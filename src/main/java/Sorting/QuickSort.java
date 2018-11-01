package Sorting;

import java.util.Random;

public class QuickSort implements SortingFormula {

    private Random random = new Random();

    @Override
    public <T extends Comparable> void sort(T[] array) {
        this.sort(array, 0, array.length - 1);
    }

    private <T extends Comparable> void sort(T[] array, int low, int high) {
        if (low < high) {
            int k = partition(array, low, high);
            this.sort(array, low, k - 1);
            this.sort(array, k + 1, high);
        }
    }

    /*
    This implementation is based on the Pseudocode from Algorithms and Designs
    by Michael Goodrich
     */
    private <T extends Comparable> int partitionPseudo(T[] array, int low, int high) {
        int partition = random.nextInt(high - low) + low;

        T bound = array[partition];

        this.swap(array, partition, high);

        int pivotIndex = high;
        high--;
        while (low <= high) {

            while (low <= high && array[low].compareTo(bound) <= 0)
                low++;
            while (high >= low && array[high].compareTo(bound) >= 0)
                high--;

            if (low < high) {
                this.swap(array, high, low);
                low++;
            }
        }
        this.swap(array, low, pivotIndex);
        return low;
    }

    //This is my implementation using a for loop
    private <T extends Comparable> int partition(T[] array, int low, int high) {
        int partition = random.nextInt(high - low) + low;
        T bound = array[partition];
        this.swap(array, partition, high);

        //Setting K as high - 1 prevents the algorithm from misplacing the pivot
        for (int k = high - 1; k >= low; k--) {

            /*
            This while loop prevents the algorithm from swapping already sorted elements
            on the left hand side to the right
            */
            while (low <= k && array[low].compareTo(bound) <= 0)
                low++;

            if (low < k && array[k].compareTo(bound) <= 0) {
                this.swap(array, k, low);
                low++;
            }
        }
        this.swap(array, low, high);
        return low;
    }
}
