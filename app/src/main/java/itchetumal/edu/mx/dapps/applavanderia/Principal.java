package itchetumal.edu.mx.dapps.applavanderia;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class Principal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setItemIconTintList(null);

        //Se crean los fragmentos
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.contenedor, new inicio()).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //Accion de los 3 puntitos
        if (id == R.id.action_settings) {
            Intent lavanderia = new Intent(Principal.this,preferencias.class);
            startActivity(lavanderia);
            Log.i("NavigationDrawer","Entro en opcion de preferencias");


            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void cerrar(){
        auth.signOut();
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.nav_Inicio) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new inicio()).commit();


        } else if (id == R.id.nav_admin) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new admin()).commit();
            //Intent lavanderia = new Intent(Principal.this,loginadmin.class);
            //startActivity(lavanderia);

        } else if (id == R.id.nav_perfil) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new perfil()).commit();

        } else if (id == R.id.nav_pedidos) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new pedidos()).commit();

        } else if (id == R.id.nav_comofunciona) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new comofunciona()).commit();

        } else if (id == R.id.nav_precios) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new precios()).commit();

        } else if (id == R.id.nav_promociones) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new promociones()).commit();

        } else if (id == R.id.nav_cerrarsesion) {
            cerrar();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);


        return true;
    }
}
