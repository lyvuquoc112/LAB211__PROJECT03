/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Objects;

/**
 *
 * @author hanly
 */
public class KOL {

    private String id;
    private String name;
    private String phone;
    private String email;
    private String platformCode;
    private int followerCount;
    private int commissionRate;

    public KOL() {
    }

    public KOL(String id, String name, String phone, String email, String platformCode, int followerCount) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.platformCode = platformCode;
        this.followerCount = followerCount;
        this.commissionRate = calculateCommissionRate(followerCount);
    }

    private int calculateCommissionRate(int followerCount) {
        return followerCount > 1000000 ? 25 : 20;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
        this.commissionRate = calculateCommissionRate(followerCount);
    }

    public int getCommissionRate() {
        return commissionRate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final KOL other = (KOL) obj;
        return Objects.equals(this.id, other.id);
    }
    
    
}
