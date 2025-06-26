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
public class KOLManager implements Workable<KOL>{
        private final Map<String, KOL> kolMap;
        
        public KOLManager(){
            this.kolMap = new HashMap<>();
        }

        public boolean isDuplicate(KOL k){
            return kolMap.containsKey(k.getId());
    }
  
    public void addNew(KOL k){
        if(!isDuplicate(k)){
            kolMap.put(k.getId(), k);          
        }
        System.out.println("KOL ID already exists!");
    }
    
}
