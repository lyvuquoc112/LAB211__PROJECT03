/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package business;

import java.util.HashMap;
import java.util.Map;
import model.KOL;

/**
 *
 * @author hanly
 */
public class KOLManager implements Workable<KOL, String> {

    private final HashMap<String, KOL> kolMap;

    public KOLManager() {
        this.kolMap = new HashMap<>();
    }

    public boolean isDuplicate(KOL k) {
        return kolMap.containsKey(k.getId());
    }

    @Override
    public void addNew(KOL k) {
        if (!isDuplicate(k)) {
            kolMap.put(k.getId(), k);
        } else {
            System.out.println("Registration failed! KOL ID already exists.");
        }
    }

    public void updateKOL(KOL updatedKOL) {
        if (updatedKOL == null || !kolMap.containsKey(updatedKOL.getId())) {
            System.out.println("KOL not found! Update failed.");
            return;
        }
        kolMap.put(updatedKOL.getId(), updatedKOL);
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
            System.out.printf("%-10s | %-32s | %-12s | %-15s | %10d | %10d%\n",
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
    }
}
