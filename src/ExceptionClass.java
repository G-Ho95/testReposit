import java.util.regex.Pattern;

public class ExceptionClass {
    final String number = "^[0-9]*$";

    boolean result;//결과
    //숫자만 입력
    public boolean onlyNumber(String inputNum){
        return result= Pattern.matches(number,inputNum);
    }
}
