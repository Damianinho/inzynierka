package com.example.ddami.zaklad.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ddami.zaklad.Models.Coupons;
import com.example.ddami.zaklad.Models.Matches;
import com.example.ddami.zaklad.R;

import java.util.ArrayList;

/**
 * Created by ddami on 30.11.2017.
 */

public class CouponsAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<Matches> coupons;

    public CouponsAdapter(Context context, int textViewResourceId, ArrayList objects) {
        super(context,textViewResourceId, objects);

        this.context= context;
        coupons=objects;

    }

    private class ViewHolder
    {
        TextView idTextView;
        TextView homeTextView;
        TextView awayTextView;
        TextView courseTextView;
        TextView winnTextView;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder=null;
        if (convertView == null)
        {
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.matches, null);

            holder = new ViewHolder();
            holder.idTextView = (TextView) convertView.findViewById(R.id.id);
            holder.homeTextView = (TextView) convertView.findViewById(R.id.course_home);
            holder.awayTextView = (TextView) convertView.findViewById(R.id.course_away);
           // holder.courseTextView = (TextView) convertView.findViewById(R.id.course);
            //holder.winnTextView = (TextView) convertView.findViewById(R.id.winn);

            convertView.setTag(holder);

        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Matches individualCar= coupons.get(position);
        holder.idTextView.setText("ID: " +  individualCar.getId() + "");
        holder.homeTextView.setText("Dom: "+ individualCar.getHome()+"");
        holder.awayTextView.setText("Wyjazd: "+ individualCar.getAway()+"");
        holder.courseTextView.setText("Kurs dom: "+ individualCar.getCourse_home()+"");
        holder.winnTextView.setText("Kurs remis: "+ individualCar.getCourse_draw()+"");

        return convertView;


    }
}
