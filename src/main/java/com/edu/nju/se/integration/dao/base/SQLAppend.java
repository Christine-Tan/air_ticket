package com.edu.nju.se.integration.dao.base;

import com.edu.nju.se.integration.tool.MyLogger;
import org.hibernate.Query;
import org.hibernate.Session;


/**
 * Created by darxan on 2017/2/25.
 */
public class SQLAppend {

    private Object table;

    private Object[] joinedTables;

    private int conditionCount;

    private StringBuilder headerSQL;

    private StringBuilder sql;

    private Session session;

    private String order = "";


    public SQLAppend(Session querySession, Object tableName, Object ... joinedTablesName) {
        session = querySession;
        conditionCount = 0;
        joinedTables = joinedTablesName;
        table = tableName;
        sql = new StringBuilder("");

    }

    private static final String joinedTablePrefix = " _joinedTable_";
    private static final String mainTablePrefix = " _mainTable";

    private void addJoined(StringBuilder headerSQL) {
        for (Object joinedTable: joinedTables) {
            headerSQL.append(" join _mainTable."+joinedTable+" _joinedTable_"+joinedTable);
        }
    }

    public Query fetchList() {
        headerSQL = new StringBuilder();
        headerSQL.append("select _mainTable from "+table+" _mainTable ");
        addJoined(headerSQL);
        return getQuery();
    }

    public Query fetchCount() {
        headerSQL = new StringBuilder();
        headerSQL.append("select count(*) from "+table+" _mainTable ");
        addJoined(headerSQL);
        return getQuery();
    }


    private void addConnector() {
        if (conditionCount==0) {
            sql.append(" WHERE ");
        }else {
            sql.append(" AND ");
        }
        conditionCount++;
    }

    private void addSQLCondition(Object column, Object value, String condition) {
        if (column==null||value==null) {
            return;
        }
        if (!column.toString().contains(".")) {
            column = mainTablePrefix+"."+column;
        }else {
            column = joinedTablePrefix+column;
        }
        addConnector();
        sql.append(column);
        sql.append(condition);
        sql.append(value);
    }

    public SQLAppend addEq(Object column, Object value) {

        addSQLCondition(column,value," = ");
        return this;
    }


    public SQLAppend addGT(Object column, Object value) {

        addSQLCondition(column,value," > ");
        return this;
    }

    public SQLAppend addGE(Object column, Object value) {

        addSQLCondition(column,value," >= ");
        return this;
    }

    public SQLAppend addLE(Object column, Object value) {

        addSQLCondition(column, value, " <= ");
        return this;
    }

    public SQLAppend addLT(Object column, Object value) {

        addSQLCondition(column,value," < ");
        return this;
    }

    public SQLAppend addLike(String column, Object value) {
        if (column==null||value==null||value.toString().length()==0){
            return this;
        }
        addSQLCondition(column,"'%"+value+"%'"," like ");
        return this;
    }

    public SQLAppend addOrder(String column, Boolean asc) {
        if (column==null||asc==null) {
            return this;
        }
        if (!column.toString().contains(".")) {
            column = mainTablePrefix+"."+column;
        }else {
            column = joinedTablePrefix+column;
        }
        order = "ORDER by "+column+(asc?" asc ":" desc ");
        return this;
    }

    public SQLAppend appendHQL(String appendString) {
        if (appendString==null) {
            return this;
        }
        addConnector();
        if (!appendString.contains(".")) {
            appendString = mainTablePrefix+table+"."+appendString;
        }else {
            appendString = joinedTablePrefix+appendString;
        }
        sql.append(" ");
        sql.append(appendString);
        sql.append(" ");
        return this;
    }

    public Query getQuery() {
        String hql = headerSQL.toString()+sql.toString()+order;
        MyLogger.log("====================hql:=================");
        MyLogger.log(hql);
        return session.createQuery(hql);
    }
}
