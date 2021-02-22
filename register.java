package com.shashank.platform.loginui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends Activity {

    private Button signup,signin;
  //  private TextView signin;
    private EditText eemail,rpass,pass;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth =  FirebaseAuth.getInstance();
        signup = findViewById(R.id.signupbb);
        eemail = findViewById(R.id.email1);
        pass = findViewById(R.id.password);
        rpass = findViewById(R.id.repassword);
        signin = findViewById(R.id.signintext);
        progressDialog = new ProgressDialog(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open();
            }
        });

    }
    private void Register()
    {
        String email = eemail.getText().toString();
        String ppass = pass.getText().toString();
        String rrpass = rpass.getText().toString();
        if(TextUtils.isEmpty(email))
        {
            eemail.setError("Enter your Email");
            return;
        }
        else if(TextUtils.isEmpty(ppass))
        {
            pass.setError("Enter your Password");
            return;
        }
        else if(TextUtils.isEmpty(rrpass))
        {
            rpass.setError("Confirm your password");
            return;
        }
        else if(!ppass.equals(rrpass))
        {
            rpass.setError("Different password");
            return;
        }
        else if(ppass.length()<4)
        {
            pass.setError("Length should be > 4");
            return;
        }
        else if(TextUtils.isEmpty(email))
        {
            eemail.setError("Enter your Email");
            return;
        }
        progressDialog.setMessage("Please wait");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth.createUserWithEmailAndPassword(email,ppass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(register.this,"Successfully Registered",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(register.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(register.this,"Sign up failed",Toast.LENGTH_LONG).show();

                }
                progressDialog.dismiss();

            }
        });

    }
    private void open()
    {
        Intent intent = new Intent(register.this,MainActivity.class);
        startActivity(intent);

    }


}