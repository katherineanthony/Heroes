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
        // read your json file into an array of questions
        Hero[] heroesList =  gson.fromJson(jsonString, Hero[].class);
        // convert your array to a list using the Arrays utility class
        List<Hero> questionList = Arrays.asList(heroesList);

        // verify that it read everything properly
        Log.d("Quiz", "onCreate: " + questionList.toString());
    }

    private void setOnClickListeners() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent
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

            TextView textViewName = convertView.findViewById(R.id.textView_heroitem_name);
            // do this for as many widgets as you need

            // set the value for each widget. use the position parameter variable
            // to get the hero that you need out of the list
            // and set the values for widgets.

            // 3. return the inflated view
            return convertView;

        }
    }
}