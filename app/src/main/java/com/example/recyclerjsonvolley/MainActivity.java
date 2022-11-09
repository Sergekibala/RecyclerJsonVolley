package com.example.recyclerjsonvolley;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Vars Glogales

    private RecyclerView recyclerView;
    private EditText etSearch;
    private Button btnSearch;

    private ArrayList<ModelItem> itemArrayList;
    private String search;
    private AdapterItem adapterItem;

    private RequestQueue requestQueue; // Pour Volley

    // Initialisation des composants

    private void initUI() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        etSearch = findViewById(R.id.etSearch);
        btnSearch = findViewById(R.id.btnSearch);

        itemArrayList = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);
    }

    public void newSearch(View view) {
        itemArrayList.clear();
        search = etSearch.getText().toString().trim();
        parseJSON((search));

    }

    public void parseJSON(String search){

      //  String pixabaykey = "31201307-b73bf8cb229121a4a460e615b";

        search = etSearch.getText().toString().trim();

        // https://pixabay.com/api/?key=31201307-b73bf8cb229121a4a460e615b&q=yellow+flowers&image_type=photo&pretty=true
        //31201307-b73bf8cb229121a4a460e615b

 String pixabaykey = "31201307-b73bf8cb229121a4a460e615b";

String urlJSONFile = "https://pixabay.com/api/"
        + "?key="
        + pixabaykey
        + "&q="
        + search
        + "&image_type=photo"
        + "&orientation=horizontal"
        + "&per_page=20"
        + "&pretty=true";

Log.i("TAG", "parseJSON:" + urlJSONFile);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urlJSONFile, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
try {
    JSONArray jsonArray = response.getJSONArray("hits");

    for(int i = 0; i<jsonArray.length(); i++){
        JSONObject hit = jsonArray.getJSONObject(i);
        String imageUrl = hit.getString("webformatURL");
        String creator = hit.getString("user");
        int likes = hit.getInt("likes");

        itemArrayList.add(new ModelItem(imageUrl, creator, likes));

    }
// ici


    adapterItem = new AdapterItem(MainActivity.this, itemArrayList);

    recyclerView.setAdapter(adapterItem);


//adapterItem.set

} catch (JSONException e) {

    e.printStackTrace();
}
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
error.printStackTrace();
            }
        });
requestQueue.add(request);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        parseJSON(search);


        getIntent().putExtra(EXTRA_URL, imageUrl);


    }

}
}