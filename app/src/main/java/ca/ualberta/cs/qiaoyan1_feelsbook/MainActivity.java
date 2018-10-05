package ca.ualberta.cs.qiaoyan1_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private TextView num_of_love;


    private TextView num_of_joy;


    private TextView num_of_surprise;


    private TextView num_of_anger;


    private TextView num_of_sadness;

    private TextView num_of_fear;


    HashMap<String, Integer> counts = new HashMap<>();
    static String feeling[] = {"LOVE", "JOY", "SURPRISE", "ANGER", "SADNESS", "FEAR"};
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

        initialize();
        Util.loadFromFile(getApplicationContext());
        getCount(Util.history, counts);

        num_of_love = findViewById(R.id.loveCount);
        num_of_sadness = findViewById(R.id.sadnessCount);
        num_of_fear = findViewById(R.id.fearCount);
        num_of_surprise = findViewById(R.id.surpriseCount);
        num_of_joy = findViewById(R.id.joyCount);
        num_of_anger = findViewById(R.id.angerCount);

        updateCount();

        Util.addListener(new Listener() {
            @Override
            public void update() {
                getCount(Util.history, counts);
                updateCount();
                Util.saveInFile(MainActivity.this);
                sortHistory();
            }
        });
    }

//    editComment = (EditText) findViewById(R.id.editComment);
//    Button commentButton = (Button) findViewById(R.id.commentButton);
//    Button loveButton = (Button) findViewById(R.id.loveButton);
//    historyText = (ListView) findViewById(R.id.historyText);

    public void sortHistory()
    {
        Collections.sort(Util.history, new Comparator<feelingHistory>() {
            @Override
            public int compare(feelingHistory o1, feelingHistory o2) {
                if(o1.getDate().after(o2.getDate()))
                    return -1;
                return 1;
            }
        });
    }

    public void updateCount()
    {
        num_of_love.setText("LOVE: " + counts.get("LOVE"));
        num_of_joy.setText("JOY: " + counts.get("JOY"));
        num_of_surprise.setText("SURPRISE: " + counts.get("SURPRISE"));
        num_of_anger.setText("ANGER: " + counts.get("ANGER"));
        num_of_sadness.setText("SADNESS: " + counts.get("SADNESS"));
        num_of_fear.setText("FEAR: " + counts.get("FEAR"));
    }

    public void initialize(){

        for(String str:feeling)
        {
            counts.put(str, 0);
        }
    }

    public static void getCount(ArrayList<feelingHistory> history, HashMap<String, Integer> counts)
    {
        for(String str:feeling)
        {
            counts.put(str, 0);
        }

        for(feelingHistory feel:history)
        {
            String feeling = feel.getMessage().split(" \\| ")[0];
            int count = counts.get(feeling) + 1;
            counts.put(feeling, count);
        }
    }

    /** Called when the user taps the Send button */
    public void love(View view) {
        Util.addEmotion(Util.EXTRA_MESSAGE, MainActivity.this, "LOVE");
    }

    public void joy(View view) {
        Util.addEmotion(Util.EXTRA_MESSAGE, MainActivity.this, "JOY");
    }

    public void surprise(View view) {
        Util.addEmotion(Util.EXTRA_MESSAGE, MainActivity.this, "SURPRISE");
    }

    public void anger(View view) {
        Util.addEmotion(Util.EXTRA_MESSAGE, MainActivity.this, "ANGER");
    }

    public void sadness(View view) {
        Util.addEmotion(Util.EXTRA_MESSAGE, MainActivity.this, "SADNESS");
    }

    public void fear(View view) {
        Util.addEmotion(Util.EXTRA_MESSAGE, MainActivity.this, "FEAR");
    }



    public void viewHistory(View view)
    {
        Intent intent = new Intent(this, DisplayCommentActivity.class);
        intent.putExtra("isHistory", true);
        startActivity(intent);
    }
}
