package xyz.belvi.mobilevisionbarcodescanner;

import android.content.Context;
import android.graphics.Bitmap;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

/**
 * Created by zone2 on 10/11/16.
 */

public class BarcodeBitmapScanner {

    public static void scanBitmap(Context context, Bitmap bitmap, BarcodeRetriever barcodeRetriever) {
        BarcodeDetector detector =
                new BarcodeDetector.Builder(context)
                        .setBarcodeFormats(Barcode.DATA_MATRIX | Barcode.QR_CODE)
                        .build();
        if (!detector.isOperational()) {
            barcodeRetriever.onRetrivedFailed("Could not set up the detector!");
//            txtView.setText("Could not set up the detector!");
            return;
        }
        Frame frame = new Frame.Builder().setBitmap(bitmap).build();
        barcodeRetriever.onBitmapScanned(detector.detect(frame));
    }
}
