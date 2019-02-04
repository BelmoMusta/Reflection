package com.musta.belmo.reflection;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
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
    public static <T> BiFunction<T, T, Boolean> getEqualsFromFields(Class<T> aClass, String... fields) {
        return getEqualsFromFields(aClass, Arrays.asList(fields));
    }

    /**
     * @param aClass {@link Class}
     * @param fields {@link Stream}
     */
    private static <T> BiFunction<T, T, Boolean> getEqualsFromFields(Class<T> aClass, List<String> fields) {
        BiFunction<T, T, Boolean> equalResult;
        if (fields == null || fields.isEmpty()) {
            equalResult = Objects::equals;
        } else {
            equalResult = (objectA, objectB) -> getFields(aClass, fields).stream().map(field -> {
                final boolean areEqual;
                if (field == null || objectA == null || objectB == null) {
                    areEqual = Objects.equals(objectA, objectB);
                } else {
                    areEqual = getEquals(objectA, objectB, field);
                }
                return areEqual;
            }).reduce(true, (condA, condB) -> condA && condB);
        }
        return equalResult;
    }

    /**
     * @param objectA T
     * @param objectB T
     * @param field   Field
     * @param <T>     T
     * @return boolean
     */
    private static <T> boolean getEquals(T objectA, T objectB, Field field) {
        boolean lRet;
        try {
            final Object a = field.get(objectA);
            final Object b = field.get(objectB);

            lRet = objectA.getClass().equals(objectB.getClass())
                    && Objects.equals(a, b);
        } catch (IllegalAccessException ignored) {
            lRet = false;
        }
        return lRet;
    }

    /**
     * @param aClass Class
     * @param fields String
     * @param <T>    T
     * @return List
     */
    private static <T> List<Field> getFields(Class<T> aClass, List<String> fields) {
        return fields.stream().map(fieldName -> {
            Field field = null;
            try {
                field = aClass.getDeclaredField(fieldName);
                field.setAccessible(true);
            } catch (NoSuchFieldException ignored) {
            }
            return field;
        }).collect(Collectors.toList());
    }
}
