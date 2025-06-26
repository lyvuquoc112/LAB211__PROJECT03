/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package business;

import java.util.HashMap;
import model.KOL;

/**
 *
 * @author hanly
 */
public class KOLManager implements Workable<KOL, String>{
        private final HashMap<String, KOL> kolMap;
        
        public KOLManager(){
            this.kolMap = new HashMap<>();
        }

        public boolean isDuplicate(KOL k){
            return kolMap.containsKey(k.getId());
    }
    @Override
    public void addNew(KOL k){
        if(!isDuplicate(k)){
            kolMap.put(k.getId(), k);          
        }else{
            System.out.println("Registration failed! KOL ID already exists.");
        }
    }
    
    public void updateKOL(KOL updatedKOL){
        if(updatedKOL == null || !kolMap.containsKey(updatedKOL.getId())){
            System.out.println("KOL not found! Update failed.");
            return;
        }
        kolMap.put(updatedKOL.getId(), updatedKOL);
    }

    @Override
    public KOL findID(String k) {
        return kolMap.get(k);
    }

    public void displayKol(KOL kol){
        System.out.println("KOL ID: "+kol.getId());
        System.out.println("Name: "+kol.getName());
        System.out.println("Phone Number: "+kol.getPhone());
        System.out.println("Email: "+kol.getEmail());
        System.out.println("Platfrom Code: "+kol.getPlatformCode());
        System.out.println("Follower Count: "+kol.getFollowerCount());
        System.out.println("Commission Rate Calculaiton: "+kol.getCommissionRate()+"%");
    }
}
