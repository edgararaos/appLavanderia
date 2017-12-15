package itchetumal.edu.mx.dapps.applavanderia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class admin extends Fragment {

    EditText nomb;
    EditText contra;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin, container, false);
        nomb = (EditText)view.findViewById(R.id.txtUsuario);
        contra = (EditText)view.findViewById(R.id.txtPassword);

        Button acceder = (Button) view.findViewById(R.id.btnIngresar);
        acceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = nomb.getText().toString();
                String pas = contra.getText().toString();

                if(usuario.equalsIgnoreCase("admin") && pas.equalsIgnoreCase("admin")){

                    // Transferir de un fragment a otro
                    Fragment nuevoFragmento = new adminControl();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.contenedor, nuevoFragmento);
                    transaction.addToBackStack(null);

                    //Transferir
                    transaction.commit();
                }else{
                    Toast.makeText(getActivity(),"Usuario Invalido", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }


}
