# MobileVisionBarcodeScanner
Barcode Scanner supported by Mobile Vision Api

[![](https://jitpack.io/v/KingsMentor/MobileVisionBarcodeScanner.svg)](https://jitpack.io/#KingsMentor/MobileVisionBarcodeScanner)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-MobileVisionBarcodeScanner-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/4516)


![Lib Sample](https://github.com/KingsMentor/MobileVisionBarcodeScanner/blob/master/lib_example.gif)



#### adding as a dependency

##### Step 1. 
Add the JitPack repository to your build file in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
##### Step 2. 
Add the dependency

	dependencies {
	        compile('com.github.KingsMentor:MobileVisionBarcodeScanner:v1.2') { transitive = true; }
	}

#### Supported Attributes

```xml

<attr name="gvb_show_text" format="boolean" />
        <attr name="gvb_draw" format="boolean" />
        <attr name="gvb_multiple" format="boolean" />
        <attr name="gvb_touch" format="boolean" />
        <attr name="gvb_auto_focus" format="boolean" />
        <attr name="gvb_flash" format="boolean" />
        <attr name="gvb_rect_colors" format="reference" />
        <attr name="gvb_code_format" format="enum">
            <enum name="all_format" value="0"></enum>
            <enum name="code_128" value="1"></enum>
            <enum name="code_39" value="2"></enum>
            <enum name="code_93" value="4"></enum>
            <enum name="code_bar" value="8"></enum>
            <enum name="data_matrix" value="16"></enum>
            <enum name="ean_13" value="32"></enum>
            <enum name="ean_8" value="64"></enum>
            <enum name="itf" value="128"></enum>
            <enum name="qr_code" value="256"></enum>
            <enum name="upc_a" value="512"></enum>
            <enum name="upc_e" value="1024"></enum>
            <enum name="pdf417" value="2028"></enum>
            <enum name="aztec" value="4029"></enum>

 </attr>
 ```
 
 
#### What are the attibutes for :

* `gvb_draw` - enable rect drawn around codes when scanning
* `gvb_multiple` - want the camera to return as many qr codes that was scanned. This works with `gvb_touch` attribute. it only returns result when the screen is clicked or touch
* `gvb_touch` - turn on touch listener for screen
* `gvb_auto_focus` - support auto focus
* `gvb_flash` - turn on flash
* `gvb_rect_colors` - arrays of colors to draw rect 
* `gvb_code_format` - barcode format that should be support . Default is `all_format`


**Note**
these attributes can also be initialised from `java code` . We would look into that later

### Using the Mobile Vision Powered Camera.

##### Step 1 - Add layout in xml:

```xml
<fragment
            android:id="@+id/barcode"
            android:name="com.google.android.gms.samples.vision.barcodereader.BarcodeCapture"
            android:layout_width="fill_parent"
            android:layout_height="fill_parent"
            app:gvb_auto_focus="true"
            app:gvb_code_format="all_format"
            app:gvb_flash="false"
            app:gvb_rect_colors="@array/rect_color" />
```

and this is `rect_color` in `colors.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>

    <color name="color_green">#14808d</color>
    <color name="color_brown">#343838</color>
    <color name="color_orange">#f38a32</color>
    <color name="color_blue">#1479b7</color>
    <color name="divider_grey">#e4e4e5</color>

    <array name="rect_color">
        <item>@color/color_blue</item>
        <item>@color/color_brown</item>
        <item>@color/color_green</item>
        <item>@color/divider_grey</item>
        <item>@color/color_orange</item>
    </array>
</resources>

```

##### Step 2 - Initialise in java

```java
BarcodeCapture barcodeCapture = (BarcodeCapture) getSupportFragmentManager().findFragmentById(barcode);
barcodeCapture.setRetrieval(this);
```

also make sure your java class  implements BarcodeRetriever

```java
public class MainActivity extends AppCompatActivity implements BarcodeRetriever {

...


    // for one time scan
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


    }

    // for multiple callback
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
        // when image is scanned and processed
    }

    @Override
    public void onRetrievedFailed(String reason) {
        // in case of failure
    }
}
```

as you can see, `BarcodeRetriever` interface  handles the callback when a code is scanned successfully based on specified attributes. 

#### Extras 

* To scan a bitmap,

```java
BarcodeBitmapScanner.scanBitmap(this, bitmap, Barcode.ALL_FORMATS, this);
```

* Set attributes from java - Use the `BarcodeCapture` instance to reference  setter methods

```java
barcodeCapture.setShowDrawRect(true);
```


#### Resources and Credits

* <a href="https://codelabs.developers.google.com/codelabs/bar-codes" target="_blank">Barcode codelab</a>
* <a href="https://github.com/googlesamples/android-vision/tree/master/visionSamples/barcode-reader" target="_blank"> google mobile vision barcode sample</a>


#### Contributions 

Contributions are welcome. Generally, contributions are managed by issues and pull requests.

1. Submit an issue describing your proposed fix or feature.
2. If your proposed fix or feature is accepted then, fork, implement your code change.
3. Ensure your code change follows the <a href="https://source.android.com/source/code-style.html" target="_blank">accepted code style and structure.<a>
4. Ensure your code is properly tested.
5. Ensure your commits follow the <a href="https://udacity.github.io/git-styleguide/" target="_blank">accepted commit message style</a>
5. Submit a pull request.

#License
The MIT License (MIT). Please see the [License File](https://github.com/KingsMentor/MobileVisionBarcodeScanner/blob/master/license) for more information.

