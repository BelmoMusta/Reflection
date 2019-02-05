package com.musta.belmo.reflection;

import java.util.stream.IntStream;

public class BeanExample {

    private String name;
    private int age;


    public BeanExample() {
    }

    public BeanExample(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * factorial method to be tested
     *
     * @param n int
     * @return int
     */
    private int privateFactorialMethod(int n) {
        return IntStream.rangeClosed(1, n).reduce(1, (a, b) -> a * b);
    }

    /**
     * factorial method to be tested
     * expected exception {@link NumberFormatException}
     *
     * @param n int
     * @return int
     */
    private int privateFactorialMethodWithException(String n) { // NOSONAR : unsused
        return privateFactorialMethod(Integer.parseInt(n));
    }

    @Override
    public String toString() {
        int n = privateFactorialMethodWithException("2");
        if (n == 2) {
            return super.toString();
        }
        // won't happen 
        return "BeanExample : {}";
    }
}
