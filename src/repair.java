import java.awt.Container;
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

public class repair extends JFrame implements ActionListener{
	    JLabel num,regid,shopid,car_rent_compid,license,content,date,price,deadline,add_content,re_regid,re_car_rent_compid,re_license,re_shopid; 
	    JTextField innum,inregid,inshopid,incar_rent_compid,inlicense,incontent,inprice,indeadline,inadd_content,inre_regid,inre_car_rent_compid,inre_license,inre_shopid,car_information; 
	    JTextField in_repair_Y, in_repair_M, in_repair_D;
	    JTextField in_money_Y, in_money_M, in_money_D;
	    JButton btn_input,btn_del,btn_update;
	    JPanel pn, pn_btn, pn_inf;
	    JTextArea txtResult;
	    
	    Container Repairdate;
	    Container Moneydate;
	    
	    Statement stmt;
	    static Connection con_repair;
	    ResultSet rs;
		public repair(Connection con) {	
			
			  super("캠핑카 수리");
			  
			  con_repair = con;
			
			  pn=new JPanel();
			  
			  pn_btn = new JPanel();
			  pn_inf = new JPanel();
		      
		      num = new JLabel("정비번호");
		      regid=new JLabel("차 등록번호");
		      shopid=new JLabel("정비소ID");
		      car_rent_compid=new JLabel("대여회사ID");
		      license=new JLabel("면허증");
		      content=new JLabel("정비내역");
		      date = new JLabel("수리날짜");
			  price=new JLabel("수리비용");
			  deadline=new JLabel("납입기한");
			  add_content=new JLabel("기타정비내역");
			  re_regid=new JLabel("차 등록번호 재확인");
			  re_car_rent_compid=new JLabel("대여회사ID 재확인");
		      re_license=new JLabel("면허증 재확인");
		      re_shopid=new JLabel("정비소ID 재확인");
		      
		      innum = new JTextField();
		      inregid=new JTextField();
		      inshopid=new JTextField();
		      incar_rent_compid=new JTextField();
		      inlicense=new JTextField();
		      incontent=new JTextField();
		      
		      in_repair_Y = new JTextField();
		      in_repair_M = new JTextField();
		      in_repair_D = new JTextField();
		      
		      in_money_Y = new JTextField();
		      in_money_M = new JTextField();
		      in_money_D = new JTextField();
		      
		      Repairdate = new Container();
		      Repairdate.setLayout(new GridLayout(1,3));
		      
		      Moneydate = new Container();
		      Moneydate.setLayout(new GridLayout(1,3));
		      
			  inprice=new JTextField();
			  indeadline=new JTextField();
			  inadd_content=new JTextField();
			  inre_regid=new JTextField();
			  inre_car_rent_compid=new JTextField();
		      inre_license=new JTextField();
		      inre_shopid=new JTextField();

		      btn_input=new JButton("입력");
		      btn_del=new JButton("삭제");
		      btn_update=new JButton("수정");
		      
		      txtResult = new JTextArea();
		      txtResult.setEditable(false);
		      
		      pn.setLayout(new GridLayout(14,2));
		      pn_btn.setLayout(new GridLayout(1,3));
		      
		      pn.add(num);
		      pn.add(innum);
		      pn.add(regid);
		      pn.add(inregid);
		      pn.add(shopid);
		      pn.add(inshopid);
		      pn.add(car_rent_compid);
		      pn.add(incar_rent_compid);
		      pn.add(license);
		      pn.add(inlicense);
		      pn.add(content);
		      pn.add(incontent);
		      
		      Repairdate.add(in_repair_Y);
		      Repairdate.add(in_repair_M);
		      Repairdate.add(in_repair_D);
		      
		      Moneydate.add(in_money_Y);
		      Moneydate.add(in_money_M);
		      Moneydate.add(in_money_D);
		      
		      pn.add(date);
		      pn.add(Repairdate);
		      
		      pn.add(price);
		      pn.add(inprice);
		      
		      pn.add(deadline);
		      pn.add(Moneydate);
		      
		      pn.add(add_content);
		      pn.add(inadd_content);
		     
		      pn.add(re_regid);
		      pn.add(inre_regid);
		      pn.add(re_car_rent_compid);
		      pn.add(inre_car_rent_compid);
		      pn.add(re_license);
		      pn.add(inre_license);
		      pn.add(re_shopid);
		      pn.add(inre_shopid);
		      
		      
			  pn_btn.add(btn_input);
			  pn_btn.add(btn_del);
			  pn_btn.add(btn_update);
			 
			  
			  add(pn);
			  add("South",pn_btn);
			  add("North",pn_inf);
			  
			  pn_inf.add(txtResult);
			  
			  btn_input.addActionListener(new ActionListenerInput_fix());
		      btn_del.addActionListener(new ActionListenerDel_fix());
		      btn_update.addActionListener(new ActionListenerUpdate_fix());
		      
		      setBounds(200, 200, 700, 800);
		      
		      
		      viewinformation();
			  
		}

		 private void viewinformation() {
			 
	         try {  
	            stmt = con_repair.createStatement();   
	            txtResult.setText("");
	            String sql = "select * from car_check_info";
	            txtResult.setText("");
	            txtResult.setText("RENTID         REGID        FRONT         LEFTSIDE         RIGHTSIDE         BACK                   IS_NEESREPAIR\n");
	            rs=stmt.executeQuery(sql);
	            while(rs.next()) {
	            	
	            	String str = rs.getInt(1) + "\t" + rs.getInt(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t"+ rs.getString(5) + "\t"+ rs.getString(6) + "\t"+ rs.getString(7) + "\n";
	            	txtResult.append(str);
	            }
	         }catch (Exception e1) {
	               System.out.println("쿼리 읽기 실패 :" + e1);
	               System.out.println("오류 발생!"); 
	         }
			
		}

		private class ActionListenerInput_fix implements ActionListener{//전체 추가
		      public void actionPerformed(ActionEvent e) {
		    	  //com.pn_adm.setVisible(true);
		         try {  
		            stmt = con_repair.createStatement();     
		            String sql = "insert into repair_info values("+innum.getText()+","+inregid.getText()+","+inshopid.getText()+","+incar_rent_compid.getText()+",'"+inlicense.getText()+"','"+incontent.getText()+"',STR_TO_DATE('"+in_repair_Y.getText()+"-"+in_repair_M.getText()+"-"+in_repair_D.getText()+"','%Y-%m-%d'),"+inprice.getText()+",STR_TO_DATE('"+in_money_Y.getText()+"-"+in_money_M.getText()+"-"+in_money_D.getText()+"','%Y-%m-%d'),'"+inadd_content.getText()+"','"+inre_regid.getText()+"','"+inre_car_rent_compid.getText()+"','"+inre_license.getText()+"','"+inre_shopid.getText()+"')";
		             stmt.executeUpdate(sql);
		             innum.setText("");
		             inregid.setText("");
		             inshopid.setText("");
		             incar_rent_compid.setText("");
		             inlicense.setText("");
		             incontent.setText("");
		             in_repair_Y.setText("");
		             in_repair_M.setText("");
		             in_repair_D.setText("");
		             in_money_Y.setText("");
		             in_money_M.setText("");
		             in_money_D.setText("");
		             inprice.setText("");
		             indeadline.setText("");
		             inadd_content.setText("");
		             inre_regid.setText("");
		             inre_car_rent_compid.setText("");
		             inre_license.setText("");
		             inre_shopid.setText("");
		            
		         }catch (Exception e1) {
		               System.out.println("쿼리 읽기 실패 :" + e1);
		               System.out.println("오류 발생!"); 
		         }
		      }
	   }
		 private class ActionListenerDel_fix implements ActionListener{//전체 추가
		      public void actionPerformed(ActionEvent e) {
		    	//  com.pn_adm.setVisible(true);
		         try {  
		            stmt = con_repair.createStatement();   
		            String sql = "delete from repair_info where repairnum = "+innum.getText()+"";
		            stmt.executeUpdate(sql);
		            innum.setText("");
		            inregid.setText("");
		            inshopid.setText("");
		            incar_rent_compid.setText("");
		            inlicense.setText("");
		            incontent.setText("");
		            in_repair_Y.setText("");
		            in_repair_M.setText("");
		            in_repair_D.setText("");
		            in_money_Y.setText("");
		            in_money_M.setText("");
		            in_money_D.setText("");
		            inprice.setText("");
		            indeadline.setText("");
		            inadd_content.setText("");
		            inre_regid.setText("");
		            inre_car_rent_compid.setText("");
		            inre_license.setText("");
		            inre_shopid.setText("");
		            
		         }catch (Exception e1) {
		               System.out.println("쿼리 읽기 실패 :" + e1);
		               System.out.println("오류 발생!"); 
		         }
		      }
		   }
		 private class ActionListenerUpdate_fix implements ActionListener{//전체 추가
		      public void actionPerformed(ActionEvent e) {
		    	 // com.pn_adm.setVisible(true);
		         try {  
		            stmt = con_repair.createStatement();     
		            String sql = "update repair_info set repairnum = "+innum.getText()+", car_regid = "+inregid.getText()+", car_repairshopid ='"+inshopid.getText()+"', car_rent_companyid = '"+incar_rent_compid.getText()+"', cust_licenseid ='"+inlicense.getText()+"', repair_content = '"+incontent.getText()+"', repair_date = STR_TO_DATE('"+in_repair_Y.getText()+"-"+in_repair_M.getText()+"-"+in_repair_D.getText()+"','%Y-%m-%d'), repair_price = "+inprice.getText()+", price_until = STR_TO_DATE(\'"+in_money_Y.getText()+"-"+in_money_M.getText()+"-"+in_money_D.getText()+"\','%Y-%m-%d'), add_repair_content = '"+inadd_content.getText()+"', car_car_regid = '"+inre_regid.getText()+"', car_car_rent_company_car_rent_companyid = '"+inre_car_rent_compid.getText()+"', customer_cust_licenseid = '"+inre_license.getText()+"', repairshop_car_repairshopid = '"+inre_shopid.getText()+"'  where repairnum = "+innum.getText()+"";
		            stmt.executeUpdate(sql);
		            innum.setText("");
		            inregid.setText("");
		            inshopid.setText("");
		            incar_rent_compid.setText("");
		            inlicense.setText("");
		            incontent.setText("");
		            in_repair_Y.setText("");
		            in_repair_M.setText("");
		            in_repair_D.setText("");
		            in_money_Y.setText("");
		            in_money_M.setText("");
		            in_money_D.setText("");
		            inprice.setText("");
		            indeadline.setText("");
		            inadd_content.setText("");
		            inre_regid.setText("");
		            inre_car_rent_compid.setText("");
		            inre_license.setText("");
		            inre_shopid.setText("");
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
