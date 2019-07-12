package com.example.ddami.zaklad.Fragments;

import android.app.LauncherActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ddami.zaklad.Adapters.MatchesAdapter;
import com.example.ddami.zaklad.Adapters.RevenuseAdapter;
import com.example.ddami.zaklad.Controllers.DatabaseHelper;
import com.example.ddami.zaklad.Models.Revenues;
import com.example.ddami.zaklad.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Wojtek on 28.10.2017.
 */

public class MessagesFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<LauncherActivity.ListItem> listItems;
    Revenues matchesModel = new Revenues();

    RevenuseAdapter revenuesad = null;
    ListView listView = null;
    ArrayList<Revenues> revenuesArrayList = null;

    Float kwota = null;
    Double kwotadouble = null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.messages_fragment, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = (ListView) getActivity().findViewById(R.id.wplaty);
        final DatabaseHelper helper = new DatabaseHelper(getActivity());
        revenuesArrayList=helper.getAllRevenuse();
        revenuesad = new RevenuseAdapter(getActivity(), R.layout.revenuse, revenuesArrayList);
        listView.setAdapter(revenuesad);

        kwotadouble = helper.getTotalMoney();
          String string = kwotadouble.toString();
          TextView textView = (TextView) getActivity().findViewById(R.id.textView2);
          textView.setText(string);

        final EditText edit2 = (EditText) getActivity().findViewById(R.id.editText2);
        Button button = (Button) getActivity().findViewById(R.id.addButton);
        final DatabaseHelper db = new DatabaseHelper(getActivity());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String data = edit2.getText().toString();


                if(!data.isEmpty() && data.length()>0){

                    Float datafloat = Float.valueOf(data);
                    matchesModel.setCost(datafloat);

                    Double homefloat = Double.parseDouble(data);

                    matchesModel.setCost(datafloat);

                    db.insertRevenues(matchesModel);

                    edit2.setText("");

                    Toast.makeText(getContext(), "Dodano kwote: " + revenuesad, Toast.LENGTH_SHORT ).show();

                    revenuesArrayList=db.getAllRevenuse();

                    revenuesad = new RevenuseAdapter(getActivity(), R.layout.revenuse, revenuesArrayList);

                    listView = (ListView) getActivity().findViewById(R.id.wplaty);
                    listView.setAdapter(revenuesad);

                } else{
                    Toast.makeText(getContext(), "Brak kwoty do dodania!", Toast.LENGTH_SHORT ).show();
                }

            }
        });
    }



}
