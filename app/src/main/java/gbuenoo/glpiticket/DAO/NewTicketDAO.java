package gbuenoo.glpiticket.DAO;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NewTicketDAO {
    public String CreateTicket(String... params) {
        OkHttpClient client = new OkHttpClient();

        String host = "GLPI_URL_HERE/apirest.php/";
        String postBody = ("{\"input\": {\"name\":\""+params[0]+"\",\"content\":\""+params[1]+"\",\"urgency\":\"3\"} } ");

        try {
             Request request = new Request.Builder()
                .url(host + "ticket?session_token=" + params[2])
                .post(RequestBody.create(MediaType.parse("Content-Type: application/json"), postBody))
                .build();

            Response response = client.newCall(request).execute();

            if(response.body() != null){
                return new JSONObject(response.body().string()).getString("id");
            }
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
