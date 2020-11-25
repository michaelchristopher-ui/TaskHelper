package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.dao.TaskGroupDao;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.database.AppDatabase;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.entities.TaskGroup;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.repository.Repository;

public class TaskGroupProvider extends ContentProvider {

    public static final String TAG = TaskGroupProvider.class.getName();

    public static final String AUTHORITY_= "taskgroup.provider";

    public static final String TASKGROUP_TABLE_NAME = "taskgroup";

    public static final int ID_TASKGROUP_DATA = 1;

    public static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private TaskGroupDao taskGroupDao;

    static {
        uriMatcher.addURI(AUTHORITY_, "taskgroup", ID_TASKGROUP_DATA);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
        }

        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        return null;
    }

    @Override
    public boolean onCreate() {
        AppDatabase database = AppDatabase.getDatabase(getContext());
        taskGroupDao = database.taskGroupDao();

        int testmatch = uriMatcher.match(Uri.parse("content://taskgroup.provider/taskgroup"));
        Log.d("test match", String.valueOf(testmatch));
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Log.d(TAG, "query");
        Cursor cursor;
        switch (uriMatcher.match(uri)) {
            case ID_TASKGROUP_DATA:
                Log.d("match", "1");
                Repository repository = Repository.getRepository();
                cursor = taskGroupDao.cursorGetAll();

                if (getContext() != null) {
                    cursor.setNotificationUri(getContext().getContentResolver(), uri);
                }
                return cursor;
            case UriMatcher.NO_MATCH:
                Log.d("TAG", "noquery");
                return null;
            default:
                throw new IllegalArgumentException
                        ("Unknown URI: " + uri);
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        return 0;
    }
}
