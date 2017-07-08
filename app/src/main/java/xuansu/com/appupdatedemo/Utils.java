package xuansu.com.appupdatedemo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by tim on 2017/5/12.
 */

public class Utils {

    /*
    * 下载差分包
    * */

    public static File downLoad (String url) {

        File file = null;
        InputStream is = null;
        FileOutputStream os = null;

        file = new File(Environment.getExternalStorageDirectory(), Constants.PATCH_FILE);

        if (file.exists()) {
            file.delete();
        }

        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setDoInput(true);

            is = conn.getInputStream();
            os = new FileOutputStream(file);

            byte[] buffer = new byte[1024];
            int len = 0;

            while ((len = is.read(buffer)) != -1) {
               Log.i("xuansu"," downLoadByte :"+String.valueOf(len));
                os.write(buffer,0,len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /*
   *获取已安装APK文件的源文件
   * */
    public static String getSourceApkPath(Context context, String packageName) {
        if (TextUtils.isEmpty(packageName)) {
            return null;
        }

        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(packageName, 0);
            return appInfo.sourceDir;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /*
    * 安装APK
    * */
    public static void installApk (Context context, String apkPath) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("file://"+ apkPath), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    public static int getVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(),0);
            Log.d("dn_tim","versionCode = "+packageInfo.versionCode);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
