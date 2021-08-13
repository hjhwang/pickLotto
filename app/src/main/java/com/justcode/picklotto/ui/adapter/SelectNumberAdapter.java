package com.justcode.picklotto.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.justcode.picklotto.R;
import com.justcode.picklotto.data.repository.entity.StatisticsEntity;
import com.justcode.picklotto.databinding.ItemStatisticsBinding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SelectNumberAdapter extends BaseAdapter {
    private String TAG = SelectNumberAdapter.class.getSimpleName();
    private Context mContext;
    private ItemStatisticsBinding mBinding;
    private List<Integer> mData = new ArrayList<>();
    private Set mNumberArr = new HashSet();

    public void addItems(List<Integer> numberArr) {
        mData.clear();
        mData = numberArr;
        mNumberArr = new HashSet<Integer>(numberArr);
    }

    public List getSelcetList() {
        return new ArrayList<>(mNumberArr);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_select_number, parent, false);
        }
        ImageView img = convertView.findViewById(R.id.img_shape);
        TextView tvNo = convertView.findViewById(R.id.tv_no);
        tvNo.setText(mData.get(position).toString());
        // imageView.setImageResource(items[i]);
        if (position < 10) {
            img.setColorFilter(context.getResources().getColor(R.color.lotto1));
        } else if (position < 20) {
            img.setColorFilter(context.getResources().getColor(R.color.lotto2));
        } else if (position < 30) {
            img.setColorFilter(context.getResources().getColor(R.color.lotto3));
        } else if (position < 40) {
            img.setColorFilter(context.getResources().getColor(R.color.lotto4));
        } else if (position < 45) {
            img.setColorFilter(context.getResources().getColor(R.color.lotto5));
        }
        //각 아이템 선택 event
        convertView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View view) {
                if (view.getAlpha() == 1) {
                    view.setAlpha(0.2f);
                    mNumberArr.remove(position + 1);
                } else {
                    view.setAlpha(1);
                    mNumberArr.add(position + 1);
                }
                Log.e(TAG, mNumberArr.toString());
            }
        });
        return convertView;
    }
}
