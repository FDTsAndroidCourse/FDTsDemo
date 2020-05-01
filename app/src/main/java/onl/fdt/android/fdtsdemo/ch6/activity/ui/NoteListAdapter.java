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

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import onl.fdt.android.fdtsdemo.R;
import onl.fdt.android.fdtsdemo.ch6.dao.DaoSession;
import onl.fdt.android.fdtsdemo.ch6.model.TodoListItem;

public class NoteListAdapter extends RecyclerView.Adapter<NoteViewHolder> {
    public static class UpdateDataHandler extends Handler {
        private NoteListAdapter adapter;

        @Override
        public void handleMessage(Message msg) {
            if (this.adapter != null && msg.obj instanceof List) {
                adapter.refresh((List<TodoListItem>) msg.obj);
            }
        }

        public void setAdapter(NoteListAdapter adapter) {
            this.adapter = adapter;
        }
    }

    public static UpdateDataHandler updateDataHandler = new UpdateDataHandler();

    private final DaoSession operator;
    private final List<TodoListItem> notes = new ArrayList<>();

    public NoteListAdapter(DaoSession operator) {
        this.operator = operator;
    }

    public void refresh(List<TodoListItem> newNotes) {
        notes.clear();
        if (newNotes != null) {
            notes.addAll(newNotes);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int pos) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ch6_todolist_item, parent, false);
        return new NoteViewHolder(itemView, operator, this);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int pos) {
        holder.bind(notes.get(pos));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
