/*
 * Copyright (c) 2020 fdt <frederic.dt.twh@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package onl.fdt.android.fdtsdemo.ch3.Activity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

import onl.fdt.android.fdtsdemo.InputListener.CheckBoxInputListener;
import onl.fdt.android.fdtsdemo.InputListener.SeekBarInputListener;
import onl.fdt.android.fdtsdemo.R;

public class Work1Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch3_work1_activity);

        final CheckBox loopPlayCheckBox = (CheckBox) this.findViewById(R.id.loop_checkbox);
        final SeekBar animationProgressSeekBar = (SeekBar) this.findViewById(R.id.progress_seekbar);
        animationProgressSeekBar.setEnabled(false);

        final LottieAnimationView animationView = (LottieAnimationView) this.findViewById(R.id.animation_view);

        CheckBoxInputListener checkBoxInputListener = new CheckBoxInputListener(loopPlayCheckBox) {
            @Override
            public void onClick(View v) {

                animationView.setRepeatCount(loopPlayCheckBox.isChecked() ? LottieDrawable.INFINITE : 0);
                if (loopPlayCheckBox.isChecked()) {
                    animationView.playAnimation();
                    animationProgressSeekBar.setEnabled(false);
                } else {
                    animationView.cancelAnimation();
                    animationProgressSeekBar.setEnabled(true);
                }
            }
        };

        SeekBarInputListener seekBarInputListener = new SeekBarInputListener(animationProgressSeekBar) {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (!loopPlayCheckBox.isChecked()) {
                    animationView.setProgress((float) (progress / 100.0));
                }
            }
        };

        animationView.addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (loopPlayCheckBox.isChecked()) {
                    animationProgressSeekBar.setProgress((int) (animation.getAnimatedFraction() * 100.0));
                }
            }
        });


    }
}
