package itchetumal.edu.mx.dapps.applavanderia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;


public class tintoreriabtn extends Fragment {

    EditText edtprecio;
    Spinner edtnombre;
    EditText estado;
    //ListView lista_datos;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tintoreriabtn, container, false);
        setHasOptionsMenu(true);

        edtnombre = (Spinner) view.findViewById(R.id.nombre);
        String[] datos = new String[] {"","Traje", "Abrigo", "Almohada", "Bata", "Blusa"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, datos);
        edtnombre.setAdapter(adapter);
        edtprecio = (EditText)view.findViewById(R.id.precio);
        //Estado del pedido
        estado = (EditText)view.findViewById(R.id.estadodelpedido);
        //lista_datos = (ListView)view.findViewById(R.id.listadatos);

        inicialiarfirebase();

        return view;
    }

    private void inicialiarfirebase() {
        FirebaseApp.initializeApp(getActivity());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_cliente, menu);
        return;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_nuevo){
            datos p = new datos();
            p.setId(UUID.randomUUID().toString());
            p.setNombrepedido(edtnombre.getSelectedItem().toString());
            p.setCosto(edtprecio.getText().toString());
            //Del estado
            p.setEstado(estado.getText().toString());
            databaseReference.child("datos").child(p.getId()).setValue(p);
        }
        return true;
    }
}
