import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Test {
    //xml파일 가져오기
    TestXML xml = new TestXML();
    HashMap<String, String> map = new HashMap<String, String>();

    String getTable;
    String getUserNum;
    String getUserName;
    String getUserId;
    String getUserPwd;
    String getUserAddr;
    String getUserPhone;
    String getUserRegdate;

    public Test(){}

    //getXmlFilePath
    public Test(String path) {
        map = xml.selectXML(path);
        getTable = map.get("testTable");
        getUserNum = map.get("userNum");
        getUserName = map.get("userName");
        getUserId = map.get("userId");
        getUserPwd = map.get("userPwd");
        getUserAddr = map.get("userAddr");
        getUserPhone = map.get("userPhone");
        getUserRegdate = map.get("userRegdate");
    }

    //insert
    public void insert(String name, String id, String pwd, String addr, int phone) {
        Connection con = JDBCConnection.getConnection();
        PreparedStatement psmt = null;
        String insertSql = "INSERT INTO " + getTable + " VALUES((SELECT IFNUll(max(" + getUserNum + "),0)+1 " +
                "FROM " + getTable + " AS max_num)," +
                "?,?,?,?,?,NOW())";
        try {
            psmt = con.prepareStatement(insertSql);
            psmt.setString(1, name);
            psmt.setString(2, id);
            psmt.setString(3, pwd);
            psmt.setString(4, addr);
            psmt.setInt(5, phone);
            int insertResult = psmt.executeUpdate();
            if (insertResult > 0) {
                System.out.println("ok");
            } else {
                System.out.println("fail");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.getMessage();
        } finally {
            JDBCConnection.getClose(con, psmt, null);
        }
    }

    //select
    public void select() {
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        String selectSql = "SELECT " + getUserNum + "," + getUserName + ", " + getUserId + ", " + getUserAddr +
                ", " + getUserPhone + ", " + getUserRegdate + " FROM " + getTable + " ORDER BY " + getUserRegdate + " desc";
        try {
            con = JDBCConnection.getConnection();
            psmt = con.prepareStatement(selectSql);
            rs = psmt.executeQuery();
            while (rs.next()) {
                int num = rs.getInt(getUserNum);
                String name = rs.getString(getUserName);
                String id = rs.getString(getUserId);
                String addr = rs.getString(getUserAddr);
                int phone = rs.getInt(getUserPhone);
                Date regdate = rs.getDate(getUserRegdate);
                System.out.println("[" + num + "," + id + "," + name + "," + addr + "," +
                        phone + "," + regdate + "]");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        } finally{
            JDBCConnection.getClose(con, psmt, rs);
        }
    }

    //update
    public void update(int updateNum, String editName, String editAddr, int editPhone) {
        Connection con = JDBCConnection.getConnection();
        PreparedStatement psmt = null;
        String updateSql = "UPDATE " + getTable + " SET " + getUserName + "=?, " +
                getUserAddr + "=?, " + getUserPhone + "=? WHERE " + getUserNum + "=?";
        try {
            psmt = con.prepareStatement(updateSql);
            psmt.setString(1, editName);
            psmt.setString(2, editAddr);
            psmt.setInt(3, editPhone);
            psmt.setInt(4, updateNum);
            int updateResult = psmt.executeUpdate();
            if (updateResult > 0) {
                System.out.println("ok");
            } else {
                System.out.println("fail");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            JDBCConnection.getClose(con, psmt, null);
        }
    }

    //delete
    public void delete(int delNum) {
        Connection con = JDBCConnection.getConnection();
        PreparedStatement psmt = null;
        String deleteSql = "DELETE FROM " + getTable + " WHERE " + getUserNum + "=?";
        //DELETE FROM test_user_info WHERE user_num =?;
        try {
            psmt = con.prepareStatement(deleteSql);
            psmt.setInt(1, delNum);
            int delResult = psmt.executeUpdate();
            if (delResult > 0) {
                System.out.println("ok");
            } else {
                System.out.println("fail");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            JDBCConnection.getClose(con, psmt, null);
        }
    }
}


