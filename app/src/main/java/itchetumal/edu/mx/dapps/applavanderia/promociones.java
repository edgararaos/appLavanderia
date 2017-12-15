package itchetumal.edu.mx.dapps.applavanderia;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class promociones extends Fragment {

    TextView descricpion;
    TextView fechaexp;
    ImageView imagenbajar;

    RequestQueue regreso;
    JsonObjectRequest json;

    String rutaimagen;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_promociones,container,false);
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_promociones, container, false);

        Button btntinto = (Button) view.findViewById(R.id.pedir);
        btntinto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lavanderia = new Intent(getActivity(),Principal.class);
                startActivity(lavanderia);
            }

        });
        //puede que haya errror
        regreso = Volley.newRequestQueue(getActivity());
        imagenbajar = (ImageView)view.findViewById(R.id.recibirimagen);
        descricpion = (TextView)view.findViewById(R.id.recibirdescripcion);
        fechaexp = (TextView)view.findViewById(R.id.recibirfecha);

        cargardatos();
        //cargardatosimagen();

        return view;
    }

    private void cargardatosimagen(String mapaimagen) {
        //Direccion a donde va a subir los datos
        String link = "http://192.168.43.30:8080/appLavanderia/" + mapaimagen;
        link = link.replace(" ","%20");
        ImageRequest imagenrequesta = new ImageRequest(link, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {

                imagenbajar.setImageBitmap(response);

            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "No hay Promociones", Toast.LENGTH_SHORT).show();
            }
        }
        );
        //Permite la comunicacion con el servidor
        regreso.add(imagenrequesta);

    }

    private void cargardatos() {

        //Direccion a donde va a subir los datos
        String link = "http://192.168.43.30:8080/appLavanderia/recibirdatos.php?";
        json = new JsonObjectRequest(Request.Method.GET, link, null, new Response.Listener<JSONObject>() {

            //En caso que funcione
            @Override
            public void onResponse(JSONObject response) {
                JSONArray arrayjson = response.optJSONArray("lavanderiaUI");
                JSONObject objetojson = null;
                try {//Obtener el campo y mostrarlo en lugar
                    objetojson = arrayjson.getJSONObject(0); //En caso que si entre pero no haya datos
                    descricpion.setText(objetojson.optString("Descripcion"));
                    fechaexp.setText(objetojson.optString("Fechaexpiracion"));

                    rutaimagen = objetojson.optString("Imagen");
                    cargardatosimagen(objetojson.optString("Imagen"));


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override//En caso de que haya error
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "No hay Promociones", Toast.LENGTH_SHORT).show();

            }
        }
        );
        //Establecer la comunicacion con los metodos
        regreso.add(json);
    }
}
