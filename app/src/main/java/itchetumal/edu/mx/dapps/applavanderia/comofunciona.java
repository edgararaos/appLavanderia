package itchetumal.edu.mx.dapps.applavanderia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class comofunciona extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_comofunciona, container, false);

        Button btn = (Button) view.findViewById(R.id.primerlavado);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lavanderia = new Intent(getActivity(),comoMI.class);
                startActivity(lavanderia);
            }

        });
        Button btn2 = (Button) view.findViewById(R.id.horario);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lavanderia = new Intent(getActivity(),comoHorario.class);
                startActivity(lavanderia);
            }

        });
        Button btn3 = (Button) view.findViewById(R.id.garantia);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lavanderia = new Intent(getActivity(),comoGarantia.class);
                startActivity(lavanderia);
            }

        });
        return view;
    }
}
