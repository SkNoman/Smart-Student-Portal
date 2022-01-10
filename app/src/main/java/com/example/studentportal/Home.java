package com.example.studentportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.EntryXComparator;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Home extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{



    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;

    TextView textViewdatashow,textemail,phone,id,major,fathername,mothername,familymembers,bod,university;
    ImageView imageView;
    Button view;

    String std_name,std_email,std_image,std_bod,std_fathern,std_mothern,std_university,std_familym,std_phone,std_major,std_id;

    SharedPreferences sharedPreferences;
    private  static final String SHN = "myfref";
    private  static  final String KEY_ID = "id";
    private  static  final String KEY_PASSWORD = "pas";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE); //hide the title
       // getSupportActionBar().hide(); // hide title bar
        setContentView(R.layout.activity_home);
        setTitle("Student Dashboard");

        imageView = findViewById(R.id.imageshow);
        textViewdatashow =(TextView)findViewById(R.id.tvshowdata);
        textemail = findViewById(R.id.tvshowdata2);
        phone = findViewById(R.id.phone);
        id = findViewById(R.id.stdid);
        major=findViewById(R.id.major);
        fathername = findViewById(R.id.fathername);
        mothername = findViewById(R.id.mothername);
        familymembers = findViewById(R.id.familymembers);
        bod = findViewById(R.id.bod);
        university = findViewById(R.id.university);
        view = findViewById(R.id.showresult);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openResultViewActivity();

            }
        });
        int n = 300;
        int j = 800;

        // shared preferences

        sharedPreferences = getSharedPreferences(SHN,MODE_PRIVATE);
        String user_id = sharedPreferences.getString(KEY_ID,null);
        String user_pas = sharedPreferences.getString(KEY_PASSWORD,null);

        // implementing bar chart

        BarChart barChart = findViewById(R.id.barChart);
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 30f));
        entries.add(new BarEntry(1f, 80f));
        entries.add(new BarEntry(2f, 60f));
        entries.add(new BarEntry(3f, 50f));
        // gap of 2f
        entries.add(new BarEntry(5f, 70f));
        entries.add(new BarEntry(6f, 60f));

        BarDataSet set = new BarDataSet(entries, "BarDataSet");


      // BarDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
      //  barDataSet.setValueTextColor(Color.BLACK);
     //   barDataSet.setValueTextSize(16f);

        BarData data = new BarData(set);
        data.setBarWidth(0.9f); // set custom bar width
      //  barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barChart.setData(data);
        barChart.setFitBars(true); // make the x-axis fit exactly all bars
        barChart.invalidate(); // refresh

        barChart.animateY(2000);


        /*view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(getApplicationContext(),ViewResult.class));
            }
        });*/


        Intent intent = getIntent();
        std_name = intent.getStringExtra("std_name");
        std_email = intent.getStringExtra("std_email");
        std_image = intent.getStringExtra("std_image");
        std_bod = intent.getStringExtra("std_bod");
        std_fathern = intent.getStringExtra("std_fathern");
        std_mothern=intent.getStringExtra("std_mothern");
        std_university = intent.getStringExtra("std_university");
        std_familym=intent.getStringExtra("std_familym");
        std_phone=intent.getStringExtra("std_phone");
        std_major = intent.getStringExtra("std_major");
        std_id = intent.getStringExtra("std_id");



        textViewdatashow.setText("Name:"+std_name);
        textemail.setText("Email:"+std_email);
        String picture = std_image;
        Glide.with(this).load(picture).into(imageView);
        phone.setText("Phone:"+std_phone);
        id.setText("Id:"+std_id);
        major.setText("Department:"+std_major);
        fathername.setText("Father_Name:"+std_fathern);
        mothername.setText("Mother_Name:"+std_mothern);
        familymembers.setText("Family_Members:"+std_familym);
        bod.setText("Date_of_Birth:"+std_bod);
        university.setText("University:"+std_university);

        //starting drawerlayout works
        drawerLayout = findViewById(R.id.drawerid);



        NavigationView navigationView = findViewById(R.id.navigationid);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

    private void openResultViewActivity()
    {
        Intent intent = new Intent(this,ViewResult.class);
        startActivity(intent);
    }

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
       
            intent = new Intent(this,MainActivity.class);
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
        else if (item.getItemId()==R.id.logoutid)
        {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
            finish();
            Toast.makeText(Home.this,"Log out Done",Toast.LENGTH_SHORT).show();
        }


        return false;

    }
}