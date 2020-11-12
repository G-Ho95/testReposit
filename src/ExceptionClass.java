import java.util.regex.Pattern;

public class ExceptionClass {
    final String number = "^[0-9]*$"; //숫자만 입력
    final String kr = "^[가-힣]*$"; //한글만 입력
    final String eng = "^[a-z]*$";//영소문자만 입력


    //숫자만 입력
    public boolean onlyNumber(String inputNum) {
        return Pattern.matches(number, inputNum);
    }

    //insert 유효성 검사
    public StringBuffer insertCheck(String name, String id, String pwd, String addr, String phone) {
        StringBuffer msg = new StringBuffer();
        if (!(Pattern.matches(kr, name)) || !(name.length() >= 2 || name.length() <= 4)) {
            //이름검사:한글만,2~4자리
            msg.append("error:이름은 한글 2~4자리로 입력해주세요.");
            msg.append(System.getProperty("line.separator"));//줄바꿈
        }
        if (!(Pattern.matches(eng, id) || !(id.length() >= 5 || id.length() <= 15))) {
            //아이디검사:영어+숫자만(5자리 이상~15자리 이하)
            msg.append("error:아이디는 영소문자 5~15자리 사이로 입력해주세요.");
            msg.append(System.getProperty("line.separator"));//줄바꿈
        }
        if (!(Pattern.matches(number, phone)) || !(phone.length() == 11)) {
            //hp검사:숫자만 11자리
            msg.append("error:핸드폰 번호는 11자리의 숫자만 입력해주세요.");
            msg.append(System.getProperty("line.separator"));
        }
        if (!(Pattern.matches(number, pwd) || !(pwd.length() >= 4 || pwd.length() <= 10))) {
            //비밀번호 검사:비밀번호는 4~10자리의 숫자만
            msg.append("error:비밀번호는 4~10자리의 숫자만 입력해주세요.");
            msg.append(System.getProperty("line.separator"));
        }
        if(!(addr.length()<=50)){
            msg.append("error:주소가 너무 깁니다.");
            msg.append(System.getProperty("Line.separator"));
        }
        return msg;
    }

    // update 유효성검사
    public StringBuffer updateCheck(String num, String name, String addr, String phone){
        StringBuffer msg=new StringBuffer();
        if (!(Pattern.matches(number, phone))) {
            //번호 검사
            msg.append("error:번호는 숫자만 입력해주세요.");
            msg.append(System.getProperty("line.separator"));
        }
        if (!(Pattern.matches(kr, name)) || !(name.length() >= 2 || name.length() <= 4)) {
            //이름검사:한글만,2~4자리
            msg.append("error:이름은 한글 2~4자리로 입력해주세요.");
            msg.append(System.getProperty("line.separator"));
        }
        if (!(Pattern.matches(number, phone)) || !(phone.length() == 11)) {
            //hp검사:숫자만 11자리
            msg.append("error:핸드폰 번호는 11자리의 숫자만 입력해주세요.");
            msg.append(System.getProperty("line.separator"));
        }
        if(!(addr.length()<=50)){
            msg.append("error:주소가 너무 깁니다.");
            msg.append(System.getProperty("Line.separator"));
        }
        return msg;
    }
}