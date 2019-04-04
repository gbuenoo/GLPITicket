package gbuenoo.glpiticket.Class;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import gbuenoo.glpiticket.Model.Login_model;
import gbuenoo.glpiticket.DAO.LoginDAO;
import gbuenoo.glpiticket.R;

public class Login extends AppCompatActivity {
    LoginDAO LoginDAO = new LoginDAO();
    Login_model Login = new Login_model();

    private EditText editUsername;
    private EditText editPassword;
    private Button btnLogin;
    private ProgressBar loadingLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        btnLogin = findViewById(R.id.btnLogin);
        loadingLogin = findViewById(R.id.loadingLogin);

        loadingLogin.setVisibility(View.INVISIBLE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login.setUsername(editUsername.getText().toString());
                Login.setPassword(editPassword.getText().toString());

                if(Login.getUsername() != null && Login.getPassword() != null) {
                    DoLogin doLogin = new DoLogin();
                    doLogin.execute(Login.getUsername(), Login.getPassword());
                } else {
                    Toast.makeText(getApplicationContext(), "Ooops! Talvez você tenha deixado algum campo em branco...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    class DoLogin extends AsyncTask<String, Void, String> {
        @Override

        protected void onPreExecute() {
            btnLogin.setVisibility(View.INVISIBLE);
            editUsername.setVisibility(View.INVISIBLE);
            editPassword.setVisibility(View.INVISIBLE);
            loadingLogin.setVisibility(View.VISIBLE);
        }

        protected String doInBackground(String... params) {
            Login.setSession_token(LoginDAO.DoLogin(params[0], params[1]));
            return null;
        }

        protected void onPostExecute(String result){
            try {
                if (Login.getSession_token() != null) {
                    Intent intentMain = new Intent(Login.this, Main.class);
                    intentMain.putExtra("session_token", Login.getSession_token());
                    finish();
                    Login.this.startActivity(intentMain);
                } else {
                    Toast.makeText(getApplicationContext(), "Ooops! Algo deu errado...\nTente mais tarde ;)", Toast.LENGTH_SHORT).show();
                    Login.this.recreate();
                }
            }catch (Exception e){
                Toast.makeText(getApplicationContext(), "Ooops! Algo deu errado...\nTente mais tarde ;)", Toast.LENGTH_SHORT).show();
                Login.this.recreate();
            }
        }

    }

}
