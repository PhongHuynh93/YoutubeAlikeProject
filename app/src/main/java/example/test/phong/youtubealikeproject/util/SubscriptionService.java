package example.test.phong.youtubealikeproject.util;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import example.test.phong.youtubealikeproject.model.SubscriptionEntity;
import io.reactivex.Flowable;

/**
 * Created by user on 12/30/2017.
 */
@Singleton
public class SubscriptionService {
    private Flowable<List<SubscriptionEntity>> mSubscription;

    @Inject
    public SubscriptionService() {

    }

    public Flowable<List<SubscriptionEntity>> getSubscription() {
        return mSubscription;
    }
}
