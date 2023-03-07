package com.milo.asmdemo.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.milo.asmdemo.R;

/**
 * Title：
 * Describe：
 * Remark：
 * <p>
 * Created by Milo
 * E-Mail : 303767416@qq.com
 * 2023/3/7
 */
public class LifeCycleObserverActivity extends AppCompatActivity {

    public static Intent createIntent(Context context) {
        return new Intent(context, LifeCycleObserverActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Activity生命周期演示");
        setContentView(R.layout.activity_lifecycle_observer);
    }

}
