package ru.elena;

import ru.elena.annotations.AfterSuite;
import ru.elena.annotations.BeforeSuite;
import ru.elena.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Veterinarian {

    public static void start(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            start(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static <T> void start(Class<T> clazz) {
        T object = null;
        try {
            object = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        Method[] methods = clazz.getMethods();

        String beforeSuiteMethodName = null;
        String afterSuiteMethodName = null;

        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                if (beforeSuiteMethodName != null) {
                    throw new RuntimeException("Метод с аннотацией BeforeSuite должен быть в единственном экземпляре");
                }
                beforeSuiteMethodName = method.getName();
            }

            if (method.isAnnotationPresent(AfterSuite.class)) {
                if (afterSuiteMethodName != null) {
                    throw new RuntimeException("Метод с аннотацией AfterSuite должен быть в единственном экземпляре");
                }
                afterSuiteMethodName = method.getName();
            }
        }

        List<String> sortedMethodNames = sortTestMethods(methods);

        // Вызовы
        try {
            if (beforeSuiteMethodName != null) {
                clazz.getMethod(beforeSuiteMethodName).invoke(object);
            }

            for (String methodName : sortedMethodNames) {
                clazz.getMethod(methodName).invoke(object);
            }

            if (afterSuiteMethodName != null) {
                clazz.getMethod(afterSuiteMethodName).invoke(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static List<String> sortTestMethods(Method[] methods) {
        Map<String, Integer> testMethods = new HashMap<String, Integer>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                testMethods.put(method.getName(), method.getAnnotation(Test.class).priority());
            }
        }

        List<String> result = new ArrayList<String>();
        int counter = 1;
        boolean isFound = true;

        while (isFound) {
            isFound = false;

            for (Map.Entry<String, Integer> entry : testMethods.entrySet()) {
                if (entry.getValue() == counter) {
                    result.add(entry.getKey());
                    isFound = true;
                }
            }

            counter++;
        }

        return result;
    }
}
