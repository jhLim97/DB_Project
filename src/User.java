
import java.awt.event.*;
import java.awt.*;
//import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTextArea;


public class User extends JFrame {
	
	// �ÿ��� �������̽� �߰�
   JButton btn_user_search,btn_user_choose,btn_user_rent;
   JPanel pn,pn_user;
   JTextArea txtResult;
   
   JLabel ladate,rentid,regid,license,rent_compid,startdate,period,price,price_until,add_content,add_price,re_license,re_regid,re_rent_compid,choose_regid;
   JTextField indate,inrentid,inregid,inlicense,inrent_compid,instartdate,inperiod,inprice,inprice_until,inadd_content,inadd_price,inre_license,inre_regid,inre_rent_compid,inchoose_regid;
   JScrollPane scrollPane;
   Container DML;

   static Connection con_user;
   Statement stmt;
   ResultSet rs;
   
   public User(Connection con) {
      super("�����");
      layInit();
      con_user = con;
      setBounds(200, 200, 800, 700);
      
   }

   public void layInit() {
	   
	  pn=new JPanel();
	  pn_user=new JPanel();
	  
	  ladate=new JLabel("��¥");
	  
	  rentid = new JLabel("�뿩��ȣ");
      regid=new JLabel("���ID");
      license=new JLabel("������");
      rent_compid=new JLabel("�뿩ȸ��ID");
      startdate=new JLabel("������");
      period=new JLabel("������");
      price=new JLabel("û�����");
      price_until=new JLabel("���Ա���");
      add_content=new JLabel("��Ÿû������");
      add_price=new JLabel("��Ÿû�����");
      re_license=new JLabel("������ ��Ȯ��");
      re_regid=new JLabel("���ID ��Ȯ��");
      re_rent_compid=new JLabel("�뿩ȸ��ID ��Ȯ��");
      choose_regid=new JLabel("�������� ���ID");
      
      indate=new JTextField();
      
      inrentid = new JTextField();
      inregid=new JTextField();
      inlicense=new JTextField();
      inrent_compid=new JTextField();
      instartdate=new JTextField();
      inperiod=new JTextField();
      inprice=new JTextField();
      inprice_until=new JTextField();
      inadd_content=new JTextField();
      inadd_price=new JTextField();
      inre_license=new JTextField();
      inre_regid=new JTextField();
      inre_rent_compid=new JTextField();
      inchoose_regid=new JTextField();
      
      btn_user_search=new JButton("�˻�");
      btn_user_choose=new JButton("����");
      btn_user_rent=new JButton("�뿩");
      
      pn_user.add(rentid);
      pn_user.add(inrentid);
      pn_user.add(regid);
      pn_user.add(inregid);
      pn_user.add(license);
      pn_user.add(inlicense);
      pn_user.add(rent_compid);
      pn_user.add(inrent_compid);
      pn_user.add(startdate);
      pn_user.add(instartdate);
      pn_user.add(period);
      pn_user.add(inperiod);
      pn_user.add(price);
      pn_user.add(inprice);
      pn_user.add(price_until);
      pn_user.add(inprice_until);
      pn_user.add(add_content);
      pn_user.add(inadd_content);
      pn_user.add(add_price);
      pn_user.add(inadd_price);
      pn_user.add(re_license);
      pn_user.add(inre_license);
      pn_user.add(re_regid);
      pn_user.add(inre_regid);
      pn_user.add(re_rent_compid);
      pn_user.add(inre_rent_compid);
      
      DML = new Container();
	  DML.setLayout(new GridLayout(1,7));
      DML.add(ladate);
	  DML.add(indate);
      DML.add(btn_user_search);
      
	  DML.add(choose_regid);
	  DML.add(inchoose_regid);
	  DML.add(btn_user_choose);
	  DML.add(btn_user_rent);
      
	  pn.add(DML);
	 // pn.add(date);
	  //pn_user.add(DML);
      
      txtResult = new JTextArea();
	  txtResult.setEditable(false);
      JScrollPane scrollPane = new JScrollPane(txtResult);
      
      pn_user.setLayout(new GridLayout(13,2));
      
      add("Center",scrollPane);
      add("South",pn_user);
      add("North",pn);
      
      pn.setVisible(true); 
      pn_user.setVisible(false);
	  txtResult.setVisible(false);
      
      btn_user_search.addActionListener(new ActionListenersearch_DML());///����� �˻���� ��ư	
      btn_user_choose.addActionListener(new ActionListenerchoose_DML());
      btn_user_rent.addActionListener(new ActionListenerrent_DML());
   }

   

 
   //@Override
 //user section
   //�˻���� -> �뿩���� ���� �� �� �뿩��Ͽ� ������ ����Ⱓ ���� �� ���
   private class ActionListenersearch_DML implements ActionListener{//��ü �߰�
	      public void actionPerformed(ActionEvent e) {
	    	  pn_user.setVisible(false);
	    	  txtResult.setVisible(true);
	    	  int rownum;
	    	  try {  
		            stmt = con_user.createStatement();   
		            txtResult.setText("");
		            String sql = "select * from car where car_regid NOT IN ( select car_regid from car_rent) OR car_regid IN (select car_regid from car_rent where DATEDIFF(STR_TO_DATE('"+indate.getText()+"','%Y-%m-%d'),car_rentperiod)>0)";
		            txtResult.setText("");
		            rs=stmt.executeQuery(sql);
		            txtResult.setText("��Ϲ�ȣ      ����          ����ȣ       �����ο�     ����ȸ��          ��������       ��������Ÿ�          �뿩���          �뿩ȸ��ID    �������          �뿩ȸ��ID��Ȯ��\n");
		            while(rs.next()) {
		            	String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getInt(4) + "\t"+ rs.getString(5) + "\t"+ rs.getInt(6) + "\t"+ rs.getInt(7) + rs.getInt(8) + "\t"+ rs.getInt(9) + "\t" + rs.getString(10) + "\t" + rs.getInt(11) + "\n";
		            	txtResult.append(str);
		            }    
	         }catch (Exception e1) {
	               System.out.println("���� �б� ���� :" + e1);
	               System.out.println("���� �߻�!"); 
	         }
	      }
	   }
   

   //���ñ�� ������ ��ĭ�� �Է��� regid�� �ش��ϴ� �ڵ��� ���� ǥ��
   private class ActionListenerchoose_DML implements ActionListener{//��ü �߰�
	      public void actionPerformed(ActionEvent e) {
	    	  pn_user.setVisible(true);
	    	  txtResult.setVisible(true);
	    	  int rownum;
	         try {  
	            stmt = con_user.createStatement();   
	            txtResult.setText("");
	            String sql = "select * from car where car_regid = "+inchoose_regid.getText()+"";
	            txtResult.setText("");
	            rs=stmt.executeQuery(sql);
	            txtResult.setText("�Ʒ��� ���ID, �뿩ȸ��ID�� �̿��Ͽ� ������ ���� �뿩�Ͻÿ�.\n");
	            while(rs.next()) {
	            	String str0 = "���ID : " + rs.getInt(1) + "\t" + "���� : " + rs.getString(2) + "\t" +  "�뿩ȸ��ID : " + rs.getInt(9)+"\n";
	            	txtResult.append(str0);
	            	String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getInt(4) + "\t"+ rs.getString(5) + "\t"+ rs.getInt(6) + "\t"+ rs.getInt(7) + rs.getInt(8) + "\t"+ rs.getInt(9) + "\t" + rs.getString(10) + "\t" + rs.getInt(11) + "\n";
	            	txtResult.append(str);
	            	
	            }
	            
	         }catch (Exception e1) {
	               System.out.println("���� �б� ���� :" + e1);
	               System.out.println("���� �߻�!"); 
	         }
	      }
	   }
   
   //�뿩���->�뿩 ����� car_rent ���̺� �����߰�
   private class ActionListenerrent_DML implements ActionListener{//��ü �߰�
	      public void actionPerformed(ActionEvent e) {
	    	  pn_user.setVisible(true);
	    	  txtResult.setVisible(true);
	    	  try {  
		            stmt = con_user.createStatement();     
		            String sql = "insert into car_rent values("+inrentid.getText()+",'"+inregid.getText()+"'"+",'"+inlicense.getText()+"','"+inrent_compid.getText()+"','"+instartdate.getText()+"','"+inperiod.getText()+"','"+inprice.getText()+"','"+inprice_until.getText()+"','"+inadd_content.getText()+"','"+inadd_price.getText()+"','"+inre_license.getText()+"','"+inre_regid.getText()+"','"+inre_rent_compid.getText()+"')";
		             int rownum = stmt.executeUpdate(sql);
		             inrentid.setText("");
		             inregid.setText("");
		             inlicense.setText("");
		             inrent_compid.setText("");
		             instartdate.setText("");
		             inperiod.setText("");
		             inprice.setText("");
		             inprice_until.setText("");
		             inadd_content.setText("");
		             inadd_price.setText("");
		             inre_license.setText("");
		             inre_regid.setText("");
		             inre_rent_compid.setText("");
		           
		         }catch (Exception e1) {
		               System.out.println("���� �б� ���� :" + e1);
		               System.out.println("���� �߻�!"); 
		         }
		      }
	      }
	   
}