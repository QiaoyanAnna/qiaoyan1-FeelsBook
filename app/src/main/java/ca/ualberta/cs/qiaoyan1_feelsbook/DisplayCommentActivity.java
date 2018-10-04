package ca.ualberta.cs.qiaoyan1_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DisplayCommentActivity extends AppCompatActivity {

    private static final String FILENAME = "file.sav";
    private EditText editComment;
    private ListView historyList;
    private ArrayList<feelingHistory> History = new ArrayList<feelingHistory>();
    private ArrayAdapter<feelingHistory> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_comment);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        final String feel = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
//    historyList = (ListView) findViewById(R.id.historyList);
//    historyList.setAdapter(adapter);


//        ImportantFeeling newHistory = new ImportantFeeling(feel);
//        History.add(newHistory);
//        adapter.notifyDataSetChanged();
//        saveInFile();

        editComment = (EditText) findViewById(R.id.editComment);
        Button commentButton = (Button) findViewById(R.id.commentButton);
        historyList = (ListView) findViewById(R.id.historyList);



        commentButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String text = editComment.getText().toString();
                String message = feel + " | " + text ;
                ImportantFeeling newHistory = new ImportantFeeling(message);
                History.add(newHistory);
                adapter.notifyDataSetChanged();
                saveInFile();

            }
        });
    }
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<feelingHistory>(this,
                R.layout.list_item, History);
        historyList.setAdapter(adapter);
    }


    private void loadFromFile() {

        try {
            FileInputStream fis = openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);

            Gson gson = new Gson();
            Type typeListTweets= new TypeToken<ArrayList<ImportantFeeling>>(){}.getType();
            History = gson.fromJson(reader, typeListTweets);
        }

        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void saveInFile() {
        try {
            FileOutputStream fos =openFileOutput(FILENAME, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter writer = new BufferedWriter(osw);
            Gson gson = new Gson();
            gson.toJson(History,osw);
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

}
