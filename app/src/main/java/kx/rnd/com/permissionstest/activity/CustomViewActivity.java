package kx.rnd.com.permissionstest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kx.rnd.com.permissionstest.R;

public class CustomViewActivity extends AppCompatActivity {

    @BindView(R.id.btn_xfremode_test)
    Button btnXfremodeTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_xfremode_test})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_xfremode_test:
                Intent xfreModeTestIntent = new Intent(CustomViewActivity.this, XfreModeTestActivity.class);
                startActivity(xfreModeTestIntent);
                break;
        }
    }
}
