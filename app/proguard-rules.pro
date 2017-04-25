# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\angitha.das\.AndroidStudio2.2/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}




-dontwarn retrofit.**
-dontwarn retrofit2.Platform$Java8
-keep class retrofit.** { *; }


-dontwarn com.squareup.okhttp3.**
-dontwarn okhttp3.**

-dontwarn com.squareup.picasso.**
-dontwarn android.net.http.AndroidHttpClient
-dontwarn sun.misc.Unsafe
-dontwarn okio.**

-dontwarn com.squareup.okhttp.**
-keep class com.squareup.picasso.** { *; }
-dontwarn com.squareup.picasso.**

-keepattributes InnerClasses
-keepclassmembers,allowobfuscation interface * {
    @retrofit.http.** <methods>;
}

