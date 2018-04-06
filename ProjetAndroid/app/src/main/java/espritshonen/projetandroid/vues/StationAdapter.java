package espritshonen.projetandroid.vues;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import espritshonen.projetandroid.R;
import espritshonen.projetandroid.métier.Station;

public class StationAdapter extends BaseAdapter {
    private ArrayList<Station> list;
    private Context context;

    public StationAdapter(ArrayList<Station> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.row,parent,false);
        }

        StationViewHolder viewHolder = (StationViewHolder) convertView.getTag();

        if (viewHolder == null){
            viewHolder = new StationViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.address = (TextView) convertView.findViewById(R.id.address);
            viewHolder.status = (TextView) convertView.findViewById(R.id.status);
            viewHolder.available_bikes = (TextView) convertView.findViewById(R.id.available_bikes);
            viewHolder.available_bike_stands = (TextView) convertView.findViewById(R.id.available_bike_stands);

            convertView.setTag(viewHolder);

        }

        Station station = (Station) this.getItem(position);
        viewHolder.name.setText(station.getName());
        viewHolder.name.setTag(position);
        viewHolder.address.setText(station.getAddress());
        viewHolder.status.setText("Statut: " + station.getStatus());
        viewHolder.available_bikes.setText(String.valueOf(station.getAvailable_bikes())+" vélo(s) disponible(s).");
        viewHolder.available_bike_stands.setText(String.valueOf(station.getAvailable_bike_stands()+" place(s) disponible(s)."));


        return convertView;
    }

    class StationViewHolder{
        private TextView name;
        private TextView address;
        private TextView status; //OPEN or CLOSE
        private TextView available_bikes;
        private TextView available_bike_stands;
    }


}

