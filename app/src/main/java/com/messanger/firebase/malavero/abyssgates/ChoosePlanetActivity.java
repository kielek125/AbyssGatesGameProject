package com.messanger.firebase.malavero.abyssgates;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.messanger.firebase.malavero.abyssgates.FINAL.StaticMethods;

public class ChoosePlanetActivity extends Activity {

    private int yourPlanet = 0;
    private ImageView choosePlanet;
    private TextView infoPlanetDesert, infoPlanetIce, infoPlanetJungle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_planet);

        choosePlanet = (ImageView)findViewById(R.id.choosePlanetImageView);
        infoPlanetDesert = (TextView)findViewById(R.id.infoPlanetDesertTextView);
        infoPlanetIce = (TextView)findViewById(R.id.infoPlanetIceTextView);
        infoPlanetJungle = (TextView)findViewById(R.id.infoPlanetJungleTextView);
    }

    public void chooseYourFirstPlanet(View view) {
        //TODO powiedz bazie jaka gracz wybrał planete
        //TODO wywołaj metodę renderującą pozycje gracza w układzie
        Intent afterChoosePlanet = new Intent(this, PlanetActivity.class);
        afterChoosePlanet.putExtra("ChoosenPlanet", yourPlanet);
        startActivity(afterChoosePlanet);
        finish();
    }

    public void choosePlanet(View view) {
        switch (view.getId()) {
            case R.id.selectPlanetJungleImageView:
                yourPlanet = 1;
                infoPlanetJungle.setBackgroundColor(Color.parseColor("#4066cd00"));
                infoPlanetIce.setBackgroundResource(0);
                infoPlanetDesert.setBackgroundColor(0);
                break;
            case R.id.selectPlanetDesertImageView:
                yourPlanet = 2;
                infoPlanetDesert.setBackgroundColor(Color.parseColor("#4066cd00"));
                infoPlanetJungle.setBackgroundResource(0);
                infoPlanetIce.setBackgroundResource(0);
                break;
            case R.id.selectPlanetIceImageView:
                yourPlanet = 3;
                infoPlanetIce.setBackgroundColor(Color.parseColor("#4066cd00"));
                infoPlanetJungle.setBackgroundResource(0);
                infoPlanetDesert.setBackgroundResource(0);
                break;
        }
        if(yourPlanet != 0){
            choosePlanet.setVisibility(View.VISIBLE);
        } else {
            choosePlanet.setVisibility(View.INVISIBLE);
        }
    }
}

