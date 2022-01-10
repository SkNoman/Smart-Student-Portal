package com.example.studentportal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

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
import java.util.List;

public class Datashow extends AppCompatActivity {

    String webapi;
    RecyclerView recyclerView;
    List<ModalClass> mList;
    AdapterClass adapterClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datashow);
        recyclerView = findViewById(R.id.rid);
        mList=new ArrayList<>();

        webapi = "https://sknoman.000webhostapp.com/student_data/db_connect.php";

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, webapi, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("response", response.toString());


                try {
                    JSONObject jsonObject = new JSONObject(String.valueOf(response));

                    JSONArray data = jsonObject.getJSONArray("data");
                    for (int i = 0; i <data.length(); i++){
                        JSONObject myobj =  data.getJSONObject(i);
                        ModalClass modalClass = new ModalClass(
                                myobj.getString("std_name"),
                                myobj.getString("std_id"),
                                myobj.getString("std_email"),
                                myobj.getString("std_phone"),
                                myobj.getString("std_bod"),
                                myobj.getString("std_major"),
                                myobj.getString("std_university"),
                                myobj.getString("std_fathern"),
                                myobj.getString("std_mothern"),
                                myobj.getString("std_familym"),
                                myobj.getString("std_image")





                        );
                        mList.add(modalClass);

                    }

                }

                catch (JSONException e) {
                    e.printStackTrace();
                }
                adapterClass = new AdapterClass(mList, Datashow.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(Datashow.this));
                recyclerView.setAdapter(adapterClass);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error
                Log.d("response", error.toString());

            }
        });


        requestQueue.add(jsonObjectRequest);
    }
}