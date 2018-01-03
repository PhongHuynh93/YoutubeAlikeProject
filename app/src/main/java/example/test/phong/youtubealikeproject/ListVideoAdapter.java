package example.test.phong.youtubealikeproject;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import org.schabi.newpipe.extractor.InfoItem;

import java.util.ArrayList;
import java.util.List;

import example.test.phong.youtubealikeproject.ui.adapter.viewholder.InfoItemBuilder;
import example.test.phong.youtubealikeproject.ui.adapter.viewholder.InfoItemHolder;
import example.test.phong.youtubealikeproject.ui.adapter.viewholder.StreamInfoItemHolder;

/**
 * Created by user on 12/30/2017.
 */

public class ListVideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<InfoItem> mDatas;
    private final InfoItemBuilder infoItemBuilder;

    public ListVideoAdapter(InfoItemBuilder infoItemBuilder) {
        this.infoItemBuilder = infoItemBuilder;
        mDatas = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StreamInfoItemHolder(infoItemBuilder, parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((InfoItemHolder) holder).updateFromItem(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void addVideos(List<InfoItem> videoList) {
        mDatas.addAll(videoList);
        // FIXME: 12/30/2017 change to used diffutil from rx
        notifyDataSetChanged();
    }
}
