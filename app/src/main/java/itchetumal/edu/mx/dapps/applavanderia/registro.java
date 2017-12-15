package itchetumal.edu.mx.dapps.applavanderia;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class registro extends AppCompatActivity {

    private EditText inputEmail, inputPassword;     //hit option + enter if you on mac , for windows hit ctrl + enter
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
//fire base base de datos
    private DatabaseReference databaseReference;
    private EditText nombre,direccion,telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //data base
        databaseReference = FirebaseDatabase.getInstance().getReference();
        nombre=(EditText) findViewById(R.id.nombre);
        direccion=(EditText) findViewById(R.id.direccion);
        telefono=(EditText) findViewById(R.id.telefono);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
      /*  if(auth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }*/
        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnResetPassword = (Button) findViewById(R.id.btn_reset_password);

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(registro.this, resetearcontra.class));
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




        btnSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Ingrese direccion de Correo!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Ingrese Contraseña!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Contraseña muy corta, Ingrese al menos 6 caracteres!", Toast.LENGTH_SHORT).show();
                    return;
                }



                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(registro.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                guardarinformacion();
                                Toast.makeText(registro.this, "Cuenta creada con: " + task.isSuccessful(), Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(registro.this, "Error de registro, Intente mas tarde" + task.getException(),
                                            Toast.LENGTH_LONG).show();
                                } else {
                                    startActivity(new Intent(registro.this, MainActivity.class));
                                    finish();
                                }

                            }
                        });

            }

        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
    public void guardarinformacion(){
       String nom= nombre.getText().toString().trim();
        String dire=direccion.getText().toString().trim();
        String tel =telefono.getText().toString().trim();

        guardarinformacion guardarinformacion= new guardarinformacion(nom,dire,tel );
        FirebaseUser user = auth.getCurrentUser();
        databaseReference.child(user.getUid()).setValue(guardarinformacion);

        Toast.makeText(this,"Informacion guardada",Toast.LENGTH_LONG).show();
    }

}
