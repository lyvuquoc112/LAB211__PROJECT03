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

    public int getInt(String mess) {
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

    public double getDouble(String mess) {
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

    public int inputNumber(String mess, String errorMess) {
        do {
            try {
                int number = getInt(mess);
                if (number < 0) {
                    System.out.println(errorMess);
                    continue;
                }
                return number;
            } catch (Exception e) {
                System.out.println("Invalid format. Please check it");
            }
        } while (true);
    }

    public String input(String mess, String errorMess, String regex) {
        String input;
        boolean more = false;
        do {
            input = inputString(mess);
            more = Validator.isValid(input, regex);
            if (!more) {
                System.out.println(errorMess + " Please Re-enter.");
                continue;
            }
            more = true;
        } while (!more);
        return input;
    }

    public KOL inputKOL(boolean isUpdated, KOL oldKol) {
        KOL kol = new KOL();
        String mess;
        String errorMess;
        String regex;

        // Nhập ID
        if (!isUpdated) {
            mess = "Enter KOL ID (e.g., BT123456): ";
            errorMess = "KOL ID must be 8 characters: first 2 characters are category code (BT, FS, BC, GM, TL), followed by 6 digits!";
            regex = Validator.ID_REGEX;
            kol.setId(input(mess, errorMess, regex).toUpperCase());
        } else {
            kol.setId(oldKol.getId());
        }

        // Nhập name
        boolean more = false;
        mess = isUpdated ? "Input name [" + oldKol.getName() + "]: " : "Input name: ";
        errorMess = "Name must be between 5 and 30 characters. Please re-enter.";
        do {

            if (isUpdated) {
                String name = inputString(mess);
                if (name.isEmpty()) {
                    kol.setName(oldKol.getName());
                    more = true;
                } else if (Validator.isValid(name, Validator.NAME_REGEX)) {
                    kol.setName(name);
                    more = true;
                } else {
                    System.out.println(errorMess);
                }
            } else {
                kol.setName(input(mess, errorMess, Validator.NAME_REGEX));
                more = true;
            }
        } while (!more);

        // Nhập phone
        more = false;
        mess = isUpdated ? "Input phone [" + oldKol.getPhone() + "]: " : "Input phone: ";
        errorMess = "Invalid phone. Please re-enter.";
        do {
            if (isUpdated) {
                String phone = inputString(mess);
                if (phone.isEmpty()) {
                    kol.setPhone(oldKol.getPhone());
                    more = true;
                } else if (Validator.isValid(phone, Validator.PHONE_REGEX)) {
                    kol.setPhone(phone);
                    more = true;
                } else {
                    System.out.println(errorMess);
                }
            } else {
                kol.setPhone(input(mess, errorMess, Validator.PHONE_REGEX));
                more = true;
            }
        } while (!more);

        // Nhập email
        more = false;
        mess = isUpdated ? "Input email [" + oldKol.getEmail() + "]: " : "Input email: ";
        errorMess = "Invalid email. Please re-enter.";
        do {
            if (isUpdated) {
                String email = inputString(mess);
                if (email.isEmpty()) {
                    kol.setEmail(oldKol.getEmail());
                    more = true;
                } else if (Validator.isValid(email, Validator.EMAIL_REGEX)) {
                    kol.setEmail(email);
                    more = true;
                } else {
                    System.out.println(errorMess);
                }
            } else {
                kol.setEmail(input(mess, errorMess, Validator.EMAIL_REGEX));
                more = true;
            }

        } while (!more);

        // Nhập platform code
        more = false;
        mess = isUpdated ? "Input platform code(e.g., TK01, FB01, IG01, YT01) [" + oldKol.getPlatformCode() + "]: " : "Input platform code(e.g., TK01, FB01, IG01, YT01): ";
        errorMess = "Invalid platform code. Please re-enter.";
        do {
            if (isUpdated) {
                String platformCode = inputString(mess);
                if (platformCode.isEmpty()) {
                    kol.setPlatformCode(oldKol.getPlatformCode());
                    more = true;
                } else if (Validator.isValid(platformCode, Validator.PLATFORM_CODE_REGEX)) {
                    kol.setPlatformCode(platformCode);
                    more = true;
                } else {
                    System.out.println(errorMess);
                }
            } else {
                kol.setPlatformCode(input(mess, errorMess, Validator.PLATFORM_CODE_REGEX));
                more = true;
            }

        } while (!more);

        // Nhập follower count
        more = false;
        mess = isUpdated ? "Input follower count [" + oldKol.getFollowerCount() + "]: " : "Input follower count: ";
        errorMess = "Invalid follower count. Please re-enter.";
        do {
            if (isUpdated) {
                String followerCount = inputString(mess);
                if (isUpdated && followerCount.isEmpty()) {
                    kol.setFollowerCount(oldKol.getFollowerCount());
                    more = true;
                } else if (Validator.isValid(followerCount, Validator.FOLLOWER_COUNT_REGEX)) {
                    try {
                        kol.setFollowerCount(Integer.parseInt(followerCount));
                        more = true;
                    } catch (Exception e) {
                        System.out.println("Number please");
                    }
                    
                } else {
                    System.out.println(errorMess);
                }
            } else {
                kol.setFollowerCount(inputNumber(mess, errorMess));
                more = true;
            }
        } while (!more);

        return kol;
    }
}
