package pe.edu.ulima.alumnosremotoapp.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AlumnosService {
    @GET("/alumnos")
    Call<List<Alumno>> obtenerAlumnos();

    @GET("/alumnos/{name}")
    Call<Alumno> obtenerAlumno(
            @Path("name")String nombre
    );

    @POST("/alumnos")
    Call<Void> guardarAlumno(@Body Alumno alumno);
}
