# MobileVisionBarcodeScanner
Barcode Scanner supported by Mobile Vision Api

[![](https://jitpack.io/v/KingsMentor/MobileVisionBarcodeScanner.svg)](https://jitpack.io/#KingsMentor/MobileVisionBarcodeScanner)

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
	        compile 'com.github.KingsMentor:MobileVisionBarcodeScanner:v1.0'
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
