import java.awt.event.*;
import java.awt.*;
//import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;


public class Search extends JFrame {
	    JButton btn_adm,btn_1,btn_2,btn_3,btn_4; //�˻�, �˻�1,2,3,4
	    JPanel pn_adm;
	    Container DML;
	    JScrollPane scrollPane;
	    ResultSet rs;
	    JTextArea txtResult;
	    
	    Statement stmt;
	    static Connection con_search;
	    
	    
		public Search(Connection con) {	
			 
			  super("�˻�");
			
			  con_search=con;
			  setBounds(200, 200, 400, 400);
			  pn_adm=new JPanel();
			  
		     
			  btn_adm=new JButton("�˻�");
		      btn_1=new JButton("�˻�1");
		      btn_2=new JButton("�˻�2");
		      btn_3=new JButton("�˻�3");
		      btn_4=new JButton("�˻�4");
		      
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
		
		//������-�˻�-�˻�1��ư
		   private class ActionListener_1 implements ActionListener{//��ü �߰�
			      public void actionPerformed(ActionEvent e) {
			    	  int rownum;
			         try {  
			            stmt = con_search.createStatement();   
			            txtResult.setText("");
			            String sql = "select custname from customers where cust_licenseid IN (select cust_licenseid from car_rent where car_rent_companyid IN (select car_rent_companyid from car_rent_company where company_name ='ķ�ΰ��')) ";
			            txtResult.setText("");
			            txtResult.setText("<�� �̸�>\n");
			            rs=stmt.executeQuery(sql);
			            while(rs.next()) {
			            	
			            	String str = rs.getString(1)  + "\n";
			            	txtResult.append(str);
			            }
			         }catch (Exception e1) {
			               System.out.println("���� �б� ���� :" + e1);
			               System.out.println("���� �߻�!"); 
			         }
			      }
			   }
		   
		   //������-�˻�-�˻�2��ư
		   private class ActionListener_2 implements ActionListener{//��ü �߰�
			      public void actionPerformed(ActionEvent e) {
			    	  int rownum;
			    	 
			         try {  
			            stmt = con_search.createStatement();   
			            txtResult.setText("");
			            String sql = "select car_name from car where car_regid IN (select car_regid from car_rent where car_rent_companyid IN (select car_rent_companyid from car_rent_company where company_name LIKE 'ķ��%')) ";
			            txtResult.setText("");
			            txtResult.setText("<�� �̸�>\n");
			            rs=stmt.executeQuery(sql);
			            while(rs.next()) {
			            	
			            	String str = rs.getString(1)  + "\n";
			            	txtResult.append(str);
			            }
			         }catch (Exception e1) {
			               System.out.println("���� �б� ���� :" + e1);
			               System.out.println("���� �߻�!"); 
			         }
			      }
			   }
		 //������-�˻�-�˻�3��ư
		   private class ActionListener_3 implements ActionListener{//��ü �߰�
			      public void actionPerformed(ActionEvent e) {
			    	  int rownum;
			    	
			         try {  
			            stmt = con_search.createStatement();   
			            txtResult.setText("");
			            String sql = "select company_name from car_rent_company where car_rent_companyid IN (select car_rent_companyid from car where cumulative_distance >=100000 AND car_regid IN (select car_regid from car_rent where chargeprice >= 450000)) ";
			            txtResult.setText("");
			            txtResult.setText("<ȸ�� �̸�>\n");
			            rs=stmt.executeQuery(sql);
			            while(rs.next()) {
			            	
			            	String str = rs.getString(1)  + "\n";
			            	txtResult.append(str);
			            }
			         }catch (Exception e1) {
			               System.out.println("���� �б� ���� :" + e1);
			               System.out.println("���� �߻�!"); 
			         }
			      }
			   }
		 //������-�˻�-�˻�4��ư
		   private class ActionListener_4 implements ActionListener{//��ü �߰�
			      public void actionPerformed(ActionEvent e) {
			    	  int rownum;
			    	
			         try {  
			            stmt = con_search.createStatement();   
			            txtResult.setText("");
			            String sql = "select repairshop_name from repairshop where car_repairshopid IN (select car_repairshopid from repair_info where repair_price >=150000 AND car_regid IN (select car_regid from car where mfgcompany = 'Daewon')) ";
			            txtResult.setText("");
			            txtResult.setText("<����� �̸�>\n");
			            rs=stmt.executeQuery(sql);
			            while(rs.next()) {
			            	
			            	String str =  rs.getString(1)  + "\n";
			            	txtResult.append(str);
			            }
			         }catch (Exception e1) {
			               System.out.println("���� �б� ���� :" + e1);
			               System.out.println("���� �߻�!"); 
			         }
			      }
			   }
}
