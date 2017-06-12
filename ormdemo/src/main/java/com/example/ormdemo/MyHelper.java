package com.example.ormdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by YC on 2017/6/5.
 *
 */

public class MyHelper extends OrmLiteSqliteOpenHelper {
    private static String dbName;
    public MyHelper(Context context, String databaseName, int databaseVersion) {
        super(context, databaseName, null, databaseVersion);
        this.dbName = databaseName;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Data.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Data.class, true);
            onCreate(database);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static MyHelper instance;

    public static synchronized MyHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (MyHelper.class) {
                if (instance == null) {
                    instance = new MyHelper(context, dbName, 1);
                }
            }
        }
        return instance;
    }

    private Dao<Data, Integer> dao;
    public Dao<Data, Integer> getDao() throws SQLException {
        if (dao == null) {
            dao = getDao(Data.class);
        }
        return dao;
    }

    @Override
    public void close() {
        super.close();
        dao = null;
    }
}
