package itchetumal.edu.mx.dapps.applavanderia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class pedidos extends Fragment {

    EditText edtprecio;
    EditText edtnombre;
    EditText estado1;

    ListView lista_datos;
    FirebaseAuth firebaseAuth;
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

        firebaseAuth = firebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {


        }
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pedidos, container, false);

        //Cuadro con opciones
        edtnombre = (EditText) view.findViewById(R.id.nombre);
        edtprecio = (EditText) view.findViewById(R.id.precio);
        //Del estado
        estado1 = (EditText) view.findViewById(R.id.estadode);

        lista_datos = (ListView) view.findViewById(R.id.listadatos);

        inicialiarfirebase();
        //Mostrar lista
        eventoDatabase();

        //Actualizar y eliminar
        lista_datos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                datosselecionados = (datos)adapterView.getItemAtPosition(i);
                edtnombre.setText(datosselecionados.getNombrepedido());
                edtprecio.setText(datosselecionados.getCosto());
                estado1.setText(datosselecionados.getEstado());

            }
        });



        return view;
    }



    private void inicialiarfirebase() {
        FirebaseApp.initializeApp(getActivity());
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();

    }



    private void eventoDatabase() {
        databaseReference.child("datos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //FirebaseUser userr =firebaseAuth.getCurrentUser();
                listadatos.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    datos p = objSnapshot.getValue(datos.class);
                    listadatos.add(p);
                    //FirebaseUser user =firebaseAuth.getCurrentUser();
                }
                arrayAdapterdatos = new ArrayAdapter<datos>(getActivity(), android.R.layout.simple_list_item_1, listadatos);
                lista_datos.setAdapter(arrayAdapterdatos);
                //FirebaseUser user =firebaseAuth.getCurrentUser();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
            //FirebaseUser user =firebaseAuth.getCurrentUser();
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_nuevo) {
            datos p = new datos();
            p.setId(UUID.randomUUID().toString());
            p.setNombrepedido(edtnombre.getText().toString());
            p.setCosto(edtprecio.getText().toString());
            p.setEstado(estado1.getText().toString());
            databaseReference.child("datos").child(p.getId()).setValue(p);
            limpiarcampos();

        }
        return true;
    }
    private void limpiarcampos() {
        edtnombre.setText("");
        edtprecio.setText("");
        estado1.setText("");
    }
}