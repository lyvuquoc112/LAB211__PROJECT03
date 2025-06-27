/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dispatcher;

import business.KOLManager;


/**
 *
 * @author hanly
 */
public class Main {

    public static void main(String[] args) {
        // 1. Khởi tạo các đối tượng quản lý
        KOLManager kolManager = new KOLManager();

        // 2. Khởi tạo và chạy menu
        Menu menu = new Menu(kolManager);
        menu.run();

        // 3. Đóng các tài nguyên
        menu.close();

    }
}
