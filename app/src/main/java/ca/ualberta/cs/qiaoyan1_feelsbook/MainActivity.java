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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "ca.ualberta.cs.qiaoyan1_feelsbook.MESSAGE";

    private TextView num_of_love;
    public int love_count;
    private String loveCountString;

    private TextView num_of_joy;
    public int joy_count;
    private String joyCountString;

    private TextView num_of_surprise;
    public int surprise_count;
    private String surpriseCountString;

    private TextView num_of_anger;
    public int anger_count;
    private String angerCountString;

    private TextView num_of_sadness;
    public int sadness_count;
    private String sadnessCountString;

    private TextView num_of_fear;
    public int fear_count;
    private String fearCountString;
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
        loveCountString = getLove();
        updateLove(loveCountString);

        joyCountString = getJoy();
        updateJoy(joyCountString);

        surpriseCountString = getSurprise();
        updateSurprise(surpriseCountString);

        angerCountString = getAnger();
        updateAnger(angerCountString);

        sadnessCountString = getSadness();
        updateSadness(sadnessCountString);

        fearCountString = getFear();
        updateFear(fearCountString);
    }

//    editComment = (EditText) findViewById(R.id.editComment);
//    Button commentButton = (Button) findViewById(R.id.commentButton);
//    Button loveButton = (Button) findViewById(R.id.loveButton);
//    historyText = (ListView) findViewById(R.id.historyText);

    public void initialize(){
        num_of_love = (TextView) findViewById(R.id.loveCount);
        num_of_love.setText("LOVE COUNT: 0");
        num_of_joy = (TextView) findViewById(R.id.joyCount);
        num_of_joy.setText("JOY COUNT: 0");
        num_of_surprise = (TextView) findViewById(R.id.surpriseCount);
        num_of_surprise.setText("SURPRISE COUNT: 0");
        num_of_anger = (TextView) findViewById(R.id.angerCount);
        num_of_anger.setText("ANGER COUNT: 0");
        num_of_sadness = (TextView) findViewById(R.id.sadnessCount);
        num_of_sadness.setText("SADNESS COUNT: 0");
        num_of_fear = (TextView) findViewById(R.id.fearCount);
        num_of_fear.setText("FEAR COUNT: 0");
    }
    public void updateLove(String loveCountString) {
        // String loveCountString = Integer.toString(loveCount);
        num_of_love = (TextView) findViewById(R.id.loveCount);
        num_of_love.setText(loveCountString);
    }

    public String getLove(){
        num_of_love = (TextView) findViewById(R.id.loveCount);
        loveCountString = num_of_love.getText().toString();
        return loveCountString;
    }

    public void updateJoy(String joyCountString) {
        num_of_joy = (TextView) findViewById(R.id.joyCount);
        num_of_joy.setText(joyCountString);
    }

    public String getJoy(){
        num_of_joy = (TextView) findViewById(R.id.joyCount);
        joyCountString = num_of_joy.getText().toString();
        return joyCountString;
    }

    public void updateSurprise(String surpriseCountString) {
        num_of_surprise = (TextView) findViewById(R.id.surpriseCount);
        num_of_surprise.setText(surpriseCountString);
    }

    public String getSurprise(){
        num_of_surprise = (TextView) findViewById(R.id.surpriseCount);
        surpriseCountString = num_of_surprise.getText().toString();
        return surpriseCountString;
    }

    public void updateAnger(String angerCountString) {
        num_of_anger = (TextView) findViewById(R.id.angerCount);
        num_of_anger.setText(angerCountString);
    }

    public String getAnger(){
        num_of_anger = (TextView) findViewById(R.id.angerCount);
        angerCountString = num_of_anger.getText().toString();
        return angerCountString;
    }

    public void updateSadness(String sadnessCountString) {
        num_of_sadness = (TextView) findViewById(R.id.sadnessCount);
        num_of_sadness.setText(sadnessCountString);
    }

    public String getSadness(){
        num_of_sadness = (TextView) findViewById(R.id.sadnessCount);
        sadnessCountString = num_of_sadness.getText().toString();
        return sadnessCountString;
    }

    public void updateFear(String fearCountString) {
        num_of_fear = (TextView) findViewById(R.id.fearCount);
        num_of_fear.setText(fearCountString);
    }

    public String getFear(){
        num_of_fear = (TextView) findViewById(R.id.fearCount);
        fearCountString = num_of_fear.getText().toString();
        return fearCountString;
    }

    /** Called when the user taps the Send button */
    public void love(View view) {
        Intent intent = new Intent(this, DisplayCommentActivity.class);
        String message = "LOVE";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
        love_count = love_count + 1;
        loveCountString = "LOVE COUNT: " + Integer.toString(love_count);
        updateLove(loveCountString);
    }

    public void joy(View view) {
        Intent intent = new Intent(this, DisplayCommentActivity.class);
        String message = "JOY";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
        joy_count = joy_count + 1;
        joyCountString = "JOY COUNT: " + Integer.toString(joy_count);
        updateJoy(joyCountString);
    }

    public void surprise(View view) {
        Intent intent = new Intent(this, DisplayCommentActivity.class);
        String message = "SURPRISE";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
        surprise_count = surprise_count + 1;
        surpriseCountString = "SURPRISE COUNT: " + Integer.toString(surprise_count);
        updateSurprise(surpriseCountString);
    }

    public void anger(View view) {
        Intent intent = new Intent(this, DisplayCommentActivity.class);
        String message = "ANGER";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
        anger_count = anger_count + 1;
        angerCountString = "ANGER COUNT: " + Integer.toString(anger_count);
        updateAnger(angerCountString);
    }

    public void sadness(View view) {
        Intent intent = new Intent(this, DisplayCommentActivity.class);
        String message = "SADNESS";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
        sadness_count = sadness_count + 1;
        sadnessCountString = "SADNESS COUNT: " + Integer.toString(sadness_count);
        updateSadness(sadnessCountString);
    }

    public void fear(View view) {
        Intent intent = new Intent(this, DisplayCommentActivity.class);
        String message = "FEAR";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
        fear_count = fear_count + 1;
        fearCountString = "FEAR COUNT: " + Integer.toString(fear_count);
        updateFear(fearCountString);
    }




}
