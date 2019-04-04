package gbuenoo.glpiticket.Class;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import gbuenoo.glpiticket.Model.Main_model;
import gbuenoo.glpiticket.R;

public class Main extends AppCompatActivity {

    Main_model main = new Main_model();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent intent = getIntent();
        main.setSession_token(intent.getStringExtra("session_token"));

        Button btnNewTicket = findViewById(R.id.btnNewTicket);

        btnNewTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNewTicket = new Intent(Main.this, NewTicket.class);
                intentNewTicket.putExtra("login_session_token", main.getSession_token());
                Main.this.startActivity(intentNewTicket);
            }
        });

    }

    public void onBackPressed(){
        Intent intentLogin = new Intent(Main.this, Login.class);
        Main.this.startActivity(intentLogin);
    }
}
