package com.musta.belmo.reflection;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PrivateMethodInvokerTest {

    @Test
    public void testInvoke() {
        BeanExample callingObject = new BeanExample();
        Object invoke = PrivateMethodInvoker.from(BeanExample.class)
                .callingObject(callingObject)
                .name("privateFactorialMethod")
                .applyOnTypes(int.class)
                .withParams(10)
                .invoke();
         assertEquals(3628800, invoke);
    }

    @Test(expected = NumberFormatException.class)
    public void testInvokeWithThrownExceptions() throws Throwable {
        BeanExample callingObject = new BeanExample();
                PrivateMethodInvoker.from(BeanExample.class)
                .callingObject(callingObject)
                .name("privateFactorialMethodWithException")
                .applyOnTypes(String.class)
                .withParams("nnnnnnnnnnn")
                .invokeWithThrownExceptions();

    }
}