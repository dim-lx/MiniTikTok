package com.example.project_mini_tiktok.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.project_mini_tiktok.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<TestData> mDataset = new ArrayList<>();
    private IOnItemClickListener mItemClickListener;

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.onBind(mDataset.get(position/* % 4*/));
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemCLick(position, mDataset.get(position));
                }
            }
        });
        holder.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemLongCLick(position, mDataset.get(position));
                }
                return false;
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface IOnItemClickListener {
        void onItemCLick(int position, TestData data);
        void onItemLongCLick(int position, TestData data);
    }

    public MyAdapter(List<TestData> myDataset) {
        mDataset.addAll(myDataset);
    }

    public void setOnItemClickListener(IOnItemClickListener listener) {
        mItemClickListener = listener;
    }


//    public void addData(int position, TestData data) {
//        mDataset.add(position, data);
//        notifyItemInserted(position);
//        if (position != mDataset.size()) {
//            //刷新改变位置item下方的所有Item的位置,避免索引错乱
//            notifyItemRangeChanged(position, mDataset.size() - position);
//        }
//    }
//
//    public void removeData(int position) {
//        if (null != mDataset && mDataset.size() > position) {
//            mDataset.remove(position);
//            notifyItemRemoved(position);
//            if (position != mDataset.size()) {
//                // 刷新改变位置item下方的所有Item的位置,避免索引错乱
//                notifyItemRangeChanged(position, mDataset.size() - position);
//            }
//        }
//    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private ImageView image;
        private View contentView;


        public MyViewHolder(View v) {
            super(v);
            contentView = v;
            tvTitle = v.findViewById(R.id.tv_title);
            image = v.findViewById(R.id.sd_cover);
        }

        public void onBind(TestData data) {
            tvTitle.setText(data.title);
            image.setImageResource(data.src);
        }

        public void setOnClickListener(View.OnClickListener listener) {
            if (listener != null) {
                contentView.setOnClickListener(listener);
            }
        }

        public void setOnLongClickListener(View.OnLongClickListener listener) {
            if (listener != null) {
                contentView.setOnLongClickListener(listener);
            }
        }
    }
}
