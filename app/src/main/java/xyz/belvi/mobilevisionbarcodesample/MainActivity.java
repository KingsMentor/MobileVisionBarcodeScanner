/*
 * Copyright (C) The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package xyz.belvi.mobilevisionbarcodesample;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.CheckBox;

import com.google.android.gms.samples.vision.barcodereader.BarcodeCapture;
import com.google.android.gms.samples.vision.barcodereader.BarcodeGraphic;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import xyz.belvi.mobilevisionbarcodescanner.BarcodeRetriever;

import static xyz.belvi.mobilevisionbarcodesample.R.id.barcode;

/**
 * Main activity demonstrating how to pass extra parameters to an activity that
 * reads barcodes.
 */
public class MainActivity extends AppCompatActivity implements BarcodeRetriever {

    // use a compound button so either checkbox or switch widgets work.


    private static final String TAG = "BarcodeMain";

    CheckBox fromXMl, pause;
    SwitchCompat drawRect, autoFocus, supportMultiple, touchBack, drawText, flash, frontCam;

    BarcodeCapture barcodeCapture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        barcodeCapture = (BarcodeCapture) getSupportFragmentManager().findFragmentById(barcode);
        barcodeCapture.setRetrieval(this);

        fromXMl = (CheckBox) findViewById(R.id.from_xml);
        pause = (CheckBox) findViewById(R.id.pause);
        drawRect = (SwitchCompat) findViewById(R.id.draw_rect);
        autoFocus = (SwitchCompat) findViewById(R.id.focus);
        supportMultiple = (SwitchCompat) findViewById(R.id.support_multiple);
        touchBack = (SwitchCompat) findViewById(R.id.touch_callback);
        drawText = (SwitchCompat) findViewById(R.id.draw_text);
        flash = (SwitchCompat) findViewById(R.id.on_flash);
        frontCam = (SwitchCompat) findViewById(R.id.front_cam);

        findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                barcodeCapture.stopScanning();
            }
        });

        findViewById(R.id.refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fromXMl.isChecked()) {

                } else {
                    barcodeCapture.setShowDrawRect(drawRect.isChecked())
                            .setSupportMultipleScan(supportMultiple.isChecked())
                            .setTouchAsCallback(touchBack.isChecked())
                            .shouldAutoFocus(autoFocus.isChecked())
                            .setShowFlash(flash.isChecked())
                            .setShowFlash(flash.isChecked())
                            .setBarcodeFormat(Barcode.ALL_FORMATS)
                            .setCameraFacing(frontCam.isChecked() ? CameraSource.CAMERA_FACING_FRONT : CameraSource.CAMERA_FACING_BACK)
                            .setShouldShowText(drawText.isChecked());
                    if (pause.isChecked())
                        barcodeCapture.pause();
                    else
                        barcodeCapture.resume();
                    barcodeCapture.refresh(true);
                }
            }
        });

    }


    @Override
    public void onRetrieved(final Barcode barcode) {
        Log.d(TAG, "Barcode read: " + barcode.displayValue);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("code retrieved")
                        .setMessage(barcode.displayValue);
                builder.show();
            }
        });
        barcodeCapture.stopScanning();


    }

    @Override
    public void onRetrievedMultiple(final Barcode closetToClick, final List<BarcodeGraphic> barcodeGraphics) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String message = "Code selected : " + closetToClick.displayValue + "\n\nother " +
                        "codes in frame include : \n";
                for (int index = 0; index < barcodeGraphics.size(); index++) {
                    Barcode barcode = barcodeGraphics.get(index).getBarcode();
                    message += (index + 1) + ". " + barcode.displayValue + "\n";
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("code retrieved")
                        .setMessage(message);
                builder.show();
            }
        });

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {
        for (int i = 0; i < sparseArray.size(); i++) {
            Barcode barcode = sparseArray.valueAt(i);
            Log.e("value", barcode.displayValue);
        }

    }

    @Override
    public void onRetrievedFailed(String reason) {

    }

    @Override
    public void onPermissionRequestDenied() {

    }


}
