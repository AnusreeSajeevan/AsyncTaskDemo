package com.example.anu.asynctaskdemo;

import android.app.ProgressDialog;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Design on 01-12-2017.
 */

public class ProgressAsyncTask extends AsyncTask<Void,Integer,String> {

    private ProgressDialog progressDialog;
    private Context context;
    private static final String TAG = ProgressAsyncTask.class.getSimpleName();

    public ProgressAsyncTask(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(context.getResources().getString(R.string.title));
        progressDialog.setMessage(context.getResources().getString(R.string.message));
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setButton(ProgressDialog.BUTTON_NEGATIVE, context.getResources().getString(R.string.cancel),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        progressDialog.dismiss();
                    }
                });
        progressDialog.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            for (int i=10; i<=100; i=i+10){
                    //sleep thread for 3 seconds and then update ui
                    Thread.sleep(3000);
                    publishProgress(i);
            }
            return context.getResources().getString(R.string.success_msg);
        } catch (InterruptedException e) {
            Log.i(TAG, "error : "+e.getMessage());
            return context.getResources().getString(R.string.failure_msg);
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        progressDialog.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        progressDialog.dismiss();
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}
