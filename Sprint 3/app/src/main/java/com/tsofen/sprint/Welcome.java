package com.tsofen.sprint;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Bundle extras = getIntent().getExtras();
        TextView name = findViewById(R.id.name_user);
        name.append(" " + extras.getString("name"));
    }

    public void goToOptions(View view) {
        Intent intent = new Intent(this, options.class);
        startActivity(intent);
    }
}