package example.test.phong.youtubealikeproject.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 1/7/2018.
 */

public class VideoModel implements Parcelable {
    String imageUrl;
    int id;
    String url;
    String name;

    public VideoModel(int id, String url, String imageUrl, String name) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.imageUrl);
        dest.writeInt(this.id);
        dest.writeString(this.url);
        dest.writeString(this.name);
    }

    protected VideoModel(Parcel in) {
        this.imageUrl = in.readString();
        this.id = in.readInt();
        this.url = in.readString();
        this.name = in.readString();
    }

    public static final Creator<VideoModel> CREATOR = new Creator<VideoModel>() {
        @Override
        public VideoModel createFromParcel(Parcel source) {
            return new VideoModel(source);
        }

        @Override
        public VideoModel[] newArray(int size) {
            return new VideoModel[size];
        }
    };
}
