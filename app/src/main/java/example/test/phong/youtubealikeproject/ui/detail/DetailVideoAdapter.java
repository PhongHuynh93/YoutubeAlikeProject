package example.test.phong.youtubealikeproject.ui.detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import example.test.phong.youtubealikeproject.databinding.DetailRelatedVideoLayoutBinding;
import example.test.phong.youtubealikeproject.databinding.DetailVideoLayoutBinding;
import example.test.phong.youtubealikeproject.ui.detail.type.BaseAdapterType;
import example.test.phong.youtubealikeproject.ui.detail.videoholder.BaseViewHolder;
import example.test.phong.youtubealikeproject.ui.detail.videoholder.DetailViewHolder;
import example.test.phong.youtubealikeproject.ui.detail.videoholder.RelatedVideoVideoHolder;
import example.test.phong.youtubealikeproject.util.Constants;

/**
 * Created by user on 1/13/2018.
 */

public class DetailVideoAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private List<? extends BaseAdapterType> mList = new ArrayList<>();

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case Constants.DETAIL_TYPE:
                DetailVideoLayoutBinding detailVideoLayoutBinding = DetailVideoLayoutBinding.inflate(layoutInflater, parent, false);
                return new DetailViewHolder(detailVideoLayoutBinding);
            case Constants.RELATED_VIDEO_TYPE:
                DetailRelatedVideoLayoutBinding detailRelatedVideoLayoutBinding = DetailRelatedVideoLayoutBinding.inflate(layoutInflater, parent, false);
                return new RelatedVideoVideoHolder(detailRelatedVideoLayoutBinding);
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

    public void setData(List<? extends BaseAdapterType> listData) {
        mList.clear();
        mList = listData;
        notifyDataSetChanged();
    }
}
