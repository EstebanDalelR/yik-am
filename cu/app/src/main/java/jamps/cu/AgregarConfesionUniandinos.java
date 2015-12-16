package jamps.cu;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;

import java.util.ArrayList;

public class AgregarConfesionUniandinos extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_confesion_uniandinos);
        final EditText conf = new EditText(this);
        conf.setTextColor(Color.WHITE);
        Button bot = new Button(this);
        bot.setText("Agregar");
        bot.setBackgroundColor(Color.WHITE);
        final Intent confesiones = new Intent(this, ConfUniandinos.class);
        LinearLayout rta = (LinearLayout) findViewById(R.id.text);
        rta.addView(conf);
        rta.addView(bot);

        bot.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String confesi = conf.getText().toString();
                Confesion c = new Confesion(confesi);
                Intent intent = new Intent(getApplicationContext(), ConfUniandinos.class);
                intent.putExtra("confesion", confesi);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_agregar_confesion_uniandinos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
