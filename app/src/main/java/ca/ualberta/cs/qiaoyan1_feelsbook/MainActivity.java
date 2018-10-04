package ca.ualberta.cs.qiaoyan1_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
//
//    private static final String FILENAME = "file.sav";
//    private EditText editComment;
//    private ListView historyText;
//    private ArrayList<feelingHistory> tweets = new ArrayList<feelingHistory>();
//
//    private ArrayAdapter<feelingHistory> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//    editComment = (EditText) findViewById(R.id.editComment);
//    Button commentButton = (Button) findViewById(R.id.commentButton);
//    Button loveButton = (Button) findViewById(R.id.loveButton);
//    historyText = (ListView) findViewById(R.id.historyText);


    /** Called when the user taps the Send button */
    public void love(View view) {
        Intent intent = new Intent(this, DisplayCommentActivity.class);
        String message = "LOVE";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void joy(View view) {
        Intent intent = new Intent(this, DisplayCommentActivity.class);
        String message = "JOY";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void surprise(View view) {
        Intent intent = new Intent(this, DisplayCommentActivity.class);
        String message = "SURPRISE";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void anger(View view) {
        Intent intent = new Intent(this, DisplayCommentActivity.class);
        String message = "ANGER";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void sadness(View view) {
        Intent intent = new Intent(this, DisplayCommentActivity.class);
        String message = "SADNESS";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void fear(View view) {
        Intent intent = new Intent(this, DisplayCommentActivity.class);
        String message = "FEAR";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }




}
