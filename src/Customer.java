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

public class Customer extends JFrame implements ActionListener {

	JLabel id,name,addr,phone,email,information;
	JTextField inid,inname,inaddr,inphone,inemail;
	
	JButton btn_input, btn_del, btn_update;
    JPanel pn, pn_btn, pn_inf;
    
    Statement stmt;
    
    static Connection con_customer;
    
	public Customer(Connection con) {
		
		super("�� ����");
		
		pn=new JPanel();
		pn_btn = new JPanel();
		pn_inf = new JPanel();
		
		con_customer = con;
		id =new JLabel("������������ȣ");
	   name=new JLabel("�� �̸�");
	   addr=new JLabel("�� �ּ�");
	   phone=new JLabel("�� ��ȭ��ȣ");	   	 
	   email=new JLabel("�� �̸���");	
	  	   
	   inid=new JTextField();	   
	   inname=new JTextField();	   
	   inaddr=new JTextField();	   
	   inphone=new JTextField(); 
	   inemail=new JTextField();	
	   
	   information = new JLabel("������ ������ �� ���������� ��ȣ�� �Է��� ������ư Ŭ��");
	   
	   pn_inf.add(information);
	   
	   btn_input=new JButton("�Է�");	   
	   btn_del=new JButton("����");	   
	   btn_update=new JButton("����");	   
	      
	   pn.setLayout(new GridLayout(5,2));
	   pn_btn.setLayout(new GridLayout(1,3));
	   
	   pn.add(id);	   
	   pn.add(inid);
	   
	   pn.add(name);	
	   pn.add(inname);
	   
	   pn.add(addr);
	   pn.add(inaddr);	
	   
	   pn.add(phone);	   
	   pn.add(inphone);
	   
	   pn.add(email);
	   pn.add(inemail);
	  
	   pn_btn.add(btn_input);	   
	   pn_btn.add(btn_del);	   
	   pn_btn.add(btn_update); 
	   
	   add(pn);
	   add("South",pn_btn);
	   add("North",pn_inf);
	   
	   btn_input.addActionListener(this);
	   btn_del.addActionListener(this);
	   btn_update.addActionListener(this);
	   
	   setBounds(200, 200, 500, 500); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			stmt = con_customer.createStatement();
			
			if(e.getSource()==btn_input) {		
				
				try {
					String sql = "insert into customers values("+inid.getText()+",'"+inname.getText()+"'"+",'"+inaddr.getText()+"','"+inphone.getText()+"','"+inemail.getText()+"')";
		               stmt.executeUpdate(sql);
		               inid.setText("");
		               inname.setText("");
		               inaddr.setText("");
		               inphone.setText("");
		               inemail.setText("");
		               information.setText("�Է� �Ϸ�");
					
				}catch(Exception e1) {
					System.out.println("Customer_�Է�_���� �б� ���� :" + e1);
					System.out.println("���� �߻�!"); 
				}	               
			}
			else if(e.getSource() == btn_del) {
				try {  
		            
		            String sql = "delete from customers where cust_licenseid = "+inid.getText()+"";
		            stmt.executeUpdate(sql);
		             inid.setText("");
		             inname.setText("");
		             inaddr.setText("");
		             inphone.setText("");
		             inemail.setText("");
		             information.setText("���� �Ϸ�");
		         }catch (Exception e1) {
		               System.out.println("���� �б� ���� :" + e1);
		               System.out.println("���� �߻�!"); 
		         }
			}
			else if(e.getSource() == btn_update) {
				String sql = "update customers set cust_licenseid = "+inid.getText()+", custname = '"+inname.getText()+"', custaddr = '"+inaddr.getText()+"', custphone = '"+inphone.getText()+"', cust_email = '"+inemail.getText()+"' where cust_licenseid = "+inid.getText()+"";
	            stmt.executeUpdate(sql);
	            inid.setText("");
	            inname.setText("");
	            inaddr.setText("");
	            inphone.setText("");
	            inemail.setText("");
	            information.setText("���� �Ϸ�");
			}
				
		} catch (Exception e1) {
            System.out.println("Customer ���� �б� ���� :" + e1);
            System.out.println("���� �߻�!"); 
      }
	}
	
}
