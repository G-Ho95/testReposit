import java.sql.*;

public class JDBCConnection {
    public static Connection getConnection() { //db연결하기
        Connection con = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://10.1.1.150:3306/testdb";
            String user = "root";
            String pwd = "manager";
            con = DriverManager.getConnection(url, user, pwd);
//            System.out.println("db연결확인");
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return con;
    }

    public static void getClose(Connection con, PreparedStatement psmt, ResultSet rs) { //사용한거 닫기
        try {
            if (con != null) con.close();
            if (psmt !=null) psmt.close();
            if (rs != null) rs.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static void getClose(PreparedStatement psmt){
        try{
            if(psmt!=null) psmt.close();
        }catch (SQLException se){
            se.printStackTrace();
        }
    }
}
