package chung.helloworld;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView output = (TextView) findViewById(R.id.textView);
        // Reading json file from assets folder
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.film_schedule_en)));
            String temp;
            while ((temp = br.readLine()) != null)
                sb.append(temp);
        } catch (IOException e) {
            e.printStackTrace();

        }
        String strJson = sb.toString();
        System.out.println(strJson);
        //String strJson="{\"Employee\" :[{\"id\":\"01\",\"name\":\"Gopal Varma\",\"salary\":\"500000\"}, {\"id\":\"02\", \"name\":\"Sairamkrishna\",\"salary\":\"500000\"}] }";
        String data = "";
        try {
            JSONObject jsonRootObject = new JSONObject(strJson);

            //Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonArray = jsonRootObject.optJSONArray("Film");

            //Iterate the jsonArray and print the info of JSONObjects
            for(int i=0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String file_name = jsonObject.optString("filmName").toString();
                String premier_date = jsonObject.optString("premierDate").toString();
                String url = jsonObject.optString("trailerURLs").toString();

                data += "Node"+i+" : \n file name= "+ file_name +" \n Premier date= "+ premier_date +" \n Url= "+ url +" \n ";
            }
            output.setText(data);
        } catch (JSONException e) {e.printStackTrace();}
    }

}
