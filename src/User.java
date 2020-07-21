
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
	
	// 시용자 인터페이스 추가
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
      super("사용자");
      layInit();
      con_user = con;
      setBounds(200, 200, 800, 700);
      
   }

   public void layInit() {
	   
      pn=new JPanel();
      pn_user=new JPanel();
      ladate=new JLabel("날짜");
      rentid = new JLabel("대여번호");
      regid=new JLabel("등록ID");
      license=new JLabel("면허증");
      rent_compid=new JLabel("대여회사ID");
      startdate=new JLabel("시작일");
      period=new JLabel("마감일");
      price=new JLabel("청구요금");
      price_until=new JLabel("납입기한");
      add_content=new JLabel("기타청구내역");
      add_price=new JLabel("기타청구요금");
      re_license=new JLabel("면허증 재확인");
      re_regid=new JLabel("등록ID 재확인");
      re_rent_compid=new JLabel("대여회사ID 재확인");
      choose_regid=new JLabel("선택차량 등록ID");
      
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
      
      btn_user_search=new JButton("검색");
      btn_user_choose=new JButton("선택");
      btn_user_rent=new JButton("대여");
      
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
      
      btn_user_search.addActionListener(new ActionListenersearch_DML());///사용자 검색기능 버튼	
      btn_user_choose.addActionListener(new ActionListenerchoose_DML());
      btn_user_rent.addActionListener(new ActionListenerrent_DML());
   }

   

 
   //@Override
 //user section
   //검색기능 -> 대여되지 않은 차 및 대여목록에 있으나 만료기간 지난 차 출력
   private class ActionListenersearch_DML implements ActionListener{//전체 추가
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
		            txtResult.setText("등록번호      차명          차번호       승차인원     제조회사          제조연도       누적주행거리          대여비용          대여회사ID    등록일자          대여회사ID재확인\n");
		            while(rs.next()) {
		            	String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getInt(4) + "\t"+ rs.getString(5) + "\t"+ rs.getInt(6) + "\t"+ rs.getInt(7) + rs.getInt(8) + "\t"+ rs.getInt(9) + "\t" + rs.getString(10) + "\t" + rs.getInt(11) + "\n";
		            	txtResult.append(str);
		            }    
	         }catch (Exception e1) {
	               System.out.println("쿼리 읽기 실패 :" + e1);
	               System.out.println("오류 발생!"); 
	         }
	      }
	   }
   

   //선택기능 누르면 빈칸에 입력한 regid에 해당하는 자동차 정보 표시
   private class ActionListenerchoose_DML implements ActionListener{//전체 추가
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
	            txtResult.setText("아래의 등록ID, 대여회사ID를 이용하여 선택한 차를 대여하시오.\n");
	            while(rs.next()) {
	            	String str0 = "등록ID : " + rs.getInt(1) + "\t" + "차명 : " + rs.getString(2) + "\t" +  "대여회사ID : " + rs.getInt(9)+"\n";
	            	txtResult.append(str0);
	            	String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getInt(4) + "\t"+ rs.getString(5) + "\t"+ rs.getInt(6) + "\t"+ rs.getInt(7) + rs.getInt(8) + "\t"+ rs.getInt(9) + "\t" + rs.getString(10) + "\t" + rs.getInt(11) + "\n";
	            	txtResult.append(str);
	            	
	            }
	            
	         }catch (Exception e1) {
	               System.out.println("쿼리 읽기 실패 :" + e1);
	               System.out.println("오류 발생!"); 
	         }
	      }
	   }
   
   //대여기능->대여 누룰시 car_rent 테이블에 정보추가
   private class ActionListenerrent_DML implements ActionListener{//전체 추가
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
		               System.out.println("쿼리 읽기 실패 :" + e1);
		               System.out.println("오류 발생!"); 
		         }
		      }
	      }
	   
}
