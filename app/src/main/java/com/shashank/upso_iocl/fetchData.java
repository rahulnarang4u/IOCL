package com.shashank.upso_iocl;

import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class fetchData extends AsyncTask<Void,Void,Void> {

    String data ="";
    String dataParsed = "";
    String singleParsed ="";



    @Override
    protected Void doInBackground(Void... voids) {


        try {

            URL url = new URL("http://192.168.43.236:5000/complaint");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONArray JA = new JSONArray(data);
            for(int i =0 ;i <JA.length(); i++){
                JSONObject JO = (JSONObject) JA.get(i);
                singleParsed =  "auto vendor:" + JO.get("auto_vendor") + "\n" +
                        "complaint no:" + JO.get("complaint_no") + "\n"+
                        "doffice:" + JO.get("d_office") + "\n"+
                        "issue:" + JO.get("issue") + "\n" +
                        "name:" + JO.get("name") + "\n" +
                        "priority:" + JO.get("priority") + "\n" +
                        "remarks:" + JO.get("remarks") + "\n"+
                        "sapcode:" + JO.get("sapcode") + "\n"+
                        "type:" + JO.get("type")  + "\n"+
                        "vsat ip:" + JO.get("vsat_ip") + "\n"+
                        "vsat vendor:" + JO.get("vsat_vendor")+ "\n"
                        +"date of issue:" + JO.get("date_of_issue") + "\n";
                dataParsed = dataParsed + singleParsed +"\n" ;


            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MainActivity2.data.setText(this.dataParsed);

    }
}
