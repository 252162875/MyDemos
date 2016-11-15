package kx.rnd.com.permissionstest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kx.rnd.com.permissionstest.behavior.BehaviorTestOne;

public class BehaviorActivity extends AppCompatActivity {

    @BindView(R.id.btn_behavior1)
    Button btnBehavior1;
    @BindView(R.id.btn_behavior2)
    Button btnBehavior2;
    @BindView(R.id.btn_behavior3)
    Button btnBehavior3;
    @BindView(R.id.btn_behavior4)
    Button btnBehavior4;
    @BindView(R.id.btn_behavior5)
    Button btnBehavior5;
    @BindView(R.id.btn_behavior6)
    Button btnBehavior6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn_behavior1, R.id.btn_behavior2, R.id.btn_behavior3, R.id.btn_behavior4, R.id.btn_behavior5, R.id.btn_behavior6})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_behavior1:
                Intent behaviorIntent = new Intent(BehaviorActivity.this, BehaviorTestOneActivity.class);
                startActivity(behaviorIntent);
                break;
            case R.id.btn_behavior2:
                break;
            case R.id.btn_behavior3:
                break;
            case R.id.btn_behavior4:
                break;
            case R.id.btn_behavior5:
                break;
            case R.id.btn_behavior6:
                break;
        }
    }
}
