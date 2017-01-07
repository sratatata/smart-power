package net.zarski.myremote.storage;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by lb_lb on 06.01.17.
 */
public class FileStorage {
    Context context;
    private String fileName;

    public FileStorage(Context context, String fileName) {
        this.context = context;
        this.fileName = fileName;
    }

    public void saveString(String value) throws IOException {
        FileOutputStream outputStream;

        outputStream = context.openFileOutput(this.fileName, MODE_PRIVATE);
        outputStream.write(value.getBytes());
        outputStream.close();
    }

    public FileInputStream loadFile() throws FileNotFoundException {

        return context.openFileInput(fileName);
//        InputStreamReader isr = new InputStreamReader(fis);
//        BufferedReader bufferedReader = new BufferedReader(isr);
//        StringBuilder sb = new StringBuilder();
//        String line;
//        while ((line = bufferedReader.readLine()) != null) {
//            sb.append(line);
//        }
    }
}
