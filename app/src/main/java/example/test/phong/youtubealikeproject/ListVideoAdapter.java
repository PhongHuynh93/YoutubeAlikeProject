package example.test.phong.youtubealikeproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.schabi.newpipe.extractor.InfoItem;
import org.schabi.newpipe.extractor.stream.StreamInfoItem;

import java.util.ArrayList;
import java.util.List;

import example.test.phong.youtubealikeproject.databinding.ListStreamItemTestBinding;
import example.test.phong.youtubealikeproject.ui.adapter.viewholder.InfoItemBuilder;
import example.test.phong.youtubealikeproject.ui.adapter.viewholder.InfoItemHolder;
import example.test.phong.youtubealikeproject.ui.adapter.viewholder.StreamInfoItemHolder;

/**
 * Created by user on 12/30/2017.
 */

public class ListVideoAdapter extends RecyclerView.Adapter<StreamInfoItemHolder> {
    private List<InfoItem> mDatas;
    private final InfoItemBuilder infoItemBuilder;

    public ListVideoAdapter(InfoItemBuilder infoItemBuilder) {
        this.infoItemBuilder = infoItemBuilder;
        mDatas = new ArrayList<>();
    }

    @Override
    public StreamInfoItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ListStreamItemTestBinding itemBinding =
                ListStreamItemTestBinding.inflate(layoutInflater, parent, false);
        return new StreamInfoItemHolder(infoItemBuilder, itemBinding);
    }

    @Override
    public void onBindViewHolder(StreamInfoItemHolder holder, int position) {
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

    public void setOnStreamSelectedListener(InfoItemBuilder.OnInfoItemSelectedListener<StreamInfoItem> listener) {
        infoItemBuilder.setOnStreamSelectedListener(listener);
    }
}
