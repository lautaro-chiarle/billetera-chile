package cl.com.billetera.desafio.model;


public class AuthResponse {
    private String response;
    
    public AuthResponse(String response) {
        this.response = response;
    }


    public String getResponse() {
        return this.response;
    }

    public void setResponse(String response) {
        this.response = response;
    }


    @Override
    public String toString() {
        return "{" +
            " response='" + getResponse() + "'" +
            "}";
    }


}