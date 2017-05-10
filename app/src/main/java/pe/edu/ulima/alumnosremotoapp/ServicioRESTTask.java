package pe.edu.ulima.alumnosremotoapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ServicioRESTTask
        extends AsyncTask<String, Void, Boolean>{
    @Override
    protected Boolean doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection conn =
                    (HttpURLConnection)url.openConnection();
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode == 200){
                // Peticion correcta
                InputStream is = conn.getInputStream();
                String resp = convertInputStreamToString(is);
                Log.i("TAG", resp);
            }else{
                // Error en la peticion
                Log.i("ERROR", "Hubo error: " + responseCode);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
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
