package com.sdk.sightverse;


import android.graphics.Bitmap;
import android.os.StrictMode;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sightverse {
    public String url = "https://dev.sightverse.io/api/";
    public String appkey;
    public String userkey;
    public String apikey;
    public Integer timeout = 20*1000;

    Sightverse()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }



    public JSONObject getCampaing(String page)
    {
        this.apikey = this.appkey;
        String urls = url+"app-campain?page="+page;
        String response = this.getJSON(urls,"GET",null);
        Logger.getLogger(getClass().getName()).log(Level.INFO, String.valueOf(response));
        return stringToJsonEncode(response);
    }

    public JSONObject getAllUsers(String page)
    {
        this.apikey = this.appkey;
        String urls = url+"app-user?page="+page;
        String response = this.getJSON(urls,"GET",null);
        Logger.getLogger(getClass().getName()).log(Level.INFO, String.valueOf(response));
        return stringToJsonEncode(response);
    }

    public JSONObject getUser(String uuid)
    {
        this.apikey = this.appkey;
        String urls = url+"app-user?uuid="+uuid;
        String response = this.getJSON(urls,"GET",null);
        Logger.getLogger(getClass().getName()).log(Level.INFO, String.valueOf(response));
        return stringToJsonEncode(response);
    }

    public JSONObject createUser(JSONObject data)
    {
        this.apikey = this.appkey;
        String urls = url+"app-user";
        String response = getJSON(urls,"POST", data);
        Logger.getLogger(getClass().getName()).log(Level.INFO, String.valueOf(response));
        return stringToJsonEncode(response);
    }

    public JSONObject deleteUser(JSONObject data)
    {
        this.apikey = this.appkey;
        String urls = url+"app-user";
        String response = getJSON(urls,"DELETE", data);
        Logger.getLogger(getClass().getName()).log(Level.INFO, String.valueOf(response));
        return stringToJsonEncode(response);
    }

    public JSONObject firstPages()
    {
        this.apikey = this.appkey;
        String urls = url+"open-pages";
        String response = getJSON(urls,"GET",null);
        Logger.getLogger(getClass().getName()).log(Level.INFO, String.valueOf(response));
        return stringToJsonEncode(response);
    }

    public JSONObject receiptAppList(String page)
    {
        this.apikey = this.appkey;
        String urls = url+"receipt-list?page="+page;
        String response = getJSON(urls,"GET",null);
        Logger.getLogger(getClass().getName()).log(Level.INFO, String.valueOf(response));
        return stringToJsonEncode(response);
    }


    public JSONObject userAllReceipt(String page)
    {
        this.apikey = this.userkey;
        String urls = url+"receipt?page="+page;
        String response = getJSON(urls,"GET",null);
        Logger.getLogger(getClass().getName()).log(Level.INFO, String.valueOf(response));
        return stringToJsonEncode(response);
    }

    public JSONObject deleteReceipt(JSONObject data)
    {
        this.apikey = this.userkey;
        String urls = url+"receipt";
        String response = getJSON(urls,"DELETE",data);
        Logger.getLogger(getClass().getName()).log(Level.INFO, String.valueOf(response));
        return stringToJsonEncode(response);
    }

    public JSONObject userMe()
    {
        this.apikey = this.userkey;
        String urls = url+"user-me";
        String response = getJSON(urls,"GET",null);
        Logger.getLogger(getClass().getName()).log(Level.INFO, String.valueOf(response));
        return stringToJsonEncode(response);
    }

    public JSONObject userEarning()
    {
        this.apikey = this.userkey;
        String urls = url+"user-earning";
        String response = getJSON(urls,"GET",null);
        Logger.getLogger(getClass().getName()).log(Level.INFO, String.valueOf(response));
        return stringToJsonEncode(response);
    }

    public JSONObject sendScreenShot(Bitmap image)
    {
        this.apikey = this.userkey;
        String urls = url+"receipt";
        String response = uploadFile(urls,"POST",null,image);
        Logger.getLogger(getClass().getName()).log(Level.INFO, String.valueOf(response));
        return stringToJsonEncode(response);
    }

    private String getJSON(String url, String method, JSONObject data)
    {
        Logger.getLogger(getClass().getName()).log(Level.INFO, url);
        Logger.getLogger(getClass().getName()).log(Level.INFO, method);
        HttpURLConnection c = null;
        try {
            URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod(method);
            c.setRequestProperty("Authorization", "Api-Key "+this.apikey);
            c.setRequestProperty("Content-Type", "application/json; utf-8");
            c.setRequestProperty("Accept", "application/json");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.setConnectTimeout(timeout);
            c.setReadTimeout(timeout);

            if(data!=null)
            {
                Logger.getLogger(getClass().getName()).log(Level.INFO, data.toString());
                c.setDoInput(true);
                c.setDoOutput(true);
                DataOutputStream localDataOutputStream = new DataOutputStream(c.getOutputStream());
                localDataOutputStream.writeBytes(data.toString());
                localDataOutputStream.flush();
                localDataOutputStream.close();
            }
            c.connect();

            int status = c.getResponseCode();
            Logger.getLogger(getClass().getName()).log(Level.INFO, String.valueOf(status));
            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line+"\n");
                    }
                    br.close();
                    return sb.toString();
                default:
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(c.getErrorStream()));
                    StringBuilder sb1 = new StringBuilder();
                    String line1;
                    while ((line1 = br1.readLine()) != null) {
                        sb1.append(line1+"\n");
                    }
                    br1.close();
                    return sb1.toString();
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (c != null) {
                try {
                    c.disconnect();
                } catch (Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }


    private String uploadFile(String url, String method, JSONObject data, Bitmap image)
    {
        String boundary =  "*****";

        Logger.getLogger(getClass().getName()).log(Level.INFO, url);
        Logger.getLogger(getClass().getName()).log(Level.INFO, method);
        HttpURLConnection c = null;
        try {
            URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod(method);
            c.setRequestProperty("Authorization", "Api-Key "+this.apikey);
            c.setRequestProperty(
                    "Content-Type", "multipart/form-data;boundary=" + boundary);
            c.setRequestProperty("Accept", "application/json");
            c.setRequestProperty("Connection", "Keep-Alive");
            c.setRequestProperty("Cache-Control", "no-cache");
            c.setRequestProperty("file", "ups.jpg");
            c.setUseCaches(false);
            c.setDoOutput(true);

            OutputStream output = c.getOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, output);

            c.connect();

            int status = c.getResponseCode();
            Logger.getLogger(getClass().getName()).log(Level.INFO, String.valueOf(status));
            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line+"\n");
                    }
                    br.close();
                    return sb.toString();
                default:
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(c.getErrorStream()));
                    StringBuilder sb1 = new StringBuilder();
                    String line1;
                    while ((line1 = br1.readLine()) != null) {
                        sb1.append(line1+"\n");
                    }
                    br1.close();
                    return sb1.toString();
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (c != null) {
                try {
                    c.disconnect();
                } catch (Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    private JSONObject stringToJsonEncode(String data)
    {
        JSONObject obj = null;
        try {
            obj = new JSONObject(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
