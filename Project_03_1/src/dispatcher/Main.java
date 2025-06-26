/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dispatcher;

import business.KOLManager;
import model.KOL;
import tool.Inputter;

import java.util.List;

/**
 *
 * @author hanly
 */
public class Main {
    public static void main(String[] args) {
        KOLManager kolManager = new KOLManager();
        Inputter inputter = new Inputter();

        while (true) {
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
            String choice = inputter.inputString("");

            switch (choice) {
                case "1":
                    kolManager.addNew(inputter.inputKOL(false, null));
                    break;
                case "2":
                    System.out.println("\n--- Update KOL Information ---");
                    String updateId = inputter.inputString("Enter KOL ID to update: ");
                    KOL oldKOL = kolManager.getKOL(updateId);
                    if (oldKOL == null) {
                        System.out.println("KOL not found!");
                    } else {
                        KOL updatedKOL = inputter.inputKOL(true, oldKOL);
                        kolManager.updateKOL(updatedKOL);
                        System.out.println("Update successful!");
                    }
                    break;
                case "3":
                    System.out.println("\n--- Registered KOL List ---");
                    List<KOL> allKOLs = kolManager.getAllKOLs();
                    if (allKOLs.isEmpty()) {
                        System.out.println("No KOLs registered.");
                    } else {
                        allKOLs.forEach(System.out::println);
                    }
                    break;
                case "4":
                    System.out.println("\n--- Delete KOL ---");
                    String delId = inputter.inputString("Enter KOL ID to delete: ");
                    if (kolManager.deleteKOL(delId)) {
                        System.out.println("Delete successful!");
                    } else {
                        System.out.println("KOL not found!");
                    }
                    break;
                case "5":
                    System.out.println("\n--- Search KOLs by Name ---");
                    String searchName = inputter.inputString("Enter name to search: ");
                    List<KOL> foundKOLs = kolManager.searchByName(searchName);
                    if (foundKOLs.isEmpty()) {
                        System.out.println("No KOLs found with that name.");
                    } else {
                        foundKOLs.forEach(System.out::println);
                    }
                    break;
                case "6":
                    System.out.println("\n--- Filter by Category ---");
                    String category = inputter.inputString("Enter category code (BT, FS, BC, GM, TL): ");
                    List<KOL> filteredKOLs = kolManager.getKOLsByCategory(category);
                    if (filteredKOLs.isEmpty()) {
                        System.out.println("No KOLs found in this category.");
                    } else {
                        filteredKOLs.forEach(System.out::println);
                    }
                    break;
                case "7":
                    System.out.println("\n--- Statistics by Platform ---");
                    kolManager.printStatisticsByPlatform();
                    break;
                case "8":
                    System.out.println("\n--- Save Data to File ---");
                    String fileName = inputter.inputString("Enter file name to save: ");
                    if (kolManager.saveToFile(fileName)) {
                        System.out.println("Data saved successfully!");
                    } else {
                        System.out.println("Failed to save data.");
                    }
                    break;
                case "9":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        }
    }
}
