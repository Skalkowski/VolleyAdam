package com.example.volleywordsexample;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends ActionBarActivity {

	private RequestQueue mRequestQueue;
	private ArrayList<Word> wordList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		wordList = new ArrayList<Word>();

		String url = "http://project-midas.com/daniel_things/eduWordsPhone/words.json";

		mRequestQueue = Volley.newRequestQueue(this);

		JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET, url,
				null, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.i("textasd", response.toString());

						parseJson(response);

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub

					}
				});

		mRequestQueue.add(jr);
	

	}
	
	
	


	private void parseJson(JSONObject json) {
		try {
			JSONArray words = json.getJSONArray("words");

			for (int i = 0; i < words.length(); i++) {
				JSONObject wordJson = words.getJSONObject(i);
				Word word = new Word();
				word.setId(Integer.parseInt(wordJson.optString("id")));
				word.setNamelanguage1(wordJson.optString("namelanguage1"));
				word.setNamelanguage2(wordJson.optString("namelanguage2"));

				Log.i("word",
						"id: " + word.getId() + " slowo: "
								+ word.getNamelanguage1() + " tlumaczeniee: "
								+ word.getNamelanguage2());
				wordList.add(word);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
