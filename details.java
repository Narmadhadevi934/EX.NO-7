
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
        setContentView(R.layout.details);package com.example.sql;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
public class DB extends SQLiteOpenHelper {
    public static final String DB_NAME = "users.db";
    public static final String TABLE_NAME = "user_table";
    public DB(Context context) {
        super(context, DB_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, LOCATION TEXT, DESIGNATION TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String name, String location, String designation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NAME", name);
        values.put("LOCATION", location);
        values.put("DESIGNATION", designation);
        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result != -1;

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
