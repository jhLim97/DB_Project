import java.awt.event.*;
import java.awt.*;
//import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;


public class Search extends JFrame {
	    JButton btn_adm,btn_1,btn_2,btn_3,btn_4; //검색, 검색1,2,3,4
	    JPanel pn_adm;
	    Container DML;
	    JScrollPane scrollPane;
	    ResultSet rs;
	    JTextArea txtResult;
	    
	    Statement stmt;
	    static Connection con_search;
	    
	    
		public Search(Connection con) {	
			 
			  super("검색");
			
			  con_search=con;
			  setBounds(200, 200, 400, 400);
			  pn_adm=new JPanel();
			  
		     
			  btn_adm=new JButton("검색");
		      btn_1=new JButton("검색1");
		      btn_2=new JButton("검색2");
		      btn_3=new JButton("검색3");
		      btn_4=new JButton("검색4");
		      
		      DML = new Container();
		      DML.setLayout(new GridLayout(1,4));
			  DML.add(btn_1);
			  DML.add(btn_2);
			  DML.add(btn_3);
			  DML.add(btn_4);
			  pn_adm.add(DML);
			 
			  
			  txtResult = new JTextArea();
			  txtResult.setEditable(false);
		      JScrollPane scrollPane = new JScrollPane(txtResult);
		      
		      add("Center", scrollPane);
		      add("North",pn_adm);
		      
		      btn_1.addActionListener(new ActionListener_1());
		      btn_2.addActionListener(new ActionListener_2());
		      btn_3.addActionListener(new ActionListener_3());
		      btn_4.addActionListener(new ActionListener_4());
		}
		
		//관리자-검색-검색1버튼
		   private class ActionListener_1 implements ActionListener{//전체 추가
			      public void actionPerformed(ActionEvent e) {
			    	  int rownum;
			         try {  
			            stmt = con_search.createStatement();   
			            txtResult.setText("");
			            String sql = "select custname from customers where cust_licenseid IN (select cust_licenseid from car_rent where car_rent_companyid IN (select car_rent_companyid from car_rent_company where company_name ='캠핑고고')) ";
			            txtResult.setText("");
			            txtResult.setText("<고객 이름>\n");
			            rs=stmt.executeQuery(sql);
			            while(rs.next()) {
			            	
			            	String str = rs.getString(1)  + "\n";
			            	txtResult.append(str);
			            }
			         }catch (Exception e1) {
			               System.out.println("쿼리 읽기 실패 :" + e1);
			               System.out.println("오류 발생!"); 
			         }
			      }
			   }
		   
		   //관리자-검색-검색2버튼
		   private class ActionListener_2 implements ActionListener{//전체 추가
			      public void actionPerformed(ActionEvent e) {
			    	  int rownum;
			    	 
			         try {  
			            stmt = con_search.createStatement();   
			            txtResult.setText("");
			            String sql = "select car_name from car where car_regid IN (select car_regid from car_rent where car_rent_companyid IN (select car_rent_companyid from car_rent_company where company_name LIKE '캠핑%')) ";
			            txtResult.setText("");
			            txtResult.setText("<차 이름>\n");
			            rs=stmt.executeQuery(sql);
			            while(rs.next()) {
			            	
			            	String str = rs.getString(1)  + "\n";
			            	txtResult.append(str);
			            }
			         }catch (Exception e1) {
			               System.out.println("쿼리 읽기 실패 :" + e1);
			               System.out.println("오류 발생!"); 
			         }
			      }
			   }
		 //관리자-검색-검색3버튼
		   private class ActionListener_3 implements ActionListener{//전체 추가
			      public void actionPerformed(ActionEvent e) {
			    	  int rownum;
			    	
			         try {  
			            stmt = con_search.createStatement();   
			            txtResult.setText("");
			            String sql = "select company_name from car_rent_company where car_rent_companyid IN (select car_rent_companyid from car where cumulative_distance >=100000 AND car_regid IN (select car_regid from car_rent where chargeprice >= 450000)) ";
			            txtResult.setText("");
			            txtResult.setText("<회사 이름>\n");
			            rs=stmt.executeQuery(sql);
			            while(rs.next()) {
			            	
			            	String str = rs.getString(1)  + "\n";
			            	txtResult.append(str);
			            }
			         }catch (Exception e1) {
			               System.out.println("쿼리 읽기 실패 :" + e1);
			               System.out.println("오류 발생!"); 
			         }
			      }
			   }
		 //관리자-검색-검색4버튼
		   private class ActionListener_4 implements ActionListener{//전체 추가
			      public void actionPerformed(ActionEvent e) {
			    	  int rownum;
			    	
			         try {  
			            stmt = con_search.createStatement();   
			            txtResult.setText("");
			            String sql = "select repairshop_name from repairshop where car_repairshopid IN (select car_repairshopid from repair_info where repair_price >=150000 AND car_regid IN (select car_regid from car where mfgcompany = 'Daewon')) ";
			            txtResult.setText("");
			            txtResult.setText("<정비소 이름>\n");
			            rs=stmt.executeQuery(sql);
			            while(rs.next()) {
			            	
			            	String str =  rs.getString(1)  + "\n";
			            	txtResult.append(str);
			            }
			         }catch (Exception e1) {
			               System.out.println("쿼리 읽기 실패 :" + e1);
			               System.out.println("오류 발생!"); 
			         }
			      }
			   }
}
