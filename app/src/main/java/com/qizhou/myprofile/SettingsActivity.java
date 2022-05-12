package com.qizhou.myprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.qizhou.myprofile.entities.Background;

import java.util.Arrays;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initSpinner();
    }

    private void initSpinner() {
        SharedPreferences sp = getSharedPreferences("SP", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        final List<Background> backgrounds = Arrays.asList(Background.values());
        final boolean[] onSpinnerStartUp = {true};

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<Background> arrayAdapter = new ArrayAdapter<>(SettingsActivity.this, android.R.layout.simple_spinner_item, backgrounds);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(arrayAdapter);

        int defaultBG = sp.getInt("defaultBG", -1);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (onSpinnerStartUp[0] && defaultBG != -1) {
                    spinner.setSelection(defaultBG);
                    position = defaultBG;
                    onSpinnerStartUp[0] = false;
                }

                editor.remove("defaultBG");
                editor.putInt("defaultBG", position);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void onConfirmClicked(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}