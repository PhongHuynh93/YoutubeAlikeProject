package example.test.phong.youtubealikeproject.ui.detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import example.test.phong.youtubealikeproject.databinding.DetailRelatedVideoLayoutBinding;
import example.test.phong.youtubealikeproject.databinding.DetailVideoLayoutBinding;
import example.test.phong.youtubealikeproject.databinding.TitleVideoLayoutBinding;
import example.test.phong.youtubealikeproject.ui.detail.type.BaseAdapterType;
import example.test.phong.youtubealikeproject.ui.detail.videoholder.BaseViewHolder;
import example.test.phong.youtubealikeproject.ui.detail.videoholder.DetailViewHolder;
import example.test.phong.youtubealikeproject.ui.detail.videoholder.RelatedVideoVideoHolder;
import example.test.phong.youtubealikeproject.ui.detail.videoholder.TitlelVideoHolder;
import example.test.phong.youtubealikeproject.util.Constants;
import example.test.phong.youtubealikeproject.util.ImageLoader;

/**
 * Created by user on 1/13/2018.
 */

public class DetailVideoAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private final ImageLoader mImageLoader;
    private List<BaseAdapterType> mList = new ArrayList<>();

    @Inject
    public DetailVideoAdapter(ImageLoader imageLoader) {
        mImageLoader = imageLoader;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case Constants.TITLE_VIDEO:
                TitleVideoLayoutBinding titleVideoLayoutBinding = TitleVideoLayoutBinding.inflate(layoutInflater, parent, false);
                return new TitlelVideoHolder(titleVideoLayoutBinding);
            case Constants.DETAIL_TYPE:
                DetailVideoLayoutBinding detailVideoLayoutBinding = DetailVideoLayoutBinding.inflate(layoutInflater, parent, false);
                return new DetailViewHolder(mImageLoader, detailVideoLayoutBinding);
            case Constants.RELATED_VIDEO_TYPE:
                DetailRelatedVideoLayoutBinding detailRelatedVideoLayoutBinding = DetailRelatedVideoLayoutBinding.inflate(layoutInflater, parent, false);
                return new RelatedVideoVideoHolder(mImageLoader, detailRelatedVideoLayoutBinding);
        }
        return null;
    }

    // make this holder not check type
    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bind(mList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getViewType();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setData(List<BaseAdapterType> listData) {
        // TODO: 1/21/2018 replace with diffutil
        mList.addAll(listData);
        notifyDataSetChanged();
    }
}
