package com.musta.belmo.reflection;

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
     * classe interne commentée
     */
    static class AA {
    }

    static class BB {
        // class interne non commentée
    }
}
