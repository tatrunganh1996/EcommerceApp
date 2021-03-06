package com.example.ecommerceapp.adapters;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ecommerceapp.ProductDetailActivity;
import com.example.ecommerceapp.R;
import com.example.ecommerceapp.models.HorizontalProductScrollModel;

import java.util.List;

public class GridProductLayoutAdapter extends BaseAdapter {
    List<HorizontalProductScrollModel> modelList;

    public GridProductLayoutAdapter(List<HorizontalProductScrollModel> modelList) {
        this.modelList = modelList;
    }

    @Override
    public int getCount() {
        return modelList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View view;
        if (convertView == null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item_layout, null);
            view.setElevation(0);
            view.setBackgroundColor(Color.parseColor("#ffffff"));

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent productDetailsIntent = new Intent(parent.getContext(), ProductDetailActivity.class);
                    parent.getContext().startActivity(productDetailsIntent);
                }
            });

            ImageView productImage = view.findViewById(R.id.h_s_product_image);
            TextView productTitle = view.findViewById(R.id.h_s_product_title);
            TextView productDesc = view.findViewById(R.id.h_s_product_desc);
            TextView productPrice = view.findViewById(R.id.h_s_product_price);

            productImage.setImageResource(modelList.get(position).getNum());
            productTitle.setText(modelList.get(position).getTitle());
            productDesc.setText(modelList.get(position).getDesc());
            productPrice.setText(modelList.get(position).getPrice());
        }else {
            view = convertView;
        }
        return view;
    }

}
