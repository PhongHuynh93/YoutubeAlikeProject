package example.test.phong.youtubealikeproject.util;

import java.util.List;

import example.test.phong.youtubealikeproject.model.SubscriptionEntity;
import io.reactivex.Flowable;

/**
 * Created by user on 12/30/2017.
 */

public class SubscriptionService {
    private Flowable<List<SubscriptionEntity>> mSubscription;

    public SubscriptionService() {

    }

    public Flowable<List<SubscriptionEntity>> getSubscription() {
        return mSubscription;
    }
}
