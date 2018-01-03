package example.test.phong.youtubealikeproject.util;

import android.util.Log;

import org.schabi.newpipe.extractor.Info;
import org.schabi.newpipe.extractor.NewPipe;
import org.schabi.newpipe.extractor.kiosk.KioskInfo;

import java.util.concurrent.Callable;

import example.test.phong.youtubealikeproject.MainActivity;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by user on 12/30/2017.
 */

public class CustomExtractorHelper {
    private static final String TAG = CustomExtractorHelper.class.getSimpleName();
    private final InfoCache cache;

    public CustomExtractorHelper(InfoCache cache) {
        this.cache = cache;
    }

    public Single<KioskInfo> getKioskInfo(final int serviceId, final String url, final String contentCountry, boolean forceLoad) {
        return checkCache(forceLoad, serviceId, url, Single.fromCallable(new Callable<KioskInfo>() {
            @Override
            public KioskInfo call() throws Exception {
                return KioskInfo.getInfo(NewPipe.getService(serviceId), url, toUpperCase(contentCountry));
            }
        }));
    }

      /*//////////////////////////////////////////////////////////////////////////
    // Utils
    //////////////////////////////////////////////////////////////////////////*/

    /**
     * Check if we can load it from the cache (forceLoad parameter), if we can't, load from the network (Single loadFromNetwork)
     * and put the results in the cache.
     */
    private <I extends Info> Single<I> checkCache(boolean forceLoad, int serviceId, String url, Single<I> loadFromNetwork) {
        checkServiceId(serviceId);
        loadFromNetwork = loadFromNetwork.doOnSuccess(new Consumer<I>() {
            @Override
            public void accept(@NonNull I i) throws Exception {
                cache.putInfo(i);
            }
        });

        Single<I> load;
        if (forceLoad) {
            cache.removeInfo(serviceId, url);
            load = loadFromNetwork;
        } else {
            load = Maybe.concat(loadFromCache(serviceId, url), loadFromNetwork.toMaybe())
                    .firstElement() //Take the first valid
                    .toSingle();
        }

        return load;
    }


    private void checkServiceId(int serviceId) {
        if (serviceId == Constants.NO_SERVICE_ID) {
            throw new IllegalArgumentException("serviceId is NO_SERVICE_ID");
        }
    }

    private String toUpperCase(String value) {
        StringBuilder sb = new StringBuilder(value);
        for (int index = 0; index < sb.length(); index++) {
            char c = sb.charAt(index);
            if (Character.isLowerCase(c)) {
                sb.setCharAt(index, Character.toUpperCase(c));
            } else {
                sb.setCharAt(index, Character.toLowerCase(c));
            }
        }
        return sb.toString();
    }


    /**
     * Default implementation uses the {@link InfoCache} to get cached results
     */
    private <I extends Info> Maybe<I> loadFromCache(final int serviceId, final String url) {
        checkServiceId(serviceId);
        return Maybe.defer(new Callable<MaybeSource<? extends I>>() {
            @Override
            public MaybeSource<? extends I> call() throws Exception {
                //noinspection unchecked
                I info = (I) cache.getFromKey(serviceId, url);
                if (MainActivity.DEBUG) Log.d(TAG, "loadFromCache() called, info > " + info);

                // Only return info if it's not null (it is cached)
                if (info != null) {
                    return Maybe.just(info);
                }

                return Maybe.empty();
            }
        });
    }

}
