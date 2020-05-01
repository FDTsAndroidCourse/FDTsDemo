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

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.greendao.database.Database;

import onl.fdt.android.fdtsdemo.R;
import onl.fdt.android.fdtsdemo.ch6.activity.ui.NoteListAdapter;
import onl.fdt.android.fdtsdemo.ch6.dao.DaoMaster;
import onl.fdt.android.fdtsdemo.ch6.dao.DaoSession;
import onl.fdt.android.fdtsdemo.ch6.dao.TodoListItemDao;
import onl.fdt.android.fdtsdemo.ch6.model.TodoListItem;

public class AddNoteActivity extends AppCompatActivity {

    private EditText editText;
    private Button addBtn;
    private EditText priorityEditText;

    private DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch6_todolist_add_node);

        // DB
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "todolist-db");
        Database db = helper.getWritableDb();

        this.daoSession = new DaoMaster(db).newSession();

        editText = findViewById(R.id.edit_text);
        editText.setFocusable(true);
        editText.requestFocus();
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.showSoftInput(editText, 0);
        }

        priorityEditText = findViewById(R.id.priority_text);

        addBtn = findViewById(R.id.btn_add);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence content = editText.getText();
                CharSequence priorityText = priorityEditText.getText();
                long priority = 100L;

                try {
                    priority = Long.parseLong(priorityText.toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(AddNoteActivity.this,
                            "Priority Error, use default 100", Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(AddNoteActivity.this,
                            "No content to add", Toast.LENGTH_SHORT).show();
                    return;
                }
//                boolean succeed = saveNote2Database(content.toString().trim());
                boolean succeed = dbTakeNote(content.toString().trim(), priority);
                if (succeed) {
                    Toast.makeText(AddNoteActivity.this,
                            "Note added", Toast.LENGTH_SHORT).show();
                    setResult(Activity.RESULT_OK);
                } else {
                    Toast.makeText(AddNoteActivity.this,
                            "Error", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private boolean dbTakeNote(String content, Long priority) {
        boolean succes = this.daoSession.insert(new TodoListItem(
                content, System.currentTimeMillis(), priority, false
        )) > 0;

        if (succes) {
            Message msg = new Message();
            msg.what = 1;
            msg.obj = daoSession.queryBuilder(TodoListItem.class).orderAsc(TodoListItemDao.Properties.Priority).orderAsc(TodoListItemDao.Properties.Time).list();;
            NoteListAdapter.updateDataHandler.sendMessage(msg);
        }
        return succes;
    }
}
