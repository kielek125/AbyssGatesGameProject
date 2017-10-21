package com.messanger.firebase.malavero.abyssgates;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.messanger.firebase.malavero.abyssgates.FINAL.StaticMethods;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PlanetActivity extends Activity implements View.OnTouchListener {

    ImageView backgroundMap;
    private int choosenPlanet = 0;
    public int WIDTH = 0;
    public int HEIGHT = 0;
//    public int DISPLAYWIDTH = 0;
//    public int DISPLAYHEIGHT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet);


        //TODO pobierz planete z bazy, jesli zwroci 0 to znaczy ze nie istnieje wiec pobierz z wczesniejszego intentu
        choosenPlanet = getIntent().getIntExtra("ChoosenPlanet", 0);
        backgroundMap = (ImageView) findViewById(R.id.backgroundMap);

        backgroundMap.setOnTouchListener(this);


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

//        try {
//            Display display = getWindowManager().getDefaultDisplay();
//            Point size = new Point();
//            display.getSize(size);
//            DISPLAYWIDTH = size.x;
//            DISPLAYHEIGHT = size.y;
//        } catch (Exception ex) {
//            StaticMethods.toastException(ex, this);
//        }
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
    }


    private static final String TAG = "Touch";
    @SuppressWarnings("unused")
    private static final float MIN_ZOOM = 10f, MAX_ZOOM = 100f;

    Matrix matrix = new Matrix();
    Matrix savedMatrix = new Matrix();

    static final int NONE = 0;
    static final int DRAG = 1;
    static final int ZOOM = 2;
    int mode = NONE;

    PointF start = new PointF();
    PointF mid = new PointF();
    float oldDist = 1f;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ImageView view = (ImageView) v;
        view.setScaleType(ImageView.ScaleType.MATRIX);
        float scale;
        //dumpEvent(event);

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:   // first finger down only
                savedMatrix.set(matrix);
                start.set(event.getX(), event.getY());
                Log.d(TAG, "mode=DRAG"); // write to LogCat
                mode = DRAG;
                break;

            case MotionEvent.ACTION_UP: // first finger lifted

            case MotionEvent.ACTION_POINTER_UP: // second finger lifted

                mode = NONE;
                Log.d(TAG, "mode=NONE");
                break;

            case MotionEvent.ACTION_POINTER_DOWN: // first and second finger down

                oldDist = spacing(event);
                Log.d(TAG, "oldDist=" + oldDist);
                if (oldDist > 5f) {
                    savedMatrix.set(matrix);
                    midPoint(mid, event);
                    mode = ZOOM;
                    Log.d(TAG, "mode=ZOOM");
                }
                break;

            case MotionEvent.ACTION_MOVE:

                if (mode == DRAG) {
                    matrix.set(savedMatrix);
                    matrix.postTranslate(event.getX() - start.x, event.getY() - start.y);
                } else if (mode == ZOOM) {
                    // pinch zooming
                    float newDist = spacing(event);
                    Log.d(TAG, "newDist=" + newDist);
                    if (newDist > 5f) {
                        matrix.set(savedMatrix);
                        scale = newDist / oldDist;
                        // matrix...
                        // if scale > 1 means
                        // zoom in...
                        // if scale < 1 means
                        // zoom out
                        matrix.postScale(scale, scale, mid.x, mid.y);
                    }
                }
                break;
        }

        view.setImageMatrix(matrix); // display the transformation on screen

        return true; // indicate event was handled
    }

    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    private void dumpEvent(MotionEvent event) {
//        String names[] = { "DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE","POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?" };
//        StringBuilder sb = new StringBuilder();
//        int action = event.getAction();
//        int actionCode = action & MotionEvent.ACTION_MASK;
//        sb.append("event ACTION_").append(names[actionCode]);
//
//        if (actionCode == MotionEvent.ACTION_POINTER_DOWN || actionCode == MotionEvent.ACTION_POINTER_UP)
//        {
//            sb.append("(pid ").append(action >> MotionEvent.ACTION_POINTER_ID_SHIFT);
//            sb.append(")");
//        }
//
//        sb.append("[");
//        for (int i = 0; i < event.getPointerCount(); i++)
//        {
//            sb.append("#").append(i);
//            sb.append("(pid ").append(event.getPointerId(i));
//            sb.append(")=").append((int) event.getX(i));
//            sb.append(",").append((int) event.getY(i));
//            if (i + 1 < event.getPointerCount())
//                sb.append(";");
//        }
//
//        sb.append("]");
//        Log.d("Touch Events ---------", sb.toString());
    }
}
