package com.zipcodewilmington.looplabs;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by leon on 1/25/18.
 */
public abstract class DuplicateDeleter<T> implements DuplicateDeleterInterface<T> {
    protected T[] array;

    public DuplicateDeleter() {
    }

    public DuplicateDeleter(T[] intArray) {
        this.array = intArray;
    }

    private T[] createNewArray(int size) {
        return Arrays.copyOf(array, size);
    }

    private int numberOfOccurrences(T valueToCount) {
        Stream<T> stream = Arrays.stream(array);
        return (int) stream.filter(element -> valueToCount.equals(element)).count();
    }


    public T[] removeDuplicates(int maxNumberOfDuplications) {
        Stream<T> stream = Arrays.stream(array);
        return stream.filter(element -> numberOfOccurrences(element) < maxNumberOfDuplications)
                .toArray(size -> Arrays.copyOf(array, size));

    }

    public T[] removeDuplicatesExactly(int exactNumberOfDuplications) {
        Stream<T> stream = Arrays.stream(array);
        return stream.filter(element -> numberOfOccurrences(element) != exactNumberOfDuplications)
                .toArray(this::createNewArray);
    }
}
