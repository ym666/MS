package com.pdxx.ms.April.April_4_10;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 */

public class ReflectClient {
    public static void main(String[] args) {
        testReflect3();
    }

    //获取class对象的三种方法
    public static void testReflect1() {
        try {
            Class<ReflectTest> class1 = ReflectTest.class;
            Class class2 = Class.forName("com.pdxx.ms.April.April_4_10.ReflectTest");
            ReflectTest reflectTest = new ReflectTest();
            Class<? extends ReflectTest> class3 = reflectTest.getClass();

            SysUtil.sys(".class=" + class1 + "\nClass.forName=" + class2 + "\ngetClass=" + class3);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public static void testReflect2() {
        Class<ReflectTest> class1 = ReflectTest.class;
        try {
            //获取无参构造方法
//            Constructor<ReflectTest> constructor = class1.getConstructor();
//            ReflectTest reflectTest = constructor.newInstance();
//            //获取有参构造方法
//            Constructor<ReflectTest> constructor1 = class1.getConstructor(String.class, String.class);
//            ReflectTest reflectTest1 = constructor1.newInstance("123", "321");
            //获取有参构造方法
//            Constructor<ReflectTest> constructor2 = class1.getConstructor(String.class, String.class, String.class);
//            ReflectTest reflectTest2 = constructor2.newInstance("123", "321", "456");
//            for (Constructor<?> constructor2 : class1.getConstructors()) {
//                constructor2.newInstance();
//            }
            Constructor<ReflectTest> constructor3 = class1.getDeclaredConstructor(String.class, String.class, String.class);
            constructor3.setAccessible(true);
            ReflectTest reflectTest = constructor3.newInstance("12", "123", "13");

        } catch (Exception e) {


        }
    }

    public static void testReflect3() {
        Class<ReflectTest> class1 = ReflectTest.class;
//        Method[] methods = class1.getMethods();
//        for (Method method : methods) {
//            System.out.println(method);
//        }

        Method[] declaredMethods = class1.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }
    }
}