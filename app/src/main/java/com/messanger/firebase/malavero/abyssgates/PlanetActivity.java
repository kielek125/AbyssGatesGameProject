package com.messanger.firebase.malavero.abyssgates;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.messanger.firebase.malavero.abyssgates.FINAL.StaticMethods;
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

        zoomLayout = (ZoomLayout)findViewById(R.id.zoomLayout);
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
                Toast.makeText(getApplicationContext(), WIDTH + " " + HEIGHT, Toast.LENGTH_LONG).show();
            }
        } catch (IOException ex) {
            StaticMethods.toastException(ex, this);
        }
        drawFlags();
    }
    public boolean onTouch(View v, MotionEvent event){
        zoomLayout.init(PlanetActivity.this);
        return false;
    }
    private void drawFlags(){
        StaticMethods.drawFlag(layout, getApplicationContext(), 100,100,100, 100);
    }
}
