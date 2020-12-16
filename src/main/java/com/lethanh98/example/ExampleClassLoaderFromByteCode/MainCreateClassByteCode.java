package com.lethanh98.example.ExampleClassLoaderFromByteCode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainCreateClassByteCode {
    private static final String WORKING_DIR = System.getProperty("user.dir");

    public static void main(String[] args) {
        String url = "com.lethanh98.example.ExampleClassLoaderFromByteCode.authen.DefaultBaseImpl";
        createByteCodeClass(url);
    }

    private static void createByteCodeClass(String classPath) {
        try {
            String url1 = "file:" + WORKING_DIR + "\\target\\classes\\" + classPath.replace(".", "\\") + ".class";
            String out = WORKING_DIR + "\\byteCode\\" + classPath.replace(".", "\\").replace(classPath.substring(classPath.lastIndexOf(".") + 1), "");

            URL myUrl = new URL(url1);
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
            Files.createDirectories(Paths.get(out));
            try (RandomAccessFile raf = new RandomAccessFile(out + classPath.substring(classPath.lastIndexOf(".") + 1), "rw")) {
                raf.write(classData);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
