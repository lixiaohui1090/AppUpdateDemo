package xuansu.com.appupdatedemo;

/**
 * Created by xuansu on 2017/7/7.
 */

public class Patch {
    public static  native int bsPatch(String oldPath, String newPath, String patchPath);
    static {
        System.loadLibrary("bspatch");
    }
}
