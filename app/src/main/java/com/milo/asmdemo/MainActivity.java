package com.milo.asmdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.milo.asmdemo.ui.LifeCycleObserverActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;

    private List<ItemData> listData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initData() {
        listData.add(ItemData.ActivityLifeCycle);
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new RecyclerView.Adapter<MainItemVH>() {

            @NonNull
            @Override
            public MainItemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new MainItemVH(LayoutInflater.from(parent.getContext()).inflate(MainItemVH.getLayoutResourceId(), parent, false));
            }

            @Override
            public void onBindViewHolder(@NonNull MainItemVH holder, int position) {
                holder.bindViewHolder(listData.get(position), MainActivity.this::onClick);
            }

            @Override
            public int getItemCount() {
                return listData.size();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View v) {
        ItemData itemData = (ItemData) v.getTag();
        switch (itemData) {
            case ActivityLifeCycle:
                startActivity(LifeCycleObserverActivity.createIntent(this));
                break;
        }
    }

    private static class MainItemVH extends RecyclerView.ViewHolder {

        public TextView tvText;

        public MainItemVH(@NonNull View itemView) {
            super(itemView);
            tvText = itemView.findViewById(R.id.tvText);
        }

        public void bindViewHolder(ItemData itemData, View.OnClickListener onClickListener) {
            tvText.setText(itemData.name);
            tvText.setTag(itemData);
            tvText.setOnClickListener(onClickListener);
        }

        public static int getLayoutResourceId() {
            return R.layout.vh_item_main;
        }
    }

    private enum ItemData {

        ActivityLifeCycle("Activity生命周期演示");

        String name;

        ItemData(String name) {
            this.name = name;
        }
    }

}