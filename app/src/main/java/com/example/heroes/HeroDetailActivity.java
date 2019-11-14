package com.example.heroes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class HeroDetailActivity extends AppCompatActivity {

    private Hero hero;
    private ImageView image;
    private TextView descriptionText;
    private TextView rankText;
    private TextView superpowerText;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_detail);

        Intent openList = getIntent();
        hero = openList.getParcelableExtra(HeroListActivity.EXTRA_POSITION);

        wireWidgets();

        name.setText(hero.getName());
        descriptionText.setText(hero.getDescription());
        rankText.setText(String.valueOf(hero.getRanking()));
        superpowerText.setText(hero.getSuperpower());
    }

    private void wireWidgets() {

        image=findViewById(R.id.imageView_herodetail_heroPicture);
        int resourceImage = getResources().getIdentifier(hero.getImage(), "drawable", getPackageName());
        image.setImageDrawable(getResources().getDrawable(resourceImage));

        descriptionText=findViewById(R.id.textView_herodetail_descriptionText);
        rankText=findViewById(R.id.textView_herodetail_rankingText);
        superpowerText=findViewById(R.id.textView_herodetail_superpowerText);
        name=findViewById(R.id.textView_herodetail_name);

    }


}
