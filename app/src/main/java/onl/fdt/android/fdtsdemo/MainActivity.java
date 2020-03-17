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

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import onl.fdt.android.fdtsdemo.InputListener.CheckBoxInputListener;
import onl.fdt.android.fdtsdemo.InputListener.InputListener;
import onl.fdt.android.fdtsdemo.InputListener.SeekBarInputListener;
import onl.fdt.android.fdtsdemo.InputListener.SwitchInputListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        Switch _switch = (Switch) findViewById(R.id.switch1);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        RadioButton radioButton = (RadioButton) findViewById(R.id.radioButton);

        TextView textView = (TextView) findViewById(R.id.text);

        InputListener buttonInputListener = new InputListener(button);
        InputListener checkBoxInputListener = new CheckBoxInputListener(checkBox);
        InputListener switchInputListener = new SwitchInputListener(_switch);
        InputListener seekBarInputListener = new SeekBarInputListener(seekBar);
        InputListener radioButtonInputListener = new InputListener(radioButton);

        InputListener textViewInputListener = new InputListener(textView);
    }
}
