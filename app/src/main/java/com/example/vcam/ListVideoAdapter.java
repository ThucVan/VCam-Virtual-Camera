package com.example.vcam;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vcam.model.MediaModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Locale;

public class ListVideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final List<MediaModel> lists;

    public ListVideoAdapter(Context context, List<MediaModel> lists) {
        this.context = context;
        this.lists = lists;
    }

    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_item_video, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        MediaModel data = lists.get(position);
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).bind(data);
        }
    }

    @Override
    public int getItemCount() {
        return lists == null ? 0 : lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDuration;
        ImageView imv;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDuration = itemView.findViewById(R.id.tv_time);
            imv = itemView.findViewById(R.id.img_video);
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        public void bind(MediaModel data) {
            tvName.setText(data.getMediaName());
            tvDuration.setText(formatTime(data.getDuration()));
//            Glide.with(context).load(data.getMediaPath()).into(imv);
        }
    }

    public static String formatTime(long j) {
        int i = (int) (j / 1000);
        int i2 = i / 3600;
        int i3 = i % 3600;
        int i4 = i3 / 60;
        int i5 = i3 % 60;

        if (i2 > 0) {
            return String.format(Locale.US, "%d:%02d:%02d", i2, i4, i5);
        }
        return String.format(Locale.US, "%d:%02d", i4, i5);
    }

}

