package com.example.sql;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
public class Details extends AppCompatActivity {
    ListView listView;
    DB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        listView = findViewById(R.id.listView);
        db = new DB(this);
        Cursor cursor = db.getAllData();
        ArrayList<String> list = new ArrayList<>();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
            return;
        }
        while (cursor.moveToNext()) {
            list.add(
                    "ID: " + cursor.getInt(0) + "\n" +
                            "Name: " + cursor.getString(1) + "\n" +
                            "Location: " + cursor.getString(2) + "\n" +
                            "Designation: " + cursor.getString(3)
            );
        }
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1,
                        list);
        listView.setAdapter(adapter);
    }
}
