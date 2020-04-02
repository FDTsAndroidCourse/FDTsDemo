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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

import onl.fdt.android.fdtsdemo.CyclerView.DataAdapter;
import onl.fdt.android.fdtsdemo.CyclerView.model.Message;
import onl.fdt.android.fdtsdemo.CyclerView.model.PullParser;
import onl.fdt.android.fdtsdemo.R;

import static onl.fdt.android.fdtsdemo.CyclerView.ListActivity.DATA_XML;

public class ListFragment extends Fragment {

    private static final Logger LOGGER = Logger.getLogger(ListFragment.class.getName());
    private final AppCompatActivity context;

    public ListFragment(final AppCompatActivity context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView view = (RecyclerView) this.context.findViewById(R.id.recycler_view);
        view.setLayoutManager(new LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false));

        try {
            InputStream dataInputStream = this.context.getAssets().open(DATA_XML);
            List<Message> messages = PullParser.pull2xml(dataInputStream);

            DataAdapter dataAdapter = new DataAdapter(messages, this.context);
            view.setAdapter(dataAdapter);

            View target = ListFragment.this.context.findViewById(R.id.recycler_view);
            ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(target, "alpha", 0, 1);
            animatorAlpha.setDuration(500);
            animatorAlpha.start();

        } catch (IOException e) {
            LOGGER.warning(String.format("onCreate() IOException when reading %s", DATA_XML));
        }
    }
}
