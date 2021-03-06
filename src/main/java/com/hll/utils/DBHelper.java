package com.hll.utils;

import com.hll.ch3.utils.PropsUtil;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by hll on 2016/4/11.
 */
public class DBHelper {

  private static Logger logger = LoggerFactory.getLogger(DBHelper.class.getName());

  private static final QueryRunner QUERY_RUNNER = new QueryRunner();

  private static final ThreadLocal<Connection> CONNECTION_HOLDER = new ThreadLocal<>();

  private static final BasicDataSource DATA_SOURCE;

  static {
    Properties conf = PropsUtil.loadProps("jdbc.properties");
    String driver = conf.getProperty("jdbc.driver");
    String url = conf.getProperty("jdbc.url");
    String username = conf.getProperty("jdbc.username");
    String password = conf.getProperty("jdbc.password");

    DATA_SOURCE = new BasicDataSource();
    DATA_SOURCE.setDriverClassName(driver);
    DATA_SOURCE.setUrl(url);
    DATA_SOURCE.setUsername(username);
    DATA_SOURCE.setPassword(password);
  }

  public static Connection getConnection() {
    Connection connection = CONNECTION_HOLDER.get();
    if (connection == null) {
      try {
        connection = DATA_SOURCE.getConnection();
      } catch (SQLException e) {
        logger.error("get connection error", e);
        throw new RuntimeException(e);
      } finally {
        CONNECTION_HOLDER.set(connection);
      }
    }
    return connection;
  }

/*  public static void closeConnection() {
    Connection conn = CONNECTION_HOLDER.get();
    if (conn != null) {
      try {
        conn.close();
      } catch (SQLException e) {
        logger.error("close connection failure", e);
        throw new RuntimeException(e);
      } finally {
        CONNECTION_HOLDER.remove();
      }
    }
  }*/

  /**
   * 查询实体列表
   */
  public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params) {
    List<T> entityList;
    try {
      Connection conn = getConnection();
      entityList = QUERY_RUNNER.query(conn, sql, new BeanListHandler<>(entityClass), params);
    } catch (SQLException e) {
      logger.error("query entity list failure", e);
      throw new RuntimeException(e);
    }
    return entityList;
  }

  /**
   * 查询实体
   */
  public static <T> T queryEntity(Class<T> entityClass, String sql, Object... params) {
    T entity;
    try {
      Connection conn = getConnection();
      entity = QUERY_RUNNER.query(conn, sql, new BeanHandler<>(entityClass), params);
    } catch (SQLException e) {
      logger.error("query entity failure", e);
      throw new RuntimeException(e);
    }
    return entity;
  }

  /**
   * 执行查询语句
   */
  public static List<Map<String, Object>> executeQuery(String sql, Object... params) {
    List<Map<String, Object>> result;
    try {
      Connection conn = getConnection();
      result = QUERY_RUNNER.query(conn, sql, new MapListHandler(), params);

    } catch (SQLException e) {
      logger.error("execute query failure", e);
      throw new RuntimeException(e);
    }
    return result;
  }

  /**
   * 执行更新语句(包括update,insert,delete)
   */
  public static int executeUpdate(String sql, Object... params) {
    int rows;
    try {
      Connection conn = getConnection();
      rows = QUERY_RUNNER.update(conn, sql, params);
    } catch (SQLException e) {
      logger.error("execute update failure", e);
      throw new RuntimeException(e);
    }
    return rows;
  }

  /**
   * 插入实体
   */
  public static <T> boolean insertEntity(Class<T> entityClass, Map<String, Object> fieldMap) {
    if (MapUtils.isEmpty(fieldMap)) {
      logger.error("can not insert entity:fieldMap is empty");
      return false;
    }
    String sql = "insert into " + getTableName(entityClass);
    StringBuilder colomns = new StringBuilder("(");
    StringBuilder values = new StringBuilder("(");
    for (String fieldName : fieldMap.keySet()) {
      colomns.append(fieldName).append(", ");
      values.append("?, ");
    }
    colomns.replace(colomns.lastIndexOf(", "), colomns.length(), ")");
    values.replace(values.lastIndexOf(", "), values.length(), ")");
    sql += colomns + " values " + values;

    Object[] params = fieldMap.values().toArray();
    return executeUpdate(sql, params) == 1;
  }

  /**
   * 更新实体
   */
  public static <T> boolean updateEntity(Class<T> entityClass, long id, Map<String, Object> fieldMap) {
    if (MapUtils.isEmpty(fieldMap)) {
      logger.error("can not update entity:fieldMap is empty");
      return false;
    }
    String sql = "update " + getTableName(entityClass) + " set ";
    StringBuilder columns = new StringBuilder();
    for (String fieldName : fieldMap.keySet()) {
      columns.append(fieldName).append("=?, ");
    }
    sql += columns.substring(0, columns.lastIndexOf(", ")) + " where id=?";

    List<Object> paramList = new ArrayList<>();
    paramList.addAll(fieldMap.values());
    paramList.add(id);
    Object[] params = paramList.toArray();
    return executeUpdate(sql, params) == 1;
  }

  /**
   * 删除实体
   */
  public static <T> boolean deleteEntity(Class<T> entityClass, long id) {
    String sql = "delete from " + getTableName(entityClass) + " where id=?";
    return executeUpdate(sql, id) == 1;
  }

  private static String getTableName(Class<?> entityClass) {
    return entityClass.getSimpleName();
  }


  public static void main(String[] args) throws SQLException {
    int defaultTransactionIsolation = getConnection().getMetaData().getDefaultTransactionIsolation();
    System.out.println(defaultTransactionIsolation);
  }

}
