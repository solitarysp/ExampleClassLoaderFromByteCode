package com.lethanh98.example.ExampleClassLoaderFromByteCode;

import com.lethanh98.example.ExampleClassLoaderFromByteCode.authen.Base;
import com.lethanh98.example.ExampleClassLoaderFromByteCode.ultils.CustomClassLoader;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws  ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader parentClassLoader = Main.class.getClassLoader();
        CustomClassLoader classLoader = new CustomClassLoader(parentClassLoader);
        Class myObjectClass = classLoader.loadClass("com.lethanh98.example.ExampleClassLoaderFromByteCode.authen.DefaultBaseImpl");
        Base base = (Base) myObjectClass.newInstance();
        System.out.println(base.run());
    }


}
