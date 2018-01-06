package example.test.phong.youtubealikeproject.ui.adapter.viewholder;

import android.support.v7.widget.RecyclerView;

import org.schabi.newpipe.extractor.InfoItem;

import example.test.phong.youtubealikeproject.databinding.ListStreamItemTestBinding;

/**
 * Created by user on 12/30/2017.
 */

public abstract class InfoItemHolder extends RecyclerView.ViewHolder {
    protected final InfoItemBuilder itemBuilder;

    public InfoItemHolder(InfoItemBuilder infoItemBuilder, ListStreamItemTestBinding binding) {
        super(binding.getRoot());
        this.itemBuilder = infoItemBuilder;
    }

    public abstract void updateFromItem(InfoItem infoItem);
}
