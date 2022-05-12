package com.qizhou.myprofile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.qizhou.myprofile.entities.Background;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBackground();
    }

    private void initBackground() {
        SharedPreferences sp = getSharedPreferences("SP", Context.MODE_PRIVATE);
        int defaultBG = sp.getInt("defaultBG", -1);
        final List<Background> backgrounds = Arrays.asList(Background.values());
        ConstraintLayout constraintLayout = findViewById(R.id.main_activity);

        if (defaultBG == -1 || Background.spring.equals(backgrounds.get(defaultBG))) {
            constraintLayout.setBackgroundResource(R.drawable.spring);
        } else if (Background.summer.equals(backgrounds.get(defaultBG))) {
            constraintLayout.setBackgroundResource(R.drawable.summer);
        } else if (Background.autumn.equals(backgrounds.get(defaultBG))) {
            constraintLayout.setBackgroundResource(R.drawable.autumn);
        } else {
            constraintLayout.setBackgroundResource(R.drawable.winter);
        }

        constraintLayout.getBackground().setAlpha(100);
    }

    public void onSettingsClicked(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}