package com.example.actividad1_interfazgraficadeusuario;

import android.app.Application;
import android.content.Context;
import android.util.MutableDouble;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainActivityViewController extends AndroidViewModel {
    private MutableLiveData<Double> moneda;
    private Context context;

    public MainActivityViewController(@NonNull Application application){
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Double> getMoneda(){
        if(moneda == null){
            this.moneda = new MutableLiveData<>();
        }
        return moneda ;
    }

    public void convertirDolar(double monto){
        try{
            moneda.setValue(monto * 0.93);
        }catch(Exception e){
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void convertirEuro(double monto){
        try{
            moneda.setValue(monto * 1.08);
        }catch(Exception e){
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void alerta(){
        Toast.makeText(context, "Debe cargar un valor antes de convertirlo", Toast.LENGTH_LONG).show();
    }
}
