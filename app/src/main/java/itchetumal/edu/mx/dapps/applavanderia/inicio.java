package itchetumal.edu.mx.dapps.applavanderia;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class inicio extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_inicio,container,false);

        Button btn = (Button) view.findViewById(R.id.btnlavanderia);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment nuevoFragmento = new pedidosBoton(); //Lavanderia
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor, nuevoFragmento);
                transaction.addToBackStack(null);

                //Transferir
                transaction.commit();
            }

        });
        Button btntinto = (Button) view.findViewById(R.id.btntintoreria);
        btntinto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment nuevoFragmento = new tintoreriabtn(); //tintoreria
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor, nuevoFragmento);
                transaction.addToBackStack(null);

                //Transferir
                transaction.commit();
            }

        });
        Button btnprendaespe = (Button) view.findViewById(R.id.btnprendaespecial);
        btnprendaespe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment nuevoFragmento = new prendasespecialesbtn();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor, nuevoFragmento);
                transaction.addToBackStack(null);

                //Transferir
                transaction.commit();
            }

        });
        Button btnedre = (Button) view.findViewById(R.id.btnedredon);
        btnedre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment nuevoFragmento = new edredonbtn();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor, nuevoFragmento);
                transaction.addToBackStack(null);

                //Transferir
                transaction.commit();
            }
        });
        Button btnplan = (Button) view.findViewById(R.id.btnplanchar);
        btnplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment nuevoFragmento = new plancharbtn();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor, nuevoFragmento);
                transaction.addToBackStack(null);

                //Transferir
                transaction.commit();
            }

        });
        Button btnalfomb = (Button) view.findViewById(R.id.btnalfombras);
        btnalfomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment nuevoFragmento = new alfombrabtn();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor, nuevoFragmento);
                transaction.addToBackStack(null);

                //Transferir
                transaction.commit();
            }

        });
        Button btnpiele = (Button) view.findViewById(R.id.btnpieles);
        btnpiele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment nuevoFragmento = new pielesbtn();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor, nuevoFragmento);
                transaction.addToBackStack(null);

                //Transferir
                transaction.commit();
            }

        });
        Button btnbono = (Button) view.findViewById(R.id.btnbonos);
        btnbono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment nuevoFragmento = new bonosbtn();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor, nuevoFragmento);
                transaction.addToBackStack(null);

                //Transferir
                transaction.commit();
            }

        });
        return view;


    }
}
