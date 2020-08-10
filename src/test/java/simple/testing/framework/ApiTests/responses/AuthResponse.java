package simple.testing.framework.ApiTests.responses;

import org.springframework.stereotype.Service;

import java.io.Serializable;

public class AuthResponse implements Serializable {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
