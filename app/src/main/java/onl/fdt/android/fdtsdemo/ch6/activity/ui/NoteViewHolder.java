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

package onl.fdt.android.fdtsdemo.ch6.activity.ui;

import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import onl.fdt.android.fdtsdemo.R;
import onl.fdt.android.fdtsdemo.ch6.dao.DaoSession;
import onl.fdt.android.fdtsdemo.ch6.dao.TodoListItemDao;
import onl.fdt.android.fdtsdemo.ch6.model.TodoListItem;

public class NoteViewHolder extends RecyclerView.ViewHolder {

//    private static final SimpleDateFormat SIMPLE_DATE_FORMAT =
//            new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss", Locale.ENGLISH);
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss z", Locale.ENGLISH);

    private final DaoSession daoSession;

    private CheckBox checkBox;
    private TextView contentText;
    private TextView dateText;
    private View deleteBtn;
    private NoteListAdapter adapter;

    public NoteViewHolder(@NonNull View itemView, DaoSession daoSession, NoteListAdapter adapter) {
        super(itemView);
        this.daoSession = daoSession;
        this.adapter = adapter;

        checkBox = itemView.findViewById(R.id.checkbox);
        contentText = itemView.findViewById(R.id.text_content);
        dateText = itemView.findViewById(R.id.text_date);
        deleteBtn = itemView.findViewById(R.id.btn_delete);
    }

    public void bind(final TodoListItem note) {
        contentText.setText(note.getText());
        dateText.setText(DATE_TIME_FORMATTER.format(note.getZonedDateTime()));

        checkBox.setOnCheckedChangeListener(null);
        checkBox.setChecked(note.getFinished());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                note.setFinished(isChecked);
                // update
                daoSession.update(note);
                adapter.refresh(daoSession.queryBuilder(TodoListItem.class).orderAsc(TodoListItemDao.Properties.Priority).orderAsc(TodoListItemDao.Properties.Time).list());
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daoSession.delete(note);
                adapter.refresh(daoSession.queryBuilder(TodoListItem.class).orderAsc(TodoListItemDao.Properties.Priority).orderAsc(TodoListItemDao.Properties.Time).list());
            }
        });

        if (note.getFinished()) {
            contentText.setTextColor(Color.GRAY);
            contentText.setPaintFlags(contentText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            contentText.setTextColor(Color.BLACK);
            contentText.setPaintFlags(contentText.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        }

    }
}
