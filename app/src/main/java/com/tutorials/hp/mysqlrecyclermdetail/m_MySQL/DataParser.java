package com.tutorials.hp.mysqlrecyclermdetail.m_MySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.tutorials.hp.mysqlrecyclermdetail.m_DataObject.Spacecraft;
import com.tutorials.hp.mysqlrecyclermdetail.m_UI.MyAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Oclemy on 6/6/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class DataParser extends AsyncTask<Void,Void,Boolean> {

    Context c;
    String jsonData;
    RecyclerView rv;

    ProgressDialog pd;
    ArrayList<Spacecraft> spacecrafts=new ArrayList<>();

    public DataParser(Context c, String jsonData, RecyclerView rv) {
        this.c = c;
        this.jsonData = jsonData;
        this.rv = rv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd=new ProgressDialog(c);
        pd.setTitle("Parse");
        pd.setMessage("Parsing...Please wait");
        pd.show();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Boolean parsed) {
        super.onPostExecute(parsed);

        pd.dismiss();

        if(parsed)
        {
            //BIND
            MyAdapter adapter=new MyAdapter(c,spacecrafts);
            rv.setAdapter(adapter);


        }else {
            Toast.makeText(c,"Unable To Parse",Toast.LENGTH_SHORT).show();

        }
    }

    private Boolean parseData()
    {
        try
        {
            JSONArray ja=new JSONArray(jsonData);
            JSONObject jo;

            spacecrafts.clear();
            Spacecraft spacecraft;

            for (int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);

                int id=jo.getInt("id");
                String name=jo.getString("name");
                String propellant=jo.getString("propellant");
                String desc=jo.getString("description");
                String imageUrl=jo.getString("imageurl");

                spacecraft=new Spacecraft();

                spacecraft.setId(id);
                spacecraft.setName(name);
                spacecraft.setPropellant(propellant);
                spacecraft.setDescription(desc);
                spacecraft.setImageUrl(imageUrl);

                spacecrafts.add(spacecraft);


            }

            return true;


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }





}
