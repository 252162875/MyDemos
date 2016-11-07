package kx.rnd.com.permissionstest;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends Activity {

    @BindView(R.id.btn_buletooth)
    Button btnBuletooth;
    @BindView(R.id.btn_muiltdownload)
    Button btnMuiltdownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
    }


    public void autoPairBlueTooth() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();


        if (!defaultAdapter.isEnabled()) {
            defaultAdapter.enable();//异步的，不会等待结果，直接返回。
        } else {
            defaultAdapter.startDiscovery();
        }
    }

    @OnClick({R.id.btn_buletooth, R.id.btn_muiltdownload, R.id.btn_verticalscroll,R.id.btn_polygon})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_buletooth:
                autoPairBlueTooth();
                break;
            case R.id.btn_muiltdownload:
                Intent downLoadIntent = new Intent(SecondActivity.this, MuiltDownLoadActivity.class);
                startActivity(downLoadIntent);
                break;
            case R.id.btn_verticalscroll:
                Intent verticalScrollIntent = new Intent(SecondActivity.this, VerticalScrollActivity.class);
                startActivity(verticalScrollIntent);
                break;
            case R.id.btn_polygon:
                Intent polygonItent = new Intent(SecondActivity.this, PolygonActivity.class);
                startActivity(polygonItent);
                break;
        }
    }
}
