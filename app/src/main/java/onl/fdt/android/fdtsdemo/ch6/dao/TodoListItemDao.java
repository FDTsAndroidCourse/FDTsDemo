package onl.fdt.android.fdtsdemo.ch6.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import onl.fdt.android.fdtsdemo.ch6.model.TodoListItem;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TODO_LIST_ITEM".
*/
public class TodoListItemDao extends AbstractDao<TodoListItem, Long> {

    public static final String TABLENAME = "TODO_LIST_ITEM";

    /**
     * Properties of entity TodoListItem.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Text = new Property(1, String.class, "text", false, "TEXT");
        public final static Property Time = new Property(2, Long.class, "time", false, "TIME");
        public final static Property Priority = new Property(3, Long.class, "priority", false, "PRIORITY");
        public final static Property Finished = new Property(4, boolean.class, "finished", false, "FINISHED");
    }


    public TodoListItemDao(DaoConfig config) {
        super(config);
    }
    
    public TodoListItemDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TODO_LIST_ITEM\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"TEXT\" TEXT NOT NULL ," + // 1: text
                "\"TIME\" INTEGER," + // 2: time
                "\"PRIORITY\" INTEGER," + // 3: priority
                "\"FINISHED\" INTEGER NOT NULL );"); // 4: finished
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_TODO_LIST_ITEM_PRIORITY_TIME ON \"TODO_LIST_ITEM\"" +
                " (\"PRIORITY\" ASC,\"TIME\" ASC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TODO_LIST_ITEM\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, TodoListItem entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getText());
 
        Long time = entity.getTime();
        if (time != null) {
            stmt.bindLong(3, time);
        }
 
        Long priority = entity.getPriority();
        if (priority != null) {
            stmt.bindLong(4, priority);
        }
        stmt.bindLong(5, entity.getFinished() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, TodoListItem entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getText());
 
        Long time = entity.getTime();
        if (time != null) {
            stmt.bindLong(3, time);
        }
 
        Long priority = entity.getPriority();
        if (priority != null) {
            stmt.bindLong(4, priority);
        }
        stmt.bindLong(5, entity.getFinished() ? 1L: 0L);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public TodoListItem readEntity(Cursor cursor, int offset) {
        TodoListItem entity = new TodoListItem( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // text
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // time
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3), // priority
            cursor.getShort(offset + 4) != 0 // finished
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, TodoListItem entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setText(cursor.getString(offset + 1));
        entity.setTime(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setPriority(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setFinished(cursor.getShort(offset + 4) != 0);
     }
    
    @Override
    protected final Long updateKeyAfterInsert(TodoListItem entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(TodoListItem entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(TodoListItem entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
