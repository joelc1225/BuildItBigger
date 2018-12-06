package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

// This is a version of the asyncTask that doesnt overide onPostExecute, so it'll work with
// testing and returns the same String result
@SuppressWarnings("ALL")
public class EndpointsAsyncTaskTESTING extends AsyncTask<Context, Void, String> {
    private MyApi myApiService = null;
// --Commented out by Inspection START (5/24/18, 2:59 PM):
//    @SuppressLint("StaticFieldLeak")
//    private Context context;
// --Commented out by Inspection STOP (5/24/18, 2:59 PM)

    Context context;

    @SafeVarargs
    @Override
    protected final String doInBackground(Context... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setApplicationName("FinalProject")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0];

        try {
            return myApiService.getJokeFromEndpointName().execute().getData();
        } catch (IOException e) {
            return  e.getMessage();
        }
    }
}