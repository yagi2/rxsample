package com.yagi2.rxsample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.yagi2.rxsample.R;
import com.yagi2.rxsample.model.WeatherModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardRecyclerViewAdapter extends RecyclerView.Adapter<CardRecyclerViewAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private List<WeatherModel.Forecasts> data;
    private Context context;

    public CardRecyclerViewAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.layout_recycler_view, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WeatherModel.Forecasts item = data.get(position);

        String mainText = item.dateLabel + " " + item.date + "\n\n" + item.telop;
        holder.textMain.setText(mainText);

        String subText = "";
        if (item.temperature.max == null) {
            subText += "--℃ / ";
        }
        else {
            subText += item.temperature.max.celsius + "℃ / ";
        }
        if (item.temperature.min == null) {
            subText += "--℃";
        }
        else {
            subText += item.temperature.min.celsius + "℃";
        }

        holder.textSub.setText(subText);

        if (item.image != null) {
            GlideDrawableImageViewTarget target = new GlideDrawableImageViewTarget(holder.imageView);
            Glide.with(context).load(item.image.url).into(target);
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView imageView;
        @BindView(R.id.text_main)
        TextView textMain;
        @BindView(R.id.text_sub)
        TextView textSub;
        @BindView(R.id.cart_linear_layout)
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setData(List<WeatherModel.Forecasts> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
