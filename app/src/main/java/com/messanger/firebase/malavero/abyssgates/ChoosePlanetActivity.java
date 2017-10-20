package com.messanger.firebase.malavero.abyssgates;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ChoosePlanetActivity extends Activity {

    private int yourPlanet = 0;
    private ImageView choosePlanet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_planet);

        choosePlanet = (ImageView)findViewById(R.id.choosePlanetImageView);
    }

    public void chooseYourFirstPlanet(View view) {
        //TODO powiedz bazie jaka gracz wybrał planete
        //TODO wywołaj metodę renderującą pozycje gracza w układzie
        Intent afterChoosePlanet = new Intent(this, PlanetActivity.class);
        afterChoosePlanet.getIntExtra("ChoosenPlanet", yourPlanet);
        startActivity(afterChoosePlanet);
        finish();
    }

    public void choosePlanet(View view) {
        switch (view.getId()) {
            case R.id.selectPlanetJungleImageView:
                yourPlanet = 1;
                break;
            case R.id.selectPlanetIceImageView:
                yourPlanet = 2;
                break;
            case R.id.selectPlanetDesertImageView:
                yourPlanet = 3;
                break;
        }
        if(yourPlanet != 0){
            choosePlanet.setVisibility(View.VISIBLE);
        } else {
            choosePlanet.setVisibility(View.INVISIBLE);
        }
    }
}

