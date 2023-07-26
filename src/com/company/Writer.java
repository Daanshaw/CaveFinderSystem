package com.company;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Writer {

     static void csnFileWriter(String path, String csnFileName) {
        OutputStream outputSteam = null;
        try {
            outputSteam = new FileOutputStream(new File(csnFileName));
            outputSteam.write(path.getBytes(), 0, path.length());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                outputSteam.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
