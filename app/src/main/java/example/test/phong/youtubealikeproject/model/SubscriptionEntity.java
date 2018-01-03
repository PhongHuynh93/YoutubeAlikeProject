package example.test.phong.youtubealikeproject.model;

import android.arch.persistence.room.Ignore;

import org.schabi.newpipe.extractor.channel.ChannelInfoItem;

import example.test.phong.youtubealikeproject.util.Constants;

/**
 * Created by user on 12/30/2017.
 */
// TODO: 12/30/2017 add database for room here
public class SubscriptionEntity {
    private int serviceId = Constants.NO_SERVICE_ID;
    private String url;
    private String name;
    private String avatarUrl;
    private Long subscriberCount;
    private String description;

    @Ignore
    public ChannelInfoItem toChannelInfoItem() {
        ChannelInfoItem item = new ChannelInfoItem(getServiceId(), getUrl(), getName());
        item.thumbnail_url = getAvatarUrl();
        item.subscriber_count = getSubscriberCount();
        item.description = getDescription();
        return item;
    }

    // FIXME: 12/30/2017 make auto generate getter and setter
    public int getServiceId() {
        return serviceId;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public Long getSubscriberCount() {
        return subscriberCount;
    }

    public String getDescription() {
        return description;
    }
}
