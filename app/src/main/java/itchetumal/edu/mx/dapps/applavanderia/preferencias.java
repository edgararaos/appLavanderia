package itchetumal.edu.mx.dapps.applavanderia;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class preferencias extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_preferencias);

        addPreferencesFromResource(R.xml.preferencias);
    }
}
