package com.musta.belmo.reflection;

import java.util.stream.IntStream;

public class BeanExample {

    private String name;
    private int age;


    boolean valid;
    /**
     * this field is commented out
     */
    private int commentedField;

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

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    /**
     * cette méthode est commentée,  à ne pas toucher
     */
    public void commentedMethod() {

    }

    public void voidMethod() {

    }

    public boolean verifier(String age) {
        return true;
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
     * @param n int
     * @return int
     */
    private int privateFactorialMethodWithException(String n) {
        return IntStream.rangeClosed(1, Integer.parseInt(n)).reduce(1, (a, b) -> a * b);
    }
}
