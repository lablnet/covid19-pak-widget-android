package com.lablnet.covid19pakistanwidget;

import org.json.JSONObject;
import java.io.IOException;
import org.json.JSONException;

public class DataReader {
    public DataReader()
    {
        // nothing goes here.
    }

    public static String GetData() throws IOException, JSONException {
        try {
            // get the data.
            JSONObject json = DataToJson.GetData("https://www.covid19.earth/data/app.json");

            String date = (String) json.get("datetime");
            // convert above datetime to formatted MM/DD/YYYY HH:MM:SS
            String formattedDate = date.substring(0, 10) + " " + date.substring(11, 16);

            return "Updated:\n" +formattedDate+ "\n"+
                    "\nTests " +json.get("last_tests")+
                    "\nCases: " +json.get("last_cases")+
                    "\nRecovered: " +json.get("last_recovered")+
                    "\nDeaths: " +json.get("last_deaths")+ "\n\n"+
                    "\nThese are last 24 hour stats.";
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return "Unable to connect to server, it will retry after 15 minutes.";
        }
    }
}

