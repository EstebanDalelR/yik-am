package jamps.cu;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.security.Policy;
import java.util.ArrayList;
import java.util.Date;

public class ConfUniandinos extends FragmentActivity {
    AgregarConfesionUniandinos a = new AgregarConfesionUniandinos();
    ArrayList<Confesion> conf = new ArrayList<>();
    ArrayList<String> cf ;

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_conf_uniandinos);
                TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);

                tabHost.setup();
        cf= new ArrayList<>();
        if (savedInstanceState != null) {
            System.out.println("1");
            // Restore value of members from saved state
            for (int i = 0; i < cf.size(); i++)
            {

                System.out.println("BBBBBBBBBBBBBB");
                String a = savedInstanceState.getString(i+"key");
                cf.add(a);
            }
        }
        else
        {
            System.out.println("BBBBBBBBBBBBBB");
            savedInstanceState = new Bundle();
            System.out.println("2");
            cf = new ArrayList<>();
        }

                final TabWidget tabWidget = tabHost.getTabWidget();

                final FrameLayout tabContent = tabHost.getTabContentView();

                // Get the original tab textviews and remove them from the viewgroup.
                TextView[] originalTextViews = new TextView[tabWidget.getTabCount()];
                for (int index = 0; index < tabWidget.getTabCount(); index++) {
                        originalTextViews[index] = (TextView) tabWidget.getChildTabViewAt(index);
                }
                tabWidget.removeAllViews();

                // Ensure that all tab content childs are not visible at startup.
                for (int index = 0; index < tabContent.getChildCount(); index++) {
                        tabContent.getChildAt(index).setVisibility(View.GONE);
                }

                // Create the tabspec based on the textview childs in the xml file.
                // Or create simple tabspec instances in any other way...
                for (int index = 0; index < originalTextViews.length; index++) {
                        final TextView tabWidgetTextView = originalTextViews[index];
                        final View tabContentView = tabContent.getChildAt(index);
                        TabHost.TabSpec tabSpec = tabHost.newTabSpec((String) tabWidgetTextView.getTag());
                        tabSpec.setContent(new TabHost.TabContentFactory() {
                                @Override
                                public View createTabContent(String tag) {
                                        return tabContentView;
                                }
                        });
                        if (tabWidgetTextView.getBackground() == null) {
                                tabSpec.setIndicator(tabWidgetTextView.getText());
                        } else {
                                tabSpec.setIndicator(tabWidgetTextView.getText(), tabWidgetTextView.getBackground());
                        }
                        tabHost.addTab(tabSpec);
                }

        setTabColor(tabHost);
        Confesion a = new Confesion(getIntent().getStringExtra("confesion"));
            String cin = getIntent().getStringExtra("confesion");
        System.out.println(cin);

        if (cin != null)
        {
            cf.add(cin);
            conf.add(a);
            System.out.println((cf.size()-1)+"key");
            savedInstanceState.putString((cf.size()-1)+"key", cin);
            super.onSaveInstanceState(savedInstanceState);
        }



           LinearLayout rta = (LinearLayout) findViewById(R.id.text);
            Button agrega = new Button(this);
            agrega.setBackgroundColor(Color.WHITE);
            agrega.setText("Agrega tu confesion");
            rta.addView(agrega);
            final Intent agregar = new Intent(this, AgregarConfesionUniandinos.class);

            agrega.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    startActivity(agregar);
                }
            });
            for (int i=0; i<conf.size(); i++)
            {
                final Confesion c = conf.get(i);
                TableLayout confesiones = new TableLayout(this);



                GradientDrawable gd=new GradientDrawable();
                gd.setStroke(2, Color.WHITE);
                confesiones.setBackground(gd);

                TableRow opciones = new TableRow(this);
                TableRow fech = new TableRow(this);
                Button lik = new Button(this);
                lik.setBackgroundColor(Color.WHITE);
                lik.setText("Like");
                TableRow confesion = new TableRow(this);
                TextView confe = new TextView(this);
                confe.setTextColor(Color.WHITE);
                confe.setText(conf.get(i).darConfesion());
                final TextView liks = new TextView(this);
                liks.setTextColor(Color.WHITE);
                TextView fecha = new TextView(this);
                fecha.setTextColor(Color.WHITE);
                fecha.setText(c.darFecha().toString());
                liks.setText(conf.get(i).darLikes() + " likes");
                confesion.addView(confe);
                opciones.addView(lik);
                opciones.addView(liks);
                fech.addView(fecha);
                confesiones.addView(confesion);
                confesiones.addView(opciones);
                confesiones.addView(fech);
                rta.addView(confesiones);

                lik.setOnClickListener(new View.OnClickListener() {
                    Boolean ya = false;
                    public void onClick(View v)
                    { if (ya == false)
                        c.clklike();
                        liks.setText(c.darLikes() + " likes");
                        ya = true;
                    }
                    });
            }

            final ArrayList<Confesion> confhof  = new ArrayList<>();
            Confesion ahof = new Confesion("conf1hof");
            Confesion bhof = new Confesion("conf2hof");
            confhof.add(ahof);
            confhof.add(bhof);
            TableLayout rtahof = (TableLayout) findViewById(R.id.text2);
            for (int i=0; i<confhof.size(); i++)
            {
                final Confesion chof = confhof.get(i);
                TableLayout confesioneshof = new TableLayout(this);
                TableRow opcioneshof = new TableRow(this);

                TableRow fechhof = new TableRow(this);

                Button likhof = new Button(this);
                GradientDrawable gd=new GradientDrawable();
                gd.setStroke(2, Color.WHITE);
                confesioneshof.setBackground(gd);
                likhof.setText("Like");
                likhof.setBackgroundColor(Color.WHITE);

                TableRow confesionhof = new TableRow(this);
                TextView confehof = new TextView(this);
                confehof.setTextColor(Color.WHITE);
                confehof.setText(confhof.get(i).darConfesion());
                final TextView likshof = new TextView(this);
                TextView fechahof = new TextView(this);
                fechahof.setTextColor(Color.WHITE);
                fechahof.setText(chof.darFecha().toString());
                likshof.setText(confhof.get(i).darLikes() + " likes");
                likshof.setTextColor(Color.WHITE);
                confesionhof.addView(confehof);
                opcioneshof.addView(likhof);
                opcioneshof.addView(likshof);
                fechhof.addView(fechahof);
                confesioneshof.addView(confesionhof);
                confesioneshof.addView(opcioneshof);
                confesioneshof.addView(fechhof);
                rtahof.addView(confesioneshof);

                likhof.setOnClickListener(new View.OnClickListener() {
                    Boolean yahof = false;
                    public void onClick(View v)
                    { if (yahof == false)
                        chof.clklike();
                        likshof.setText(chof.darLikes() + " likes");
                        yahof = true;
                    }
                });
            }
//		tabHost.setCurrentTab(0);
        }
    public static void setTabColor(TabHost tabhost) {
        for(int i=0;i<tabhost.getTabWidget().getChildCount();i++) {
            tabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.WHITE); //unselected
        }
        tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.WHITE); // selected
    }
}

