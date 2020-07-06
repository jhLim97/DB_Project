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
		
		super("고객 관리");
		
		pn=new JPanel();
		pn_btn = new JPanel();
		pn_inf = new JPanel();
		
		con_customer = con;
		id =new JLabel("운전면허증번호");
	   name=new JLabel("고객 이름");
	   addr=new JLabel("고객 주소");
	   phone=new JLabel("고객 전화번호");	   	 
	   email=new JLabel("고객 이메일");	
	  	   
	   inid=new JTextField();	   
	   inname=new JTextField();	   
	   inaddr=new JTextField();	   
	   inphone=new JTextField(); 
	   inemail=new JTextField();	
	   
	   information = new JLabel("삭제시 삭제할 고객 운전면허증 번호만 입력후 삭제버튼 클릭");
	   
	   pn_inf.add(information);
	   
	   btn_input=new JButton("입력");	   
	   btn_del=new JButton("삭제");	   
	   btn_update=new JButton("수정");	   
	      
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
		               information.setText("입력 완료");
					
				}catch(Exception e1) {
					System.out.println("Customer_입력_쿼리 읽기 실패 :" + e1);
					System.out.println("오류 발생!"); 
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
		             information.setText("삭제 완료");
		         }catch (Exception e1) {
		               System.out.println("쿼리 읽기 실패 :" + e1);
		               System.out.println("오류 발생!"); 
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
	            information.setText("수정 완료");
			}
				
		} catch (Exception e1) {
            System.out.println("Customer 쿼리 읽기 실패 :" + e1);
            System.out.println("오류 발생!"); 
      }
	}
	
}
