
import java.awt.event.*;
import java.awt.*;
//import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTextArea;

public class Main extends JFrame implements ActionListener {
   
   
	//main panel
   JButton btn_adm, btn_user;
   Administrator adm;
   User user;
   
   JPanel pn_main;
  
   //adm panel
      
   static Connection con;
   Statement stmt;
   ResultSet rs;
   String Driver = "";
   String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
   String userid = "madang";
   String pwd = "madang";
   
   public Main() {
      super("캠핑카 대여시스템");
      conDB();
      layInit();
      
      setVisible(true);
      setBounds(300, 300, 300, 150); 
   }

   public void layInit() {
	   
	  btn_adm = new JButton("관리자");
      btn_user = new JButton("고객");
	  
      pn_main = new JPanel();
      adm = new Administrator(con);
      user = new User(con);
      
      pn_main.setLayout(new GridLayout(1,2));
      
      pn_main.add(btn_adm);
      pn_main.add(btn_user);
      add(pn_main);
      
      btn_adm.addActionListener(this);
      btn_user.addActionListener(this);
      
   }

   public void conDB() {
	      try {
	         Class.forName("com.mysql.cj.jdbc.Driver");
	         System.out.println("드라이버 로드 성공");
	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      }
	      
	      try { /* 데이터베이스를 연결하는 과정 */
	          System.out.println("데이터베이스 연결 준비...");
	          con = DriverManager.getConnection(url, userid, pwd);
	          System.out.println("데이터베이스 연결 성공");
	       } catch (SQLException e1) {
	          e1.printStackTrace();
	       }
	   }
   
   @Override
   public void actionPerformed(ActionEvent e) {    
      try {
         if (e.getSource() == btn_adm) {
        	 System.out.println("adm");
        	 adm.setVisible(true);
        	 
         }else if (e.getSource() == btn_user) {
        	 System.out.println("user");
        	 user.setVisible(true);
        	
         }
      } catch (Exception e2) {
         System.out.println("Main 쿼리 읽기 실패 :" + e2);
         System.out.println("오류 발생!"); 
      }
   }

   public static void main(String[] args) {
      Main CampingCarRentalSystem = new Main();
      //BLS.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      //BLS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
}