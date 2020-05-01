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

package onl.fdt.android.fdtsdemo.ch6.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.greenrobot.greendao.database.Database;

import onl.fdt.android.fdtsdemo.R;
import onl.fdt.android.fdtsdemo.ch6.activity.ui.NoteListAdapter;
import onl.fdt.android.fdtsdemo.ch6.dao.DaoMaster;
import onl.fdt.android.fdtsdemo.ch6.dao.DaoSession;
import onl.fdt.android.fdtsdemo.ch6.dao.TodoListItemDao;
import onl.fdt.android.fdtsdemo.ch6.model.TodoListItem;

public class TodoListActivity extends AppCompatActivity {
    private DaoSession daoSession;
    private RecyclerView recyclerView;
    private NoteListAdapter notesAdapter;

    public DaoSession getDaoSession() {
        return this.daoSession;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch6_todolist);

        // DB
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "todolist-db");
        Database db = helper.getWritableDb();

        this.daoSession = new DaoMaster(db).newSession();

        // Add note button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(
                        new Intent(TodoListActivity.this, AddNoteActivity.class),
                        1);
            }
        });

        // Node recycler view
        recyclerView = findViewById(R.id.list_todo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        notesAdapter = new NoteListAdapter(this.daoSession);
        recyclerView.setAdapter(notesAdapter);

        NoteListAdapter.updateDataHandler.setAdapter(notesAdapter);
        Message updateDataMessage = new Message();
        updateDataMessage.obj = daoSession.queryBuilder(TodoListItem.class).orderAsc(TodoListItemDao.Properties.Priority).orderAsc(TodoListItemDao.Properties.Time).list();
        updateDataMessage.what = 1;
        NoteListAdapter.updateDataHandler.sendMessage(updateDataMessage);
    }
}
