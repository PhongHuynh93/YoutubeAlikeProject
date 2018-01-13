package example.test.phong.youtubealikeproject.ui;

import org.schabi.newpipe.extractor.stream.StreamInfo;

/**
 * Created by user on 1/12/2018.
 */

public interface DetailContract {
    // FIXME: 1/12/2018 make this class has T to have general showData() method
    interface View {
        void showData(StreamInfo result);

        void showError(Throwable throwable);
    }

    interface Presenter {
        void getData(int serviceId, String url, boolean forceLoad);
    }
}
