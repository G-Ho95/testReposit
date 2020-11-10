import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TestMain {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Test crud=new Test();

        while (true) {
            System.out.println("==============================");
            System.out.println("1.추가  2.조회  3.수정  4.삭제  5.종료");
            System.out.print("번호입력:");
            int btNum = scan.nextInt();
            if (btNum == 5) {
                System.out.println("★종료★");
                break;
            }
            switch (btNum) {
                case 1:
                    System.out.print("이름:");
                    String name=scan.next();
                    System.out.print("아이디");
                    String id= scan.next();
                    System.out.print("비밀번호:");
                    String pwd= scan.next();
                    System.out.print("주소:");
                    String addr= scan.next();
                    System.out.print("전화번호:");
                    int phone= scan.nextInt();
                    crud.insert(name,id,pwd,addr,phone);
                    break;
                case 2:
                    crud.select();
                    break;
                case 3:
                    System.out.print("수정할번호:");
                    int updateNum=scan.nextInt();
                    System.out.print("이름:");
                    String editName=scan.next();
                    System.out.print("주소:");
                    String editAddr=scan.next();
                    System.out.print("번호:");
                    int editPhone= scan.nextInt();
                    crud.update(updateNum,editName,editAddr,editPhone);
                    break;
                case 4:
                    System.out.print("삭제할 번호:");
                    int delNum=scan.nextInt();
                    crud.delete(delNum);
                    break;
                default:
                    System.out.println("다시입력");
                    break;
            }
        }
    }
}
