package com.example.studentportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    EditText student_id,std_passowrd_editext;
    Button std_login;
    TextView showname;
    SharedPreferences sharedPreferences;
    private  static final String SHN = "myfref";
    private  static  final String KEY_ID = "id";
    private  static  final String KEY_PASSWORD = "pas";

    public ProgressBar spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);
        student_id = findViewById(R.id.id_etext_student);
        std_passowrd_editext = findViewById(R.id.password_etext_student);
        std_login = findViewById(R.id.login_btn_student);
        showname = findViewById(R.id.textviewname);
        std_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinner.setVisibility(View.VISIBLE);
                getData();
            }
        });


        drawerLayout = findViewById(R.id.drawerid);



        NavigationView navigationView = findViewById(R.id.navigationid);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        // shared perferences ;
        sharedPreferences = getSharedPreferences(SHN,MODE_PRIVATE);
        String user_id = sharedPreferences.getString(KEY_ID,null);
        String user_pas = sharedPreferences.getString(KEY_PASSWORD,null);
        student_id.setText(user_id);
        std_passowrd_editext.setText(user_pas);


    }

    private void getData() {



        String std_id = student_id.getText().toString().trim();



        if (std_id.equals("")) {
            spinner.setVisibility(View.GONE);
            Toast.makeText(this, "Enter Your Id:", Toast.LENGTH_LONG).show();
            return;
        }


        String url = Config.DATA_URL + student_id.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                showJSONS(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void showJSONS(String response) {

        String std_name = "";
        String std_email = "";
        String std_image = "";
        String std_id = "";
        String std_phone = "";
        String std_university="";
        String std_fathern="";
        String std_mothern="";
        String std_major="";
        String std_bod="";
        String std_familym="";
        String std_password="";

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            JSONObject collegeData = result.getJSONObject(0);
            std_name = collegeData.getString(Config.KEY_NAME);
            std_email = collegeData.getString(Config.email_name);
            std_id=collegeData.getString(Config.id);
            std_image = collegeData.getString(Config.std_image);
            std_bod = collegeData.getString(Config.bod);
            std_fathern=collegeData.getString(Config.father_name);
            std_mothern=collegeData.getString(Config.mother_name);
            std_university=collegeData.getString(Config.university_name);
            std_phone=collegeData.getString(Config.phone);
            std_major=collegeData.getString(Config.major);
            std_familym=collegeData.getString(Config.familymembers);
            std_password = collegeData.getString(Config.password);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (std_passowrd_editext.getText().toString().equals(std_password)) {
            student_id.getText().toString();
            Intent intent = new Intent(this,Home.class);
            intent.putExtra("std_name",std_name);
            intent.putExtra("std_email",std_email);
            intent.putExtra("std_image",std_image);
            intent.putExtra("std_bod",std_bod);
            intent.putExtra("std_fathern",std_fathern);
            intent.putExtra("std_mothern",std_mothern);
            intent.putExtra("std_university",std_university);
            intent.putExtra("std_phone",std_phone);
            intent.putExtra("std_familym",std_familym);
            intent.putExtra("std_major",std_major);
            intent.putExtra("std_id",std_id);


            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_ID,student_id.getText().toString());
            editor.putString(KEY_PASSWORD,std_passowrd_editext.getText().toString());
            editor.apply();


            spinner.setVisibility(View.GONE);

            startActivity(intent);
            Toast.makeText(this, "Welcome "+std_name, Toast.LENGTH_SHORT).show();
        }

        else {
            Toast.makeText(this, "Wrong Password or Id !", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Check Detail !", Toast.LENGTH_SHORT).show();
            std_passowrd_editext.setText("");
            spinner.setVisibility(View.GONE);
        }

    }

    //saving password for user ....

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (toggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        if (item.getItemId()==R.id.homemenuId)
        {
            intent = new Intent(this,MainActivity.class);//here should be add home.java = letter.
            startActivity(intent);
        }

        else if (item.getItemId()==R.id.facultyId)
        {
            intent = new Intent(this,Faculty.class);
            startActivity(intent);
        }
        else if (item.getItemId()==R.id.adminId)
        {
            intent = new Intent(this,Admin.class);
            startActivity(intent);
        }


        return false;
    }
}