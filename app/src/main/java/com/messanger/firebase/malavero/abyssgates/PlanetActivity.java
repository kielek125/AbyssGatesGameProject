package com.messanger.firebase.malavero.abyssgates;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.messanger.firebase.malavero.abyssgates.FINAL.StaticMethods;
import com.messanger.firebase.malavero.abyssgates.FINAL.StaticValues;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class PlanetActivity extends Activity implements View.OnTouchListener {

    private ImageView backgroundMap;
    private int choosenPlanet = 0;
    public int WIDTH = 0;
    public int HEIGHT = 0;
    private ZoomLayout zoomLayout;
    private RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet);


        //TODO pobierz planete z bazy, jesli zwroci 0 to znaczy ze nie istnieje wiec pobierz z wczesniejszego intentu
        choosenPlanet = getIntent().getIntExtra("ChoosenPlanet", 0);
        backgroundMap = (ImageView) findViewById(R.id.backgroundMap);

        zoomLayout = (ZoomLayout) findViewById(R.id.zoomLayout);
        zoomLayout.setOnTouchListener(PlanetActivity.this);
        layout = (RelativeLayout) findViewById(R.id.contentPlanet);

        InputStream is = null;
        try {
            Drawable drawable;
            switch (choosenPlanet) {
                case 1:
                    drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.junglemap, null);
                    break;
                case 2:
                    drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.desertmap, null);
                    break;
                case 3:
                    drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.icemap, null);
                    break;
                default:
                    drawable = null;
                    break;
            }
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();
            WIDTH = bitmap.getWidth();
            HEIGHT = bitmap.getHeight();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            is = new ByteArrayInputStream(stream.toByteArray());
        } catch (Exception ex) {
            StaticMethods.toastException(ex, this);
        }

        try {
            if (is != null) {
                BitmapRegionDecoder decoder = BitmapRegionDecoder.newInstance(is, false);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                Bitmap partOfBitmap = decoder.decodeRegion(new Rect(0, 0, WIDTH, HEIGHT), options);
                backgroundMap.setImageBitmap(partOfBitmap);
            }
        } catch (IOException ex) {
            StaticMethods.toastException(ex, this);
        }
        drawFlags();
    }

    public boolean onTouch(View v, MotionEvent event) {
        zoomLayout.init(PlanetActivity.this);
        return false;
    }
    private void drawFlags() {
        DisplayMetrics display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        double x = display.widthPixels;
        double y = display.heightPixels;
        double myWidth = (x / StaticValues.BASIC_WIDTH) * 2.625;
        double myHeight = (y / StaticValues.BASIC_HEIGHT) * 2.625;
        Toast.makeText(getApplicationContext(), "X: " + myWidth + "Y: " + myHeight, Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), "X: " + x + "Y: " + y, Toast.LENGTH_LONG).show();

        final ImageView flagEnergonMine = StaticMethods.drawFlag(layout, getApplicationContext(), (int) (50 * myWidth), (int) (50 * myHeight), (int) (441 * myWidth), (int) (331 * myHeight));
        final ImageView flagEnergonStore = StaticMethods.drawFlag(layout, getApplicationContext(), (int) (50 * myWidth), (int) (50 * myHeight), (int) (379 * myWidth), (int) (297 * myHeight));
        final ImageView flagHeadquarters = StaticMethods.drawFlag(layout, getApplicationContext(), (int) (50 * myWidth), (int) (50 * myHeight), (int) (361 * myWidth), (int) (201 * myHeight));
        final ImageView flagLaboratory = StaticMethods.drawFlag(layout, getApplicationContext(), (int) (50 * myWidth), (int) (50 * myHeight), (int) (142 * myWidth), (int) (290 * myHeight));
        final ImageView flagMaterialMine = StaticMethods.drawFlag(layout, getApplicationContext(), (int) (50 * myWidth), (int) (50 * myHeight), (int) (48 * myWidth), (int) (200 * myHeight));
        final ImageView flagMineralMine = StaticMethods.drawFlag(layout, getApplicationContext(), (int) (50 * myWidth), (int) (50 * myHeight), (int) (182 * myWidth), (int) (117 * myHeight));
        final ImageView flagNavalShipyard = StaticMethods.drawFlag(layout, getApplicationContext(), (int) (50 * myWidth), (int) (50 * myHeight), (int) (312 * myWidth), (int) (126 * myHeight));
        final ImageView flagObservatory = StaticMethods.drawFlag(layout, getApplicationContext(), (int) (50 * myWidth), (int) (50 * myHeight), (int) (630 * myWidth), (int) (196 * myHeight));
        final ImageView flagShieldGenerator = StaticMethods.drawFlag(layout, getApplicationContext(), (int) (50 * myWidth), (int) (50 * myHeight), (int) (368 * myWidth), (int) (134 * myHeight));
        final ImageView flagSolarBattery = StaticMethods.drawFlag(layout, getApplicationContext(), (int) (50 * myWidth), (int) (50 * myHeight), (int) (526 * myWidth), (int) (128 * myHeight));
        final ImageView flagStorehouse = StaticMethods.drawFlag(layout, getApplicationContext(), (int) (50 * myWidth), (int) (50 * myHeight), (int) (517 * myWidth), (int) (235 * myHeight));
        final ImageView flagTemple = StaticMethods.drawFlag(layout, getApplicationContext(), (int) (50 * myWidth), (int) (50 * myHeight), (int) (289 * myWidth), (int) (74 * myHeight));
        final ImageView flagTradePavilon = StaticMethods.drawFlag(layout, getApplicationContext(), (int) (50 * myWidth), (int) (50 * myHeight), (int) (173 * myWidth), (int) (232 * myHeight));
        final ImageView flagWorkshop = StaticMethods.drawFlag(layout, getApplicationContext(), (int) (50 * myWidth), (int) (50 * myHeight), (int) (490 * myWidth), (int) (113 * myHeight));


        flagEnergonMine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagEnergonMine.setImageResource(R.drawable.testkopalnia);
                Toast.makeText(getApplicationContext(), "X = " + v.getX() + " || Y = " + v.getY(), Toast.LENGTH_LONG).show();
            }
        });
        flagEnergonStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "X = " + v.getX() + " || Y = " + v.getY(), Toast.LENGTH_LONG).show();
            }
        });
        flagHeadquarters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "X = " + v.getX() + " || Y = " + v.getY(), Toast.LENGTH_LONG).show();
            }
        });
        flagLaboratory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "X = " + v.getX() + " || Y = " + v.getY(), Toast.LENGTH_LONG).show();
            }
        });
        flagMaterialMine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "X = " + v.getX() + " || Y = " + v.getY(), Toast.LENGTH_LONG).show();
            }
        });
        flagMineralMine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "X = " + v.getX() + " || Y = " + v.getY(), Toast.LENGTH_LONG).show();
            }
        });
        flagNavalShipyard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "X = " + v.getX() + " || Y = " + v.getY(), Toast.LENGTH_LONG).show();
            }
        });
        flagObservatory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "X = " + v.getX() + " || Y = " + v.getY(), Toast.LENGTH_LONG).show();
            }
        });
        flagShieldGenerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "X = " + v.getX() + " || Y = " + v.getY(), Toast.LENGTH_LONG).show();
            }
        });
        flagSolarBattery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "X = " + v.getX() + " || Y = " + v.getY(), Toast.LENGTH_LONG).show();
            }
        });
        flagStorehouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "X = " + v.getX() + " || Y = " + v.getY(), Toast.LENGTH_LONG).show();
            }
        });
        flagTemple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "X = " + v.getX() + " || Y = " + v.getY(), Toast.LENGTH_LONG).show();
            }
        });
        flagTradePavilon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "X = " + v.getX() + " || Y = " + v.getY(), Toast.LENGTH_LONG).show();
            }
        });
        flagWorkshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "X = " + v.getX() + " || Y = " + v.getY(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
