package com.example.android.happystory.network;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.ResultReceiver;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadQuoteIntent extends IntentService {
    public static final String EXTRA_RECEIVER = "receiver";
    public static final String ACTION_FETCH_IMG = "fetch_img";

    public static final String EXTRA_URL = "extra_url";
    public static final String EXTRA_OUT_IMG = "img";


    public DownloadQuoteIntent() {
        super("DownloadQuoteIntent");
    }

    @SuppressLint("LogNotTimber")
    @Override
    protected void onHandleIntent(Intent intent) {

        if (intent != null){
            final String action = intent.getAction();
            ResultReceiver resultReceiver = (ResultReceiver) intent.getParcelableExtra(EXTRA_RECEIVER);
            Bundle bundle = new Bundle();

            try {
                URL url = new URL(intent.getStringExtra(EXTRA_URL));
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream inputStream = connection.getInputStream();

                if (ACTION_FETCH_IMG.equals(action)) {
                    Bitmap img = downloadImage(inputStream);
                    bundle.putParcelable(EXTRA_OUT_IMG, img);
                    resultReceiver.send(0, bundle);
                }
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }

    private Bitmap downloadImage(InputStream inputStream) {
        return BitmapFactory.decodeStream(inputStream);
    }
}
