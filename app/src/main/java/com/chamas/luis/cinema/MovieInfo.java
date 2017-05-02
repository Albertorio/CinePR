package com.chamas.luis.cinema;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chamas.luis.cinema.Utility.JsonTask;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import developer.shivam.library.CrescentoImageView;

public class MovieInfo extends AppCompatActivity {

    CrescentoImageView crescentoImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);

        crescentoImageView = (CrescentoImageView)findViewById(R.id.crescentoImageView);

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

//                    movieInfo.setText(jsonObject.getJSONObject("aguadilla").getJSONObject(keylist.get(0)).getJSONObject(keylist.get(0)).toString());
                    String picUrl = jsonObject.getJSONObject("aguadilla").getJSONObject(keylist.get(0)).getJSONObject(keylist.get(0)).getString("img");
                    Picasso.with(MovieInfo.this).load(picUrl).into(crescentoImageView);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).execute("aguadilla");
    }
}
