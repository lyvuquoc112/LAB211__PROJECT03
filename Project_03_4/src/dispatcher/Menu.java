/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dispatcher;

import business.KOLManager;
import java.util.List;
import java.util.Scanner;
import model.KOL;
import tool.Inputter;
import tool.Validator;

/**
 *
 * @author hanly
 */
public class Menu {
    private static final int CONTINUE = 1;
    
    private static final int NEW_REGISTRATION = 1;
    private static final int UPDATE_REGISTRATION_INFORMATION = 2;
    private static final int DISPLAY_REGISTERED_LIST = 3;
    private static final int DELETE_REGISTRATION_INFORMATION = 4;
    private static final int SEARCH_KOLS_BY_NAME = 5;
    private static final int FILTER_DATA_BY_CATEGORY = 6;
    private static final int STATISTICS_OF_REGISTRATION_NUMBER_BY_PLATFORM = 7;
    private static final int SAVE_DATA_TO_REGISTRATION_FILE = 8;
    private static final int EXIT_THE_PROGRAM = 9;

    private static Scanner scanner;
    private static KOLManager kolManager;
    private static Inputter inputter;

    

    public Menu(KOLManager kolManager) {
        this.scanner = new Scanner(System.in);
        this.kolManager = kolManager;
        this.inputter = new Inputter();
    }
    
    private static void showMenu() {
        System.out.println("\n===== KOL Recruitment System =====");
        System.out.println("1. New Registration");
        System.out.println("2. Update Registration Information");
        System.out.println("3. Display Registered List");
        System.out.println("4. Delete Registration Information");
        System.out.println("5. Search KOLs by Name");
        System.out.println("6. Filter Data by Category");
        System.out.println("7. Statistics of Registration Numbers by Platform");
        System.out.println("8. Save Data to File");
        System.out.println("9. Exit");
        System.out.print("Your choice: ");
    }
    
    private static int getMenuChoice() {
        int result;
        do {
            System.out.print("Enter Test Case No. : ");
            try {
                result = Integer.parseInt(scanner.nextLine());
                if(result >= 0 && result <=10){
                    return result;
                }
            } catch (NumberFormatException e) {
                System.out.println("Accept only interger. Please Re-enters");
            }
        } while (true);   
    }
    
     private static void handelNewRegistration() {
        kolManager.addNew(inputter.inputKOL(false, null));
    }

    private static void handelUpdateRegistrationInformation() {
        String kolCode = inputter.input("Input KOL Code", "8 characters, as per the validation rules for registration"
                , Validator.ID_REGEX);
        KOL oldKol = kolManager.findID(kolCode);
        if(oldKol != null){
            System.out.println("Current information: ");
            kolManager.displayKol(oldKol);
            System.out.println("---------------------------------------------------------------");
            kolManager.updateKOL(inputter.inputKOL(true, oldKol));
            System.out.println("Update successful!");
        }else{
            System.out.println("This KOL has not registered yet.");
        }
    }
    
     private static void handelDisplayRegisteredList() {
         kolManager.displayAllKols();
    }
     
     private static void handelDeleteRegistrationInformation() {
         String kolCode = inputter.input("Input KOL Code", "8 characters, as per the validation rules for registration"
                , Validator.ID_REGEX);
        KOL oldKol = kolManager.findID(kolCode);
        if(oldKol != null){
            System.out.println("KOL Details: ");
            System.out.println("----------------------------------------------------------------------------------------------------------");
            kolManager.displayKol(oldKol);
            System.out.println("----------------------------------------------------------------------------------------------------------");
            System.out.println("Are you sure you want to delete this registration?");
            String conformation = inputter.input("Input Y/N", "Invalid", "^[YyNn]$");
            if(conformation.equalsIgnoreCase("Y")){
                kolManager.deleteKOL(oldKol.getId());
                System.out.println("Sucessfully");
            }
        }else{
            System.out.println("This KOL has not registered yet.");
        }
    }
    
     private static void handelSerachKOLSByName() {
          String kolName = inputter.input("Input KOL Name", 
                  "Cannot be empty and length must be between 5 and 30 characters",
                 Validator.NAME_REGEX);
        List<KOL> list = kolManager.filterByName(kolName);
        if(!list.isEmpty()){
            kolManager.displayKolByName(list);
        }else{
            System.out.println("No one matches the search criteria!");
        }
    }
     
    private static void processMenuChoice(int testCase) {
        switch (testCase) {
            case NEW_REGISTRATION:
                handelNewRegistration();
                break;
            case UPDATE_REGISTRATION_INFORMATION:
                handelUpdateRegistrationInformation();
                break;
            case DISPLAY_REGISTERED_LIST:
                handelDisplayRegisteredList();
                break;
            case DELETE_REGISTRATION_INFORMATION:
                handelDeleteRegistrationInformation();
                break;
            case SEARCH_KOLS_BY_NAME:
                handelSerachKOLSByName();
                break;
            case FILTER_DATA_BY_CATEGORY:
                //handelDeleteGuestReservation();
                break;
            case STATISTICS_OF_REGISTRATION_NUMBER_BY_PLATFORM:
                //handelListVacantRooms();
                break;
            case SAVE_DATA_TO_REGISTRATION_FILE:
                //handelMonthlyRevenueReport();
                break;
            case EXIT_THE_PROGRAM:
                //handelRevenueReportByRoomType();
                break;
            default:
                System.out.println("\nInvalid choice! Please try again.");
        }
    }

    public void run() {
        int testCase = EXIT_THE_PROGRAM;
        do {
            showMenu();
            testCase = getMenuChoice();
            processMenuChoice(testCase);
        } while (testCase != EXIT_THE_PROGRAM);
    }
    
    public void close() {
        scanner.close();
    }
}
