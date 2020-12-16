package com.lethanh98.example.ExampleClassLoaderFromByteCode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainCreateClassByteCode {
    private static final String WORKING_DIR = System.getProperty("user.dir");

    public static void main(String[] args) {
        try {
            String url = "file:"+WORKING_DIR+"\\target\\classes\\com\\lethanh98\\example\\ExampleClassLoaderFromByteCode\\authen\\DefaultBaseImpl.class";
            String out = WORKING_DIR+"\\byteCode\\authen\\DefaultBaseImpl.txt";
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
            try (RandomAccessFile raf = new RandomAccessFile(out, "rw")) {
                raf.write(classData);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
