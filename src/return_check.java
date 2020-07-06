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
		
		  super("캠핑카 반환");
		  
		  pn=new JPanel();
		  pn_btn = new JPanel();
		  pn_inf = new JPanel();
		  
		  con_return = con;
	      
	      rentid = new JLabel("고유대여번호");
	      regid=new JLabel("캠핑카등록ID");
	      front=new JLabel("앞부분설명");
	      leftside=new JLabel("왼쪽설명");
	      rightside=new JLabel("오른쪽설명");
	      back=new JLabel("뒤쪽설명");
	      Is_needrepair=new JLabel("수리필요 여부 (o,x 로 표기)");
	      information = new JLabel("안내사항 칸");
	      
	      pn_inf.add(information);
	      
	      inrentid=new JTextField();
	      inregid=new JTextField();
	      infront=new JTextField();
	      inleftside=new JTextField();
	      inrightside=new JTextField();
	      inback=new JTextField();
	      inIs_needrepair=new JTextField();
	      btn_input=new JButton("입력");
	      
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

	private class ActionListenerInput_check implements ActionListener{//전체 추가
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
	               System.out.println("쿼리 읽기 실패 :" + e1);
	               System.out.println("오류 발생!"); 
	         }
	      }
 }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

