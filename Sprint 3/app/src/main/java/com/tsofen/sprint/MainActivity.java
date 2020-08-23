package com.tsofen.sprint;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loginUser(View view) {
        Intent intent = new Intent(this, Welcome.class);
        startActivity(intent);
    }

    public void download(final View view) {
        final TextView usernameInput = findViewById(R.id.editText2);
        final TextView passwordInput = findViewById(R.id.editText);
        final TextView indicator = findViewById(R.id.indicator);
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/users");
            TextDownloader download = new TextDownloader();
            download.setCallback(new WebserviceCallback() {
                @Override
                public void onDataDownloadStarted() { indicator.setText("Please wait");}

                @Override
                public void onDataReceived(String data) {
                    indicator.setText(data);                                                   //Should contain the array of users from the URL
                    try {
                        JSONArray array = new JSONArray(data);
                        boolean flag = false;
                        String username = usernameInput.getText().toString();                                                       //Should receive input from user
                        String password = passwordInput.getText().toString();                                                       //Should receive input from user
                        JSONObject user = null;
                        for (int i = 0; i<array.length(); i++) {
                            user = array.getJSONObject(i);
                            if(username.equals(user.getString("username"))) {
                                //Toast.makeText(MainActivity.this, user.getString("username") + " " + user.getString("id"), Toast.LENGTH_SHORT).show();
                                if (password.equals(user.getString("id"))) {
                                    flag = true;
                                }
                                break;
                            }
                        }
                        if(flag) {
                            indicator.setText("Welcome "+user.get("name"));
                            //wait 2 seconds??
                            //jump to login dashboard

                            Intent intent = new Intent(view.getContext(), Welcome.class);
                            intent.putExtra("name", user.getString("name"));
                            startActivity(intent);
                        }
                        else{
                            indicator.setText("Wrong username or password");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
            download.execute(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}