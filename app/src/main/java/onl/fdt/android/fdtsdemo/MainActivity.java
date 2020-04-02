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

package onl.fdt.android.fdtsdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.logging.Logger;

import onl.fdt.android.fdtsdemo.InputListener.ClickStartNewIntentActivityInputListener;
import onl.fdt.android.fdtsdemo.InputListener.StartCyclerViewInputListener;
import onl.fdt.android.fdtsdemo.ch3.Activity.Work1Activity;

public class MainActivity extends AppCompatActivity {

    public static final Logger LOGGER = Logger.getLogger(MainActivity.class.getName());
    private Intent chapter3work1Intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.chapter3work1Intent = new Intent(this, Work1Activity.class);

        Button startCyclerViewButton = (Button) this.findViewById(R.id.cycler_view_button);
        // Listener to start ListActivity
        StartCyclerViewInputListener startCyclerViewInputListener = new StartCyclerViewInputListener(startCyclerViewButton, this);

        // ch3 work1 button onclick
        Button chapter3Work1Button = (Button) this.findViewById(R.id.ch3_work1);
        ClickStartNewIntentActivityInputListener chapter3Work1ClickStartNewIntentActivityInputListener = new ClickStartNewIntentActivityInputListener(chapter3Work1Button, this, this.chapter3work1Intent);


    }

    @Override
    protected void onDestroy() {
        LOGGER.info("onDestroy()");
        super.onDestroy();
    }
}
