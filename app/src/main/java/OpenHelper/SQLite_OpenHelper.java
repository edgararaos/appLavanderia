package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by araos on 08/11/2017.
 */

public class SQLite_OpenHelper extends SQLiteOpenHelper {


    public SQLite_OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version); //poner el nombre de la base de datos
    }

    @Override //estructura que tendra las tablas
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "create table usuarios (_ID integer primary key autoincrement, " +
                "Nombre text, Direccion text, Correo text, Password text);";
        sqLiteDatabase.execSQL(query);
    }

    @Override //actualizacion de ellas
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //Metodo para abrir la base de datos
    public void abrir(){
        this.getWritableDatabase();
    }
    //Cerrar la base de datos
    public void cerrar(){
        this.close();
    }

    //Metodo para insertar registros
    public void insertarReg (String nom, String dir, String cor, String pas){
        ContentValues valores = new ContentValues();
        valores.put("Nombre",nom);
        valores.put("Direccion",dir);
        valores.put("Correo",cor);
        valores.put("Password",pas);
        this.getWritableDatabase().insert("usuarios",null,valores);
    }

    //Validar Si el usuario Existe
    public Cursor ConsultarUsuPas (String usu, String pas) throws SQLException{
        Cursor mcursor=null;
        mcursor = this.getReadableDatabase().query("usuarios",new String []{"_ID",
                "Nombre","Direccion","Correo","Password"},"Correo like '"+usu+"' " +
                "and Password like '"+pas+"'",null,null,null,null);
        return mcursor;
    }

}
