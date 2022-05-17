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
        var campaings: JSONObject? = sdk.getCampaing("0");
        System.out.println(campaings);

        //Application tüm kullanıcıları listele
        var users: JSONObject? = sdk.getAllUsers("0");
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


        //Delete User
        val data_delete = JSONObject()
        data.put("id", "1")
        var delete: JSONObject? = sdk.deleteUser(data_delete);
        System.out.println(delete);

        //Get User
        var apiuser: JSONObject? = sdk.getUser("1234567890");
        System.out.println(apiuser);

        //App Open Pages
        var pages: JSONObject? = sdk.firstPages();
        System.out.println(pages);

        //App get all Receipt
        var receipts: JSONObject? = sdk.receiptAppList("0");
        System.out.println(receipts);

        //All user Receipt
        var user_receipts: JSONObject? = sdk.userAllReceipt("0");
        System.out.println(user_receipts);

        //Delete receipt
        val receipt_delete = JSONObject()
        data.put("id", "1")
        var delete_receipt: JSONObject? = sdk.deleteReceipt(receipt_delete);
        System.out.println(delete_receipt);

        //User
        var userme: JSONObject? = sdk.userMe();
        System.out.println(userme);

        //User earning
        var earning: JSONObject? = sdk.userEarning();
        System.out.println(earning);
    }
}