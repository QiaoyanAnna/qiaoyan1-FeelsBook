package ca.ualberta.cs.qiaoyan1_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DisplayCommentActivity extends AppCompatActivity {

    /** initialize */
    private EditText editComment;
    private EditText editHistory;
    private ListView historyList;
    private ArrayAdapter<feelingHistory> adapter;
    private feelingHistory currentFeeling = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_comment);

        // initialize
        editComment = (EditText) findViewById(R.id.editComment);
        final Button commentButton = (Button) findViewById(R.id.commentButton);
        historyList = (ListView) findViewById(R.id.historyList);
        editHistory = (EditText) findViewById(R.id.editHistory);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        // if history button ie taped, editComment and commentButton go away
        if(intent.getBooleanExtra("isHistory", false))
        {
            editComment.setVisibility(View.GONE);
            commentButton.setVisibility(View.GONE);
        }
        final String feel = intent.getStringExtra(Util.EXTRA_MESSAGE);

        /** if commentButton is taped */
        commentButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // editComment and commentButton goes away
                editComment.setVisibility(View.GONE);
                commentButton.setVisibility(View.GONE);
                // get the text and set the text
                String text = editComment.getText().toString();
                Util.currentImportantFeeling.setComment(text);
                // notify the the adapter that data has been changed and save the data
                adapter.notifyDataSetChanged();
                Util.saveInFile(getApplicationContext());

            }
        });

        /** if historyList is clicked, get the click position and put the text to the editHistory */
        historyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                currentFeeling = Util.history.get(position);
                editHistory.setText(currentFeeling.toString());
            }
        });

        /** when you long click, it will be deleted */
        historyList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                feelingHistory currentFeel = Util.history.get(position);
                Util.history.remove(currentFeel);
                Util.notifyListener();
                adapter.notifyDataSetChanged();
                return false;
            }
        });

    }
    /** start with historyList */
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        adapter = new ArrayAdapter<feelingHistory>(this,
                R.layout.list_item, Util.history);
        historyList.setAdapter(adapter);
    }

    /** */
    public void edit_and_store(View view){
        // if the user does not select anything but click editButton, notice will come up.
        if(currentFeeling==null) {
            Toast.makeText(this, "Select the history you want to update", Toast.LENGTH_SHORT).show();
        }
        // else update what the user has been changed
        else {
            // get what user has typed and split into corresponding categories
            String data = editHistory.getText().toString();
            String splits[] = data.split(" \\| ");
            String date = splits[0];
            String feeling = splits[1];
            String comment = "No Comment";
            // update the date
            Date newDate = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            try {
                // if the format is right, change the string into date format and assign newDate
                newDate = sdf.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(newDate!=null){
                currentFeeling.setDate(newDate);
            }
            // update comment
            if (splits.length > 2){
                comment = splits[2];
            }
            // update feeling
            currentFeeling.setMessage(feeling);
            // update and save
            currentFeeling.setComment(comment);
            adapter.notifyDataSetChanged();
            Util.saveInFile(getApplicationContext());
            Util.notifyListener();
        }

    }

}
