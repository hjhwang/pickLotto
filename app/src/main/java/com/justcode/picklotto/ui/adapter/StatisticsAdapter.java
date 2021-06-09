package com.justcode.picklotto.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.justcode.picklotto.R;
import com.justcode.picklotto.data.repository.entity.StatisticsEntity;
import com.justcode.picklotto.databinding.ItemStatisticsBinding;

import java.util.ArrayList;
import java.util.List;

public class StatisticsAdapter extends RecyclerView.Adapter<StatisticsAdapter.ViewHolder> {
    private String TAG = StatisticsAdapter.class.getSimpleName();
    private Context mContext;
    private ItemStatisticsBinding mBinding;
    private List<StatisticsEntity> mData = new ArrayList<>();

    public StatisticsAdapter(Context context) {
        mContext = context;
        mData.clear();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImg;
        private TextView mTvNo;
        private TextView mTvCount;

        public ViewHolder(@NonNull View view) {
            super(view);
            mImg = mBinding.imgShape;
            mTvNo = mBinding.tvNo;
            mTvCount = mBinding.tvCount;
        }

        void onBind(StatisticsEntity entity) {
            int targetNum = entity.getBallNumber();
            mTvNo.setText(String.valueOf(targetNum));
            if (targetNum <= 10) {
                mImg.setColorFilter(mContext.getResources().getColor(R.color.lotto1));
            } else if (targetNum <= 20) {
                mImg.setColorFilter(mContext.getResources().getColor(R.color.lotto2));
            } else if (targetNum <= 30) {
                mImg.setColorFilter(mContext.getResources().getColor(R.color.lotto3));
            } else if (targetNum <= 40) {
                mImg.setColorFilter(mContext.getResources().getColor(R.color.lotto4));
            } else if (targetNum <= 45) {
                mImg.setColorFilter(mContext.getResources().getColor(R.color.lotto5));
            }
            mTvCount.setText(String.valueOf(entity.getCount()) + "번 당첨");
        }
    }

    @NonNull
    @Override
    public StatisticsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.item_statistics, viewGroup, false);
        StatisticsAdapter.ViewHolder vh = new StatisticsAdapter.ViewHolder(mBinding.itemContainer);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StatisticsEntity entity = getEntity(position);
        // Log.e(TAG, entity.toString());
        if (entity != null) {
            holder.onBind(entity);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public StatisticsEntity getEntity(int position) {
        return mData.get(position);
    }

    public void setItems(List<StatisticsEntity> entities) {
        mData.clear();
        mData.addAll(entities);
        notifyDataSetChanged();
    }
}
