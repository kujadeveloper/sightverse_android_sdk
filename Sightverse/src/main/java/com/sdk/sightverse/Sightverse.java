package com.sdk.sightverse;


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
    public Integer timeout = 10*1000;

    Sightverse()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
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

    public JSONObject getCampaing()
    {
        String urls = url+"app-campain";
        String response = this.getJSON(urls,"GET",null);
        Logger.getLogger(getClass().getName()).log(Level.INFO, String.valueOf(response));
        return stringToJsonEncode(response);
    }

    public JSONObject getAllUsers()
    {
        String urls = url+"app-user";
        String response = this.getJSON(urls,"GET",null);
        Logger.getLogger(getClass().getName()).log(Level.INFO, String.valueOf(response));
        return stringToJsonEncode(response);
    }

    public JSONObject getUser(String uuid)
    {
        String urls = url+"app-user?uuid="+uuid;
        String response = this.getJSON(urls,"GET",null);
        Logger.getLogger(getClass().getName()).log(Level.INFO, String.valueOf(response));
        return stringToJsonEncode(response);
    }

    public JSONObject createUser(JSONObject data)
    {
        String urls = url+"app-user";
        String response = getJSON(urls,"POST", data);
        Logger.getLogger(getClass().getName()).log(Level.INFO, String.valueOf(response));
        return stringToJsonEncode(response);
    }

    public JSONObject firstPages()
    {
        String urls = url+"open-pages";
        String response = getJSON(urls,"GET",null);
        Logger.getLogger(getClass().getName()).log(Level.INFO, String.valueOf(response));
        return stringToJsonEncode(response);
    }

    public String getJSON(String url, String method, JSONObject data)
    {
        Logger.getLogger(getClass().getName()).log(Level.INFO, url);
        HttpURLConnection c = null;
        try {
            URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod(method);
            c.setRequestProperty("Authorization", "Api-Key "+this.appkey);
            c.setRequestProperty("Content-Type", "application/json; utf-8");
            c.setRequestProperty("Accept", "application/json");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.setConnectTimeout(timeout);
            c.setReadTimeout(timeout);

            if(data!=null)
            {
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
                case 400:
                    return "{'message':'error'}";
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

    private void setPostRequestContent(HttpURLConnection c, JSONObject data) {

    }
}
