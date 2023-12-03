package com.example.kosmea_nextlvl;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.kosmea_nextlvl.Retrofit.RetrofitBuilder;
import com.example.kosmea_nextlvl.Retrofit.RetrofitInterface;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {
    Button button;
    EditText currencyToBeConverted;
    TextView tvResult1;
    TextView tvResult2;
    TextView tvResult3;
    Spinner convertToDropdown1;
    Spinner convertToDropdown2;
    Spinner convertToDropdown3;
    Spinner convertFromDropdown1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        currencyToBeConverted = findViewById(R.id.etFrom1);
        tvResult1 = findViewById(R.id.tvResult1);
        tvResult2 = findViewById(R.id.tvResult2);
        tvResult3 = findViewById(R.id.tvResult3);
        convertFromDropdown1 = findViewById(R.id.spinner1);
        convertToDropdown1 = findViewById(R.id.spinner2);
        convertToDropdown2 = findViewById(R.id.spinner3);
        convertToDropdown3 = findViewById(R.id.spinner4);
        button = findViewById(R.id.btnConvert);

        String[] dropDownList = {"IDR", "USD","INR","EUR","NGN","NZD","GBP","JPY","CAD","AUD","CHF","CNY","HKD","KRW"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, dropDownList);
        convertFromDropdown1.setAdapter(adapter);
        convertToDropdown1.setAdapter(adapter);
        convertToDropdown2.setAdapter(adapter);
        convertToDropdown3.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                RetrofitInterface retrofitInterface = RetrofitBuilder.getRetrofitInstance().create(RetrofitInterface.class);
                Call<JsonObject> call1 = retrofitInterface.getExchangeCurrency(convertFromDropdown1.getSelectedItem().toString());
                call1.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        JsonObject res = response.body();
                        JsonObject rates = res.getAsJsonObject("rates");
                        double currency = Double.parseDouble(currencyToBeConverted.getText().toString());
                        double multiplier = Double.parseDouble(rates.get(convertToDropdown1.getSelectedItem().toString()).toString());
                        double result = currency * multiplier;
                        tvResult1.setText(String.valueOf(result));
                    }
                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }

                });
                Call<JsonObject> call2 = retrofitInterface.getExchangeCurrency(convertFromDropdown1.getSelectedItem().toString());
                call2.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        JsonObject res = response.body();
                        JsonObject rates = res.getAsJsonObject("rates");
                        double currency = Double.parseDouble(currencyToBeConverted.getText().toString());
                        double multiplier = Double.parseDouble(rates.get(convertToDropdown2.getSelectedItem().toString()).toString());
                        double result = currency * multiplier;
                        tvResult2.setText(String.valueOf(result));
                    }
                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        // Handle failure
                    }
                });
                Call<JsonObject> call3 = retrofitInterface.getExchangeCurrency(convertFromDropdown1.getSelectedItem().toString());
                call3.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        JsonObject res = response.body();
                        JsonObject rates = res.getAsJsonObject("rates");
                        double currency = Double.parseDouble(currencyToBeConverted.getText().toString());
                        double multiplier = Double.parseDouble(rates.get(convertToDropdown3.getSelectedItem().toString()).toString());
                        double result = currency * multiplier;
                        tvResult3.setText(String.valueOf(result));
                    }
                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        // Handle failure
                    }
                });
            }
        });
    }
}