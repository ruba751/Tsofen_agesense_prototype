package com.tsofen.sprint;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TextDownloader extends AsyncTask<URL, Void, String> {

    WebserviceCallback callback;
    @Override
    protected String doInBackground(URL... urls) {
        HttpURLConnection connection = null;
        InputStream is = null;
        InputStreamReader isr = null;
        try
        {
            connection = (HttpURLConnection) urls[0].openConnection(); // Connect to the server
            int statusCode = connection.getResponseCode();
            if(statusCode != HttpURLConnection.HTTP_OK)
            {
                return "";
            }
            //OK - 200

            is = connection.getInputStream();
            isr = new InputStreamReader(is);

            BufferedReader reader = new BufferedReader(isr);
            String myText = "";
            String line = reader.readLine();
            while(line !=null)
            {
                myText += line;
                line = reader.readLine();
            }
            return myText;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return null;
    }

    public void setCallback(WebserviceCallback callback) {
        this.callback = callback;
    }

    protected  void onPostExecute(String downloadedText)
    {
        this.callback.onDataReceived(downloadedText);
    }


}
