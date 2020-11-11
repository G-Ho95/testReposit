import java.io.File;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Test {
    //xml파일 가져오기
    WriteXmlQuery query=new WriteXmlQuery();
    HashMap<String,String> map=new HashMap<String, String>();

    //파일 존재여부 확인
    public Boolean fileCheck(File filePath){
        //파일 존재여부 확인
        if(filePath.isFile()){
            map= query.getQuery(filePath.toString());
            return true;
        }else {
            return false;
        }
    }

    //1.insert
    public void insert(String name, String id, String pwd, String addr, int phone) {
        Connection con = JDBCConnection.getConnection();
        PreparedStatement psmt = null;
        String insertSql = map.get("insert");
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
        String selectSql = map.get("select");
        try {
            con = JDBCConnection.getConnection();
            psmt = con.prepareStatement(selectSql);
            rs = psmt.executeQuery();
            while (rs.next()) {
                int num = rs.getInt("user_num");
                String name = rs.getString("user_name");
                String id = rs.getString("user_id");
                String addr = rs.getString("user_addr");
                int phone = rs.getInt("user_phone");
                Date regdate = rs.getDate("user_regdate");
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
        String updateSql = map.get("update");
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
        String deleteSql = map.get("delete");
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

    //tableCopy
    public void copyTable(){
        Connection con=JDBCConnection.getConnection();
        PreparedStatement psmt=null;
        String copyTableSql="";
        try{
            psmt=con.prepareStatement(copyTableSql);
            psmt.executeUpdate();

        }catch (SQLException se){
            se.printStackTrace();
        }finally {
            JDBCConnection.getClose(con,psmt,null);
        }
    }
}


