package provided.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public abstract class DaoBase {
  protected void startTransaction(Connection conn) throws SQLException {
    conn.setAutoCommit(false); // Start a transaction by disabling auto-commit
  }

  protected void commitTransaction(Connection conn) throws SQLException {
    conn.commit(); // Commit the transaction
  }

  protected void rollbackTransaction(Connection conn) throws SQLException {
    conn.rollback(); // Rollback the transaction
  }

  protected void setParameter(PreparedStatement stmt, int parameterIndex, Object value,
      Class<?> classType) throws SQLException {
    int sqlType = convertJavaClassToSqlType(classType);

    if (Objects.isNull(value)) {
      stmt.setNull(parameterIndex, sqlType); // Handle null parameters
    } else {
      switch (sqlType) {
        case Types.DECIMAL:
          stmt.setBigDecimal(parameterIndex, (BigDecimal) value);
          break;

        case Types.DOUBLE:
          stmt.setDouble(parameterIndex, (Double) value);
          break;

        case Types.INTEGER:
          stmt.setInt(parameterIndex, (Integer) value);
          break;

        case Types.OTHER:
          stmt.setObject(parameterIndex, value);
          break;

        case Types.VARCHAR:
          stmt.setString(parameterIndex, (String) value);
          break;

        default:
          throw new DaoException("Unknown parameter type: " + classType); // Throw exception for unsupported types
      }
    }
  }

  private int convertJavaClassToSqlType(Class<?> classType) {
    if (Integer.class.equals(classType)) {
      return Types.INTEGER;
    }

    if (String.class.equals(classType)) {
      return Types.VARCHAR;
    }

    if (Double.class.equals(classType)) {
      return Types.DOUBLE;
    }

    if (BigDecimal.class.equals(classType)) {
      return Types.DECIMAL;
    }

    if (LocalTime.class.equals(classType)) {
      return Types.OTHER;
    }

    throw new DaoException("Unsupported class type: " + classType.getName()); // Throw exception for unsupported class types
  }

  protected Integer getNextSequenceNumber(Connection conn, Integer id, String tableName,
      String idName) throws SQLException {
    String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE " + idName + " = ?";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      setParameter(stmt, 1, id, Integer.class);

      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          return rs.getInt(1) + 1;
        }

        return 1;
      }
    }
  }

  protected Integer getLastInsertId(Connection conn, String table) throws SQLException {
    String sql = String.format("SELECT LAST_INSERT_ID() FROM %s", table);

    try (Statement stmt = conn.createStatement()) {
      try (ResultSet rs = stmt.executeQuery(sql)) {
        if (rs.next()) {
          return rs.getInt(1);
        }

        throw new SQLException("Unable to retrieve the primary key value. No result set!"); // Handle no result set
      }
    }
  }

  protected <T> T extract(ResultSet rs, Class<T> classType) {
    try {
      Constructor<T> con = classType.getConstructor();
      T obj = con.newInstance(); // Create new instance

      for (Field field : classType.getDeclaredFields()) {
        String colName = camelCaseToSnakeCase(field.getName());
        Class<?> fieldType = field.getType();

        field.setAccessible(true); // Allow access to private fields
        Object fieldValue = null;

        try {
          fieldValue = rs.getObject(colName);
        } catch (SQLException e) {
          // Field not found in result set, ignore
        }

        if (Objects.nonNull(fieldValue)) {
          if (fieldValue instanceof Time && fieldType.equals(LocalTime.class)) {
            fieldValue = ((Time) fieldValue).toLocalTime();
          } else if (fieldValue instanceof Timestamp && fieldType.equals(LocalDateTime.class)) {
            fieldValue = ((Timestamp) fieldValue).toLocalDateTime();
          }

          field.set(obj, fieldValue); // Set field value
        }
      }

      return obj;

    } catch (Exception e) {
      throw new DaoException("Unable to create object of type " + classType.getName(), e); // Handle object creation error
    }
  }

  private String camelCaseToSnakeCase(String identifier) {
    StringBuilder nameBuilder = new StringBuilder();

    for (char ch : identifier.toCharArray()) {
      if (Character.isUpperCase(ch)) {
        nameBuilder.append('_').append(Character.toLowerCase(ch));
      } else {
        nameBuilder.append(ch);
      }
    }

    return nameBuilder.toString();
  }

  @SuppressWarnings("serial")
  static class DaoException extends RuntimeException {

    public DaoException(String message, Throwable cause) {
      super(message, cause);
    }

    public DaoException(String message) {
      super(message);
    }
  }
}