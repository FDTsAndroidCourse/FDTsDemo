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

package onl.fdt.android.fdtsdemo.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.logging.Logger;

import onl.fdt.android.fdtsdemo.R;

public class LoadingFragment extends Fragment {

    private static final Logger LOGGER = Logger.getLogger(LoadingFragment.class.getName());

    private final AppCompatActivity context;

    public LoadingFragment(final AppCompatActivity context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // DONE ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
//        LOGGER.info("loading fragment created, onCreateView()");
        return inflater.inflate(R.layout.loading_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // DONE ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入

                View target = LoadingFragment.this.context.findViewById(R.id.animation_view2);
                ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(target, "alpha", 1, 0);
                animatorAlpha.setDuration(200);
                animatorAlpha.start();

                ((ViewGroup) target.getParent()).removeView(target);

                LoadingFragment.this.context.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new ListFragment(LoadingFragment.this.context)).commit();
            }
        }, 5000);
    }
}
