package com.musta.belmo.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author mustabelmo
 */
public class PrivateMethodInvoker {

    /**
     * The name field
     */
    private String name;

    /**
     * The aClass field
     */
    private Class<?> aClass;

    /**
     * The method field
     */
    private Method method;

    /**
     * The args field
     */
    private Object[] args;

    /**
     * The callingObject field
     */
    private Object callingObject;

    /**
     * The parameterTypes field
     */
    private Class<?>[] parameterTypes;

    /**
     * TODO: provide a description for this method
     *
     * @param parameterTypes { @link Class<?>}
     * @return PrivateMethodInvoker
     */
    public PrivateMethodInvoker applyOnTypes(Class<?>... parameterTypes) {
        this.parameterTypes = parameterTypes;
        return this;
    }

    /**
     * TODO: provide a description for this method
     *
     * @return Object
     */
    public Object invoke() {
        try {
            method = getaClass().getDeclaredMethod(name, parameterTypes);
            method.setAccessible(true);
            return method.invoke(callingObject, args);
        } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | SecurityException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * TODO: provide a description for this method
     *
     * @return Object
     */
    public Object invokeWithThrownExceptions() throws Throwable {
        try {
            method = getaClass().getDeclaredMethod(name, parameterTypes);
            method.setAccessible(true);
            return method.invoke(callingObject, args);
        } catch (IllegalArgumentException | IllegalAccessException | SecurityException | NoSuchMethodException ignored) {
            throw new RuntimeException(ignored);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * TODO: provide a description for this method
     *
     * @param pClass { @link Class<?>}
     * @return PrivateMethodInvoker
     */
    public static PrivateMethodInvoker from(Class<?> pClass) {
        PrivateMethodInvoker lPrivateMethod = new PrivateMethodInvoker();
        lPrivateMethod.setaClass(pClass);
        return lPrivateMethod;
    }

    /**
     * TODO: provide a description for this method
     *
     * @param pName { @link String}
     * @return PrivateMethodInvoker
     */
    public PrivateMethodInvoker name(String pName) {
        setName(pName);
        return this;
    }

    /**
     * @param pName Valeur à affecter à l'attribut {@link #name}
     */
    public void setName(String pName) {
        name = pName;
    }

    /**
     * @return Attribut {@link #aClass}
     */
    public Class<?> getaClass() {
        return aClass;
    }

    /**
     * @param pAClass Valeur à affecter à l'attribut {@link #aClass}
     */
    public void setaClass(Class<?> pAClass) {
        aClass = pAClass;
    }

    /**
     * @param pCallingObject Valeur à affecter à l'attribut {@link #callingObject}
     */
    public PrivateMethodInvoker callingObject(Object pCallingObject) {
        callingObject = pCallingObject;
        return this;
    }

    /**
     * @param pArgs Valeur à affecter à l'attribut {@link #args}
     */
    public PrivateMethodInvoker withParams(Object... pArgs) {
        args = pArgs;
        return this;
    }

}
