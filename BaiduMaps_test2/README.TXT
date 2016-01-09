I. PROJECT GOALS
----------------

    Display a panel with a Baidu map using the Baidu Maps API. Show 3 points in Shanghai city on
    displayed map. Make so that after the application is started, the map is centered so that the
    new POI are all visible and point A is in the middle. Values can be hardcoded.

II. PROBLEMS ENCOUNTERED
------------------------

    1.
    Problem:
        Application would crash immediately after starting up.
    Analysis and solution:
        Instructions found at "http://developer.baidu.com/map/index.php?title=androidsdk/guide/buildproject"
        are imprecise and don't mention that the *.so library files need to be placed under
        "..\app\src\main\jniLibs\armeabi\". The manual doesn't mention the "armeabi" subdirectory.

    2.
    Problem:
        Application would start but would not display the map and log an authorization error.
        "Authentication Error errorcode: 230 uid: -1 appid -1 msg: APP Scode码校验失败"
    Analysis and solution:
        Before the map API can be used in an application, an API key must be entered in
        "AndroidManifest.xml". The key can be generated at "http://lbsyun.baidu.com/apiconsole/key".
        Input data for the key generator is the SHA1 fingerprint of the application's certificate
        and the package's full name like "com.example.w.baidumaps_test2". Every Android application
        uses a certificate, the default one is the debug certificate found in
        ~/.android/debug.keystore, they keystore and key password is "android". The "keytool" can
        be used to retrieve a key's fingerprints like this: keytool -list -v -keystore debug.keystore

    3.
    Problem:
        A bitmap for POI couldn't be located during compilation (linking) when used according
        to instructions from the online manual.

    Analysis and solution:
        Baidu Maps API doesn't contain any icons to be used as markers so they have to be copied
        manually to "..\app\src\main\res\drawable\icon.png" and used by referring to
        R.drawable.icon.png from the Java code. Other formats may be supported but PNG surely works.