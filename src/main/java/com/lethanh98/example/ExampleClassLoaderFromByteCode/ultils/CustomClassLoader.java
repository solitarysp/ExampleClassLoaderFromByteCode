package com.lethanh98.example.ExampleClassLoaderFromByteCode.ultils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class CustomClassLoader extends ClassLoader {
    private static final String WORKING_DIR = System.getProperty("user.dir");
    ClassLoader parent;

    public CustomClassLoader(ClassLoader parent) {
        super(parent);
        this.parent = parent;
    }

    public Class loadClass(String name) throws ClassNotFoundException {
        try {
            return super.loadClass(name);
        } catch (Exception e) {
        }
        try {
            String url = "file:" + WORKING_DIR + "\\byteCode\\authen\\DefaultBaseImpl.txt";
            URL myUrl = new URL(url);
            URLConnection connection = myUrl.openConnection();
            InputStream input = connection.getInputStream();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int data = input.read();

            while (data != -1) {
                buffer.write(data);
                data = input.read();
            }

            input.close();

            byte[] classData = buffer.toByteArray();
            return defineClass(name,
                    classData, 0, classData.length);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}