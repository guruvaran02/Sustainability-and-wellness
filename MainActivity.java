package com.shashank.platform.loginui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends Activity {
    private Button button;
    private TextView signup;
    private EditText femail,pass;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.sign_in);
        signup = findViewById(R.id.sign_upb);
        firebaseAuth =  FirebaseAuth.getInstance();

        femail = findViewById(R.id.siemail);
        pass = findViewById(R.id.sipassword);

        progressDialog = new ProgressDialog(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser mfirebaseuser = firebaseAuth.getCurrentUser();
        if(mfirebaseuser != null)
        {
          /*  Intent i = new Intent(MainActivity.this, home.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i); */
            // startActivity(new Intent(this,home.class));
            //   Toast.makeText(this,"login",Toast.LENGTH_LONG).show();
        }
        else
        {
            // startActivity(new Intent(this,MainActivity.class));
        }
    }

    private void Register()
    {
        String email = femail.getText().toString();
        String ppass = pass.getText().toString();

        if(TextUtils.isEmpty(email))
        {
            femail.setError("Enter your Phone No");
            return;
        }
        else if(TextUtils.isEmpty(ppass))
        {
            pass.setError("Enter your Password");
            return;
        }

        else if(ppass.length()<4)
        {
            pass.setError("Length should be > 10");
            return;
        }
        else if(TextUtils.isEmpty(email))
        {
            femail.setError("Enter your Email");
            return;
        }
        progressDialog.setMessage("Please wait");
        progressDialog.show();;
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth.signInWithEmailAndPassword(email,ppass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(MainActivity.this,"Successfully Login",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this,home.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Incorrect username or password",Toast.LENGTH_LONG).show();

                }
                progressDialog.dismiss();

            }
        });

    }

    private void open()
    {
        Intent i = new Intent(getApplicationContext(),register.class);
        startActivity(i);
    }

}
