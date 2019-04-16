package com.musta.belmo.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomIteratorTest {


    @Test
    public void testRandomIterator() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        RandomIterator<Integer> randomIterator = new RandomIterator<>(integers);
        List<Integer> list = new ArrayList<>();

        while (randomIterator.hasNext()) {
            list.add(randomIterator.next());
        }
        Assert.assertEquals(6, list.size());
        Assert.assertTrue(list.contains(1));
        Assert.assertTrue(list.contains(2));
        Assert.assertTrue(list.contains(3));
        Assert.assertTrue(list.contains(4));
        Assert.assertTrue(list.contains(5));
        Assert.assertTrue(list.contains(6));
    }

    @Test
    public void testRandomIteratorFoundAfterLessOfIterations() {
        List<Integer> integers = IntStream.range(0, 1000).boxed().collect(Collectors.toList());
        RandomIterator<Integer> randomIterator = new RandomIterator<>(integers);

        int counter = 0;
        boolean found = false;
        while (randomIterator.hasNext() && !found) {

            Integer next = randomIterator.next();
            if (next.equals(501)) {
                found = true;
            }
            counter++;
        }
        System.out.printf("%d found after %d iterations%n", 501, counter);

    }

}