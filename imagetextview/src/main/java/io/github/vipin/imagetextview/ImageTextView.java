package io.github.vipin.imagetextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by user on 5/17/2016.
 */
public class ImageTextView extends LinearLayout {

    RoundImageView roundImageView;
    TextView title,subTitle;


    public ImageTextView(Context context) {
        super(context);
        initialize(context);
    }

    public ImageTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
        TypedArray params=context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ImageTextView,
                0,0
        );
        try{
            roundImageView.setImageDrawable(params.getDrawable(R.styleable.ImageTextView_imageSrc));
            title.setText(params.getText(R.styleable.ImageTextView_imageTitle));
            subTitle.setText(params.getText(R.styleable.ImageTextView_imageSubTitle));
        }
        finally {
            params.recycle();
        }

    }

    private void initialize(Context context){
        View view = inflate(context, R.layout.element_image_text, this);
        roundImageView=(RoundImageView)view.findViewById(R.id.imageView);
        title=(TextView)view.findViewById(R.id.title);
        subTitle=(TextView)view.findViewById(R.id.subTitle);
    }
}
