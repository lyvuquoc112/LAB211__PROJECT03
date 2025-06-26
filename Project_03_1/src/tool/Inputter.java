/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tool;

import java.util.Scanner;
import model.KOL;

/**
 *
 * @author hanly
 */
public class Inputter {

    private Scanner scanner;

    public Inputter() {
        scanner = new Scanner(System.in);
    }

    public String inputString(String mess) {
        System.out.println(mess);
        return scanner.nextLine();

    }

    public int getIntInput(String mess) {
        while (true) {  // Vòng lặp vô hạn
            try {
                System.out.print(mess);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                // Không có return ở đây, nên vòng lặp sẽ tiếp tục
            }
        }
    }

    public double getDoubleInput(String mess) {
        while (true) {  // Vòng lặp vô hạn
            try {
                System.out.print(mess);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                // Không có return ở đây, nên vòng lặp sẽ tiếp tục
            }
        }
    }
    
     public String input(String mess, String errorMess, String regex){
        String input;
        boolean more = false;
        do {            
            input = inputString(mess);
            more = Validator.isValid(input, regex);
            if(!more){
                System.out.println(errorMess+ " Please Re-enter.");
            }
            more = true;
        } while (!more);
        return input;
    }

    public KOL inputKOL(boolean isUpdated, KOL oldKol) {
        KOL kol = new KOL();

        // Nhập ID
        if (!isUpdated) {
            String mess = "Enter KOL ID (e.g., BT123456): ";
            String errorMess = "KOL ID must be 8 characters: first 2 characters are category code (BT, FS, BC, GM, TL), followed by 6 digits!";
            String regex = Validator.ID_REGEX;
            kol.setId(input(mess, errorMess, regex).toUpperCase());
        }else{
            kol.setId(oldKol.getId());
        }
        
        // Nhập name
        boolean more = false;
        do {            
            String mess = isUpdated ? "Input name [" + oldKol.getName() + "]: " : "Input name: ";
            String name = inputString(mess);
            if (isUpdated && name.isEmpty()) {
                kol.setName(oldKol.getName());
                more = true;
            } else if (Validator.isValid(name, Validator.NAME_REGEX)) {
                kol.setName(name);
                more = true;
            } else {
                System.out.println("Name must be between 5 and 30 characters. Please re-enter.");
            }
        } while (!more);
        
        // Nhập phone
        more = false;
        do {            
            String mess = isUpdated ? "Input phone [" + oldKol.getPhone()+ "]: " : "Input phone: ";
            String phone = inputString(mess);
            if (isUpdated && phone.isEmpty()) {
                kol.setPhone(oldKol.getPhone());
                more = true;
            } else if (Validator.isValid(phone, Validator.PHONE_REGEX)) {
                kol.setPhone(phone);
                more = true;
            } else {
                System.out.println("Invalid phone. Please re-enter.");
            }
        } while (!more);
        
        // Nhập email
        more = false;
        do {            
            String mess = isUpdated ? "Input email [" + oldKol.getEmail()+ "]: " : "Input email: ";
            String email = inputString(mess);
            if (isUpdated && email.isEmpty()) {
                kol.setEmail(oldKol.getEmail());
                more = true;
            } else if (Validator.isValid(email, Validator.EMAIL_REGEX)) {
                kol.setEmail(email);
                more = true;
            } else {
                System.out.println("Invalid email. Please re-enter.");
            }
        } while (!more);
        
        // Nhập platform code
        more = false;
        do {            
            String mess = isUpdated ? "Input platform code [" + oldKol.getPlatformCode()+ "]: " : "Input platform code: ";
            String platformCode = inputString(mess);
            if (isUpdated && platformCode.isEmpty()) {
                kol.setPlatformCode(oldKol.getPlatformCode());
                more = true;
            } else if (Validator.isValid(platformCode, Validator.PLATFORM_CODE_REGEX)) {
                kol.setPlatformCode(platformCode);
                more = true;
            } else {
                System.out.println("Invalid platform code. Please re-enter.");
            }
        } while (!more);
        
        // Nhập follower count
        more = false;
        do {            
            String mess = isUpdated ? "Input follower count [" + oldKol.getFollowerCount()+ "]: " : "Input follower count: ";
            String followerCount = inputString(mess);
            if (isUpdated && followerCount.isEmpty()) {
                kol.setFollowerCount(oldKol.getFollowerCount());
                more = true;
            } else if (Validator.isValid(followerCount, Validator.FOLLOWER_COUNT_REGEX)) {
                kol.setPlatformCode(followerCount);
                more = true;
            } else {
                System.out.println("Invalid follower count. Please re-enter.");
            }
        } while (!more);
        
       

        return kol;
    }
}
