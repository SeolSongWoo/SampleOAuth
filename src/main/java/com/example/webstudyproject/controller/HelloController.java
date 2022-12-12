package com.example.webstudyproject.controller;

import com.example.webstudyproject.OAuth.NaverOAuth;
import com.example.webstudyproject.helper.WebHelper;
import com.example.webstudyproject.model.NAVERUSER;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.Locale;

@Controller
public class HelloController {

    @Autowired
    WebHelper web;

    @GetMapping(value = "/debouncing")
    public String Debouncing() {
        return "debouncing";
    }

    @GetMapping(value = "/throttling")
    public String Throttling () {
        return "throttling";
    }

    @GetMapping("/loginpage")
    public ModelAndView LoginPage(Locale locale, Model model, HttpServletResponse response, HttpServletRequest request) throws Exception {
        web.init(response);

        String clientId = "nMUNpsgNjmad0FDEnocF";//애플리케이션 클라이언트 아이디값";
        String redirectURI = URLEncoder.encode("http://localhost:8080/NaverResult", "UTF-8");
        SecureRandom random = new SecureRandom();
        String state = new BigInteger(130, random).toString();
        String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
        apiURL += "&client_id=" + clientId;
        apiURL += "&redirect_uri=" + redirectURI;
        apiURL += "&state=" + state;
        model.addAttribute("state", state);
        model.addAttribute("APIURL",apiURL);
        return new ModelAndView("loginpage");
    }

    @GetMapping("/NaverResult")
    public ModelAndView NaverResult(Model model, HttpServletResponse response) throws Exception {
        web.init(response);
        String clientId = "nMUNpsgNjmad0FDEnocF";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "b3Y4hXHA1I";//애플리케이션 클라이언트 시크릿값";
        String code = web.getString("code", null);
        String state = web.getString("state", null);
        String redirectURI = URLEncoder.encode("http://localhost:8080/NaverResult", "UTF-8");
        String apiURL;
        JSONObject jsonObject = null;
        apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
        apiURL += "client_id=" + clientId;
        apiURL += "&client_secret=" + clientSecret;
        apiURL += "&redirect_uri=" + redirectURI;
        apiURL += "&code=" + code;
        apiURL += "&state=" + state;
        String access_token = "";
        String refresh_token = "";
        System.out.println("apiURL=" + apiURL);
        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            BufferedReader br;
            System.out.print("responseCode=" + responseCode);
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer res = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                res.append(inputLine);
            }
            br.close();
            if (responseCode == 200) {
                System.out.println(res.toString());
                jsonObject = new JSONObject(res.toString());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        NAVERUSER NaverUser = null;
        try {
            NaverOAuth naverOAuth = new NaverOAuth(jsonObject.getString("access_token"));
            NaverUser = new NAVERUSER(naverOAuth.get());
        } catch (JSONException e) {
            e.printStackTrace();
            return new ModelAndView("loginpage");
        }

        model.addAttribute("user",NaverUser);
        return new ModelAndView("NaverloginResult");
    }
}
