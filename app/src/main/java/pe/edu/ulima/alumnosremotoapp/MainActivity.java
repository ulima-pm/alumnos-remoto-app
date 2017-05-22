package pe.edu.ulima.alumnosremotoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import pe.edu.ulima.alumnosremotoapp.model.Alumno;
import pe.edu.ulima.alumnosremotoapp.model.AlumnosService;
import pe.edu.ulima.alumnosremotoapp.model.GuardarAlumnoTask;
import pe.edu.ulima.alumnosremotoapp.model.ServicioRESTTask;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener{
    private EditText eteNombre, eteEdad;
    private Button butGuardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eteNombre = (EditText) findViewById(R.id.eteNombre);
        eteEdad = (EditText) findViewById(R.id.eteEdad);
        butGuardar = (Button) findViewById(R.id.butGuardar);

        butGuardar.setOnClickListener(this);

        //ServicioRESTTask task = new ServicioRESTTask();
        //task.execute("http://demo3346217.mockable.io/alumnos");

    }

    @Override
    public void onClick(View view) {
        /*Alumno alumno = new Alumno(
                eteNombre.getText().toString(),
                Integer.parseInt(eteEdad.getText().toString())
        );
        GuardarAlumnoTask task = new GuardarAlumnoTask(alumno);
        task.execute("http://demo3346217.mockable.io/alumnos");*/

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://demo3346217.mockable.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AlumnosService alumnosService =
                retrofit.create(AlumnosService.class);
        /*alumnosService.obtenerAlumnos().enqueue(new Callback<List<Alumno>>() {
            @Override
            public void onResponse(Call<List<Alumno>> call, Response<List<Alumno>> response) {
                List<Alumno> alumnos = response.body();
                for (Alumno a : alumnos) {
                    Log.i("TAG", "Nombre:" + a.getNombre());
                }
            }

            @Override
            public void onFailure(Call<List<Alumno>> call, Throwable t) {

            }
        });*/
        /*alumnosService.obtenerAlumno("pepito").enqueue(new Callback<Alumno>() {
            @Override
            public void onResponse(Call<Alumno> call, Response<Alumno> response) {
                Alumno alumno = response.body();
                Log.i("NOMBRE" , alumno.getNombre());
                Log.i("EDAD" , alumno.getEdad() + "");
            }

            @Override
            public void onFailure(Call<Alumno> call, Throwable t) {

            }
        });*/
        Alumno alumno = new Alumno(
                eteNombre.getText().toString(),
                Integer.parseInt(eteEdad.getText().toString())
        );
        alumnosService.guardarAlumno(alumno).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("TAG", "Guardado correcto");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }


}
