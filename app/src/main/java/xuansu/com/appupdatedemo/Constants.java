package xuansu.com.appupdatedemo;

import android.os.Environment;

import java.io.File;

/**
 * Created by tim on 2017/5/9.
 */

public class Constants {

    public static final String PATCH_FILE = "apk.patch";
    //url

    public static final String SD_CARD = Environment.getExternalStorageDirectory() + File.separator;


    public static final String NEW_APK_PATH = SD_CARD + "xuansu_apk_new.apk";
    public static final String PATCH = SD_CARD + "a.patch";

}
