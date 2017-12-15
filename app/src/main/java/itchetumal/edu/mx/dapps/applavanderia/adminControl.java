package itchetumal.edu.mx.dapps.applavanderia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class adminControl extends Fragment {

    //datos
    EditText edtprecio;
    EditText edtnombre;
    EditText estado;
    ListView lista_datos;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    //tabla de datos
    private List<datos> listadatos = new ArrayList<datos>();
    private ArrayAdapter<datos> arrayAdapterdatos;

    //Actualizar y eliminar
    datos datosselecionados;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_control, container, false);
        setHasOptionsMenu(true);


        Button subirpro = (Button) view.findViewById(R.id.button3);
        subirpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lavanderia = new Intent(getActivity(),subirpromocion.class);
                startActivity(lavanderia);
            }

        });

        //Cuadro con opciones
        edtnombre = (EditText) view.findViewById(R.id.nombre);
        edtprecio = (EditText)view.findViewById(R.id.precio);
        //Del estado
        estado = (EditText)view.findViewById(R.id.estadode);;

        lista_datos = (ListView)view.findViewById(R.id.listadatos);

        inicialiarfirebase();
        //Mostar lista
        eventoDatabase();



        //Actualizar y eliminar
        lista_datos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                datosselecionados = (datos)adapterView.getItemAtPosition(i);
                edtnombre.setText(datosselecionados.getNombrepedido());
                edtprecio.setText(datosselecionados.getCosto());
                estado.setText(datosselecionados.getEstado());

            }
        });



        return view;
    }



    private void eventoDatabase() {
        databaseReference.child("datos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listadatos.clear();
                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    datos p = objSnapshot.getValue(datos.class);
                    listadatos.add(p);
                }
                arrayAdapterdatos = new ArrayAdapter<datos>(getActivity(),android.R.layout.simple_list_item_1,listadatos);
                lista_datos.setAdapter(arrayAdapterdatos);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void inicialiarfirebase() {
        FirebaseApp.initializeApp(getActivity());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_datos, menu);
        return;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_nuevo){
            datos p = new datos();
            p.setId(UUID.randomUUID().toString());
            p.setNombrepedido(edtnombre.getText().toString());
            p.setCosto(edtprecio.getText().toString());
            p.setEstado(estado.getText().toString());
            databaseReference.child("datos").child(p.getId()).setValue(p);
            limpiarcampos();

            //Actualizar
        }else if (id == R.id.menu_actualizar){
            datos p = new datos();
            p.setId(datosselecionados.getId());
            p.setNombrepedido(edtnombre.getText().toString().trim());
            p.setCosto(edtprecio.getText().toString().trim());
            p.setEstado(estado.getText().toString().trim());
            databaseReference.child("datos").child(p.getId()).setValue(p);
            limpiarcampos();

            //Eliminar
        }else if (id == R.id.menu_borrar){
            datos p = new datos();
            p.setId(datosselecionados.getId());
            databaseReference.child("datos").child(p.getId()).removeValue();
            limpiarcampos();

        }


        return true;
    }

    private void limpiarcampos() {
        edtnombre.setText("");
        edtprecio.setText("");
        estado.setText("");
    }


}
