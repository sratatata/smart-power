package net.zarski.myremote;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import net.zarski.myremote.core.SwitchButton;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpExecutor extends AsyncTask<SwitchButton, Integer, String> {

    OkHttpClient client = new OkHttpClient();
    private Context context;

    public HttpExecutor(Context context) {
        this.context = context;
    }

    // Do the long-running work in here
        protected String doInBackground(SwitchButton... buttons) {
            try {
                return send(buttons[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "Error";
        }

        // This is called each time you call publishProgress()
        protected void onProgressUpdate(Integer... progress) {
        }

        // This is called when doInBackground() is finished
        protected void onPostExecute(Long result) {
        }

    private String send(SwitchButton button) throws IOException {
        return run(String.format(context.getString(R.string.url_pattern),
                button.getRcState().getRepresentation(),
                button.getFamily().toString(),
                button.getId().toString()));
    }

    private String run(String url) throws IOException {
        Log.d("MyExecutor", url);
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
