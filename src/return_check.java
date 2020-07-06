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

public class return_check extends JFrame implements ActionListener{
	JLabel rentid,regid,front,leftside,rightside,back,Is_needrepair, information; 
    JTextField inrentid,inregid,infront,inleftside,inrightside,inback,inIs_needrepair;
    JButton btn_input;
    JPanel pn, pn_btn, pn_inf;
    
    Statement stmt;
    static Connection con_return;
    
	public return_check(Connection con) {	
		
		  super("ķ��ī ��ȯ");
		  
		  pn=new JPanel();
		  pn_btn = new JPanel();
		  pn_inf = new JPanel();
		  
		  con_return = con;
	      
	      rentid = new JLabel("�����뿩��ȣ");
	      regid=new JLabel("ķ��ī���ID");
	      front=new JLabel("�պκм���");
	      leftside=new JLabel("���ʼ���");
	      rightside=new JLabel("�����ʼ���");
	      back=new JLabel("���ʼ���");
	      Is_needrepair=new JLabel("�����ʿ� ���� (o,x �� ǥ��)");
	      information = new JLabel("�ȳ����� ĭ");
	      
	      pn_inf.add(information);
	      
	      inrentid=new JTextField();
	      inregid=new JTextField();
	      infront=new JTextField();
	      inleftside=new JTextField();
	      inrightside=new JTextField();
	      inback=new JTextField();
	      inIs_needrepair=new JTextField();
	      btn_input=new JButton("�Է�");
	      
	      pn.setLayout(new GridLayout(7,2));
		  pn_btn.setLayout(new GridLayout(1,1));
	      
	      pn.add(rentid);
	      pn.add(inrentid);
	      pn.add(regid);
	      pn.add(inregid);
	      pn.add(front);
	      pn.add(infront);
	      pn.add(leftside);
	      pn.add(inleftside);
	      pn.add(rightside);
	      pn.add(inrightside);
	      pn.add(back);
	      pn.add(inback);
	      pn.add(Is_needrepair);
	      pn.add(inIs_needrepair);
	      
	      pn_btn.add(btn_input);
	      
	      add(pn);
		  add("South",pn_btn);
		  add("North",pn_inf);
	     	      
		  btn_input.addActionListener(new ActionListenerInput_check());
	      
	      setBounds(200, 200, 500, 500);
	}

	private class ActionListenerInput_check implements ActionListener{//��ü �߰�
	      public void actionPerformed(ActionEvent e) {
	    	  //com.pn_adm.setVisible(true);
	         try {  
	            stmt = con_return.createStatement();     
	            String sql = "insert into car_check_info values("+inrentid.getText()+",'"+inregid.getText()+"'"+",'"+infront.getText()+"','"+inleftside.getText()+"','"+inrightside.getText()+"','"+inback.getText()+"','"+inIs_needrepair.getText()+"')";
	             int rownum = stmt.executeUpdate(sql);
	             inrentid.setText("");
	             inregid.setText("");
	             infront.setText("");
	             inleftside.setText("");
	             inrightside.setText("");
	             inback.setText("");
	             inIs_needrepair.setText("");
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

