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

package onl.fdt.android.fdtsdemo.log.handler;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TextViewLogHandler extends Handler {

    private static final Logger LOGGER = Logger.getLogger(TextViewLogHandler.class.getName());

    private final TextView v;
    private String text = "";
    private final static Formatter formatter = new SimpleFormatter();
    private LinkedList<LogRecord> logRecords = new LinkedList<LogRecord>();

    public TextViewLogHandler(TextView v) {
        this.v = v;
    }

    @Override
    public void publish(LogRecord record) {
        logRecords.add(record);
        syncText();
    }

    public void syncText() {
        final StringBuilder text = new StringBuilder();
        for (LogRecord i : logRecords) {
            text.append(formatter.format(i));
        }
        this.text = text.toString();
        v.setText(text);
    }

    @Override
    public void flush() {
        logRecords.clear();
        syncText();
    }

    @Override
    public void close() throws SecurityException {

    }
}
