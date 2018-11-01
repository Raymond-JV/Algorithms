package Sorting;

import java.util.Arrays;

public interface SortingFormula {

    <T extends Comparable> void sort(T array[]);

    default <T extends Comparable> void swap(T[] array, int i, int j) {
        T tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    default <T> void inlinePrint(T[] array) {
        Arrays.stream(array).forEach(x -> System.out.print(x + " "));
        System.out.println();
    }
}
