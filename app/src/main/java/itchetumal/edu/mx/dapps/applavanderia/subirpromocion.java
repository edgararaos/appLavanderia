package itchetumal.edu.mx.dapps.applavanderia;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class subirpromocion extends AppCompatActivity implements View.OnClickListener {

    EditText nombreimagen;
    EditText descricpion;
    EditText fechaexp;
    ImageView imagensubir;

    //Realizar envio de datos (conexion de datos)
    RequestQueue envio;
    //Almacenar respuesta del servidor
    StringRequest respuesta;
    //Almacenar la imagen
    Bitmap imagenpro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subirpromocion);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

       View seleccionarimagen=findViewById(R.id.seleccionarimagen);
        seleccionarimagen.setOnClickListener(this);
        View seleccionarsubir=findViewById(R.id.enviar);
        seleccionarsubir.setOnClickListener(this);
        nombreimagen = (EditText)findViewById(R.id.nombrePromocion);
        descricpion = (EditText)findViewById(R.id.descripciontxt);
        fechaexp = (EditText)findViewById(R.id.fechaexpir);
        imagensubir = (ImageView) findViewById(R.id.imageView7);
        envio = Volley.newRequestQueue(this);



    }

    private void capturaDatos() {

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.seleccionarimagen){
      cargarimagen();

        }else if(view.getId() == R.id.enviar) {
           cargardatos();

        }
    }

    public void cargarimagen(){
        //aqui carga  para abrir la galeria
        Intent intent= new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent.createChooser(intent,"Seleccione la imagen"),69);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            //es para mostrar directamente en el imge view
            Uri imagen = data.getData();
            imagensubir.setImageURI(imagen);
            try{
                imagenpro=MediaStore.Images.Media.getBitmap(this.getContentResolver(),imagen);
                imagensubir.setImageBitmap(imagenpro);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imagenpro=resolucion(imagenpro,600,800);
        }

    }

    private Bitmap resolucion(Bitmap imagenpro, float ancho, float alto) {
        int Anchobit=imagenpro.getWidth();
        int Altobit=imagenpro.getHeight();

        if(Anchobit>ancho|| Altobit>alto){
            float cambiarAncho=ancho/Anchobit;
            float cambiaralto=alto/Altobit;
        Matrix matrix=new Matrix();
            matrix.postScale(cambiaralto,cambiarAncho);
            return  imagenpro.createBitmap(imagenpro,0,0,Anchobit,Altobit,matrix,false);

        }else{
            return imagenpro;
        }

    }

    private void cargardatos() {

        //Direccion a donde va a subir los datos
        String link = "http://192.168.43.30:8080/appLavanderia/subidadatos.php?";

        //Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();
        respuesta = new StringRequest(Request.Method.POST, link, new Response.Listener<String>() {

            @Override //En caso que si se pudo hacer la conexion
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("registra")){
                    Toast.makeText(getApplicationContext(), "Registro Exitoso", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "errorr", Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_SHORT).show();

            }//En dado caso que no se pueda guardar
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "No "+error.toString(), Toast.LENGTH_SHORT).show();

            }
        }){
            @Override //Contiene la informacion para enviar
            protected Map<String, String> getParams() throws AuthFailureError {

                String nombre = nombreimagen.getText().toString();
                String descripcion = descricpion.getText().toString();
                String fecha = fechaexp.getText().toString();
                String Foto = ConvertImagenTexto(imagenpro);

                //enviar los datos al servidor
                Map<String, String> datosparaenviar = new HashMap<>();
                datosparaenviar.put("Nombre",nombre);
                datosparaenviar.put("DescripcionPromo",descripcion);
                datosparaenviar.put("FechaPromo",fecha);
                datosparaenviar.put("ImagenPromo",Foto);
                return datosparaenviar;
            }
        };
        envio.add(respuesta);
    }

    //Convertir la imagen a String
    private String ConvertImagenTexto(Bitmap imagenpro) {
        ByteArrayOutputStream espacio = new ByteArrayOutputStream();
        imagenpro.compress(Bitmap.CompressFormat.JPEG,100,espacio);
        byte[] imagen = espacio.toByteArray();
        String imagenAstring = Base64.encodeToString(imagen,Base64.DEFAULT);
        return imagenAstring;
    }
}