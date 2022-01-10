package com.example.studentportal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class add_data extends AppCompatActivity {
    EditText name,id,email,phone,dept,university,fathern,mothern,familym,dob,imagelink,password;
    Button sbmt;
    private static final String url="https://sknoman.000webhostapp.com/student_data/save_data.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        sbmt=(Button)findViewById(R.id.btn_save);

        sbmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertdata();
            }
        });
    }

    private void insertdata()
    {

        name=(EditText)findViewById(R.id.name_edit_text);
        id=(EditText)findViewById(R.id.id_edit_text);
        password=(EditText)findViewById(R.id.password_edit_text);
        phone=(EditText)findViewById(R.id.phone_edit_text);
        email=(EditText)findViewById(R.id.email_edit_text);
        dept=(EditText)findViewById(R.id.dept_edit_text);
        university=(EditText)findViewById(R.id.university_edit_text);
        fathern=(EditText)findViewById(R.id.fathername_edit_text);
        mothern=(EditText)findViewById(R.id.mothername_edit_text);
        familym=(EditText)findViewById(R.id.familymembers_edit_text);
        dob=(EditText)findViewById(R.id.dob_edit_text);
        imagelink=(EditText)findViewById(R.id.imagelink_edit_text);

        final String std_name=name.getText().toString().trim();
        final String std_id=id.getText().toString().trim();
        final String std_password=password.getText().toString().trim();
        final String std_phone=phone.getText().toString().trim();
        final String std_email=email.getText().toString().trim();
        final String std_major=dept.getText().toString().trim();
        final String std_university=university.getText().toString().trim();
        final String std_fathern=fathern.getText().toString().trim();
        final String std_mothern=mothern.getText().toString().trim();
        final String std_familym=familym.getText().toString().trim();
        final String std_bod=dob.getText().toString().trim();
        final String std_image=imagelink.getText().toString().trim();


        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                name.setText("");
                id.setText("");
                password.setText("");
                phone.setText("");
                email.setText("");
                dept.setText("");
                university.setText("");
                fathern.setText("");
                mothern.setText("");
                familym.setText("");
                dob.setText("");
                imagelink.setText("");
                Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String,String> param=new HashMap<String,String>();
                param.put("name",std_name);
                param.put("id",std_id);
                param.put("password",std_password);
                param.put("phone",std_phone);
                param.put("email",std_email);
                param.put("dept",std_major);
                param.put("university",std_university);
                param.put("fathern",std_fathern);
                param.put("mothern",std_mothern);
                param.put("familym",std_familym);
                param.put("dob",std_bod);
                param.put("imagelink",std_image);

                return param;
            }
        };


        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request);

    }
}