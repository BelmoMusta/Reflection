package com.musta.belmo.reflection;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.BiFunction;

public class EqualByReflectionTest {
    @Test
    public void testGetEqualsFromFields() {
        BeanExample beanA = new BeanExample();
        BeanExample beanB = new BeanExample();

        beanA.setName("A simple name");
        beanB.setName("A simple name");

        beanA.setAge(4);
        beanB.setAge(4);
        BiFunction<BeanExample, BeanExample, Boolean> equals =
                EqualByReflection.getEqualsFromFields(BeanExample.class, "name", "age");
        // null == null
        Assert.assertTrue(equals.apply(null, null));
        // beanA is not null
        Assert.assertFalse(equals.apply(beanA, null));
        // beanB is not null
        Assert.assertFalse(equals.apply(null, beanB));
        // tautology
        Assert.assertTrue(equals.apply(beanA, beanA));
        // beanA and beanB have the same values for name and age
        Assert.assertTrue(equals.apply(beanA, beanB));
        // changing the age for beanA
        beanA.setAge(5);
        Assert.assertFalse(equals.apply(beanA, beanB));

        beanB.setAge(5);
        Assert.assertTrue(equals.apply(beanA, beanB));
    }

    @Test
    public void testGetEqualsFromFieldsWithNoFields() {
        BeanExample beanA = new BeanExample();
        BeanExample beanB = new BeanExample();

        beanA.setName("A simple name");
        beanB.setName("A simple name");

        beanA.setAge(4);
        beanB.setAge(4);
        BiFunction<BeanExample, BeanExample, Boolean> equals =
                EqualByReflection.getEqualsFromFields(BeanExample.class);
        // in the BeanExample class, the equals method is not implemented, so it's true that beanA!=beanB
        Assert.assertFalse(equals.apply(beanA, beanB));
    }

    @Test
    public void testGetEqualsFromFieldsWithNonDeclaredFields() {
        BeanExample beanA = new BeanExample();
        BeanExample beanB = new BeanExample();

        BiFunction<BeanExample, BeanExample, Boolean> equals =
                EqualByReflection.getEqualsFromFields(BeanExample.class, "street");
        // in the BeanExample class,the field 'street' does not exist,
        // so the equal method will fall back to the Object::equals one
        Assert.assertTrue(equals.apply(beanA, beanA)); // as for tautology

        Assert.assertFalse(equals.apply(beanA, beanB));
    }

}