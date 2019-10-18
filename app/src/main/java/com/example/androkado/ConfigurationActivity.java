package com.example.androkado;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class ConfigurationActivity extends AppCompatActivity {

    public static final String SORT = "sort";
    public static final String PRICE = "price";
    private SharedPreferences.Editor editor;


    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        SharedPreferences preferences = this.getSharedPreferences("PREFS_FILE", Context.MODE_PRIVATE);
        this.editor = preferences.edit();

        Switch aSwitch = this.findViewById(R.id.conf_switch);
        EditText etPrice = this.findViewById(R.id.price_value);

        this.setValueFromSharedPreferences(preferences, aSwitch, etPrice);
        this.onChangePrice(etPrice);
    }

    private void setValueFromSharedPreferences(SharedPreferences preferences, Switch aSwitch, EditText etPrice) {

        if (preferences.contains(SORT)) {
            aSwitch.setChecked(preferences.getBoolean(SORT, false));
        }

        if (preferences.contains(PRICE)) {
            etPrice.setText(preferences.getString(PRICE, ""));
        }
    }

    public void onToggleSorting(View view) {
        Switch aSwitch = this.findViewById(R.id.conf_switch);
        boolean value = aSwitch.isChecked();

        this.editor.putBoolean(SORT, value);
        this.editor.apply();
    }

    public void onChangePrice(EditText etPrice) {
        etPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editor.putString(PRICE, s.toString());
                editor.apply();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}
