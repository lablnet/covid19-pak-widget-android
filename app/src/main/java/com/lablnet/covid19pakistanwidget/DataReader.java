package com.lablnet.covid19pakistanwidget;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataReader {
    public DataReader()
    {

    }

    public static String GetData()
    {
        String result = "";
        try{
            URL url = new URL("https://www.covid19.earth/data/summery.js");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();

            BufferedReader br = null;
            if (100 <= conn.getResponseCode() && conn.getResponseCode() <= 399) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            String str = null;

            while((str = br.readLine()) != null)
            {
                result += str;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        if(result.equals(""))
            return "Result Not Found!";

        result = result.split("=")[1].trim();

        try{

            JSONObject obj = new JSONObject(result);
            String resultToShow = "";

            JSONObject last = obj.getJSONObject("98");

            resultToShow = "Updated: " +last.getString("datetime")+
                    "\nTests " +last.getString("last_tests")+
                    "\nCases: " +last.getString("last_cases")+
                    "\nRecovered: " +last.getString("last_recovered")+
                    "\nDeaths: " +last.getString("last_deaths")+
                    "\nThe data Show Last 24 hour stat.";

            return resultToShow;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return "Error";
        }
    }
}

