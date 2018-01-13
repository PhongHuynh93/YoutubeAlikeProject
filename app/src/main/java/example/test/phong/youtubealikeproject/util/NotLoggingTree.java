package example.test.phong.youtubealikeproject.util;

import timber.log.Timber;

import static android.util.Log.ERROR;
import static android.util.Log.WARN;

/**
 * Created by user on 1/13/2018.
 * <a href="https://medium.com/@caueferreira/timber-enhancing-your-logging-experience-330e8af97341"></a>
 */
public class NotLoggingTree extends Timber.Tree {
    // log nothing
    @Override
    protected void log(final int priority, final String tag, final String message, final Throwable throwable) {
        // in release, send the error to crashlytics
        if (priority == ERROR || priority == WARN) {
//            YourCrashLibrary.log(priority, tag, message);
        }
    }
}
