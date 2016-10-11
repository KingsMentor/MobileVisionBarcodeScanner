package xyz.belvi.mobilevisionbarcodescanner;

import android.support.v4.app.Fragment;

/**
 * Created by zone2 on 10/11/16.
 */

abstract public class BarcodeFragment extends Fragment {

    protected boolean shouldShowText, multipleScan, showDrawRect, touchAsCallback, shouldFocus, showFlash;

    protected Integer[] rectColors;


    public boolean isShouldShowText() {
        return this.shouldShowText;
    }

    public void setShouldShowText(boolean shouldShowText) {
        this.shouldShowText = shouldShowText;
    }

    public boolean isShowDrawRect() {
        return this.showDrawRect;
    }

    public void setShowDrawRect(boolean showDrawRect) {
        this.showDrawRect = showDrawRect;
    }

    public boolean isTouchAsCallback() {
        return this.touchAsCallback;
    }

    public void setTouchAsCallback(boolean touchAsCallback) {
        this.touchAsCallback = touchAsCallback;
    }

    public boolean isShouldFocus() {
        return this.shouldFocus;
    }

    public void setShouldFocus(boolean shouldFocus) {
        this.shouldFocus = shouldFocus;
    }

    public boolean isShowFlash() {
        return this.showFlash;
    }

    public void setShowFlash(boolean showFlash) {
        this.showFlash = showFlash;
    }

    public boolean isMultipleScan() {
        return this.multipleScan;
    }

    public void setMultipleScan(boolean multipleScan) {
        this.multipleScan = multipleScan;
    }

    public Integer[] getRectColors() {
        return this.rectColors;
    }

    public void setRectColors(Integer[] rectColors) {
        this.rectColors = rectColors;
    }

    protected BarcodeRetriever barcodeRetriever;

    public void setRetrieval(BarcodeRetriever retrieval) {
        barcodeRetriever = retrieval;
    }
}
