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

import gbuenoo.glpiticket.R;
import gbuenoo.glpiticket.Model.NewTicket_model;
import gbuenoo.glpiticket.DAO.NewTicketDAO;

public class NewTicket extends AppCompatActivity {

    NewTicket_model newticket = new NewTicket_model();

    EditText editTicketTitle;
    EditText editTicketDesc;
    Button btnSendNewTicket;
    ProgressBar loadingSendNewTicket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_ticket);

        Intent intent = getIntent();
        newticket.setSession_token(intent.getStringExtra("login_session_token"));


        editTicketTitle = findViewById(R.id.editTicketTitle);
        editTicketDesc = findViewById(R.id.editTicketDesc);
        btnSendNewTicket = findViewById(R.id.btnSendNewTicket);
        loadingSendNewTicket = findViewById(R.id.loadingSendNewTicket);
        loadingSendNewTicket.setVisibility(View.INVISIBLE);

        btnSendNewTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newticket.setTitle(editTicketTitle.getText().toString());
                newticket.setDescription(editTicketDesc.getText().toString());

                if ((newticket.getTitle().length() >= 2) && (newticket.getDescription().length() >= 2) && (newticket.getSession_token() != null)) {
                    DoNewTicket doNewTicket = new DoNewTicket();
                    doNewTicket.execute(newticket.getTitle(), newticket.getDescription(), newticket.getSession_token());
                } else {
                    Toast.makeText(getApplicationContext(), "Ooops! Talvez você tenha deixado algum campo em branco...", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    class DoNewTicket extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            btnSendNewTicket.setVisibility(View.INVISIBLE);
            loadingSendNewTicket.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            NewTicketDAO NewTicketDAO = new NewTicketDAO();
            return NewTicketDAO.CreateTicket(params[0], params[1], params[2]);
        }

        @Override
        protected void onPostExecute(String result) {
            Intent intentMain = new Intent(NewTicket.this, Main.class);
            super.onPostExecute(result);
            try {
                if (result != null) {
                    Toast.makeText(getApplicationContext(), "Seu chamado é o Nº " + result + "\n Acompanhe o status por e-mail ;)", Toast.LENGTH_LONG).show();
                    intentMain.putExtra("session_token", newticket.getSession_token());
                    NewTicket.this.startActivity(intentMain);
                } else {
                    Toast.makeText(getApplicationContext(), "Ooops! Algo deu errado...\nTente mais tarde ;)", Toast.LENGTH_SHORT).show();
                    NewTicket.this.startActivity(intentMain);
                }
            }
            catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Ooops! Algo deu errado... \nTente mais tarde ;)", Toast.LENGTH_SHORT).show();
                NewTicket.this.startActivity(intentMain);
            }
        }
    }
}
