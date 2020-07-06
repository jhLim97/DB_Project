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
    	super("ķ��ī ����");
    	
    	pn=new JPanel();
		pn_btn = new JPanel();
		pn_inf = new JPanel();
    	
		con_car = con;
		
		regid = new JLabel("ķ��ī ��Ϲ�ȣ");
		name = new JLabel("ķ��ī �̸�");
		num = new JLabel("ķ��ī ������ȣ");
		ridenum = new JLabel("ķ��ī ���� �ο���");
		mfgcompany = new JLabel("����ȸ��");
		mfgyear = new JLabel("��������");
		cumul_distance = new JLabel("���� ����Ÿ�");
		price = new JLabel("ķ��ī �뿩���");
		
		companyid = new JLabel("ķ��ī�뿩ȸ�� ID");
		regdate = new JLabel("ķ��ī �������");
		
		recompanyid = new JLabel("ķ��ī�뿩ȸ�� ID��Ȯ��");
				
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
		
	    btn_input=new JButton("�Է�");
	    btn_del=new JButton("����");
	    btn_update=new JButton("����");
	    
	    
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
           txtResult.setText("ķ��ī ��Ϲ�ȣ	ķ��ī �̸�		ķ��ī ������ȣ    ķ��ī ���� �ο���        ����ȸ��         ���� ����Ÿ�		 ķ��ī �뿩���		ķ��ī�뿩ȸ�� ID	ķ��ī �������\n");
           rs=stmt.executeQuery(sql);
           while(rs.next()) {
           	
           	String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getInt(4) + "\t"+ rs.getString(5) + "\t"+ rs.getInt(6) + "\t"+ rs.getInt(7) + "\t"+ rs.getInt(8) + "\t"+ rs.getInt(9) + "\t"+ rs.getString(10) + "\n";
           	txtResult.append(str);
           }
        }catch (Exception e1) {
              System.out.println("���� �б� ���� :" + e1);
              System.out.println("���� �߻�!"); 
        }
		
	}
    
    private class ActionListenerInput implements ActionListener{//��ü �߰�
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
	               System.out.println("���� �б� ���� :" + e1);
	               System.out.println("���� �߻�!"); 
	         }
	      }
 }
    
    private class ActionListenerDel implements ActionListener{//��ü �߰�
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
	               System.out.println("���� �б� ���� :" + e1);
	               System.out.println("���� �߻�!"); 
	         }
	      }
	   }
    
    private class ActionListenerUpdate implements ActionListener{//��ü �߰�
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
