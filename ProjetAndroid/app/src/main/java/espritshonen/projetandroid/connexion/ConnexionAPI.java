package espritshonen.projetandroid.connexion;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import espritshonen.projetandroid.métier.Station;
import espritshonen.projetandroid.vues.StationAdapter;

public class ConnexionAPI extends AsyncTask<Object,Void,ArrayList<Station>>{
    private String url = "https://api.jcdecaux.com/vls/v1/stations?contract=lyon&apiKey=0961c642e8c7b354dc20842b6d2654f63c1e39c0";
    private ListView list;
    private Context context;
    private ArrayList<Station> mesStations;

    //constructeur
    public ConnexionAPI(ListView list, Context context){
        this.list = list;
        this.context = context;
    }

    @Override
    protected ArrayList<Station> doInBackground(Object[] objects){
        ArrayList<Station> listeStations = new ArrayList<Station>();
        BufferedReader in = null;
        URL url = null;
        StringBuilder str = new StringBuilder();
        JSONArray jsonArray = null;

        try{
            url = new URL(this.url);
        } catch(MalformedURLException e){
            e.printStackTrace();
        }

        HttpsURLConnection urlConnection = null;

        try{
            urlConnection = (HttpsURLConnection) url.openConnection();
            if(urlConnection.getResponseCode() == HttpsURLConnection.HTTP_OK){
                in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String concat = null;

                while((concat = in.readLine())!= null){
                    str.append(concat);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        urlConnection.disconnect();

        try{
            jsonArray = new JSONArray(str.toString());
        } catch (JSONException e){
            e.printStackTrace();
        }

        JSONObject object = null;
        for(int i=0; i< jsonArray.length();i++){
            Station objectStation = null;
            try{
                object = jsonArray.getJSONObject(i);
                objectStation = new Station(
                        object.getString("name"),
                        object.getString("address"),
                        object.getString("status"),
                        (float)object.getJSONObject("position").getDouble("lat"),
                        (float)object.getJSONObject("position").getDouble("lng"),
                        object.getInt("bike_stands"),
                        object.getInt("available_bikes"),
                        object.getInt("available_bike_stands")
                );
            } catch(JSONException e){
                e.printStackTrace();
            }

            listeStations.add(objectStation);
        }

        mesStations = listeStations;
        return listeStations;
    }

    @Override
    protected void onPostExecute(ArrayList<Station> listeStations){
        StationAdapter stationAdapter = new StationAdapter(listeStations, this.context);
        this.list.setAdapter(stationAdapter);
    }

    public ArrayList<Station> getMesStations() {
        return mesStations;
    }

    public ArrayList<Station> getStationsLibre(){
        ArrayList<Station> stationLibre = new ArrayList<>();
        for (Station item : mesStations) {
            if(item.getAvailable_bike_stands() > 0){
                stationLibre.add(item);
            }
        }

        return stationLibre;
    }

}
