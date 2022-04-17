//import java.io.*;
//import java.sql.*;

public class test {
    public static void main(String[] args) {
        DB db = new DB();
        System.out.println(db.init());
        System.out.println(db.insertImg("C:\\Users\\Lenovo\\Dan\\Dan\\kmitl\\y1s2\\OOP\\project\\img\\hituesday.jpg", "hituesday"));
        System.out.println(db.getImg("C:\\Users\\Lenovo\\testimg", "hituesday","jpg")); 
    }
}
