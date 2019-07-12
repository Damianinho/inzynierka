package com.example.ddami.zaklad.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ddami.zaklad.Models.Matches;
import com.example.ddami.zaklad.R;

import java.util.ArrayList;

/**
 * Created by ddami on 26.11.2017.
 */

public class MatchesAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<Matches> matches;

    public MatchesAdapter(Context context, int textViewResourceId, ArrayList objects) {
        super(context,textViewResourceId, objects);

        this.context= context;
        matches=objects;

    }

    private class ViewHolder
    {
        TextView idTextView;
        TextView homeTextView;
        TextView awayTextView;
        TextView homecourseTextView;
        TextView awaycourseTextView;
        TextView drawcourseTextView;
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
            holder.homecourseTextView = (TextView) convertView.findViewById(R.id.course_home);
            holder.awaycourseTextView = (TextView) convertView.findViewById(R.id.course_away);
            holder.drawcourseTextView = (TextView) convertView.findViewById(R.id.course_draw);
            holder.homeTextView = (TextView) convertView.findViewById(R.id.home);
            holder.awayTextView = (TextView) convertView.findViewById(R.id.away);
            convertView.setTag(holder);

        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Matches individualCar= matches.get(position);
        holder.idTextView.setText("ID: " +  individualCar.getId() + "");
        holder.homeTextView.setText("Dom: "+ individualCar.getHome()+"");
        holder.awayTextView.setText("Wyjazd: "+ individualCar.getAway()+"");
        holder.homecourseTextView.setText("Kurs dom: "+ individualCar.getCourse_home()+"");
        holder.drawcourseTextView.setText("Kurs remis: "+ individualCar.getCourse_draw()+"");
        holder.awaycourseTextView.setText("Kurs wyjazd: "+ individualCar.getCourse_away()+"");
        return convertView;


    }
}
