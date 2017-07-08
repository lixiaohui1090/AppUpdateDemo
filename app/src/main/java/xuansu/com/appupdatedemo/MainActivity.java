package xuansu.com.appupdatedemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Utils.getVersionCode(this) < 2) {
            Log.i("xuansu"," 开始任务 ");
            File patchFile = new File(Constants.PATCH);
            if(patchFile.exists()){
                new ApkUpdateTask().execute();
            }

        }

    }

    class ApkUpdateTask extends AsyncTask<Void,Void,Boolean> {


        @Override
        protected Boolean doInBackground(Void... params) {

//            File patchFile = Utils.downLoad(Constants.URL_PATCH_DOWLOAD);
            File patchFile = new File(Constants.PATCH);
            if(!patchFile.exists()){
                return  false;
            }
            Log.i("xuansu","开始下载");
            String oldPath = Utils.getSourceApkPath(MainActivity.this, getPackageName());
            String newPath = Constants.NEW_APK_PATH;
            String patchPath = patchFile.getAbsolutePath();
            Log.i("xuansu","===oldpath==="+oldPath);
            Log.i("xuansu","===newPath==="+newPath);
            Log.i("xuansu","===patchPath==="+patchPath);
            int ret = Patch.bsPatch(oldPath,newPath,patchPath);
            if (ret < 0) {
                return false;
            } else {
                return true;
            }

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                Utils.installApk(MainActivity.this, Constants.NEW_APK_PATH);
            }
        }
    }

}
