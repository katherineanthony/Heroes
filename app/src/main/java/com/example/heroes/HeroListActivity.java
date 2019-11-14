package com.example.heroes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;

public class HeroListActivity extends AppCompatActivity {
    private String jsonString;
    private ListView listView;
    private List<Hero> heroList;


    private TextView textViewRank;
    private TextView textViewName;
    private TextView textViewDesc;
    private HeroAdapter heroAdapter;
    public static final String EXTRA_POSITION = "position";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes_list);

        wireWidgets();
        setOnClickListeners();

        InputStream xmlFileInputStream = getResources().openRawResource(R.raw.heroes);
        jsonString = readTextFile(xmlFileInputStream);

        // create a gson object
        Gson gson = new Gson();

        // read your json file into an array of heroes
        Hero[] heroesArray =  gson.fromJson(jsonString, Hero[].class);

        // convert your array to a list using the Arrays utility class
        heroList = Arrays.asList(heroesArray);

        heroAdapter = new HeroAdapter(heroList);

        listView.setAdapter(heroAdapter);
    }

    private void setOnClickListeners() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Hero heroClicked = heroList.get(position);

                Intent listViewClicked = new Intent (HeroListActivity.this,
                        HeroDetailActivity.class);
                listViewClicked.putExtra(EXTRA_POSITION, heroClicked);
                startActivity(listViewClicked);

            }
        });


    }

    private void wireWidgets() {
        listView=findViewById(R.id.listView_heroeslist_listView);

    }

    public String readTextFile(InputStream inputStream){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();

    }

    private class HeroAdapter extends ArrayAdapter<Hero> {
        // make an instance variable to keep track of the hero list
        private List<Hero> heroesList;

        public HeroAdapter(List<Hero> heroesList) {
            // since we're in the HeroListActivity class, we already have the context
            // we're hardcoding in a particular layout, so we don't need to put it in
            // the constructor either
            super(HeroListActivity.this, -1, heroesList);
            this.heroesList=heroesList;
        }

        // the goal of the adapter is to link your list to the listview
        // and tell the listview where each aspect of the list item goes/
        // so we override a method called getView


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // 1. inflate a layout
            LayoutInflater inflater = getLayoutInflater();

            // check if convertView is null, if so, replace it
            if(convertView == null)
            {
                // R.layout.item_hero is a custom layout we can make that represents
                // what a single item would look like in our listview
                convertView = inflater.inflate(R.layout.item_hero, parent, false);
            }

            // 2. wire widgets and link the hero to those widgets
            // instead of calling findViewById at the activity class leve,
            // we're calling it from the inflated layout to find THOSE widgets

            textViewName = convertView.findViewById(R.id.textView_heroitem_name);
            textViewDesc = convertView.findViewById(R.id.textView_heroitem_description);
            textViewRank = convertView.findViewById(R.id.textView_heroitem_number);
            // do this for as many widgets as you need

            // set the value for each widget. use the position parameter variable
            // to get the hero that you need out of the list
            // and set the values for widgets.

            textViewName.setText(heroesList.get(position).getName());
            textViewDesc.setText(heroesList.get(position).getDescription());
            textViewRank.setText(String.valueOf(heroesList.get(position).getRanking()));

            // 3. return the inflated view
            return convertView;

        }
    }
}