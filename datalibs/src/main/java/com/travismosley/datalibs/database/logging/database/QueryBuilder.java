package com.travismosley.datalibs.database.logging.database;

import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import java.util.List;

/**
 * A base query builder, with some methods simplifying defining sort-order,
 * grouping and columns at a class level
 *
 * Sub-classes can override getGroupBy and getOrderBy to define those clauses,
 * and can override getColumns to define the column list.
 *
 */
public abstract class QueryBuilder {

    private final String LOG_TAG = QueryBuilder.class.getSimpleName();

    private SQLiteQueryBuilder mQueryBuilder;
    private final String[] mColumns;

    public QueryBuilder(){
        mQueryBuilder = new SQLiteQueryBuilder();
        mQueryBuilder.setTables(getTableName());
        List<String> columns = getColumns();
        mColumns = new String[columns.size()];
        columns.toArray(mColumns);
    }

    protected abstract String getTableName();
    protected abstract List<String> getColumns();

    protected String getGroupBy(){
        return null;
    }

    protected String getOrderBy(){
        return null;
    }

    protected String queryWhere(String whereClause){
        String query = mQueryBuilder.buildQuery(
                mColumns,                          // SELECT <List>
                whereClause,                       // WHERE <String>
                getGroupBy(),                      // GROUP BY <String>
                null,                              // HAVING <String>
                getOrderBy(),                      // ORDER BY <String>
                null                               // LIMIT <String>
        );
        Log.d(LOG_TAG, "Built query: " + query);
        return query;
    }

    public String queryAll(){
        // passing null returns all rows
        return queryWhere(null);
    }

}
