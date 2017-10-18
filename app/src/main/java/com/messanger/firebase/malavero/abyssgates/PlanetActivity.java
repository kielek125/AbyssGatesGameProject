package com.messanger.firebase.malavero.abyssgates;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.messanger.firebase.malavero.abyssgates.FINAL.StaticMethods;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PlanetActivity extends Activity {

    ImageView backgroundJungle;
    public int WIDTH = 0;
    public int HEIGHT = 0;
    public int DISPLAYWIDTH = 0;
    public int DISPLAYHEIGHT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_planet);

        new SwipeDetector(getWindow().getDecorView().findViewById(R.id.content)).setOnSwipeListener(new SwipeDetector.onSwipeEvent() {
            @Override
            public void SwipeEventDetected(View v, SwipeDetector.SwipeTypeEnum SwipeType) {
                if(SwipeType == SwipeDetector.SwipeTypeEnum.LEFT_TO_RIGHT)
                    Toast.makeText(getApplicationContext(), "igor to pala", Toast.LENGTH_SHORT).show(); //TODO
            }
        });

        backgroundJungle = (ImageView) findViewById(R.id.backgroundJungle);

        InputStream is = null;
        try {
            Drawable drawable = getResources().getDrawable(R.drawable.junglemap);
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
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            DISPLAYWIDTH = size.x;
            DISPLAYHEIGHT = size.y;
        } catch (Exception ex) {
            StaticMethods.toastException(ex, this);
        }
        try {
            if (is != null) {
                BitmapRegionDecoder decoder = BitmapRegionDecoder.newInstance(is, false);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                Bitmap partOfBitmap = decoder.decodeRegion(new Rect(0, 0, DISPLAYWIDTH, DISPLAYHEIGHT), options);
                backgroundJungle.setImageBitmap(partOfBitmap);
                Toast.makeText(getApplicationContext(), DISPLAYWIDTH + " " + DISPLAYHEIGHT, Toast.LENGTH_LONG).show();
            }
        } catch (IOException ex) {
            StaticMethods.toastException(ex, this);
        }
    }
}
