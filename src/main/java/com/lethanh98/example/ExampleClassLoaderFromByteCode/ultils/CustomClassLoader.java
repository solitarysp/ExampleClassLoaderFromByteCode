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

    public Class loadClass(String classPath) throws ClassNotFoundException {
        try {
            /**
             * Vì java sẽ load cả các class cha, vì vậy hãy để java tự tìm kiếm trước, nếu không có mới tìm bằng bytecode.
             * Có thể chỉnh sửa đoạn này tùy theo nhu cầu
             */
            return super.loadClass(classPath);
        } catch (Exception e) {
        }
        try {
            String out = WORKING_DIR + "\\byteCode\\" + classPath.replace(".", "\\");

            String url = "file:" +out;
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
            return defineClass(classPath,
                    classData, 0, classData.length);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}