import java.io.File;
import java.util.List;

public class Util {
    /**
     * Deletes a directory and all its contents regardless of whether it's empty or not.
     *
     * @param dir The directory to delete
     */
    public static void deleteDirectory(File dir) {
        for (File file: dir.listFiles()) {
            if (file.isDirectory())
                deleteDirectory(file);
            file.delete();
        }
        dir.delete();
    }

    public static String[] toStrArray(List<String> lst) {
        return lst.toArray(new String[lst.size()]);
    }
}
