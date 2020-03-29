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

package onl.fdt.android.fdtsdemo.ViewsCounter;

import android.view.View;
import android.view.ViewGroup;

import java.util.logging.Logger;

public class ViewsCounter {
    private final static Logger LOGGER = Logger.getLogger(ViewsCounter.class.getName());
    public static int getRecursiveChildCount(ViewGroup v) {
        int c = 0;
        int i = v.getChildCount();
        c += i;
        if (i > 0) {
            for (int j = 0; j < i; ++j) {
                View vi = v.getChildAt(j);
                if (vi instanceof ViewGroup) {
                    LOGGER.finest(String.format("index %d", j));
                    int k = getRecursiveChildCount((ViewGroup) v.getChildAt(j));
                    LOGGER.finest(String.format("sub %d", k));
                    c += k;
                }
            }
        }
        return c;
    }
}
