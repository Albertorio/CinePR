package com.chamas.luis.cinema;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chamas.luis.cinema.Utility.JsonTask;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    ProgressDialog pd;
    String txtJson;

    TextView movieInfo;
    ImageView poster;
    Button movieInfoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieInfo = (TextView)findViewById(R.id.movieInfo);
        poster = (ImageView)findViewById(R.id.poster);
        movieInfoButton = (Button)findViewById(R.id.movieInfoButton);

        new JsonTask(this, new JsonTask.AsyncResponse() {
            @Override
            public void processFinish(String result) {
                System.out.println(result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    System.out.println(jsonObject.getJSONObject("aguadilla").getJSONObject("Smurfs: The Lost Village").getJSONObject("Smurfs: The Lost Village"));

                    // Get movies from theater (getting keys from jsonObject)
                    Iterator keysToCopyIterator = jsonObject.getJSONObject("aguadilla").keys();
                    ArrayList<String> keylist = new ArrayList<String>();
                    while(keysToCopyIterator.hasNext()) {
                        String key = (String) keysToCopyIterator.next();
                        keylist.add(key);
                    }

                    System.out.println(keylist.toString());

                    movieInfo.setText(jsonObject.getJSONObject("aguadilla").getJSONObject(keylist.get(0)).getJSONObject(keylist.get(0)).toString());
                    String picUrl = jsonObject.getJSONObject("aguadilla").getJSONObject(keylist.get(0)).getJSONObject(keylist.get(0)).getString("img");
                    Picasso.with(MainActivity.this).load(picUrl).into(poster);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).execute("aguadilla");

        movieInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MovieInfo.class);
                startActivity(intent);
            }
        });

    }

}
