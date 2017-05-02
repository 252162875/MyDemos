package kx.rnd.com.permissionstest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.SeekBar;
import android.widget.Toast;

import com.eftimoff.androipathview.PathView;
import com.jrummyapps.android.widget.AnimatedSvgView;

import kx.rnd.com.permissionstest.R;
import kx.rnd.com.permissionstest.SVG;

public class SvgTestActivity extends AppCompatActivity {

    private AnimatedSvgView svgView;
    private int index = -1;
    private PathView pathView;
    private SeekBar seekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg_test);
        svgView = (AnimatedSvgView) findViewById(R.id.animated_svg_view);
        pathView = (PathView) findViewById(R.id.pathView);
        seekbar = (SeekBar) findViewById(R.id.seekbar);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float p = progress / 100.0f;
                pathView.setPercentage(p);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        pathView.useNaturalColors();
        pathView.setFillAfter(true);
        pathView.getPathAnimator().listenerStart(new PathView.AnimatorBuilder.ListenerStart() {
            @Override
            public void onAnimationStart() {
                Toast.makeText(SvgTestActivity.this, "pathView动画开始", Toast.LENGTH_SHORT).show();
            }
        }).listenerEnd(new PathView.AnimatorBuilder.ListenerEnd() {
            @Override
            public void onAnimationEnd() {
                Toast.makeText(SvgTestActivity.this, "pathView动画结束", Toast.LENGTH_SHORT).show();
            }
        }).
//        pathView.getSequentialPathAnimator().
        delay(100).
                duration(1500).
                interpolator(new AccelerateDecelerateInterpolator()).
                start();

        pathView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pathView.getPathAnimator().
                        delay(100).
                        duration(1500).
                        interpolator(new AccelerateDecelerateInterpolator()).
                        start();
            }
        });
        setSvg(SVG.values()[0]);
        svgView.postDelayed(new Runnable() {

            @Override
            public void run() {
                svgView.start();
            }
        }, 500);


        svgView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (svgView.getState() == AnimatedSvgView.STATE_FINISHED) {
                    svgView.start();
                }
            }
        });

        svgView.setOnStateChangeListener(new AnimatedSvgView.OnStateChangeListener() {

            @Override
            public void onStateChange(int state) {
                if (state == AnimatedSvgView.STATE_TRACE_STARTED) {
                    findViewById(R.id.btn_previous).setEnabled(false);
                    findViewById(R.id.btn_next).setEnabled(false);
                } else if (state == AnimatedSvgView.STATE_FINISHED) {
                    findViewById(R.id.btn_previous).setEnabled(index != -1);
                    findViewById(R.id.btn_next).setEnabled(true);
                    if (index == -1) index = 0; // first time
                }
            }
        });
    }

    public void onNext(View view) {
        if (++index >= SVG.values().length) index = 0;
        setSvg(SVG.values()[index]);
    }

    public void onPrevious(View view) {
        if (--index < 0) index = SVG.values().length - 1;
        setSvg(SVG.values()[index]);
    }

    private void setSvg(SVG svg) {
        svgView.setGlyphStrings(svg.glyphs);
        svgView.setFillColors(svg.colors);
        svgView.setViewportSize(svg.width, svg.height);
        svgView.setTraceResidueColor(0x32000000);
        svgView.setTraceColors(svg.colors);
        svgView.rebuildGlyphData();
        svgView.start();
    }
}
