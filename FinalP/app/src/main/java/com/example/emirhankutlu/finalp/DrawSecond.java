package com.example.emirhankutlu.finalp;

import android.graphics.Color;
import android.os.Bundle;

/**
 * Created by emirhankutlu on 17/05/15.
 */

public class DrawSecond {
    public class Second extends MainActivity {
        Draw drawView;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            drawView = new Draw(this);
            drawView.setBackgroundColor(Color.WHITE);
            setContentView(drawView);

        }
    }
}
