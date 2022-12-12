package com.example.webstudyproject.model;

import org.json.JSONObject;

public class NAVERUSER {
    private String mobile;
    private String name;
    private String id;
    private String email;
    private String mobile_e164;

    public NAVERUSER(String userData) {
        JSONObject temp = new JSONObject(userData);
        JSONObject user = temp.getJSONObject("response");
        this.mobile = user.getString("mobile");
        this.name = user.getString("name");
        this.id = user.getString("id");
        this.email = user.getString("email");
        this.mobile_e164 = user.getString("mobile_e164");
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile_e164() {
        return mobile_e164;
    }

    public void setMobile_e164(String mobile_e164) {
        this.mobile_e164 = mobile_e164;
    }

    @Override
    public String toString() {
        return "NAVERUSER{" +
                "mobile='" + mobile + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", mobile_e164='" + mobile_e164 + '\'' +
                '}';
    }
}
