package gbuenoo.glpiticket.Model;

public class Login_model {
    private String username;
    private String password;
    private String session_token;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSession_token() {
        return session_token;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSession_token(String session_token) {
        this.session_token = session_token;
    }
}
