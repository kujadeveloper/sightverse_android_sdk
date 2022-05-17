package com.sdk.sightverse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var sdk = Sightverse();
        sdk.url = "https://dev.sightverse.io/api/";
        sdk.appkey = "LrCwPozO.rXj5VOcbVxvEQMyH2Z700tTyCcz9dIbT";
        sdk.userkey = "ooHdvekT.l7vk4VWoNvbHEI70ko4McXV1Q4YNgB8H";

        //Application tüm kampanyaları listele
        var campaings: JSONObject? = sdk.getCampaing();
        System.out.println(campaings);

        //Application tüm kullanıcıları listele
        var users: JSONObject? = sdk.getAllUsers();
        System.out.println(users);

        //Yeni kullanıcı gönderme
        val data = JSONObject()
        data.put("name", "Test")
        data.put("lastname", "Test")
        data.put("phone", "05555555555")
        data.put("email", "test@test.com")
        data.put("uuid", "1234567890")
        var response: JSONObject? = sdk.createUser(data);
        System.out.println(response);

        //Get User
        var apiuser: JSONObject? = sdk.getUser("1234567890");
        System.out.println(apiuser);

        //App First Pages
        var pages: JSONObject? = sdk.firstPages();
        System.out.println(pages);


    }
}