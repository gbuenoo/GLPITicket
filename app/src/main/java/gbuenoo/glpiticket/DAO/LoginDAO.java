package gbuenoo.glpiticket.DAO;

import org.json.JSONObject;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginDAO {
    public String DoLogin(String... params){
        OkHttpClient client = new OkHttpClient();

        String host = "GLPI_URL_HERE/apirest.php/initSession";

        try{
            Request request = new Request.Builder()
                    .url(host)
                    .header("Authorization",Credentials.basic(params[0], params[1]))
                    .build();
            Response response = client.newCall(request).execute();

            if(response.body() != null) {
                return new JSONObject(response.body().string()).getString("session_token");
            }
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
