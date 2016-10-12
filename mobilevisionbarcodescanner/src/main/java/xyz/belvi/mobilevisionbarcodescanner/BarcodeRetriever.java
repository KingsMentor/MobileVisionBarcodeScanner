package xyz.belvi.mobilevisionbarcodescanner;

import android.util.SparseArray;

import com.google.android.gms.samples.vision.barcodereader.BarcodeGraphic;
import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

/**
 * Created by zone2 on 10/11/16.
 */

public interface BarcodeRetriever {
    void onRetrieved(Barcode barcode);

    void onRetrievedMultiple(Barcode closetToClick, List<BarcodeGraphic> barcode);

    void onBitmapScanned(SparseArray<Barcode> sparseArray);

    void onRetrievedFailed(String reason);
}
