package com.example.webstudyproject.OAuth;


import lombok.Data;

@Data
public class NaverDTO {
    private String URL;
    private String client_id;
    private String client_secret;
    private String redirect_uri;
    private String code;
    private String state;

    @Override
    public String toString() {
        String uri = "";
        uri = URL;
        uri += "client_id=" + client_id;
        uri += "&client_secret=" + client_secret;
        uri += "&redirect_uri=" + redirect_uri;
        uri += "&code=" + code;
        uri += "&state=" + state;
        return uri;
    }
}
