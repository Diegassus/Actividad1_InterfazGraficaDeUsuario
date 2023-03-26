package com.example.actividad1_interfazgraficadeusuario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;

import com.example.actividad1_interfazgraficadeusuario.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding ;
    private MainActivityViewController mv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate((getLayoutInflater()));
        setContentView(binding.getRoot());
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create((MainActivityViewController.class));

        mv.getMoneda().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                binding.tvResultado.setText(aDouble.toString());
            }
        });

        binding.etEuros.setClickable(false);
        binding.etEuros.setFocusable(false);
        //binding.etEuros.setInputType(0);
        binding.etEuros.setTextIsSelectable(false);
        binding.rbDolarEuro.setChecked(true);

        binding.rbEuroDolar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                binding.etEuros.setClickable(true);
                binding.etEuros.setFocusable(true);
                //binding.etEuros.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                binding.etEuros.setTextIsSelectable(true);

                binding.etDolares.setClickable(false);
                binding.etDolares.setFocusable(false);
                //binding.etDolares.setInputType(0);
                binding.etDolares.setTextIsSelectable(false);
                binding.etDolares.setText("");
            }
        });

        binding.rbDolarEuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.etDolares.setClickable(true);
                binding.etDolares.setFocusable(true);
                //binding.etDolares.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                binding.etDolares.setTextIsSelectable(true);

                binding.etEuros.setClickable(false);
                binding.etEuros.setFocusable(false);
                //binding.etEuros.setInputType(0);
                binding.etEuros.setTextIsSelectable(false);
                binding.etEuros.setText("");
            }
        });

        binding.btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.rbDolarEuro.isChecked()==true){
                    String dolares =binding.etDolares.getText().toString();
                    if(dolares .length()<1){
                        mv.alerta();
                    }else {
                        mv.convertirDolar(Double.parseDouble(dolares));
                    }
                } else {
                    String euros = binding.etEuros.getText().toString();
                    if(euros.length()<1){
                        mv.alerta();
                    }else{
                        mv.convertirEuro(Double.parseDouble(euros));
                    }
                }

            }
        });
    }
}