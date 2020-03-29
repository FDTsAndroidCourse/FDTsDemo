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

package onl.fdt.android.fdtsdemo.CyclerView;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

import onl.fdt.android.fdtsdemo.CyclerView.model.Message;
import onl.fdt.android.fdtsdemo.CyclerView.model.PullParser;
import onl.fdt.android.fdtsdemo.R;

public class ListActivity extends AppCompatActivity {

    public static final String DATA_XML = "data.xml";
    private static final Logger LOGGER = Logger.getLogger(ListActivity.class.getName());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RecyclerView view = (RecyclerView) this.findViewById(R.id.recycler_view);

        view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        TextView textView = (TextView) this.findViewById(R.id.cycler_view_text);

        try {
            InputStream dataInputStream = this.getAssets().open(DATA_XML);
            List<Message> messages = PullParser.pull2xml(dataInputStream);

            textView.setText(messages.toString());

        } catch (IOException e) {
            LOGGER.warning(String.format("onCreate() IOException when reading %s", DATA_XML));
        }
    }
}
