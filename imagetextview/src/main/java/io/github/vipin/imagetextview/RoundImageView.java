package io.github.vipin.imagetextview;

/**
 * Created by user on 5/17/2016.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.ImageView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

public class RoundImageView extends ImageView {

    Drawable drawable;
    Bitmap bitmap;
    Canvas c;

    public RoundImageView(Context context) {
        super(context);
        drawable = getDrawable();
        if (drawable instanceof BitmapDrawable) {

        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight(), Config.ARGB_8888);
            c = new Canvas(bitmap);
        }
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        drawable = getDrawable();

        if (drawable instanceof BitmapDrawable) {

        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight(), Config.ARGB_8888);
            c = new Canvas(bitmap);
        }
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        drawable = getDrawable();
        if (drawable instanceof BitmapDrawable) {

        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight(), Config.ARGB_8888);
            c = new Canvas(bitmap);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Drawable drawable = getDrawable();

        if (drawable == null) {
            return;
        }

        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }

        Bitmap b;
        if (drawable instanceof BitmapDrawable) {
            b = ((BitmapDrawable) drawable).getBitmap();
        } else {
            drawable.setBounds(0, 0, c.getWidth(), c.getHeight());
            drawable.draw(c);
            b = bitmap;
        }

        Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);


        int w = getWidth(), h = getHeight();

        Bitmap roundBitmap = getCroppedBitmap(getFaceBitmap(bitmap,getContext()), w<h?w:h);

        canvas.drawBitmap(roundBitmap, 0, 0, null);


    }

    public static Bitmap getCroppedBitmap(Bitmap bmp, int radius) {
        Bitmap sbmp;
        System.out.println("bmpw :" + bmp.getWidth()+"\n"+
                "bmph :"+ bmp.getHeight()+"\n"+
                "radius :"+radius);

        if (bmp.getWidth() != radius || bmp.getHeight() != radius) {
            float smallest = Math.min(bmp.getWidth(), bmp.getHeight());
            float factor = smallest / radius;
            sbmp = Bitmap.createScaledBitmap(bmp,
                    (int) (bmp.getWidth() / factor),
                    (int) (bmp.getHeight() / factor), false);
        } else {
            sbmp = bmp;
        }

        Bitmap output = Bitmap.createBitmap(radius, radius, Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xffa19774;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, radius, radius);

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(radius / 2 + 0.7f, radius / 2 + 0.7f,
              radius / 2 + 0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(sbmp, rect, rect, paint);

        return output;
    }


    private Bitmap getFaceBitmap(Bitmap bmp,Context context){

        FaceDetector faceDetector = new
                FaceDetector.Builder(context).setTrackingEnabled(false)
                .build();
        if(!faceDetector.isOperational()){
            System.out.println("Face detector not working");
            return null;
        }
        Bitmap faceBitmap = null;

        Frame frame = new Frame.Builder().setBitmap(bmp).build();
        SparseArray<Face> faces = faceDetector.detect(frame);
        for(int i=0; i<faces.size(); i++) {
            Face thisFace = faces.valueAt(i);

            int faceWidth=(int)thisFace.getWidth();
            int faceHeight=(int)thisFace.getHeight();
            int x1 = (int)thisFace.getPosition().x;
            int y1 = (int)thisFace.getPosition().y;


            faceBitmap=Bitmap.createBitmap(bmp,
                    x1> (faceWidth/2) ? (x1-faceWidth/2):0,
                    y1> (faceHeight/2) ? (y1-faceHeight/2):0,
                    1.5*faceWidth < bmp.getWidth() ? (int)(1.5*faceWidth) : bmp.getWidth(),
                    1.5*faceHeight < bmp.getWidth() ? (int)(1.5*faceHeight) : bmp.getHeight());

        }
        return faceBitmap;
    }

}