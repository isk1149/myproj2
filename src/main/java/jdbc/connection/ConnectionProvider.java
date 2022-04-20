package jdbc.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*
 * 커넥션을 구할 때 사용하는 클래스
 * 
 * JDBC를 이용해서 데이터베이스를 사용하려면 데이터베이스와 연결된 커넥션을 구해야 한다.
 * java.sql.Connection 타입이 데이터베이스 커넥션을 나타내며,
 * java.sql.DriverManager 클래스가 제공하는 getConnection() 메소드를 사용해서 커넥션을 구할 수 있다
 * DriverManager 클래스는 다음의 두 getConnection() 메소드를 제공하고 있다
 * 1. DriverManager.getConnection(String jdbcURL)
 * 2. DriverManager.getConnection(String jdbcURL, String user, String password)
 * 올바른 매개변수가 전달되면 DriverManager.getConnection() 메소드는 DB와 연결된 Connection 객체를 리턴한다
 * 객체를 생성하지 못하면 SQLException 예외를 발생시킨다
 */
public class ConnectionProvider {

    public static Connection getConnection() throws SQLException {
    	//
        return DriverManager.getConnection("jdbc:apache:commons:dbcp:myproj");
    }
}
