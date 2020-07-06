import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class Company extends JFrame implements ActionListener{
    JLabel id,name,addr,phone,adm_name,adm_email, information; 
    JTextField inid,inname,inaddr,inphone,inadm_name,inadm_email;
    JButton btn_input,btn_del,btn_update;
    
    JPanel pn, pn_btn, pn_inf;
    
    Statement stmt;
    static Connection con_com;
    
	public Company(Connection con) {	
		
		  super("ķ��ī �뿩ȸ��");
		  
		  pn=new JPanel();
		  pn_btn = new JPanel();
		  pn_inf = new JPanel();
		  
		  con_com = con;
		  
	      id = new JLabel("ȸ�� ��ȣ");
	      name=new JLabel("ȸ���");
	      addr=new JLabel("ȸ�� �ּ�");
	      phone=new JLabel("ȸ�� ��ȭ��ȣ");
	      adm_name=new JLabel("ȸ�� �����ڸ�");
	      adm_email=new JLabel("ȸ�� ������ �̸���");
	      information = new JLabel("�����ϴ� ��� ������ ȸ�� ��ȣ�� �Է��ϸ� �����Ϸ�");
	      
	      pn_inf.add(information);
	      
	      inid=new JTextField();
	      inname=new JTextField();
	      inaddr=new JTextField();
	      inphone=new JTextField();
	      inadm_name=new JTextField();
	      inadm_email=new JTextField();
	      btn_input=new JButton("�Է�");
	      btn_del=new JButton("����");
	      btn_update=new JButton("����");
	      
	      pn.setLayout(new GridLayout(6,2));
		  pn_btn.setLayout(new GridLayout(1,3));
		   
		  pn.add(id);	   
		  pn.add(inid);
		   
		  pn.add(name);	
		  pn.add(inname);
		   
		  pn.add(addr);
		  pn.add(inaddr);	
		   
		  pn.add(phone);	   
		  pn.add(inphone);
		   
		  pn.add(adm_name);	
		  pn.add(inadm_name);
		   
		  pn.add(adm_email);
		  pn.add(inadm_email);
	      
	      pn_btn.add(btn_input);
	      pn_btn.add(btn_del);
	      pn_btn.add(btn_update);
	      
	      add(pn);
		  add("South",pn_btn);
		  add("North",pn_inf);
	     	      
		  btn_input.addActionListener(new ActionListenerInput_com());
	      btn_del.addActionListener(new ActionListenerDel_com());
	      btn_update.addActionListener(new ActionListenerUpdate_com());
	      
	      setBounds(200, 200, 500, 500);
	}

	private class ActionListenerInput_com implements ActionListener{//��ü �߰�
	      public void actionPerformed(ActionEvent e) {
	    	  //com.pn.setVisible(true);
	         try {  
	            stmt = con_com.createStatement();     
	            String sql = "insert into car_rent_company values("+inid.getText()+",'"+inname.getText()+"'"+",'"+inaddr.getText()+"','"+inphone.getText()+"','"+inadm_name.getText()+"','"+inadm_email.getText()+"')";
	             stmt.executeUpdate(sql);
	             inid.setText("");
	             inname.setText("");
	             inaddr.setText("");
	             inphone.setText("");
	             inadm_name.setText("");
	             inadm_email.setText("");
	            
	         }catch (Exception e1) {
	               System.out.println("���� �б� ���� :" + e1);
	               System.out.println("���� �߻�!"); 
	         }
	      }
 }
	private class ActionListenerDel_com implements ActionListener{//��ü �߰�
	      public void actionPerformed(ActionEvent e) {
	    	//  com.pn.setVisible(true);
	         try {  
	            stmt = con_com.createStatement();   
	            String sql = "delete from car_rent_company where car_rent_companyid = "+inid.getText()+"";
	            stmt.executeUpdate(sql);
	            inid.setText("");
	            inname.setText("");
	            inaddr.setText("");
	            inphone.setText("");
	            inadm_name.setText("");
	            inadm_email.setText("");
	         }catch (Exception e1) {
	               System.out.println("���� �б� ���� :" + e1);
	               System.out.println("���� �߻�!"); 
	         }
	      }
	   }
	private class ActionListenerUpdate_com implements ActionListener{//��ü �߰�
	      public void actionPerformed(ActionEvent e) {
	    	 // com.pn.setVisible(true);
	         try {  
	            stmt = con_com.createStatement();     
	            String sql = "update car_rent_company set car_rent_companyid = "+inid.getText()+", company_name = '"+inname.getText()+"', company_addr = '"+inaddr.getText()+"', company_phone = '"+inphone.getText()+"', company_admin_name = '"+inadm_name.getText()+"', company_admin_email = '"+inadm_email.getText()+"' where car_rent_companyid = "+inid.getText()+"";
	            int rownum = stmt.executeUpdate(sql);
	            inid.setText("");
	            inname.setText("");
	            inaddr.setText("");
	            inphone.setText("");
	            inadm_name.setText("");
	            inadm_email.setText("");
	         }catch (Exception e1) {
	               System.out.println("���� �б� ���� :" + e1);
	               System.out.println("���� �߻�!"); 
	         }
	      }
	   }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
		
		 

	


