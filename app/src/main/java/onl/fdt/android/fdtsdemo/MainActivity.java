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
import onl.fdt.android.fdtsdemo.ch3.Activity.Work2Activity;
import onl.fdt.android.fdtsdemo.ch3.Activity.Work3Activity;
import onl.fdt.android.fdtsdemo.ch4.ClockApplication;
import onl.fdt.android.fdtsdemo.ch5.Ch5Activity;
import onl.fdt.android.fdtsdemo.ch6.activity.TodoListActivity;
import onl.fdt.android.fdtsdemo.ch7.PlayerActivity;

public class MainActivity extends AppCompatActivity {

    public static final Logger LOGGER = Logger.getLogger(MainActivity.class.getName());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent chapter3work1Intent = new Intent(this, Work1Activity.class);
        final Intent chapter3work2Intent = new Intent(this, Work2Activity.class);
        final Intent chapter3work3Intent = new Intent(this, Work3Activity.class);
        final Intent chapter4ClockIntent = new Intent(this, ClockApplication.class);
        final Intent chapter5HTTPIntent = new Intent(this, Ch5Activity.class);
        final Intent chapter6DBIntent = new Intent(this, TodoListActivity.class);
        final Intent chapter7Intent = new Intent(this, PlayerActivity.class);

        Button startCyclerViewButton = (Button) this.findViewById(R.id.cycler_view_button);
        // Listener to start ListActivity
        StartCyclerViewInputListener startCyclerViewInputListener = new StartCyclerViewInputListener(startCyclerViewButton, this);

        // ch3 work1 button onclick
        Button chapter3Work1Button = (Button) this.findViewById(R.id.ch3_work1);
        ClickStartNewIntentActivityInputListener chapter3Work1ClickStartNewIntentActivityInputListener = new ClickStartNewIntentActivityInputListener(chapter3Work1Button, this, chapter3work1Intent);

        // ch3 work2 button onclick
        Button chapter3Work2Button = (Button) this.findViewById(R.id.ch3_work2_button);
        ClickStartNewIntentActivityInputListener chapter3Work2ClickStartNewIntentActivityInputListener = new ClickStartNewIntentActivityInputListener(chapter3Work2Button, this, chapter3work2Intent);

        // ch3 work3 button onclick
        Button chapter3Work3Button = (Button) this.findViewById(R.id.ch3_work3_button);
        ClickStartNewIntentActivityInputListener chapter3Work3ClickStartNewIntentActivityInputListener = new ClickStartNewIntentActivityInputListener(chapter3Work3Button, this, chapter3work3Intent);

        // ch4 clock button onclick
        Button chapter4ClockButton = (Button) this.findViewById(R.id.ch4_button);
        ClickStartNewIntentActivityInputListener chapter4ClockClickStartNewIntentActivityInputListener = new ClickStartNewIntentActivityInputListener(chapter4ClockButton, this, chapter4ClockIntent);

        // ch5 HTTP button onclick
        Button chapter5ClockButton = (Button) this.findViewById(R.id.ch5_enter_button);
        ClickStartNewIntentActivityInputListener chapter5HTTPClickStartNewIntentActivityInputListener = new ClickStartNewIntentActivityInputListener(chapter5ClockButton, this, chapter5HTTPIntent);

        // ch6 DB button onclick
        Button chapter6DBButton = (Button) this.findViewById(R.id.ch6_enter_button);
        ClickStartNewIntentActivityInputListener chapter6DBClickStartNewIntentActivityInputListener = new ClickStartNewIntentActivityInputListener(chapter6DBButton, this, chapter6DBIntent);

        // ch7 button onclick
        Button chapter7PlayerButton = (Button) this.findViewById(R.id.ch7_enter_button);
        ClickStartNewIntentActivityInputListener chapter7PlayerClickStartNewIntentActivityInputListener = new ClickStartNewIntentActivityInputListener(chapter7PlayerButton, this, chapter7Intent);
    }

    @Override
    protected void onDestroy() {
        LOGGER.info("onDestroy()");
        super.onDestroy();
    }
}
