package example.test.phong.youtubealikeproject.util;

import android.support.annotation.NonNull;
import android.support.v4.util.LruCache;
import android.util.Log;

import org.schabi.newpipe.extractor.Info;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import example.test.phong.youtubealikeproject.MainActivity;

/**
 * Created by user on 12/30/2017.
 */
@Singleton
public class InfoCache {
    private static final boolean DEBUG = MainActivity.DEBUG;
    private static final String TAG = InfoCache.class.getSimpleName();
    /**
     * Trim the cache to this size
     */
    private static final int TRIM_CACHE_TO = 30;
    private static final int DEFAULT_TIMEOUT_HOURS = 4;
    private static final int MAX_ITEMS_ON_CACHE = 60;

    private static final LruCache<String, CacheData> lruCache = new LruCache<>(MAX_ITEMS_ON_CACHE);

    @Inject
    public InfoCache() {
    }

    public void putInfo(@NonNull Info info) {
        if (DEBUG) Log.d(TAG, "putInfo() called with: info = [" + info + "]");
        synchronized (lruCache) {
            final CacheData data = new CacheData(info, DEFAULT_TIMEOUT_HOURS, TimeUnit.HOURS);
            lruCache.put(keyOf(info), data);
        }
    }

    public void removeInfo(int serviceId, @NonNull String url) {
        if (DEBUG)
            Log.d(TAG, "removeInfo() called with: serviceId = [" + serviceId + "], url = [" + url + "]");
        synchronized (lruCache) {
            lruCache.remove(keyOf(serviceId, url));
        }
    }

    public Info getFromKey(int serviceId, @NonNull String url) {
        if (DEBUG)
            Log.d(TAG, "getFromKey() called with: serviceId = [" + serviceId + "], url = [" + url + "]");
        synchronized (lruCache) {
            return getInfo(lruCache, keyOf(serviceId, url));
        }
    }

    final private static class CacheData {
        final private long expireTimestamp;
        final private Info info;

        private CacheData(@NonNull final Info info,
                          final long timeout,
                          @NonNull final TimeUnit timeUnit) {
            this.expireTimestamp = System.currentTimeMillis() +
                    TimeUnit.MILLISECONDS.convert(timeout, timeUnit);

            this.info = info;
        }

        private boolean isExpired() {
            return System.currentTimeMillis() > expireTimestamp;
        }
    }

    private String keyOf(final int serviceId, @NonNull final String url) {
        return serviceId + url;
    }

    private String keyOf(@NonNull final Info info) {
        return keyOf(info.getServiceId(), info.getUrl());
    }

    private Info getInfo(@NonNull final LruCache<String, CacheData> cache,
                         @NonNull final String key) {
        final CacheData data = cache.get(key);
        if (data == null) return null;

        if (data.isExpired()) {
            cache.remove(key);
            return null;
        }

        return data.info;
    }
}
