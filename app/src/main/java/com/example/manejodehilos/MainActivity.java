package com.example.manejodehilos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btnContar;
    private TextView txtvContador;
    private int sum;
    private Handler manejador=new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnContar = findViewById(R.id.btnContar);
        txtvContador = findViewById(R.id.txtvContador);
        btnContar.setOnClickListener(onClickbtnContar);
        //manejador= new Handler();
    }

    View.OnClickListener onClickbtnContar= View -> {
      //tareaLarga();
        /*
        lanar múltiples hilos anónimos
        hilo();*/
        sincronizarHilos();
    };

    private void sincronizarHilos() {
        manejador.postDelayed(new Runnable() {
            @Override
            public void run() {
                txtvContador.setText("paso 1 seg");
                tareaLarga();
            }
        },1000);

        manejador.postDelayed(new Runnable() {
            @Override
            public void run() {
                txtvContador.setText("pasan 10 segundos");
                nuevaTareaLarga();
            }
        },10000);
    }

    public void hilo(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //tareaLarga(); utilizado para congelaral hilo principal
                nuevaTareaLarga();
            }
        }).start();
    }

    public void nuevaTareaLarga(){
        for(int i=0;i<10;i++) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sum+=i;
            Log.d("Suma tarea:  "+sum," "+sum+" "+ Thread.currentThread().getName());
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtvContador.setText(String.valueOf(sum));
            }
        });


    }

    public void tareaLarga(){
        int suma=0;
        for(int i=0;i<1_000_000_000;i++)
        {
            suma+=i;

        }
        Log.d("Suma:  "+suma," "+suma);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtvContador.setText(String.valueOf("suma"));
            }
        });


    }
}