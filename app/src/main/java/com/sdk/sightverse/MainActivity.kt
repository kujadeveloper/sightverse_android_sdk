package com.sdk.sightverse

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.graphics.*
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private val imageView: ImageView? = null
    private val mImageBitmap: Bitmap? = null
    var sdk = Sightverse();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sdk.url = "https://dev.sightverse.io/api/";
        sdk.appkey = "LrCwPozO.rXj5VOcbVxvEQMyH2Z700tTyCcz9dIbT";
        sdk.userkey = "ooHdvekT.l7vk4VWoNvbHEI70ko4McXV1Q4YNgB8H";

        //Application tüm kampanyaları listele
        var campaings: JSONObject? = sdk.getCampaing("1");
        System.out.println(campaings);

        //Application tüm kullanıcıları listele
        var users: JSONObject? = sdk.getAllUsers("1");
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
        data_delete.put("id", "1")
        var delete: JSONObject? = sdk.deleteUser(data_delete);
        System.out.println(delete);

        //Get User
        var apiuser: JSONObject? = sdk.getUser("1234567890");
        System.out.println(apiuser);

        //App Open Pages
        var pages: JSONObject? = sdk.firstPages();
        System.out.println(pages);

        //App get all Receipt
        var receipts: JSONObject? = sdk.receiptAppList("1");
        System.out.println(receipts);

        //All user Receipt
        var user_receipts: JSONObject? = sdk.userAllReceipt("1");
        System.out.println(user_receipts);

        //Delete receipt
        val receipt_delete = JSONObject()
        receipt_delete.put("id", "1")
        var delete_receipt: JSONObject? = sdk.deleteReceipt(receipt_delete);
        System.out.println(delete_receipt);

        //User
        var userme: JSONObject? = sdk.userMe();
        System.out.println(userme);

        //User earning
        var earning: JSONObject? = sdk.userEarning();
        System.out.println(earning);

        //fiş resmi gonder

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, 1);

    }

    private fun Bitmap.drawRectangle(): Bitmap? {
        val bitmap = copy(config, true)
        val canvas = Canvas(bitmap)

        Paint().apply {
            color = Color.RED
            isAntiAlias = true

            // draw rectangle on canvas
            canvas.drawRect(
                20f, // left side of the rectangle to be drawn
                20f, // top side
                width / 3 - 20f, // right side
                height - 20f, // bottom side
                this
            )
        }

        return bitmap
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode== RESULT_OK) {
            val imageUri = data?.data as Uri
            var imageBitmap: Bitmap

            val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                imageBitmap =
                    ImageDecoder.decodeBitmap(ImageDecoder.createSource(contentResolver, imageUri))
            } else {
                imageBitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
            }

            var earning: JSONObject? = sdk.sendScreenShot(imageBitmap);
            System.out.println(earning);

        }
    }
}