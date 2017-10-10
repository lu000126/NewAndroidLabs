package com.example.jin.newandroidlabs;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;



public class ChatWindow extends Activity {
    protected static final String ACTIVITY_NAME = "ChatWindow";
    ListView listView;
    Button sendButton;
    EditText sendText;
    ArrayList<String> list = new ArrayList<String>();
    ChatDatabaseHelper dbHelper;
    SQLiteDatabase db;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        listView = (ListView) findViewById(R.id.listView);
        sendButton = (Button) findViewById(R.id.sendButton);
        sendText = (EditText) findViewById(R.id.sendText);
        //in this case, “this” is the ChatWindow, which is-A Context object
        final ChatAdapter messageAdapter = new ChatAdapter( this );
        listView.setAdapter (messageAdapter);
        dbHelper = new ChatDatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add(sendText.getText().toString());

                ContentValues contentValues = new ContentValues();
                contentValues.put(ChatDatabaseHelper.KEY_MESSAGE, sendText.getText().toString());
                db.insert(ChatDatabaseHelper.TABLE_NAME, "null", contentValues);
                messageAdapter.notifyDataSetChanged();//this restarts the process of getCount()/getView()
                sendText.setText("");
            }
        });

        String [] allColumns = {ChatDatabaseHelper.KEY_ID,ChatDatabaseHelper.KEY_MESSAGE};

        cursor = db.query(ChatDatabaseHelper.TABLE_NAME,allColumns,null,null,null,null,null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            String newMessage = cursor.getString(cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE));
            list.add(newMessage);
            Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + cursor.getString(cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE)));
            cursor.moveToNext();
        }
        for(int i=0;i<cursor.getColumnCount();i++){
            Log.i("Cursor column name",cursor.getColumnName(i));
        }

        Log.i(ACTIVITY_NAME,"Cursor column count = " + cursor.getColumnCount());
    }
    public void onDestroy(){
        Log.i(ACTIVITY_NAME,"In onDestroy()");
        super.onDestroy();
        cursor.close();
        dbHelper.close();
        db.close();
    }
    private class ChatAdapter extends ArrayAdapter<String>{

        public ChatAdapter(Context ctx) {
            super(ctx, 0);
        }
        public int getCount(){
            return list.size();
        }
        public String getItem(int position){
            return list.get(position);
        }
        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null ;
            if(position%2 == 0)
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            else
                result = inflater.inflate(R.layout.chat_row_outgoing, null);

            TextView message = (TextView)result.findViewById(R.id.message_text);
            message.setText( getItem(position) ); // get the string at position
            return result;
        }
    }
}
