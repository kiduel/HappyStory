package com.example.android.happystory.network;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

import com.example.android.happystory.ui.MainActivity;

import static com.example.android.happystory.network.DownloadQuoteIntent.EXTRA_OUT_IMG;

public class SampleResultReceiver extends ResultReceiver {
    private final MainActivity activityIntentService;


    public SampleResultReceiver(MainActivity mainActivity, Handler handler) {
        super(handler);
        this.activityIntentService = mainActivity;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        super.onReceiveResult(resultCode, resultData);
        if ( resultData.containsKey(EXTRA_OUT_IMG) )
            activityIntentService.onImageDownloaded((Bitmap) resultData.getParcelable(EXTRA_OUT_IMG));
    }
}
