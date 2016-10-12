package xyz.belvi.mobilevisionbarcodescanner;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;

import java.util.ArrayList;

/**
 * Created by zone2 on 10/11/16.
 */

abstract public class BarcodeFragment extends Fragment {

    private boolean shouldShowText, multipleScan, showDrawRect, touchAsCallback, shouldFocus, showFlash;

    private Integer[] rectColors;

    private int barcodeFormat;

    @Override
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(context, attrs, savedInstanceState);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.gvb);
        showFlash = a.getBoolean(R.styleable.gvb_gvb_flash, false);
        showDrawRect = a.getBoolean(R.styleable.gvb_gvb_draw, false);
        shouldShowText = a.getBoolean(R.styleable.gvb_gvb_show_text, false);
        shouldFocus = a.getBoolean(R.styleable.gvb_gvb_auto_focus, false);
        touchAsCallback = a.getBoolean(R.styleable.gvb_gvb_touch, false);
        multipleScan = a.getBoolean(R.styleable.gvb_gvb_multiple, false);
        barcodeFormat = a.getInt(R.styleable.gvb_gvb_code_format, 0);
        int colors = a.getResourceId(R.styleable.gvb_gvb_rect_colors, R.array.rect_color);
        if (colors != 0) {
            TypedArray resourceArray = getResources().obtainTypedArray(colors);
            ArrayList<Integer> rectColorsList = new ArrayList<>();
            for (int i = 0; i < resourceArray.length(); i++) {
                final int resourceId = resourceArray.getResourceId(i, 0);
                rectColorsList.add(resourceId);
                // do stuff with resourceId, such as res.getDrawable(resourceId)

            }
            rectColors = rectColorsList.toArray(new Integer[rectColorsList.size()]);
            resourceArray.recycle();

        }

        a.recycle();

    }

    public int getBarcodeFormat() {
        return this.barcodeFormat;
    }

    public void setBarcodeFormat(int barcodeFormat) {
        this.barcodeFormat = barcodeFormat;
    }

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

    public boolean isAutoFocus() {
        return this.shouldFocus;
    }

    public void shouldAutoFocus(boolean shouldFocus) {
        this.shouldFocus = shouldFocus;
    }

    public boolean isShowFlash() {
        return this.showFlash;
    }

    public void setShowFlash(boolean showFlash) {
        this.showFlash = showFlash;
    }

    public boolean supportMultipleScan() {
        return this.multipleScan;
    }

    public void setSupportMultipleScan(boolean multipleScan) {
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
