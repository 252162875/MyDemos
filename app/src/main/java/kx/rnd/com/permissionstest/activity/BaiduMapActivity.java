package kx.rnd.com.permissionstest.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.LogoPosition;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kx.rnd.com.permissionstest.R;

public class BaiduMapActivity extends Activity {

    @BindView(R.id.bmapView)
    MapView mMapView;
    @BindView(R.id.iv_mode)
    ImageView ivMode;
    private BaiduMap mBaiduMap;
    private int[] mode = {BaiduMap.MAP_TYPE_NORMAL, BaiduMap.MAP_TYPE_SATELLITE, BaiduMap.MAP_TYPE_NONE};
    private int i = 0;
    private double v;
    private double v1;
    private Marker marker;
    private MarkerOptions options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidu_map);
        ButterKnife.bind(this);
        mBaiduMap = mMapView.getMap();
        //开启交通图
        mBaiduMap.setTrafficEnabled(true);
        //开启城市热力图
        mBaiduMap.setBaiduHeatMapEnabled(true);
        //  地图Logo 默认在左下角显示，不可以移除。
        mMapView.setLogoPosition(LogoPosition.logoPostionRightTop);

        //定义Maker坐标点
        v = 34.341568;
        v1 = 108.940174;
        LatLng point = new LatLng(v, v1);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.arrow);
        //构建MarkerOption，用于在地图上添加Marker
        //设置marker的位置
        //设置marker图标
        //设置marker所在层级
        //设置手势拖拽
        options = new MarkerOptions()
                .position(point)  //设置marker的位置
                .icon(bitmap)  //设置marker图标
                .zIndex(9) //设置marker所在层级
                .alpha(0.5f)  //设置透明度
                .draggable(true);

        // 生长动画
        options.animateType(MarkerOptions.MarkerAnimateType.drop);
        //将marker添加到地图上
        marker = (Marker) mBaiduMap.addOverlay(options);


        //调用BaiduMap对象的setOnMarkerDragListener方法设置marker拖拽的监听
        mBaiduMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
            public void onMarkerDrag(Marker marker) {
                //拖拽中
            }

            public void onMarkerDragEnd(Marker marker) {
                //拖拽结束
                LatLng position = marker.getPosition();
                Toast.makeText(BaiduMapActivity.this, "" + position.latitude + "---" + position.longitude, Toast.LENGTH_SHORT).show();
            }

            public void onMarkerDragStart(Marker marker) {
                //开始拖拽
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @OnClick({R.id.iv_mode, R.id.iv_anim})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_mode:
                if (i == 3) {
                    i = 0;
                }
                mBaiduMap.setMapType(mode[i]);
                i++;
                break;
            case R.id.iv_anim:
                marker = (Marker) mBaiduMap.addOverlay(options);
                break;
        }
    }
}
