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


    private EditText editComment;
    private EditText editHistory;
    private ListView historyList;

    private ArrayAdapter<feelingHistory> adapter;

    private feelingHistory currentFeeling = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_comment);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        editComment = (EditText) findViewById(R.id.editComment);
        final Button commentButton = (Button) findViewById(R.id.commentButton);
        if(intent.getBooleanExtra("isHistory", false))
        {
            editComment.setVisibility(View.GONE);
            commentButton.setVisibility(View.GONE);
        }
        final String feel = intent.getStringExtra(Util.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
//    historyList = (ListView) findViewById(R.id.historyList);
//    historyList.setAdapter(adapter);


//        ImportantFeeling newHistory = new ImportantFeeling(feel);
//        History.add(newHistory);
//        adapter.notifyDataSetChanged();
//        saveInFile();


        historyList = (ListView) findViewById(R.id.historyList);
        editHistory = (EditText) findViewById(R.id.editHistory);


        commentButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                editComment.setVisibility(View.GONE);
                commentButton.setVisibility(View.GONE);
                String text = editComment.getText().toString();

                Util.currentImportantFeeling.setComment(text);

                adapter.notifyDataSetChanged();
                Util.saveInFile(getApplicationContext());

            }
        });

        historyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                currentFeeling = Util.history.get(position);
                editHistory.setText(currentFeeling.toString());
            }
        });

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
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        adapter = new ArrayAdapter<feelingHistory>(this,
                R.layout.list_item, Util.history);
        historyList.setAdapter(adapter);
    }

//    private void displayOnline(String historyItem){
//        editHistory = (EditText) findViewById(R.id.editHistory);
//        editHistory.setText(historyItem);
//
//
//    }

    public void edit_and_store(View view){
//        PreviousHistoryItemList = historyItem.split("\\|'");
//        NewHistoryItemList = editHistory.getText().toString().split("\\|");
//       if (NewHistoryItemList[0] != PreviousHistoryItemList[0]){
//            changeDate(NewHistoryItemList);
//        }
//        else if (NewHistoryItemList[1] != PreviousHistoryItemList[1]){
//            changeFeeling();
//        }
//        else if (NewHistoryItemList[2] != PreviousHistoryItemList[2]){
//            changeComment();
//        }

        //String new_history = newHistory.toString()
        //History.remove(historyListPositon);
        if(currentFeeling==null) {
            Toast.makeText(this, "You need click one emotion first.", Toast.LENGTH_SHORT).show();
        }
        else {
            String data = editHistory.getText().toString();
            String splits[] = data.split(" \\| ");
            String date = splits[0];
            String feeling = splits[1];
            String comment = splits[2];
            Date newDate = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            try {
                newDate = sdf.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(newDate!=null)
                currentFeeling.setDate(newDate);
            currentFeeling.setMessage(feeling);
            currentFeeling.setComment(comment);
            adapter.notifyDataSetChanged();
            Util.saveInFile(getApplicationContext());
            Util.notifyListener();
        }

    }

//    private void changeDate(String[] NewHistoryItemList){
//
//    }



}
