/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package business;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.KOL;
import tool.Inputter;

/**
 *
 * @author hanly
 */
public class KOLManager implements Workable<KOL, String> {
    private boolean saved;
    private String pathFile;
    private final HashMap<String, KOL> kolMap;
    private Inputter inputter;

    public KOLManager() {
        this.kolMap = new HashMap<>();
    }

    public KOLManager(String pathFile, HashMap<String, KOL> kolMap) {
        this.pathFile = pathFile;
        this.kolMap = kolMap;
    }
    
    

    public boolean isDuplicate(KOL k) {
        return kolMap.containsKey(k.getId());
    }

    @Override
    public void addNew(KOL k) {
        if (!isDuplicate(k)) {
            kolMap.put(k.getId(), k);
            saved = false;
        } else {
            System.out.println("Registration failed! KOL ID already exists.");
        }
    }

    public void updateKOL(KOL updatedKOL) {
        kolMap.put(updatedKOL.getId(), updatedKOL);
        saved = false;
    }

    @Override
    public KOL findID(String k) {
        return kolMap.get(k);
    }

    public void displayKol(KOL kol) {
        System.out.println("KOL ID: " + kol.getId());
        System.out.println("Name: " + kol.getName());
        System.out.println("Phone Number: " + kol.getPhone());
        System.out.println("Email: " + kol.getEmail());
        System.out.println("Platfrom Code: " + getPlatformName(kol.getPlatformCode()));
        System.out.println("Follower Count: " + String.format("%,d", kol.getFollowerCount()));
        System.out.println("Commission Rate Calculaiton: " + kol.getCommissionRate() + "%");
    }

    public void displayAllKols() {
        if (kolMap.isEmpty()) {
            System.out.println("No KOLs have registered yet");
        }
        System.out.println("Registered KOLs:");
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("%-10s | %-32s | %-12s | %-15s | %-10s | %-10s%n",
                "KOL ID", "Name", "Phone", "Platform", "Followers", "Commission");
        System.out.println("--------------------------------------------------------------------------");
        
        for (KOL kol : kolMap.values()) {
            System.out.printf("%-10s | %-32s | %-12s | %-15s | %10d | %10d%%%n",
                    kol.getId(),
                    kol.getName(),
                    kol.getPhone(),
                    getPlatformName(kol.getPlatformCode()),
                    kol.getFollowerCount(),
                    kol.getCommissionRate());
        }
        System.out.println("--------------------------------------------------------------------------");
    }

    private static final Map<String, String> PLATFORM_MAP = new HashMap<>();

    static {
        PLATFORM_MAP.put("TK01", "TikTok");
        PLATFORM_MAP.put("FB01", "Facebook");
        PLATFORM_MAP.put("IG01", "Instagram");
        PLATFORM_MAP.put("YT01", "YouTube");
    }

    private static String getPlatformName(String code) {
        return PLATFORM_MAP.getOrDefault(code, code);
    }
    
    public void deleteKOL(String ID){
        kolMap.remove(ID);
        saved = false;
    }
    
    // không nhất thiết phải nhập đầy đủ họ tên, có thể chỉ nhập một phần thôi
    public List<KOL> filterByName(String name){
        List<KOL> result = new ArrayList<>();
        for (KOL kol : kolMap.values()) {
            if(kol.getName().toUpperCase().contains(name.toUpperCase().trim())){
                result.add(kol);
            }
        }
        return result;
    }
    
    public void displayKolByName(List<KOL> list) {
        System.out.println("Matching KOLs:");
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("%-10s | %-32s | %-12s | %-15s | %-10s | %-10s%n",
                "KOL ID", "Name", "Phone", "Platform", "Followers", "Commission");
        System.out.println("--------------------------------------------------------------------------");
        for (KOL kol : list) {
            System.out.printf("%-10s | %-32s | %-12s | %-15s | %10d | %10d%%%n",
                    kol.getId(),
                    kol.getName(),
                    kol.getPhone(),
                    getPlatformName(kol.getPlatformCode()),
                    kol.getFollowerCount(),
                    kol.getCommissionRate());
        }
        System.out.println("--------------------------------------------------------------------------");
    }

    public List<KOL> filterByCatergory(String catergoryCode) {
        List<KOL> result = new ArrayList<>();
        for (KOL kol : kolMap.values()) {
            if(kol.getId().toUpperCase().startsWith(catergoryCode.toUpperCase().trim())){
                result.add(kol);
            }
        }return result;
    }
   
    public void displayKOLListByCategory(List<KOL> list, String categoryCode) {
        System.out.println("Registered KOLs Under " + getCategoryName(categoryCode) + " Category (" + categoryCode.toUpperCase() + "):");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%-10s | %-13s | %-10s | %-9s | %-10s | %-10s%n",
                "KOL ID", "Name", "Phone", "Platform", "Followers", "Commission");
        System.out.println("-----------------------------------------------------------------------------");
        for (KOL kol : list) {
            System.out.printf("%-10s | %-13s | %-10s | %-9s | %10d | %8d%%%n",
                    kol.getId(),
                    kol.getName(),
                    kol.getPhone(),
                    getPlatformName(kol.getPlatformCode()),
                    kol.getFollowerCount(),
                    kol.getCommissionRate());
        }
        System.out.println("-----------------------------------------------------------------------------");
    }

    private String getCategoryName(String code) {
        switch (code.toUpperCase()) {
            case "BT":
                return "Beauty";
            case "FS":
                return "Fashion";
            case "BC":
                return "Broadcasting";
            case "GM":
                return "Gaming";
            case "TL":
                return "Travel";
            default:
                return code;
        }
    }
    
    public void displayPlatformStatistics() {
        // Map<PlatformCode, [count, sumCommission]>
        Map<String, int[]> result = new HashMap<>();
        for (KOL kol : kolMap.values()) {
            String code = kol.getPlatformCode();
            int[] arr = result.getOrDefault(code, new int[2]);
            arr[0]++; // đếm số lượng kol 
            arr[1] += kol.getCommissionRate(); // sum commission
            result.put(code, arr);
        }

        if (result.isEmpty()) {
            System.out.println("No KOLs have registered yet.");
            return;
        }

        System.out.println("Statistics of Registration by Platform:");
        System.out.println("-----------------------------------------------------------------");
        System.out.printf("%-15s | %-15s | %-20s%n", "Platform", "Number of KOLs", "Avg. Commission Rate");
        System.out.println("-----------------------------------------------------------------");
        for (String code : result.keySet()) {
            int count = result.get(code)[0];
            int sumCommission = result.get(code)[1];
            double avgCommission = (double) sumCommission / count;
            String platformName = getPlatformName(code);
            System.out.printf("%-15s (%s) | %-15d | %19.1f%%%n",
                    platformName, code, count, avgCommission);
        }
        System.out.println("-----------------------------------------------------------------");
    }
    
     public void saveDataRegistrationToFile() {
        if(this.saved){
            return;
        }
        try {
            // Tạo file để lưu dữ liệu
            File f = new File(pathFile);
            if(!f.exists()){
                f.createNewFile();
            }
            // Tạo ObjectOutputStream để ghi dữ liệu
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))) {
                // Ghi từng guest
                oos.writeObject(new ArrayList<>(kolMap.values()));
                System.out.println("Guest information has been successfully saved to "+pathFile);
                this.saved = true;
            }
        } catch (IOException e) {
            System.out.println("\"Error saving data: " + e.getMessage());
        }
    }

     public void exit(){
         if (!saved) {
            String confirm = inputter.input("You have unsaved changes. Do you want to save the changes before exiting? (Y/N)", "Invalid", "^[YyNn]$");
            if (confirm.equalsIgnoreCase("Y")) {
                saveDataRegistrationToFile();
                System.out.println("Goodbye!");
            } else {
                String confirm2 = inputter.input("Are you sure you want to exit without saving? (Y/N)", "Invalid", "^[YyNn]$");
                if (confirm2.equalsIgnoreCase("Y")) {
                    System.out.println("Goodbye!");
                }
            }
        } else {
            System.out.println("Goodbye!");
        }
     }
}
