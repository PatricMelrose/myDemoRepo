package com.akimov.helloworldrxjava.data.db.storio;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;

import com.akimov.helloworldrxjava.presentation.model.StockUpdate;
import com.pushtorefresh.storio3.sqlite.SQLiteTypeMapping;
import com.pushtorefresh.storio3.sqlite.StorIOSQLite;
import com.pushtorefresh.storio3.sqlite.impl.DefaultStorIOSQLite;
import com.pushtorefresh.storio3.sqlite.operations.delete.DefaultDeleteResolver;
import com.pushtorefresh.storio3.sqlite.operations.delete.DeleteResolver;
import com.pushtorefresh.storio3.sqlite.operations.get.DefaultGetResolver;
import com.pushtorefresh.storio3.sqlite.operations.get.GetResolver;
import com.pushtorefresh.storio3.sqlite.queries.DeleteQuery;

public class StorIOFactory {
  private static StorIOSQLite INSTANCE;

  public synchronized static StorIOSQLite get(Context context) {
    if (INSTANCE != null) {
      return INSTANCE;
    }
    INSTANCE = DefaultStorIOSQLite.builder()
        .sqliteOpenHelper(new StorIODBHelper(context))
        .addTypeMapping(StockUpdate.class, SQLiteTypeMapping.<StockUpdate>builder()
            .putResolver(new StockUpdatePutResolver())
            .getResolver(createGetResolver())
            .deleteResolver(createDeleteResolver()).build())
        .build();
    return INSTANCE;
  }

  private static DeleteResolver<StockUpdate> createDeleteResolver() {

    return new DefaultDeleteResolver<StockUpdate>() {
      @NonNull
      @Override
      protected DeleteQuery mapToDeleteQuery(@NonNull StockUpdate object) {
        return null;
      }
    };
  }

  private static GetResolver<StockUpdate> createGetResolver() {
    return new DefaultGetResolver<StockUpdate>() {
      @NonNull
      @Override
      public StockUpdate mapFromCursor(@NonNull StorIOSQLite storIOSQLite, @NonNull Cursor cursor) {
        return null;
      }
    };
  }
}
