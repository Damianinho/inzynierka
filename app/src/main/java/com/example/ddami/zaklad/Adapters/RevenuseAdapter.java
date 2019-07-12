package com.example.ddami.zaklad.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ddami.zaklad.Models.Matches;
import com.example.ddami.zaklad.Models.Revenues;
import com.example.ddami.zaklad.R;

import java.util.ArrayList;

/**
 * Created by ddami on 26.11.2017.
 */

public class RevenuseAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<Revenues> revenues;

    public RevenuseAdapter(Context context, int textViewResourceId, ArrayList objects) {
        super(context,textViewResourceId, objects);

        this.context= context;
        revenues=objects;

    }

    private class ViewHolder
    {
        TextView idTextView;
        TextView dateTextView;
        TextView kwotaTextView;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder=null;
        if (convertView == null)
        {
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.revenuse, null);

            holder = new ViewHolder();
            holder.idTextView = (TextView) convertView.findViewById(R.id.id);
            holder.dateTextView = (TextView) convertView.findViewById(R.id.date);
            holder.kwotaTextView = (TextView) convertView.findViewById(R.id.kwota);
            convertView.setTag(holder);

        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Revenues individualCar= revenues.get(position);
        holder.idTextView.setText("ID: " +  individualCar.getId() + "");
        holder.dateTextView.setText("Data: "+ individualCar.getPrzychody()+"");
        holder.kwotaTextView.setText("Kwota: "+ individualCar.getCost()+"");
        return convertView;


    }
}
