package jamps.cu;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class PreguntaInicio extends AppCompatActivity {

    Random randomGenerator;
    final Context context = this;
    int sumatoria = 0;
    String[] f;

    @Override

    protected void onCreate(final Bundle savedInstanceState) {


        //Remove title bar
        //set up notitle
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //set up full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta_inicio);
        final ScrollView scrll = (ScrollView)findViewById(R.id.ScrollView01);

        final Button ingComoUniandino = (Button)findViewById(R.id.button);
        final Button ingComoInv = (Button)findViewById(R.id.button2);
        final Button regresar = (Button)findViewById(R.id.button4);

        ImageView iv = (ImageView)findViewById(R.id.imageView);
        final TextView tv1 = (TextView)findViewById(R.id.textView2);
        tv1.setTextColor(Color.WHITE);
        final TextView tv2 = (TextView)findViewById(R.id.textView3);
        final EditText tv3 = (EditText)findViewById(R.id.editText);
        final Intent i = new Intent(this, ConfUniandinos.class);
        final Intent inv = new Intent(this, ConfUniandinos.class);
        final Button ingresar = (Button)findViewById(R.id.button3);
        ingresar.setText("Ingresar");


        iv.setImageResource(R.drawable.goat);

        ingresar.setVisibility(View.GONE);

        regresar.setVisibility(View.GONE);
        tv1.setVisibility(View.GONE);
        tv2.setVisibility(View.GONE);
        tv3.setVisibility(View.GONE);

        ingComoUniandino.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ingresar.setVisibility(View.VISIBLE);
                ingComoUniandino.setVisibility(View.GONE);
                ingComoInv.setVisibility(View.GONE);
                regresar.setVisibility(View.VISIBLE);
                tv1.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.VISIBLE);
                tv3.setVisibility(View.VISIBLE);
            }
        });
        tv3.setOnKeyListener(new View.OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode ==  KeyEvent.KEYCODE_DPAD_CENTER
                        || keyCode ==  KeyEvent.KEYCODE_ENTER) {
                    if (event.getAction() == KeyEvent.ACTION_DOWN) {
                        // do nothing yet
                    } else if (event.getAction() == KeyEvent.ACTION_UP) {
                        ingresar.performClick();
                    }

                            return true;

                    }

                return false;
            }
        });
        regresar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

        f = fijarRta();

        ingresar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String resp = f[1];
                EditText rta = (EditText) findViewById(R.id.editText);
                String a = rta.getText().toString();
                System.out.println(a + resp + a.compareTo(resp));
                if (sumatoria != 2) {
                    if (a.compareTo(resp) == 0) {
                        System.out.println("HOLA");
                        startActivity(i);
                    } else if (a != resp) {
                        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                context);

                        // set title
                        alertDialogBuilder.setTitle("Respuesta Incorrecta!");

                        // set dialog message
                        alertDialogBuilder
                                .setMessage("Por favor ingresa la respuesta correcta, aun tienes " + (2 - sumatoria) + " intento(s)!")
                                .setCancelable(false)
                                .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // if this button is clicked, close
                                        // current activity
                                        f = fijarRta();
                                        sumatoria++;
                                    }
                                });

                        // create alert dialog
                        AlertDialog alertDialog = alertDialogBuilder.create();

                        // show it
                        alertDialog.show();
                    }
                } else {
                    startActivity(inv);
                }

            }

        });
        ingComoInv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(inv);
            }
            });

    }




public String[] fijarRta()
{
    TextView pregunta = (TextView)findViewById(R.id.textView3);
    String[] pregResp = generarPreguntaRandom().split("-");
    String preg = pregResp[0];
    pregunta.setText(preg);
    return pregResp;
}

    public String generarPreguntaRandom()
    {
        ArrayList<String> rand = new ArrayList<>();
        String p1 = "¿Donde se come la arepa con cuchara?-monas";
        String p2 = "¿Como se le dice de cariño a los empleados de la universidad?-monitos";
        String p3 = "¿Que tienda hay en los vagones del tren?-avenacubana";
        String p4 = "¿Cuantas salas de computadores hay en el 5to piso del ML?-2";
        String p5 = "¿Cuantos ascensores hay en el SD?-4";
        String p6 = "¿El puente del ML llega a que piso del W?-5";
        String p7 = "¿Cuantos ascensores tiene el ML?-3";
        String p8 = "¿Cuales son las siglas del edificio mas feo de la universidad?-au";
        rand.add(p1);
        rand.add(p2);
        rand.add(p3);
        rand.add(p4);
        rand.add(p5);
        rand.add(p6);
        rand.add(p7);
        rand.add(p8);
        randomGenerator = new Random();
        int index = randomGenerator.nextInt(rand.size());
        String randomItem = rand.get(index);
        return randomItem;


    }


}
