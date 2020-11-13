import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.*;
import java.util.HashMap;

public class Test {
    //xml파일 가져오기
    WriteXmlQuery query=new WriteXmlQuery();
    HashMap<String,String> map=new HashMap<String, String>();

    //파일 존재여부 확인
    public Boolean fileCheck(File filePath) throws MalformedURLException, IOException {
        //파일 존재여부 확인
        try {
            if(filePath.isFile()){
                map= query.getQuery(filePath.toString());
                return true;
            }else {
                return false;
            }
        } catch (MalformedURLException mue) {
            throw mue;
        } catch (IOException ioe) {
            throw ioe;
        } catch (Exception e) {
            return false;
        }
    }

    //1.insert
    public void insert(String name, String id, String pwd, String addr, String phone) {
        Connection con = JDBCConnection.getConnection();
        PreparedStatement psmt = null;
        String insertSql = map.get("insert");
        try {
            psmt = con.prepareStatement(insertSql);
            psmt.setString(1, name);
            psmt.setString(2, id);
            psmt.setString(3, pwd);
            psmt.setString(4, addr);
            psmt.setInt(5, Integer.parseInt(phone));
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

            //is.isBeforeFirst():커서가 첫 row 바로 앞이면 true,
            //바로 앞이 아니거나 결과row가 없으면 false
            if(!rs.isBeforeFirst()){
                System.out.println("[ 조회된 결과가 없습니다. ]");
            }
            while (rs.next()) {
                int num         = rs.getInt("user_num");
                String name     = rs.getString("user_name");
                String id       = rs.getString("user_id");
                String addr     = rs.getString("user_addr");
                int phone       = rs.getInt("user_phone");
                Date regdate    = rs.getDate("user_regdate");
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
    public void update(String updateNum, String editName, String editAddr, String editPhone) {
        Connection con = JDBCConnection.getConnection();
        PreparedStatement psmt = null;
        String updateSql = map.get("update");
        try {
            psmt = con.prepareStatement(updateSql);
            psmt.setString(1, editName);
            psmt.setString(2, editAddr);
            psmt.setInt(3, Integer.parseInt(editPhone));
            psmt.setInt(4, Integer.parseInt(updateNum));
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
        Connection con              = null;
        PreparedStatement psmtDel   = null;
        PreparedStatement psmtCopy  = null;

        String deleteDataSql;
        String copyData;
        try{
            con           = JDBCConnection.getConnection();
            deleteDataSql = map.get("deleteData");
            copyData      = map.get("copyData");

            con.setAutoCommit(false);

            psmtDel = con.prepareStatement(deleteDataSql);
            psmtDel.executeUpdate();

            psmtCopy = con.prepareStatement(copyData);
            psmtCopy.executeUpdate();

            con.commit();

            System.out.println("연동완료");
        }catch (SQLException se){
            try {
                con.rollback();
            }catch (SQLException sqle){
                sqle.printStackTrace();
            }
            se.printStackTrace();
        }finally {
            JDBCConnection.getClose(psmtCopy);
            JDBCConnection.getClose(con,psmtDel,null);
        }
    }
}


