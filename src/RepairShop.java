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

public class RepairShop extends JFrame implements ActionListener {
   JLabel id,name,addr,phone,adm_name,adm_email, information; 
    JTextField inid,inname,inaddr,inphone,inadm_name,inadm_email;
    JButton btn_input,btn_del,btn_update;
    JPanel pn, pn_btn, pn_inf;
    
    Statement stmt;
    
    static Connection con_shop;
    
   public RepairShop(Connection con) {   
	   
	   super("ķ��ī�����");
	   
	   pn=new JPanel();
	   pn_btn = new JPanel();
	   pn_inf = new JPanel();
	   
	   con_shop = con;
	   
	   id =new JLabel("����� ��ȣ");
	   name=new JLabel("����� �̸�");
	   addr=new JLabel("����� �ּ�");
	   phone=new JLabel("����� ��ȭ��ȣ");	
	   adm_name=new JLabel("����� �����ڸ�");	 
	   adm_email=new JLabel("����� ������ �̸���");	
	  	   
	   inid=new JTextField();	   
	   inname=new JTextField();	   
	   inaddr=new JTextField();	   
	   inphone=new JTextField();	   
	   inadm_name=new JTextField();	   
	   inadm_email=new JTextField();	
	   
	   information = new JLabel("�����ϴ� ��� ������ ����� ��ȣ�� �Է��ϸ� �����Ϸ�");
	   
	   pn_inf.add(information);
	   
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
	   
	   btn_input.addActionListener(this);
	   btn_del.addActionListener(this);
	   btn_update.addActionListener(this);
	   
	   setBounds(200, 200, 500, 500); 
   }

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			stmt = con_shop.createStatement();
			
			if(e.getSource()==btn_input) {		
				
				try {
					String sql = "insert into RepairShop values("+inid.getText()+",'"+inname.getText()+"'"+",'"+inaddr.getText()+"','"+inphone.getText()+"','"+inadm_name.getText()+"','"+inadm_email.getText()+"')";
		               stmt.executeUpdate(sql);
		               inid.setText("");
		               inname.setText("");
		               inaddr.setText("");
		               inphone.setText("");
		               inadm_name.setText("");
		               inadm_email.setText("");
		               information.setText("�Է� �Ϸ�");
					
				}catch(Exception e1) {
					System.out.println("RepairShop_�Է�_���� �б� ���� :" + e1);
					System.out.println("���� �߻�!"); 
				}	               
			}
			else if(e.getSource() == btn_del) {
				try {  
		            
		            String sql = "delete from repairshop where car_repairshopid = "+inid.getText()+"";
		            stmt.executeUpdate(sql);
		             inid.setText("");
		             inname.setText("");
		             inaddr.setText("");
		             inphone.setText("");
		             inadm_name.setText("");
		             inadm_email.setText("");
		             information.setText("���� �Ϸ�");
		         }catch (Exception e1) {
		               System.out.println("���� �б� ���� :" + e1);
		               System.out.println("���� �߻�!"); 
		         }
			}
			else if(e.getSource() == btn_update) {
				String sql = "update repairshop set car_repairshopid = "+inid.getText()+", repairshop_name = '"+inname.getText()+"', repairshop_addr = '"+inaddr.getText()+"', repairshop_phone = '"+inphone.getText()+"', repairshop_admin_name = '"+inadm_name.getText()+"', repairshop_admin_email = '"+inadm_email.getText()+"' where car_repairshopid = "+inid.getText()+"";
	            stmt.executeUpdate(sql);
	            inid.setText("");
	            inname.setText("");
	            inaddr.setText("");
	            inphone.setText("");
	            inadm_name.setText("");
	            inadm_email.setText("");
			}
				
		} catch (Exception e1) {
            System.out.println("RepairShop ���� �б� ���� :" + e1);
            System.out.println("���� �߻�!"); 
      }
	}
   
}