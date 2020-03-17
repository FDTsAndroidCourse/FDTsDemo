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
import android.widget.SeekBar;

import java.util.logging.Logger;

public class SeekBarInputListener extends InputListener implements SeekBar.OnSeekBarChangeListener {
    private static final Logger LOGGER = Logger.getLogger(SeekBarInputListener.class.getName());

    public SeekBarInputListener(SeekBar view) {
        super(view);
        view.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        LOGGER.info(seekBar.getId() + " getProgress(): " + progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        LOGGER.info(seekBar.getId() + " onStartTrackingTouch()");
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        LOGGER.info(seekBar.getId() + " onStopTrackingTouch()");
    }
}
