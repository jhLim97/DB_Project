
import java.awt.event.*;
import java.awt.*;
//import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTextArea;


public class Administrator extends JFrame implements ActionListener {
   
   
   JPanel pn; 
   
   // 버튼 8개 - 초기화, 캠핑카 대여회사, 캠핑카, 고객, 캠핑카 정비소, 캠핑카반환, 수리, 검색
   JButton btn_init, btn_com, btn_car, btn_cust, btn_shop, btn_return, btn_repair, btn_search;
   
   static Connection con_adm;
   
   Statement stmt;

   public Administrator(Connection con) {
      super("관리자");
      layInit();
      con_adm = con;
      setBounds(200, 200, 500, 300);       
   }

   public void layInit() {
                  
	  
	  //shop = new RepairShop(con_adm);
	   
      //관리자 화면
      pn=new JPanel();
      
      // 버튼 8개 초기화
      btn_init = new JButton("DB 초기화");
      btn_com = new JButton("대여회사");
      btn_car = new JButton("캠핑카 관리");
      btn_cust = new JButton("고객 관리");
      btn_shop = new JButton("정비소");
      btn_return = new JButton("캠핑카 반환");
      btn_repair = new JButton("캠핑카 수리");
      btn_search = new JButton("검색");
      
      
      pn.setLayout(new GridLayout(2,4));
      // 패널에 버튼 8개 추가
      pn.add(btn_init);
      pn.add(btn_com);
      pn.add(btn_car);
      pn.add(btn_cust);
      pn.add(btn_shop);
      pn.add(btn_return);
      pn.add(btn_repair);
      pn.add(btn_search);
      
      add(pn);
      
      pn.setVisible(true);
      
      btn_init.addActionListener(new ActionListenerInit());
      btn_com.addActionListener(this);
      btn_car.addActionListener(this);
      btn_cust.addActionListener(this);
      btn_shop.addActionListener(this);
      btn_return.addActionListener(this);
      btn_repair.addActionListener(this);
      btn_search.addActionListener(this);
   }
   
   // 초기화
   private class ActionListenerInit implements ActionListener {
	      public void actionPerformed (ActionEvent e) {
	         
	         System.out.println("DB 초기화 시작");
	         
	         try {
	            stmt = con_adm.createStatement();
	            
	            //프로젝트 부분 2020.06.11
	            stmt.execute("drop table if exists car,car_check_info,car_rent,car_rent_Company,car_rent_has_car_check_info,customers,repair_info,RepairShop;");
	            
	            stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`car_rent_Company` (\r\n" + 
	                  "  `car_rent_Companyid` INT NOT NULL,\r\n" + 
	                  "  `Company_name` VARCHAR(30) NULL,\r\n" + 
	                  "  `Company_addr` VARCHAR(45) NULL,\r\n" + 
	                  "  `Company_phone` VARCHAR(45) NULL,\r\n" + 
	                  "  `Company_admin_name` VARCHAR(45) NULL,\r\n" + 
	                  "  `Company_admin_email` VARCHAR(45) NULL,\r\n" + 
	                  "  PRIMARY KEY (`car_rent_Companyid`))\r\n" + 
	                  "ENGINE = InnoDB;");
	            stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`car` (\r\n" + 
	                  "  `car_regid` INT NOT NULL,\r\n" + 
	                  "  `car_name` VARCHAR(45) NULL,\r\n" + 
	                  "  `car_num` INT NULL,\r\n" + 
	                  "  `car_ridenum` INT NULL,\r\n" + 
	                  "  `mfgCompany` VARCHAR(45) NULL,\r\n" + 
	                  "  `mfgyear` INT NULL,\r\n" + 
	                  "  `cumulative_distance` INT NULL,\r\n" + 
	                  "  `car_rent_price` INT NULL,\r\n" + 
	                  "  `car_rent_Companyid` INT NULL,\r\n" + 
	                  "  `car_regdate` DATE NULL,\r\n" + 
	                  "  `car_rent_Company_car_rent_Companyid` INT NOT NULL,\r\n" + 
	                  "  PRIMARY KEY (`car_regid`, `car_rent_Company_car_rent_Companyid`),\r\n" + 
	                  "  INDEX `fk_car_car_rent_Company1_idx` (`car_rent_Company_car_rent_Companyid` ASC) VISIBLE,\r\n" + 
	                  "  CONSTRAINT `fk_car_car_rent_Company1`\r\n" + 
	                  "    FOREIGN KEY (`car_rent_Company_car_rent_Companyid`)\r\n" + 
	                  "    REFERENCES `madang`.`car_rent_Company` (`car_rent_Companyid`)\r\n" + 
	                  "    ON DELETE NO ACTION\r\n" + 
	                  "    ON UPDATE NO ACTION)\r\n" + 
	                  "ENGINE = InnoDB;");
	            stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`customers` (\r\n" + 
	                  "  `cust_licenseid` INT NOT NULL,\r\n" + 
	                  "  `custname` VARCHAR(45) NULL,\r\n" + 
	                  "  `custaddr` VARCHAR(45) NULL,\r\n" + 
	                  "  `custphone` VARCHAR(45) NULL,\r\n" + 
	                  "  `cust_email` VARCHAR(45) NULL,\r\n" + 
	                  "  PRIMARY KEY (`cust_licenseid`))\r\n" + 
	                  "ENGINE = InnoDB;");
	            stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`car_rent` (\r\n" + 
	                  "  `car_rentid` INT NOT NULL,\r\n" + 
	                  "  `car_regid` INT NULL,\r\n" + 
	                  "  `cust_licenseid` INT NULL,\r\n" + 
	                  "  `car_rent_Companyid` INT NULL,\r\n" + 
	                  "  `car_rent_startdate` DATE NULL,\r\n" + 
	                  "  `car_rentperiod` DATE NULL,\r\n" + 
	                  "  `chargeprice` INT NULL,\r\n" + 
	                  "  `price_until` DATE NULL,\r\n" + 
	                  "  `add_content` VARCHAR(45) NULL,\r\n" + 
	                  "  `add_price` INT NULL,\r\n" + 
	                  "  `customer_cust_licenseid` INT NOT NULL,\r\n" + 
	                  "  `car_car_regid` INT NOT NULL,\r\n" + 
	                  "  `car_car_rent_Company_car_rent_Companyid` INT NOT NULL,\r\n" + 
	                  "  PRIMARY KEY (`car_rentid`),\r\n" + 
	                  "  INDEX `fk_car_rent_customer1_idx` (`customer_cust_licenseid` ASC) VISIBLE,\r\n" + 
	                  "  INDEX `fk_car_rent_car1_idx` (`car_car_regid` ASC, `car_car_rent_Company_car_rent_Companyid` ASC) VISIBLE,\r\n" + 
	                  "  CONSTRAINT `fk_car_rent_customer1`\r\n" + 
	                  "    FOREIGN KEY (`customer_cust_licenseid`)\r\n" + 
	                  "    REFERENCES `madang`.`customers` (`cust_licenseid`)\r\n" + 
	                  "    ON DELETE NO ACTION\r\n" + 
	                  "    ON UPDATE NO ACTION,\r\n" + 
	                  "  CONSTRAINT `fk_car_rent_car1`\r\n" + 
	                  "    FOREIGN KEY (`car_car_regid` , `car_car_rent_Company_car_rent_Companyid`)\r\n" + 
	                  "    REFERENCES `madang`.`car` (`car_regid` , `car_rent_Company_car_rent_Companyid`)\r\n" + 
	                  "    ON DELETE NO ACTION\r\n" + 
	                  "    ON UPDATE NO ACTION)\r\n" + 
	                  "ENGINE = InnoDB;");
	            stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`car_check_info` (\r\n" + 
	                  "  `car_rentid` INT NOT NULL,\r\n" + 
	                  "  `car_regid` INT NULL,\r\n" + 
	                  "  `front` VARCHAR(45) NULL,\r\n" + 
	                  "  `leftside` VARCHAR(45) NULL,\r\n" + 
	                  "  `rightside` VARCHAR(45) NULL,\r\n" + 
	                  "  `back` VARCHAR(45) NULL,\r\n" + 
	                  "  `Is_needrepair` VARCHAR(45) NULL,\r\n" + 
	                  "  PRIMARY KEY (`car_rentid`))\r\n" + 
	                  "ENGINE = InnoDB;");
	            stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`RepairShop` (\r\n" + 
	                  "  `car_RepairShopid` INT NOT NULL,\r\n" + 
	                  "  `RepairShop_name` VARCHAR(45) NULL,\r\n" + 
	                  "  `RepairShop_addr` VARCHAR(45) NULL,\r\n" + 
	                  "  `RepairShop_phone` VARCHAR(45) NULL,\r\n" + 
	                  "  `RepairShop_admin_name` VARCHAR(45) NULL,\r\n" + 
	                  "  `RepairShop_admin_email` VARCHAR(45) NULL,\r\n" + 
	                  "  PRIMARY KEY (`car_RepairShopid`))\r\n" + 
	                  "ENGINE = InnoDB;");
	            stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`repair_info` (\r\n" + 
	                  "  `repairnum` INT NOT NULL,\r\n" + 
	                  "  `car_regid` INT NULL,\r\n" + 
	                  "  `car_RepairShopid` INT NULL,\r\n" + 
	                  "  `car_rent_Companyid` INT NULL,\r\n" + 
	                  "  `cust_licenseid` INT NULL,\r\n" + 
	                  "  `repair_content` VARCHAR(45) NULL,\r\n" + 
	                  "  `repair_date` DATE NULL,\r\n" + 
	                  "  `repair_price` INT NULL,\r\n" + 
	                  "  `price_until` DATE NULL,\r\n" + 
	                  "  `add_repair_content` VARCHAR(45) NULL,\r\n" + 
	                  "  `car_car_regid` INT NOT NULL,\r\n" + 
	                  "  `car_car_rent_Company_car_rent_Companyid` INT NOT NULL,\r\n" + 
	                  "  `customer_cust_licenseid` INT NOT NULL,\r\n" + 
	                  "  `RepairShop_car_RepairShopid` INT NOT NULL,\r\n" + 
	                  "  PRIMARY KEY (`repairnum`),\r\n" + 
	                  "  INDEX `fk_repair_info_car1_idx` (`car_car_regid` ASC, `car_car_rent_Company_car_rent_Companyid` ASC) VISIBLE,\r\n" + 
	                  "  INDEX `fk_repair_info_customer1_idx` (`customer_cust_licenseid` ASC) VISIBLE,\r\n" + 
	                  "  INDEX `fk_repair_info_RepairShop1_idx` (`RepairShop_car_RepairShopid` ASC) VISIBLE,\r\n" + 
	                  "  CONSTRAINT `fk_repair_info_car1`\r\n" + 
	                  "    FOREIGN KEY (`car_car_regid` , `car_car_rent_Company_car_rent_Companyid`)\r\n" + 
	                  "    REFERENCES `madang`.`car` (`car_regid` , `car_rent_Company_car_rent_Companyid`)\r\n" + 
	                  "    ON DELETE NO ACTION\r\n" + 
	                  "    ON UPDATE NO ACTION,\r\n" + 
	                  "  CONSTRAINT `fk_repair_info_customer1`\r\n" + 
	                  "    FOREIGN KEY (`customer_cust_licenseid`)\r\n" + 
	                  "    REFERENCES `madang`.`customers` (`cust_licenseid`)\r\n" + 
	                  "    ON DELETE NO ACTION\r\n" + 
	                  "    ON UPDATE NO ACTION,\r\n" + 
	                  "  CONSTRAINT `fk_repair_info_RepairShop1`\r\n" + 
	                  "    FOREIGN KEY (`RepairShop_car_RepairShopid`)\r\n" + 
	                  "    REFERENCES `madang`.`RepairShop` (`car_RepairShopid`)\r\n" + 
	                  "    ON DELETE NO ACTION\r\n" + 
	                  "    ON UPDATE NO ACTION)\r\n" + 
	                  "ENGINE = InnoDB;");
	            stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`car_rent_has_car_check_info` (\r\n" + 
	                  "  `car_rent_car_rentid` INT NOT NULL,\r\n" + 
	                  "  `car_check_info_car_rentid` INT NOT NULL,\r\n" + 
	                  "  PRIMARY KEY (`car_rent_car_rentid`, `car_check_info_car_rentid`),\r\n" + 
	                  "  INDEX `fk_car_rent_has_car_check_info_car_check_info1_idx` (`car_check_info_car_rentid` ASC) VISIBLE,\r\n" + 
	                  "  INDEX `fk_car_rent_has_car_check_info_car_rent1_idx` (`car_rent_car_rentid` ASC) VISIBLE,\r\n" + 
	                  "  CONSTRAINT `fk_car_rent_has_car_check_info_car_rent1`\r\n" + 
	                  "    FOREIGN KEY (`car_rent_car_rentid`)\r\n" + 
	                  "    REFERENCES `madang`.`car_rent` (`car_rentid`)\r\n" + 
	                  "    ON DELETE NO ACTION\r\n" + 
	                  "    ON UPDATE NO ACTION,\r\n" + 
	                  "  CONSTRAINT `fk_car_rent_has_car_check_info_car_check_info1`\r\n" + 
	                  "    FOREIGN KEY (`car_check_info_car_rentid`)\r\n" + 
	                  "    REFERENCES `madang`.`car_check_info` (`car_rentid`)\r\n" + 
	                  "    ON DELETE NO ACTION\r\n" + 
	                  "    ON UPDATE NO ACTION)\r\n" + 
	                  "ENGINE = InnoDB;");
	                  
	            
	           
	            
	            stmt.executeUpdate("INSERT INTO car_rent_Company VALUES (1, 'CampingCarMaster', '서울특별시 광진구 ', '02-523-1645', '김민준', 'minjun123@naver.com');");
	            stmt.executeUpdate("INSERT INTO car_rent_Company VALUES (2, '캠핑좋아요', '경기도 수원시 장안구 송죽동 ', '031-245-1254', '임서준', 'LSJ1004@naver.com');");
	            stmt.executeUpdate("INSERT INTO car_rent_Company VALUES (3, '캠핑고고', '인천광역시 남동구 구월1동  ', '032-456-7351', '이예준', 'YJLee77@naver.com');");
	            stmt.executeUpdate("INSERT INTO car_rent_Company VALUES (4, '어디로든 캠핑', '경기도 구리시 교문동 ', '031-841-4258 ', '강도윤 ', 'DoYun1357@naver.com');");
	            stmt.executeUpdate("INSERT INTO car_rent_Company VALUES (5, ' MoveCamping', '경기도 양평군 양평읍 ', '031-548-7523 ', '이채원 ', 'Lee_CHone1@naver.com');");
	            stmt.executeUpdate("INSERT INTO car_rent_Company VALUES (6, '원주캠핑카 대여소', '강원도 원주시 중앙동 ', '033-422-8852', '김민서', 'KMSeo3231@naver.com');");
	            stmt.executeUpdate("INSERT INTO car_rent_Company VALUES (7, '세종시캠핑카', '세종특별자치시 보람동  ', '044-220-3020', '정지민', 'JJM0000@naver.com');");
	            stmt.executeUpdate("INSERT INTO car_rent_Company VALUES (8, '부산캠핑카렌트', '부산광역시 기장군 장안읍 ', '051-200-7301', '김선우', 'SSunWoo2@naver.com');");
	            stmt.executeUpdate("INSERT INTO car_rent_Company VALUES (9, ' 대전캠핑카 대여소', '대전광역시 대덕구 비래동  ', '042-859-9985', '이현우', 'HSHyunW@naver.com');");
	            stmt.executeUpdate("INSERT INTO car_rent_Company VALUES (10, '대구 캠핑카 대여소', '대구광역시 수성구 만촌동 ', '053-705-9243', '임다은 ', 'DaEunL@naver.com');");
	            stmt.executeUpdate("INSERT INTO car_rent_Company VALUES (11, '제주캠핑카', '제주특별자치도 제주시 용담동 ', '064-201-0210', '이시우', 'LSWoo909@naver.com');");
	            stmt.executeUpdate("INSERT INTO car_rent_Company VALUES (12, '서귀포레전드캠핑카', '제주특별자치도 서귀포시 서홍동  ', '064-038-2022', '정주원', 'Joo1Jung@naver.com');");
	            stmt.executeUpdate("INSERT INTO car_rent_Company VALUES (13, '창원어디든캠핑카', '경상남도 창원시 성산구 삼정자동 ', '055-541-3207', '이건우', 'gwLee333@naver.com');");
	            stmt.executeUpdate("INSERT INTO car_rent_Company VALUES (14, '떠나요캠핑카', '광주광역시 북구 문흥동 ', '062-804-6352', '김예은 ', 'YesEun@naver.com');");
	            stmt.executeUpdate("INSERT INTO car_rent_Company VALUES (15, 'ToGoCampingCar', '전라북도 전주시 덕진구 우아동  ', '063-120-9863', '구하린', 'Haryn58@naver.com');");
	            
	            stmt.executeUpdate("INSERT INTO car VALUES (1, '근본캠핑카', 1325, 6, 'Hondai', 2000, 554883, 50000, 6, STR_TO_DATE('2000-4-12','%Y-%m-%d'),6);");
	            stmt.executeUpdate("INSERT INTO car VALUES (2, '다같이캠핑카', 2185, 6, 'Daewon', 2005, 440124, 50000, 2, STR_TO_DATE('2006-3-27','%Y-%m-%d'),2);");
	            stmt.executeUpdate("INSERT INTO car VALUES (3, 'CampingCarseries1', 3207, 8, 'Hondai', 2006, 400223, 55000, 11, STR_TO_DATE('2006-9-21','%Y-%m-%d'),11);");
	            stmt.executeUpdate("INSERT INTO car VALUES (4, '좋아캠핑카', 9582, 6, 'Daewon', 2008, 340124, 55000, 12, STR_TO_DATE('2008-12-10','%Y-%m-%d'),12);");
	            stmt.executeUpdate("INSERT INTO car VALUES (5, 'extremeCampCar', 6487, 8, 'Panda', 2010, 300012, 60000, 15, STR_TO_DATE('2010-1-24','%Y-%m-%d'),15);");
	            stmt.executeUpdate("INSERT INTO car VALUES (6, 'Yesss캠핑Car', 2203, 6, 'Koogne', 2012, 270124, 60000, 4, STR_TO_DATE('2012-5-25','%Y-%m-%d'),4);");
	            stmt.executeUpdate("INSERT INTO car VALUES (7, 'Yesss캠핑Car', 5489, 6, 'Koogne', 2012, 290154, 60000, 14, STR_TO_DATE('2012-5-25','%Y-%m-%d'),14);");
	            stmt.executeUpdate("INSERT INTO car VALUES (8, 'Nice캠핑카', 2367, 8, 'Koogne', 2013, 210489, 65000, 5, STR_TO_DATE('2014-4-21','%Y-%m-%d'),5);");
	            stmt.executeUpdate("INSERT INTO car VALUES (9, 'CampingCarSeries2', 1648, 6, 'Hondai', 2014, 201573, 65000, 10, STR_TO_DATE('2014-6-12','%Y-%m-%d'),10);");
	            stmt.executeUpdate("INSERT INTO car VALUES (10, '캠핑카마스터즈', 3489, 6, 'Daewon', 2015, 187423, 65000, 1, STR_TO_DATE('2015-1-19','%Y-%m-%d'),1);");
	            stmt.executeUpdate("INSERT INTO car VALUES (11, 'CarForCamping', 4385, 8, 'Panda', 2015, 154682, 65000, 13, STR_TO_DATE('2015-4-8','%Y-%m-%d'),13);");
	            stmt.executeUpdate("INSERT INTO car VALUES (12, '캠핑카마스터즈', 8632, 6, 'Daewon', 2015, 143287, 65000, 8, STR_TO_DATE('2015-8-27','%Y-%m-%d'),8);");
	            stmt.executeUpdate("INSERT INTO car VALUES (13, 'SpecialCampingCar', 7243, 8, 'Panda', 2016, 103425, 70000, 3, STR_TO_DATE('2016-7-10','%Y-%m-%d'),3);");
	            stmt.executeUpdate("INSERT INTO car VALUES (14, 'CampingCarSeries3', 6230, 6, 'Hondai', 2018, 82431, 80000, 9, STR_TO_DATE('2018-8-6','%Y-%m-%d'),9);");
	            stmt.executeUpdate("INSERT INTO car VALUES (15, 'CampingCarSeries3', 1564, 6, 'Hondai', 2018, 62147, 80000, 7, STR_TO_DATE('2018-9-17','%Y-%m-%d'),7);");
	            
	            stmt.executeUpdate("INSERT INTO car_check_info VALUES (1,4,\"범퍼 파손\",\"이상 무\",\"이상 무\",\"이상 무\",\"o\");");
	            stmt.executeUpdate("INSERT INTO car_check_info VALUES (2,9,\"이상 무\",\"이상 무\",\"앞 타이어 펑크\",\"이상 무\",\"o\");");
	            stmt.executeUpdate("INSERT INTO car_check_info VALUES (3,7,\"이상 무\",\"뒤 타이어 펑크\",\"이상 무\",\"이상 무\",\"o\");");
	            stmt.executeUpdate("INSERT INTO car_check_info VALUES (4,10,\"범퍼 파손\",\"이상 무\",\"이상 무\",\"이상 무\",\"o\");");
	            stmt.executeUpdate("INSERT INTO car_check_info VALUES (5,15,\"이상 무\",\"앞타이어 펑크\",\"앞타이어 펑크\",\"이상 무\",\"o\");");
	            stmt.executeUpdate("INSERT INTO car_check_info VALUES (6,3,\"이상 무\",\"이상 무\",\"이상 무\",\"도색 벗겨짐\",\"o\");");
	            stmt.executeUpdate("INSERT INTO car_check_info VALUES (7,14,\"이상 무\",\"이상 무\",\"이상 무\",\"뒷창문 파손\",\"o\");");
	            stmt.executeUpdate("INSERT INTO car_check_info VALUES (8,11,\"이상 무\",\"사이드 미러 파손\",\"이상 무\",\"이상 무\",\"o\");");
	            stmt.executeUpdate("INSERT INTO car_check_info VALUES (9,5,\"이상 무\",\"뒤타이어 펑크\",\"뒤타이어 펑크\",\"이상 무\",\"o\");");
	            stmt.executeUpdate("INSERT INTO car_check_info VALUES (10,2,\"이상 무\",\"이상 무\",\"이상 무\",\"범퍼 파손\",\"o\");");
	            stmt.executeUpdate("INSERT INTO car_check_info VALUES (11,13,\"이상 무\",\"앞 타이어 펑크\",\"이상 무\",\"이상 무\",\"o\");");
	            stmt.executeUpdate("INSERT INTO car_check_info VALUES (12,1,\"이상 무\",\"이상 무\",\"뒤타이어 펑크\",\"범퍼 파손\",\"o\");");
	            stmt.executeUpdate("INSERT INTO car_check_info VALUES (13,12,\"이상 무\",\"이상 무\",\"사이드 미러 파손\",\"이상 무\",\"o\");");
	            stmt.executeUpdate("INSERT INTO car_check_info VALUES (14,6,\"범퍼파손\",\"사이드 미러 파손\",\"이상 무\",\"이상 무\",\"o\");");
	            stmt.executeUpdate("INSERT INTO car_check_info VALUES (15,8,\"범퍼 파손\",\"앞 타이어 펑크\",\"이상 무\",\"이상 무\",\"o\");");
	            
	            stmt.executeUpdate("INSERT INTO RepairShop VALUES (1,\"해주카센터\",\"서울특별시 광진구 중곡동\",\"02-452-1325\",\"김우재\",\"WooJ123@naver.com\");");
	            stmt.executeUpdate("INSERT INTO RepairShop VALUES (2,\"송죽카서비스\",\"경기도 수원시 장안구 송죽동\",\"031-215-5432\",\"강재민\",\"JMKang@naver.com\");");
	            stmt.executeUpdate("INSERT INTO RepairShop VALUES (3,\"열성정비소\",\"인천광역시 남동구 구월동\",\"032-426-1231\",\"정지원\",\"Jone11@naver.com\");");
	            stmt.executeUpdate("INSERT INTO RepairShop VALUES (4,\"만능카센타\",\"경기도 구리시 교문동\",\"031-813-1148\",\"이다훈\",\"LDH332412@naver.com\");");
	            stmt.executeUpdate("INSERT INTO RepairShop VALUES (5,\"양평카센터\",\"경기도 양평군 양평읍\",\"031-543-8853\",\"고정석\",\"GoodJung@naver.com\");");
	            stmt.executeUpdate("INSERT INTO RepairShop VALUES (6,\"미들카센터\",\"강원도 원주시 중앙동\",\"033-472-5132\",\"이호현\",\"HHLee@naver.com\");");
	            stmt.executeUpdate("INSERT INTO RepairShop VALUES (7,\"특별카센터\",\"세종특별자치시 보람동\",\"044-210-8541\",\"임재민\",\"JDIDoit@naver.com\");");
	            stmt.executeUpdate("INSERT INTO RepairShop VALUES (8,\"장안카센터정비소\",\"부산광역시 기장군 장안읍\",\"051-204-0214\",\"문종원\",\"BadBoy@naver.com\");");
	            stmt.executeUpdate("INSERT INTO RepairShop VALUES (9,\"장인카센터\",\"대전광역시 대덕구 비래동\",\"042-853-1215\",\"김희태\",\"HTae@naver.com\");");
	            stmt.executeUpdate("INSERT INTO RepairShop VALUES (10,\"만물카센터\",\"대구광역시 수성구 만촌동\",\"053-710-9555\",\"박지수\",\"Jisso@naver.com\");");
	            stmt.executeUpdate("INSERT INTO RepairShop VALUES (11,\"하이카센터\",\"제주특별자치도 제주시 용담동\",\"064-211-5275\",\"윤창열\",\"CHang999@naver.com\");");
	            stmt.executeUpdate("INSERT INTO RepairShop VALUES (12,\"평생카센터\",\"제주특별자치도 서귀포시 서홍동\",\"064-033-8578\",\"심지훈\",\"SimJH@naver.com\");");
	            stmt.executeUpdate("INSERT INTO RepairShop VALUES (13,\"삼정카센터\",\"경상남도 창원시 성산구 삼정자동\",\"055-541-1502\",\"박기후\",\"Weatherman@naver.com\");");
	            stmt.executeUpdate("INSERT INTO RepairShop VALUES (14,\"헤드카센터\",\"광주광역시 북구 문흥동\",\"062-803-4487\",\"고영훈\",\"Go0H@naver.com\");");
	            stmt.executeUpdate("INSERT INTO RepairShop VALUES (15,\"우와카센터\",\"전라북도 전주시 덕진구 우아동\",\"063-120-9863\",\"정경철\",\"GCheol@naver.com\");");
	            
	            stmt.executeUpdate("INSERT INTO customers VALUES (762531,\"임서현\",\"서울특별시 성동구 용답동\",\"010-8465-2046\",\"SH2046@naver.com\");");
	            stmt.executeUpdate("INSERT INTO customers VALUES (620154,\"정예준\",\"서울특별시 관악구 봉천동\",\"010-2432-0125\",\"Yejun0125@naver.com\");");
	            stmt.executeUpdate("INSERT INTO customers VALUES (905432,\"이도윤\",\"경기도 성남시 분당구 서현동\",\"010-2105-9982\",\"DoYunLee2105@naver.com\");");
	            stmt.executeUpdate("INSERT INTO customers VALUES (320156,\"김주원\",\"부산광역시 부산진구 연지동\",\"010-7523-2015\",\"KJone631@naver.com\");");
	            stmt.executeUpdate("INSERT INTO customers VALUES (426583,\"강준서\",\"경상남도 창원시 성산구 가음동\",\"010-8542-0012\",\"KKJunSeo012@naver.com\");");
	            stmt.executeUpdate("INSERT INTO customers VALUES (201603,\"이준우\",\"전라남도 순천시 조곡동\",\"010-4352-0156\"   ,\"HandsomJW@naver.com\");");
	            stmt.executeUpdate("INSERT INTO customers VALUES (302154,\"강현우\",\"전라남도 목포시 산정동\",\"010-4852-2356\",\"KangHW@naver.com\");");
	            stmt.executeUpdate("INSERT INTO customers VALUES (301264,\"권지훈\",\"전라북도 전주시 완산구 중화산2동\",\"010-9421-0605\",\"JiiHun65@naver.com\");");
	            stmt.executeUpdate("INSERT INTO customers VALUES (532015,\"정수아\",\"충청남도 보령시 대천동\",\"010-3287-9182\",\"SuA32@naver.com\");");
	            stmt.executeUpdate("INSERT INTO customers VALUES (862513,\"장다은\",\"충청남도 태안군 태안읍\",\"010-0248-5347\",\"DAEUN2485347@naver.com\");");
	            stmt.executeUpdate("INSERT INTO customers VALUES (201543,\"임현준\",\"경기도 안산시 단원구\",\"010-9820-0031\",\"Lim0031@naver.com\");");
	            stmt.executeUpdate("INSERT INTO customers VALUES (753216,\"강서진\",\"제주특별자치도 제주시 오라동\",\"010-5103-0569\",\"SeoJinee@naver.com\");");
	            stmt.executeUpdate("INSERT INTO customers VALUES (102436,\"김연우\",\"제주특별자치도 서귀포시 송산동\",\"010-0504-0104\",\"KKKWoo@naver.com\");");
	            stmt.executeUpdate("INSERT INTO customers VALUES (510546,\"김예서\",\"충청북도 충주시 교현동\",\"010-7821-3582\",\"SkyCastle7821@naver.com\");");
	            stmt.executeUpdate("INSERT INTO customers VALUES (105468,\"이건우\",\"경기도 의정부시 신곡동\",\"010-7744-5521\",\"7744Gun@naver.com\");");
	            
	            stmt.executeUpdate("INSERT INTO car_rent VALUES (1,4,762531,12,\"2020-02-10\",\"2020-02-15\",55000*6,\"2020-02-09\",\"물 2L * 12\",1000*12,762531,4,12);");
	            stmt.executeUpdate("INSERT INTO car_rent VALUES (2,9,620154,10,\"2020-02-14\",\"2020-02-20\",65000*7,\"2020-02-13\",\"휴대용 가스렌지, 물 2L * 14\",20000+1000*14,620154,9,10);");
	            stmt.executeUpdate("INSERT INTO car_rent VALUES (3,7,905432,14,\"2020-02-16\",\"2020-02-23\",60000*8,\"2020-02-15\",\"휴대용 가스렌지, 삼겹살 3kg, 물 2L * 16\",20000+50000+1000*16,905432,7,14);");
	            stmt.executeUpdate("INSERT INTO car_rent VALUES (4,10,320156,1,\"2020-02-19\",\"2020-02-27\",65000*9,\"2020-02-18\",\"물 2L * 18\",1000*18,320156,10,1);");
	            stmt.executeUpdate("INSERT INTO car_rent VALUES (5,15,426583,7,\"2020-02-20\",\"2020-02-28\",80000*9,\"2020-02-19\",\"쌀 1kg, 물 2L * 18\",30000+1000*18,426583,15,7);");
	            stmt.executeUpdate("INSERT INTO car_rent VALUES (6,3,201603,11,\"2020-02-24\",\"2020-03-01\",55000*6,\"2020-02-23\",\"물 2L * 12\",1000*12,201603,3,11);");
	            stmt.executeUpdate("INSERT INTO car_rent VALUES (7,14,302154,9,\"2020-03-10\",\"2020-03-16\",80000*7,\"2020-03-09\",\"휴대용 가스렌지, 삼겹살 3kg, 물 2L * 14\",20000+50000+1000*14,302154,14,9);");
	            stmt.executeUpdate("INSERT INTO car_rent VALUES (8,11,301264,13,\"2020-03-12\",\"2020-03-16\",65000*5,\"2020-03-11\",\"물 2L * 10\",1000*10,301264,11,13);");
	            stmt.executeUpdate("INSERT INTO car_rent VALUES (9,5,532015,15,\"2020-03-21\",\"2020-03-24\",60000*4,\"2020-03-20\",\"초콜렛 8개, 물 2L * 8\",1000*8+1000*8,532015,5,15);");
	            stmt.executeUpdate("INSERT INTO car_rent VALUES (10,2,862513,2,\"2020-03-24\",\"2020-03-26\",50000*3,\"2020-03-23\",\"물 2L * 6\",1000*6,862513,2,2);");
	            stmt.executeUpdate("INSERT INTO car_rent VALUES (11,13,201543,3,\"2020-04-02\",\"2020-04-05\",70000*4,\"2020-04-01\",\"물 2L * 8\",1000*8,201543,13,3);");
	            stmt.executeUpdate("INSERT INTO car_rent VALUES (12,1,753216,6,\"2020-04-10\",\"2020-04-13\",50000*4,\"2020-04-09\",\"물 2L * 8\",1000*8,753216,1,6);");
	            stmt.executeUpdate("INSERT INTO car_rent VALUES (13,12,102436,8,\"2020-04-16\",\"2020-04-19\",65000*3,\"2020-04-15\",\"초콜렛 3개, 물 2L * 6\",1000*6+1000*3,102436,12,8);");
	            stmt.executeUpdate("INSERT INTO car_rent VALUES (14,6,510546,4,\"2020-04-25\",\"2020-04-27\",60000*3,\"2020-04-24\",\"물 2L * 6\",1000*6,510546,6,4);");
	            stmt.executeUpdate("INSERT INTO car_rent VALUES (15,8,105468,5,\"2020-05-04\",\"2020-05-08\",65000*5,\"2020-05-03\",\"물 2L * 10\",1000*10,105468,8,5);");
	           
	            stmt.executeUpdate("INSERT INTO repair_info VALUES (1,4,12,12,762531,\"앞 범퍼 파손\",\"2020-02-13\",100000,\"2020-02-17\",\"뒤 도색\",4,12,762531,12);");
	            stmt.executeUpdate("INSERT INTO repair_info VALUES (2,9,10,10,620154,\"오른쪽 앞 타이어 펑크\",\"2020-02-15\",200000,\"2020-02-22\",\"엔진 오일 교체\",9,10,620154,10);");
	            stmt.executeUpdate("INSERT INTO repair_info VALUES (3,7,14,14,905432,\"왼쪽 뒤 타이어 펑크\",\"2020-02-17\",200000,\"2020-02-25\",\"냉각수 교체\",7,14,905432,14);");
	            stmt.executeUpdate("INSERT INTO repair_info VALUES (4,10,1,1,320156,\"앞 범퍼 파손\",\"2020-02-19\",100000,\"2020-03-01\",\"운전석 시트 교체\",10,1,320156,1);");
	            stmt.executeUpdate("INSERT INTO repair_info VALUES (5,15,7,7,426583,\"왼쪽 앞타이어 펑크\",\"2020-02-20\",200000,\"2020-03-02\",\"뒷좌석 시트 교체\",15,7,426583,7);");
	            stmt.executeUpdate("INSERT INTO repair_info VALUES (6,3,11,11,201603,\"뒤 도색 벗겨짐\",\"2020-02-21\",50000,\"2020-03-03\",\"운전석 안전벨트 수리\",3,11,201603,11);");
	            stmt.executeUpdate("INSERT INTO repair_info VALUES (7,14,9,9,302154,\"뒤창문 파손\",\"2020-03-01\",150000,\"2020-03-18\",\"후미등 교체\",14,9,302154,9);");
	            stmt.executeUpdate("INSERT INTO repair_info VALUES (8,11,13,13,301264,\"왼쪽 사이드 미러 파손\",\"2020-03-13\",180000,\"2020-03-18\",\"왼쪽 앞 도색\",11,13,301264,13);");
	            stmt.executeUpdate("INSERT INTO repair_info VALUES (9,5,15,15,532015,\"왼쪽 뒤타이어 펑크 오른쪽 뒤타이어 펑크\",\"2020-03-15\",200000+200000,\"2020-03-26\",\"엔진 오일 교체\",5,15,532015,15);");
	            stmt.executeUpdate("INSERT INTO repair_info VALUES (10,2,2,2,862513,\"뒤 범퍼 파손\",\"2020-03-17\",100000,\"2020-03-28\",\"조수석 안전벨트 수리\",2,2,862513,2);");
	            stmt.executeUpdate("INSERT INTO repair_info VALUES (11,13,3,3,201543,\"왼쪽 앞 타이어 펑크\",\"2020-03-20\",200000,\"2020-04-07\",\"백미러 교체\",13,3,201543,3);");
	            stmt.executeUpdate("INSERT INTO repair_info VALUES (12,1,6,6,753216,\"오른쪽 뒤타이어 펑크 뒤 범퍼 파손\",\"2020-03-23\",200000+100000,\"2020-04-15\",\"후미등 및 내부등 교체\",1,6,753216,6);");
	            stmt.executeUpdate("INSERT INTO repair_info VALUES (13,12,8,8,102436,\"오른쪽 사이드 미러 파손\",\"2020-04-13\",180000,\"2020-04-21\",\"조수석 시트 교체\",12,8,102436,8);");
	            stmt.executeUpdate("INSERT INTO repair_info VALUES (14,6,4,4,510546,\"앞 범퍼 파손 왼쪽  사이드 미러 파손\",\"2020-04-17\",100000+180000,\"2020-04-29\",\"왼쪽 앞 타이어 교체\",6,4,510546,4);");
	            stmt.executeUpdate("INSERT INTO repair_info VALUES (15,8,5,5,105468,\"앞 범퍼 파손 왼쪽 앞 타이어 펑크\",\"2020-05-3\",100000+200000,\"2020-05-10\",\"냉각수 교체 및 엔진 오일 교체\",8,5,105468,5);");
	            
	            stmt.executeUpdate("INSERT INTO car_rent_has_car_check_info VALUES (1,1);");
	            stmt.executeUpdate("INSERT INTO car_rent_has_car_check_info VALUES (2,2);");
	            stmt.executeUpdate("INSERT INTO car_rent_has_car_check_info VALUES (3,3);");
	            stmt.executeUpdate("INSERT INTO car_rent_has_car_check_info VALUES (4,4);");
	            stmt.executeUpdate("INSERT INTO car_rent_has_car_check_info VALUES (5,5);");
	            stmt.executeUpdate("INSERT INTO car_rent_has_car_check_info VALUES (6,6);");
	            stmt.executeUpdate("INSERT INTO car_rent_has_car_check_info VALUES (7,7);");
	            stmt.executeUpdate("INSERT INTO car_rent_has_car_check_info VALUES (8,8);");
	            stmt.executeUpdate("INSERT INTO car_rent_has_car_check_info VALUES (9,9);");
	            stmt.executeUpdate("INSERT INTO car_rent_has_car_check_info VALUES (10,10);");
	            stmt.executeUpdate("INSERT INTO car_rent_has_car_check_info VALUES (11,11);");
	            stmt.executeUpdate("INSERT INTO car_rent_has_car_check_info VALUES (12,12);");
	            stmt.executeUpdate("INSERT INTO car_rent_has_car_check_info VALUES (13,13);");
	            stmt.executeUpdate("INSERT INTO car_rent_has_car_check_info VALUES (14,14);");
	            stmt.executeUpdate("INSERT INTO car_rent_has_car_check_info VALUES (15,15);");
	           
	            System.out.println("DB 초기화 완료");
	            
	            //2020.06.11
	           
	       
	         } catch (Exception e4) {
	            System.out.println("DB 초기화 실패" + e4);
	         }	         
	      }
	   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
    
	   try {
		   	if (e.getSource() == btn_com) {
	        	 System.out.println("com");
	        	 Company com = new Company(con_adm);	        	 
	        	 com.setVisible(true);	        	 	        	 
	         }else if (e.getSource() == btn_car) {
	        	 System.out.println("car");
	        	 stmt = con_adm.createStatement();
	        	 
	        	 Car car = new Car(con_adm);
	        	 car.setVisible(true);
	        	 
	         }else if (e.getSource() == btn_cust) {
	        	 System.out.println("cust");
	        	 stmt = con_adm.createStatement();
	        	 
	        	 Customer cust = new Customer(con_adm);
	        	 cust.setVisible(true);
	        	 
	         }else if (e.getSource() == btn_shop) {
	        	 System.out.println("shop");
	        	 stmt = con_adm.createStatement();
	        	 RepairShop shop = new RepairShop(con_adm);	        	 
	        	 shop.setVisible(true);
	         }else if (e.getSource() == btn_return) {
	        	 System.out.println("return");
	        	 stmt = con_adm.createStatement();
	        	 
	        	 return_check ret = new return_check(con_adm);
	        	 ret.setVisible(true);
	        	 
	         }else if (e.getSource() == btn_repair) {
	        	 System.out.println("repair");
	        	 stmt = con_adm.createStatement();
	        	 
	        	 repair rep=new repair(con_adm);
	        	 rep.setVisible(true);
	        	 
	         }else if (e.getSource() == btn_search) {
	        	 System.out.println("search");
	        	 stmt = con_adm.createStatement();
	        	 
	        	 Search search = new Search(con_adm);
	        	 search.setVisible(true);
	        	 //search
	        	 //RepairShop shop = new RepairShop(con_adm);
	        	 
	        	 //shop.setVisible(true);
	         }
	      } catch (Exception e2) {
	         System.out.println("쿼리 읽기 실패 :" + e2);
	         System.out.println("오류 발생!"); 
	      }

   }
      
}
