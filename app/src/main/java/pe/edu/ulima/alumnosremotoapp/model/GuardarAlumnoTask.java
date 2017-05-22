package pe.edu.ulima.alumnosremotoapp.model;


import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import pe.edu.ulima.alumnosremotoapp.model.Alumno;

public class GuardarAlumnoTask
        extends AsyncTask<String, Void, Void>{
    private Alumno alumno;

    public GuardarAlumnoTask(Alumno alumno) {
        this.alumno = alumno;
    }

    @Override
    protected Void doInBackground(String... strings) {
        URL url = null;
        try {
            url = new URL(strings[0]);
            HttpURLConnection conn =
                    (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();

            String jsonAlumno = new Gson().toJson(this.alumno);

            conn.getOutputStream().write(jsonAlumno.getBytes("utf-8"));
            if (conn.getResponseCode() == 200){
                String resp =
                        convertInputStreamToString(
                                conn.getInputStream());
                Log.i("TAG", resp);
            }else{

            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String convertInputStreamToString(InputStream is) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            total.append(line).append('\n');
        }
        return total.toString();
    }
}
