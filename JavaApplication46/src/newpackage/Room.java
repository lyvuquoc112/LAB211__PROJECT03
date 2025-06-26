/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newpackage;

/**
 *
 * @author hanly
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Room;

import java.util.HashSet;

import java.io.File;

import java.io.FileReader;


/**
 *
 * @author my-pc
 */
public class Rooms extends HashSet<Room> {

    private String pathFile;
    private boolean saved;

    public Rooms(String pathFile) {
        this.pathFile = pathFile;
        this.readFromFile();
    }

    public void readFromFile() {
        int loaded = 0;
        int failed = 0;
        try {
            File f = new File(pathFile);
            if (!f.exists()) {
                System.err.println("Cannot read data from" + this.pathFile + "Please check it..");
                return;
            }
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String tmp = "";
            while ((tmp = br.readLine()) != null) {
                if(dataToObject(tmp)) {
                    loaded++;
                } else {
                    failed++;
                }
                
            }
            br.close();
            fr.close();
            if(loaded>0){
            System.out.println(loaded + " rooms successfully loaded.");
            }
            if(failed>0){
            System.out.println(failed + " entries failed.");
            }

        } catch (Exception e) {
            System.out.println("Errol!"+e.getMessage());
        }

    }

    public boolean dataToObject(String line) {
        Room room = null;
        String[] arg = line.split(";");
        if (arg.length != 6) {
            return false;
        } else {
            try {
                String roomId = arg[0].trim();
                String roomName = arg[1].trim();
                String type = arg[2].trim();
                Double rate = Double.parseDouble(arg[3].trim());
                int capacity = Integer.parseInt(arg[4].trim());
                String furniture = arg[5].trim();
                if (rate <= 0 ||!arg[3].contains(".")|| capacity <= 0) {
                    return false;
                }

                if (isDuplicate(room)) {
                    return false;
                }
                return this.add(new Room(roomId, roomName, type, rate, capacity, furniture));
                   

            } catch (Exception e) {
                return false;
            }
        }
    }

    public boolean isDuplicate(Room room) {
return(room!=null && this.contains(room));
    }

    public void addNew(Room r) {
        if (!isDuplicate(r)) {
            this.add(r);
            saved = false;
        } else {
            System.err.println("Room " + r.getRoomID() + " is already exist!");
        }
    }

    public Guests searchById(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
