package example.test.phong.youtubealikeproject.ui.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import example.test.phong.youtubealikeproject.model.VideoModel;

/**
 * Created by user on 1/7/2018.
 */

public class VideoViewModel extends ViewModel {
    private final MutableLiveData<VideoModel> mVideoModelMutableLiveData = new MutableLiveData<>();

    public void setVideoModel(VideoModel item) {
        mVideoModelMutableLiveData.setValue(item);
    }

    public LiveData<VideoModel> getViewModel() {
        return mVideoModelMutableLiveData;
    }
}
