package com.musta.belmo.util;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Random Iterator
 * @param <E>
 */
public class RandomIterator<E> implements Iterator<E> {

    private final ArrayList<E> internalCollection;
    private int currentIndex;
    private List<Integer> indices;

    public RandomIterator(Collection<E> internalCollection) {
        if (internalCollection != null) {
            this.internalCollection = new ArrayList<>(internalCollection);
        } else this.internalCollection = new ArrayList<>();

        indices = IntStream.range(0, this.internalCollection.size())
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(indices);
    }

    @Override
    public boolean hasNext() {
        return currentIndex < internalCollection.size();
    }

    @Override
    public E next() {
        if (currentIndex >= internalCollection.size()) {
            throw new NoSuchElementException();
        }
        int index = indices.get(currentIndex++);
        return internalCollection.get(index);
    }
}
