package example.test.phong.youtubealikeproject.ui;

/**
 * Created by user on 1/12/2018.
 */

public interface DetailContract {
    interface View {

    }

    interface Presenter {
        void getData(int serviceId, String url, boolean forceLoad);
    }
}
