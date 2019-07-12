package com.example.ddami.zaklad.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ddami.zaklad.Adapters.MatchesAdapter;
import com.example.ddami.zaklad.Controllers.DatabaseHelper;
import com.example.ddami.zaklad.Models.Matches;
import com.example.ddami.zaklad.R;

import java.util.ArrayList;

/**
 * Created by ddami on 19.11.2017.
 */

public class PageFragment extends Fragment {

    MatchesAdapter matchesAdapter=null;
    ListView listView=null;
    ArrayList<Matches> matches=null;
    DatabaseHelper helper = new DatabaseHelper(getActivity());
    Matches matchesModel = new Matches();
    Button addButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.page_fragment, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        listView = (ListView) getActivity().findViewById(R.id.listview_matches);
        addButton = (Button) getActivity().findViewById(R.id.add_button);

        final DatabaseHelper db = new DatabaseHelper(getActivity());
        /*db.insertOneCategory(categoriesModel);*/
        matches=db.getAllMatches();
        matchesAdapter= new MatchesAdapter(getActivity(),R.layout.matches,matches);

        listView = (ListView) getActivity().findViewById(R.id.listview_matches);
        listView.setAdapter(matchesAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==matchesAdapter.getCount()-1) {

/*                    String category_name;

                    category_name = matches.get(position).getCategory_name();

                    db.removeSingleCategory(category_name);*/

                    Toast.makeText(getContext(), "To bedzie usuniecie", Toast.LENGTH_SHORT ).show();

/*                    categories = db.getAllCategories();

                    categoriesAdapter = new CategoriesAdapter(getActivity(), R.layout.categories_details, categories);

                    listView = (ListView) getActivity().findViewById(R.id.listview_categories);
                    listView.setAdapter(categoriesAdapter);*/
                }
                else{
                    Toast.makeText(getContext(), "Można usunąć jedynie ostatni element z listy ", Toast.LENGTH_SHORT ).show();
                }
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText homeeditText = (EditText) getActivity().findViewById(R.id.home_edittext);
                EditText awayeditText = (EditText) getActivity().findViewById(R.id.away_edittext);
                EditText homecourseeditText = (EditText) getActivity().findViewById(R.id.homecourse_edittext);
                EditText awaycourseeditText = (EditText) getActivity().findViewById(R.id.awaycourse_edittext);
                EditText drawcourseeditText = (EditText) getActivity().findViewById(R.id.drawcourse_edittext);
                String home = homeeditText.getText().toString();
                String away = awayeditText.getText().toString();
                String homecourse = homecourseeditText.getText().toString();
                String awaycourse = awaycourseeditText.getText().toString();
                String drawcourse = drawcourseeditText.getText().toString();

                if(!home.isEmpty() && home.length()>0 && !away.isEmpty() && away.length()>0 && !homecourse.isEmpty() && homecourse.length()>0 && !awaycourse.isEmpty() && awaycourse.length()>0 && !drawcourse.isEmpty() && drawcourse.length()>0){

                    matchesModel.setHome(home);
                    matchesModel.setAway(away);
                    float homefloat = Float.parseFloat(homecourse);
                    float awayfloat = Float.parseFloat(awaycourse);
                    float drawfloat = Float.parseFloat(drawcourse);
                    matchesModel.setCourse_away(awayfloat);
                    matchesModel.setCourse_draw(drawfloat);
                    matchesModel.setCourse_home(homefloat);
                    db.insertMatches(matchesModel);
                    homeeditText.setText("");
                    awayeditText.setText("");
                    homecourseeditText.setText("");
                    awaycourseeditText.setText("");
                    drawcourseeditText.setText("");
                    Toast.makeText(getContext(), "Dodano mecz: " + matches, Toast.LENGTH_SHORT ).show();

                    matches=db.getAllMatches();
                    matchesAdapter= new MatchesAdapter(getActivity(),R.layout.matches,matches);

                    listView = (ListView) getActivity().findViewById(R.id.listview_matches);
                    listView.setAdapter(matchesAdapter);

                } else{
                    Toast.makeText(getContext(), "Brak meczu do dodania!", Toast.LENGTH_SHORT ).show();
                }

            }
        });
        getActivity().setTitle("Mecze");
    }
}
