package ca.ualberta.cs.qiaoyan1_feelsbook;

import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class Util
{
    /** initialize*/
    private static final String FILENAME = "file.sav";
    public static String EXTRA_MESSAGE = "ca.ualberta.cs.qiaoyan1_feelsbook.MESSAGE";
    public static ArrayList<feelingHistory> history = new ArrayList<>();
    private static ArrayList<Listener> listeners = new ArrayList<>();
    public static feelingHistory currentImportantFeeling = null;


    public static void addEmotion(String EXTRA_MESSAGE, Context context, String feeling)
    {
        ImportantFeeling newFeel = new ImportantFeeling(feeling);
        history.add(newFeel); // add to history
        currentImportantFeeling = newFeel; // set currentImportantFeeling as newFeel
        notifyListener(); // notify listener
        // pass the information to DisplayCommentActivity
        Intent intent = new Intent(context, DisplayCommentActivity.class);
        intent.putExtra(EXTRA_MESSAGE, feeling);
        context.startActivity(intent);
    }

    /** test */
    public static String test()
    {
        return "test";
    }

    /** load file */
    public static void loadFromFile(Context context) {

        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);

            Gson gson = new Gson();
            Type typeListHistory= new TypeToken<ArrayList<ImportantFeeling>>(){}.getType();
            history = gson.fromJson(reader, typeListHistory);
        }

        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /** save file */
    public static void saveInFile(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter writer = new BufferedWriter(osw);
            Gson gson = new Gson();
            gson.toJson(history,osw);
            writer.flush();
            fos.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void addListener(Listener listener)
    {
        listeners.add(listener);
    }

    public static void notifyListener()
    {
        for(Listener l:listeners)
        {
            l.update();
        }
    }
}
