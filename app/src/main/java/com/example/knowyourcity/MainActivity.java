package com.example.knowyourcity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Build;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ConstraintLayout layout;
    private ConstraintSet constraintSetOld = new ConstraintSet();
    private ConstraintSet constraintSetNew = new ConstraintSet();
    private boolean altLayout;
    private Button btnNext;
    private ImageView introImage;
    private TextView placeName;
    private TextView placeText;

    public int[][] places;
    private int position=0;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.layout);

        constraintSetOld.clone(layout);
        constraintSetNew.clone(this, R.layout.activity_main_alt);
        btnNext = findViewById(R.id.btnNext);
        placeName = findViewById(R.id.textView);
        placeText = findViewById(R.id.textView2);
        btnNext.setOnClickListener(this);

        places = new int[][]{
            new int[]{R.string.cmhr_name, R.string.cmhr_text, R.drawable.cmhr},
            new int[]{R.string.circleoflifethunderbirdhouse_name, R.string.circleoflifethunderbirdhouse_text, R.drawable.circleoflifethunderbirdhouse},
            new int[]{R.string.backalleyarctic_name, R.string.backalleyarctic_text, R.drawable.backalleyarctic},
            new int[]{R.string.manitobalegislativebuilding_name, R.string.manitobalegislativebuilding_text, R.drawable.manitobalegislativebuilding},
            new int[]{R.string.esplanaderielpedestrianbridge_name, R.string.esplanaderielpedestrianbridge_text, R.drawable.esplanaderielpedestrianbridge},
            new int[]{R.string.stbonifacecathedral_name, R.string.stbonifacecathedral_text, R.drawable.stbonifacecathedral},
            new int[]{R.string.universityofmanitoba_name, R.string.universityofmanitoba_text, R.drawable.universityofmanitoba},
        };
    }
   public void onClick(View view){

       int lastPosition = position;
       Random random = new Random();
       position = random.nextInt(places.length);

       if (position == lastPosition){
           position = random.nextInt(places.length);
       }

       introImage = findViewById(R.id.imageView);
       constraintSetOld.applyTo(layout);
       placeName.setText(places[position][0]);
       placeText.setText(places[position][1]);
       introImage.setImageResource(places[position][2]);
   }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void swapView(View v) {
        Transition changeBounds = new ChangeBounds();
        changeBounds.setInterpolator(new AccelerateDecelerateInterpolator());

        TransitionManager.beginDelayedTransition(layout, changeBounds);

        if (!altLayout) {
            constraintSetNew.applyTo(layout);
            altLayout = true;
        } else {
            constraintSetOld.applyTo(layout);
            altLayout = false;
        }
    }
}