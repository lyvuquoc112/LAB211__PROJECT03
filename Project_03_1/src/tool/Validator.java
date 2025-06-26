/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tool;

/**
 *
 * @author hanly
 */
public class Validator {
    public static String ID_REGEX ="^(BT|FS|BC|GM|TL)\\d{6}$";   
    public static String NAME_REGEX ="^.{5,30}$";  
    public static String PHONE_REGEX ="^(03|05|07|08|09)\\d{8}$";  
    public static String EMAIL_REGEX ="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";  
    public static String PLATFORM_CODE_REGEX ="^(TK01|FB01|IG01|YT01)$";  
    public static String FOLLOWER_COUNT_REGEX ="^\\d{1,10}$";  
    
    public static boolean isValid(String input, String regex) {
        return input.matches(regex);
    }
}
