package pe.edu.ulima.alumnosremotoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ServicioRESTTask task = new ServicioRESTTask();
        task.execute("http://demo2000337.mockable.io/alumno");
    }
}
