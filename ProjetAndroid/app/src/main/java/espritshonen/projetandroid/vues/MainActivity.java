package espritshonen.projetandroid.vues;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import espritshonen.projetandroid.R;
import espritshonen.projetandroid.connexion.ConnexionAPI;
import espritshonen.projetandroid.métier.Station;

public class MainActivity extends AppCompatActivity {

    ListView listeViewStation;
    public ArrayList<Station> listeStations;
    ConnexionAPI maConnexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        maConnexion = new ConnexionAPI((ListView)findViewById(R.id.listView),getBaseContext());
        maConnexion.execute();
        listeViewStation = (ListView) findViewById(R.id.listView);
        listeStations = maConnexion.getMesStations();
        TextView idd = findViewById(R.id.name);
        listeViewStation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView idd = view.findViewById(R.id.name);
                int index = (Integer) idd.getTag();
                String lat = String.valueOf(maConnexion.getMesStations().get(index).getLat());
                String lng = String.valueOf(maConnexion.getMesStations().get(index).getLng());
                TextView dfs = view.findViewById(R.id.available_bike_stands);
                String k = (String) dfs.getText();
                Intent myIntent = new Intent(MainActivity.this , MapsActivity.class);
                myIntent.putExtra("lat", lat);
                myIntent.putExtra("lng", lng);
                myIntent.putExtra("mode","main");
                startActivity(myIntent);

            }
        });

    }

    public void mapStationLibre(View view){
        ArrayList<Station> stationLibre =  maConnexion.getStationsLibre();
        int i = 0,total = stationLibre.size();
        double[] lat = new double[total];
        double[] lng = new double[total];

        for (Station item : stationLibre) {
            lat[i] = item.getLat();
            lng[i] = item.getLng();
            i++;
        }
        Intent myIntent = new Intent(MainActivity.this , MapsActivity.class);
        myIntent.putExtra("latArray", lat);
        myIntent.putExtra("lngArray", lng);
        myIntent.putExtra("mode","libre");
        startActivity(myIntent);
    }

}