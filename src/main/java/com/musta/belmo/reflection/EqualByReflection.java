package com.musta.belmo.reflection;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.stream.Stream;

/**
 * @author mustabelmo
 * @version 1.0.0
 * @since 1.0.0
 */
public class EqualByReflection {

    /**
     * @param aClass {@link Class}
     * @param fields {@link String}
     */

    public static <T> BiFunction<T, T, Boolean> getEqualsFromFields(Class<T> aClass, List<String> fields) {
        return getEqualsFromFields(aClass, fields.stream());
    }

    /**
     * @param aClass {@link Class}
     * @param fields {@link String}
     */

    public static <T> BiFunction<T, T, Boolean> getEqualsFromFields(Class<T> aClass, String... fields) {
        return getEqualsFromFields(aClass, Stream.of(fields));
    }

    /**
     * @param aClass {@link Class}
     * @param fields {@link Stream}
     */

    private static <T> BiFunction<T, T, Boolean> getEqualsFromFields(Class<T> aClass, Stream<String> fields) {
        BiFunction<T, T, Boolean> equalResult;
        if (fields.count() == 0) {
            equalResult = Objects::equals;
        } else {
            equalResult = (objectA, objectB) -> fields.map(fieldName -> {
                Field field = null;
                try {
                    field = aClass.getDeclaredField(fieldName);
                    field.setAccessible(true);
                } catch (NoSuchFieldException ignored) {

                }
                return field;
            }).map(field -> {
                boolean areEqual = false;
                try {
                    if (objectA == null || objectB == null) {
                        areEqual = objectA == objectB;
                    } else if (field != null) {
                        final Object a = field.get(objectA);
                        final Object b = field.get(objectB);
                        areEqual = objectA.getClass().equals(objectB.getClass())
                                && Objects.equals(a, b);
                    } else {
                        areEqual = Objects.equals(objectA, objectB);
                    }
                } catch (IllegalAccessException ignored) {
                }
                return areEqual;
            }).reduce(true, (condA, condB) -> condA && condB);
        }
        return equalResult;
    }
}
