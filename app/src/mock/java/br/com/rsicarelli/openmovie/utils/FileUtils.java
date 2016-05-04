package br.com.rsicarelli.openmovie.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by rodrigosicarelli on 5/4/16.
 */
public class FileUtils {
    public static void writeStringToFile(final String fileName, final String data, final Context context) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    File path = context.getExternalFilesDir(null);
                    File file = new File(path, fileName);
                    FileOutputStream stream = new FileOutputStream(file);
                    stream.write(data.getBytes());
                    stream.close();

                    Log.d("open-movie", "Done with the file, master.");
                } catch (IOException e) {
                    Log.e("Exception", "File write failed: " + e.toString());
                }
                return null;
            }
        }.execute();
    }
}
