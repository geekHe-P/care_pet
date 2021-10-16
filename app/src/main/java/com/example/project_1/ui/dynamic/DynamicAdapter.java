


package com.example.project_1.ui.dynamic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_1.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DynamicAdapter extends RecyclerView.Adapter<DynamicAdapter.ViewHolder> {

    private List<Dynamic> dynamicList;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView content;
        ImageView iconImage;
        ImageView picture;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.dynamic_icon);
            name = itemView.findViewById(R.id.dynamic_name);
            content = itemView.findViewById(R.id.dynamic_text);
            picture = itemView.findViewById(R.id.dynamic_img);
        }
    }

    public DynamicAdapter(List<Dynamic> dynamicList) {
        this.dynamicList = dynamicList;
    }

    @NonNull
    @NotNull
    @Override
    //为每条数据记录绑定一个填充的布局
    public DynamicAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dynamic_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DynamicAdapter.ViewHolder holder, int position) {
        Dynamic dynamic = dynamicList.get(position);
        holder.iconImage.setImageResource(dynamic.getIconId());
        holder.content.setText(dynamic.getContent());
        holder.name.setText(dynamic.getName());
        holder.picture.setImageResource(dynamic.getPrictureId());
    }

    @Override
    //返回当前列表返回的数据总数
    public int getItemCount() {
        return dynamicList.size();
    }
}
