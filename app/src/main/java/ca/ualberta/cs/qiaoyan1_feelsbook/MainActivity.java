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

    /** initialize six emotion TextView */
    private TextView num_of_love;
    private TextView num_of_joy;
    private TextView num_of_surprise;
    private TextView num_of_anger;
    private TextView num_of_sadness;
    private TextView num_of_fear;
    /** initialize a HashMap stores counts of the corresponding feeling */
    HashMap<String, Integer> counts = new HashMap<>();
    /** put all six feelings into a list */
    static String feeling[] = {"LOVE", "JOY", "SURPRISE", "ANGER", "SADNESS", "FEAR"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize(); // call initialize()
        Util.loadFromFile(getApplicationContext()); // use Util to call loadFromFile
        getCount(Util.history, counts); // call getCount() to update the counts

        /** generate all six TextView */
        num_of_love = findViewById(R.id.loveCount);
        num_of_sadness = findViewById(R.id.sadnessCount);
        num_of_fear = findViewById(R.id.fearCount);
        num_of_surprise = findViewById(R.id.surpriseCount);
        num_of_joy = findViewById(R.id.joyCount);
        num_of_anger = findViewById(R.id.angerCount);

        updateCount(); // call updateCount() to update the view

        /** to notice the change and update the view and save the updated data */
        Util.addListener(new Listener() {
            @Override
            public void update() {
                getCount(Util.history, counts); // update HashMap
                updateCount(); // update view
                Util.saveInFile(MainActivity.this);
                sortHistory(); //sort history by date
            }
        });
    }

    // initialize all six counts as 0
    public void initialize(){

        for(String str:feeling)
        {
            counts.put(str, 0);
        }
    }

    /** update HashMap */
    public static void getCount(ArrayList<feelingHistory> history, HashMap<String, Integer> counts)
    {
        // set counts to zero
        for(String str:feeling)
        {
            counts.put(str, 0);
        }
        // update counts
        for(feelingHistory feel:history)
        {
            String feeling = feel.getMessage().split(" \\| ")[0]; // get the first item
            int count = counts.get(feeling) + 1; // for corresponding feeling, update counts
            counts.put(feeling, count); //update HashMap
        }
    }

    /** update view */
    public void updateCount()
    {
        /** update all six counts value */
        num_of_love.setText("LOVE: " + counts.get("LOVE"));
        num_of_joy.setText("JOY: " + counts.get("JOY"));
        num_of_surprise.setText("SURPRISE: " + counts.get("SURPRISE"));
        num_of_anger.setText("ANGER: " + counts.get("ANGER"));
        num_of_sadness.setText("SADNESS: " + counts.get("SADNESS"));
        num_of_fear.setText("FEAR: " + counts.get("FEAR"));
    }

    /** sort the history by date */
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

    /** Called when the user taps the love button */
    public void love(View view) {
        Util.addEmotion(Util.EXTRA_MESSAGE, MainActivity.this, "LOVE");
    }

    /** Called when the user taps the joy button */
    public void joy(View view) {
        Util.addEmotion(Util.EXTRA_MESSAGE, MainActivity.this, "JOY");
    }

    /** Called when the user taps the surprise button */
    public void surprise(View view) {
        Util.addEmotion(Util.EXTRA_MESSAGE, MainActivity.this, "SURPRISE");
    }

    /** Called when the user taps the anger button */
    public void anger(View view) {
        Util.addEmotion(Util.EXTRA_MESSAGE, MainActivity.this, "ANGER");
    }

    /** Called when the user taps the sadness button */
    public void sadness(View view) {
        Util.addEmotion(Util.EXTRA_MESSAGE, MainActivity.this, "SADNESS");
    }

    /** Called when the user taps the fear button */
    public void fear(View view) {
        Util.addEmotion(Util.EXTRA_MESSAGE, MainActivity.this, "FEAR");
    }

    /** change to DisplayCommentActivity when the user taps the history button */
    public void viewHistory(View view)
    {
        Intent intent = new Intent(this, DisplayCommentActivity.class);
        intent.putExtra("isHistory", true);
        startActivity(intent);
    }
}
