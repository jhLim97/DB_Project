import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Car extends JFrame implements ActionListener{

	JLabel regid, name, num, ridenum, mfgcompany, mfgyear, cumul_distance, price, companyid, regdate, recompanyid; 
	JTextField in_regid, in_name, in_num, in_ridenum, in_mfgcompany, in_mfgyear, in_cumul_distance, in_price, in_companyid, in_regdate,in_recompanyid; 
	
	JButton btn_input, btn_del, btn_update;
    JPanel pn, pn_btn, pn_inf;
    
    JTextArea txtResult;
    ResultSet rs;
    Statement stmt;
    
    static Connection con_car;
    
    public Car(Connection con) {
    	super("캠핑카 관리");
    	
    	pn=new JPanel();
		pn_btn = new JPanel();
		pn_inf = new JPanel();
    	
		con_car = con;
		
		regid = new JLabel("캠핑카 등록번호");
		name = new JLabel("캠핑카 이름");
		num = new JLabel("캠핑카 차량번호");
		ridenum = new JLabel("캠핑카 승차 인원수");
		mfgcompany = new JLabel("제조회사");
		mfgyear = new JLabel("제조연도");
		cumul_distance = new JLabel("누적 주행거리");
		price = new JLabel("캠핑카 대여비용");
		
		companyid = new JLabel("캠핑카대여회사 ID");
		regdate = new JLabel("캠핑카 등록일자");
		
		recompanyid = new JLabel("캠핑카대여회사 ID재확인");
				
		pn.setLayout(new GridLayout(11,2));
	    pn_btn.setLayout(new GridLayout(1,3));
		
		in_regid = new JTextField();
		in_name = new JTextField();
		in_num = new JTextField();
		in_ridenum = new JTextField();
		in_mfgcompany = new JTextField();
		in_mfgyear = new JTextField();
		in_cumul_distance = new JTextField();
		in_price = new JTextField();
		in_companyid = new JTextField();
		in_regdate = new JTextField();
		in_recompanyid=new JTextField();
		
		txtResult = new JTextArea();
	    txtResult.setEditable(false);
		
	    btn_input=new JButton("입력");
	    btn_del=new JButton("삭제");
	    btn_update=new JButton("수정");
	    
	    
	    pn.add(regid);
	    pn.add(in_regid);
	    
	    pn.add(name);
	    pn.add(in_name);
	    
	    pn.add(num);
	    pn.add(in_num);
	    
	    pn.add(ridenum);
	    pn.add(in_ridenum);
	    
	    pn.add(mfgcompany);
	    pn.add(in_mfgcompany);
	    
	    pn.add(mfgyear);
	    pn.add(in_mfgyear);
	    
	    pn.add(cumul_distance);
	    pn.add(in_cumul_distance);
	    
	    pn.add(price);
	    pn.add(in_price);
	    
	    pn.add(companyid);
	    pn.add(in_companyid);
	    
	    pn.add(regdate);
	    pn.add(in_regdate);
	    
	    pn.add(recompanyid);
	    pn.add(in_recompanyid);
	    
	    
	    pn_btn.add(btn_input);
		pn_btn.add(btn_del);
		pn_btn.add(btn_update);
		
		add(pn);
		add("South",pn_btn);
		add("North",pn_inf);
		
		pn_inf.add(txtResult);
		
		setBounds(200, 200, 700, 800);
	    
		btn_input.addActionListener(new ActionListenerInput());
	    btn_del.addActionListener(new ActionListenerDel());
	    btn_update.addActionListener(new ActionListenerUpdate());
	      
	    viewinformation();
	    
    }
    private void viewinformation() {
		
        try {  
           stmt = con_car.createStatement();   
           txtResult.setText("");
           String sql = "select * from car";
           txtResult.setText("");
           txtResult.setText("캠핑카 등록번호	캠핑카 이름		캠핑카 차량번호    캠핑카 승차 인원수        제조회사         누적 주행거리		 캠핑카 대여비용		캠핑카대여회사 ID	캠핑카 등록일자\n");
           rs=stmt.executeQuery(sql);
           while(rs.next()) {
           	
           	String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getInt(4) + "\t"+ rs.getString(5) + "\t"+ rs.getInt(6) + "\t"+ rs.getInt(7) + "\t"+ rs.getInt(8) + "\t"+ rs.getInt(9) + "\t"+ rs.getString(10) + "\n";
           	txtResult.append(str);
           }
        }catch (Exception e1) {
              System.out.println("쿼리 읽기 실패 :" + e1);
              System.out.println("오류 발생!"); 
        }
		
	}
    
    private class ActionListenerInput implements ActionListener{//전체 추가
	      public void actionPerformed(ActionEvent e) {
	    	  //com.pn_adm.setVisible(true);
	         try {  
	            stmt = con_car.createStatement();     
	            String sql = "insert into car values("+in_regid.getText()+",'"+in_name.getText()+"',"+in_num.getText()+",'"+in_ridenum.getText()+"','"+in_mfgcompany.getText()+"','"+in_mfgyear.getText()+"','"+in_cumul_distance.getText()+"',"+in_price.getText()+",'"+in_companyid.getText()+"','"+in_regdate.getText()+"',"+in_recompanyid.getText()+")";
	             stmt.executeUpdate(sql);
	             in_regid.setText("");
	             in_name.setText("");
	             in_num.setText("");
	             in_ridenum.setText("");
	             in_mfgcompany.setText("");
	             in_mfgyear.setText("");
	             in_cumul_distance.setText("");
	             in_price.setText("");
	             in_companyid.setText("");
	             in_regdate.setText("");
	             in_recompanyid.setText("");
	             
	         }catch (Exception e1) {
	               System.out.println("쿼리 읽기 실패 :" + e1);
	               System.out.println("오류 발생!"); 
	         }
	      }
 }
    
    private class ActionListenerDel implements ActionListener{//전체 추가
	      public void actionPerformed(ActionEvent e) {
	    	//  com.pn_adm.setVisible(true);
	         try {  
	            stmt = con_car.createStatement();   
	            String sql = "delete from car where car_regid = "+in_regid.getText()+"";
	            stmt.executeUpdate(sql);
	            in_regid.setText("");
	             in_name.setText("");
	             in_num.setText("");
	             in_ridenum.setText("");
	             in_mfgcompany.setText("");
	             in_mfgyear.setText("");
	             in_cumul_distance.setText("");
	             in_price.setText("");
	             in_companyid.setText("");
	             in_regdate.setText("");
	             in_recompanyid.setText("");
	            
	         }catch (Exception e1) {
	               System.out.println("쿼리 읽기 실패 :" + e1);
	               System.out.println("오류 발생!"); 
	         }
	      }
	   }
    
    private class ActionListenerUpdate implements ActionListener{//전체 추가
	      public void actionPerformed(ActionEvent e) {
	    	 // com.pn_adm.setVisible(true);
	         try {  
	            stmt = con_car.createStatement();     
	            String sql = "update car set car_regid = "+in_regid.getText()+", car_name = '"+in_name.getText()+"', car_num ='"+in_num.getText()+"', car_ridenum = '"+in_ridenum.getText()+"', mfgcompany ='"+in_mfgcompany.getText()+"', mfgyear = '"+in_mfgyear.getText()+"', cumulative_distance = '"+in_cumul_distance.getText()+"', car_rent_price = "+in_price.getText()+", car_rent_companyid = '"+in_companyid.getText()+"', car_regdate = '"+in_regdate.getText()+"', car_rent_company_car_rent_companyid = "+in_recompanyid.getText()+"  where car_regid = "+in_regid.getText()+"";
	            stmt.executeUpdate(sql);
	            in_regid.setText("");
	             in_name.setText("");
	             in_num.setText("");
	             in_ridenum.setText("");
	             in_mfgcompany.setText("");
	             in_mfgyear.setText("");
	             in_cumul_distance.setText("");
	             in_price.setText("");
	             in_companyid.setText("");
	             in_regdate.setText("");
	             in_recompanyid.setText("");
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
