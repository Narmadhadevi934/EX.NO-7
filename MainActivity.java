package com.example.sql;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    EditText name, location, designation;
    Button insertBtn, viewBtn;
    DB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.txtName);
        location = findViewById(R.id.txtLocation);
        designation = findViewById(R.id.txtDesignation);
        insertBtn = findViewById(R.id.btnInsert);
        viewBtn = findViewById(R.id.btnView);
        db = new DB(this);

        // INSERT
        insertBtn.setOnClickListener(v -> {
            boolean inserted = db.insertData(
                    name.getText().toString(),
                    location.getText().toString(),
                    designation.getText().toString()
            );
            Toast.makeText(this,
                    inserted ? "Inserted Successfully" : "Insert Failed",
                    Toast.LENGTH_SHORT).show();
        });
        // OPEN NEXT PAGE
        viewBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Details.class);
            startActivity(intent);
        });
    }
}
