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

package onl.fdt.android.fdtsdemo.InputListener;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.logging.Logger;

import onl.fdt.android.fdtsdemo.R;
import onl.fdt.android.fdtsdemo.ViewsCounter.ViewsCounter;

public class CountViewsButtonInputListener extends InputListener {
    private static final Logger LOGGER = Logger.getLogger(CountViewsButtonInputListener.class.getName());
    private static final String PREFIX = "Count Views ";

    private final TextView outputTextView;
    private final AppCompatActivity activity;

    public CountViewsButtonInputListener(View button, TextView outputTextView, AppCompatActivity appCompatActivity) {
        super(button);
        this.outputTextView = outputTextView;
        this.activity = appCompatActivity;
    }

    @Override
    public void onClick(View v) {
        int count = ViewsCounter.getRecursiveChildCount((ViewGroup) activity.findViewById(R.id.root_layout));
        String messageText = String.format("%s %d", PREFIX, count);
        outputTextView.setText(messageText);
        LOGGER.info(messageText);
        super.onClick(v);
    }
}
