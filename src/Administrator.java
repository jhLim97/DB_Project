
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
   
   // ��ư 8�� - �ʱ�ȭ, ķ��ī �뿩ȸ��, ķ��ī, ��, ķ��ī �����, ķ��ī��ȯ, ����, �˻�
   JButton btn_init, btn_com, btn_car, btn_cust, btn_shop, btn_return, btn_repair, btn_search;
   
   static Connection con_adm;
   
   Statement stmt;

   public Administrator(Connection con) {
      super("������");
      layInit();
      con_adm = con;
      setBounds(200, 200, 500, 300);       
   }

   public void layInit() {
                  
	  
	  //shop = new RepairShop(con_adm);
	   
      //������ ȭ��
      pn=new JPanel();
      
      // ��ư 8�� �ʱ�ȭ
      btn_init = new JButton("DB �ʱ�ȭ");
      btn_com = new JButton("�뿩ȸ��");
      btn_car = new JButton("ķ��ī ����");
      btn_cust = new JButton("�� ����");
      btn_shop = new JButton("�����");
      btn_return = new JButton("ķ��ī ��ȯ");
      btn_repair = new JButton("ķ��ī ����");
      btn_search = new JButton("�˻�");
      
      
      pn.setLayout(new GridLayout(2,4));
      // �гο� ��ư 8�� �߰�
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
   
   // �ʱ�ȭ
   private class ActionListenerInit implements ActionListener {
	      public void actionPerformed (ActionEvent e) {
	         
	         System.out.println("DB �ʱ�ȭ ����");
	         
	         try {
	            stmt = con_adm.createStatement();
	            
	            //������Ʈ �κ� 2020.06.11
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
	                  
	            
	           
	            
	            stmt.executeUpdate("INSERT INTO car_rent_Company VALUES (1, 'CampingCarMaster', '����Ư���� ������ ', '02-523-1645', '�����', 'minjun123@naver.com');");
	            stmt.executeUpdate("INSERT INTO car_rent_Company VALUES (2, 'ķ�����ƿ�', '��⵵ ������ ��ȱ� ���׵� ', '031-245-1254', '�Ӽ���', 'LSJ1004@naver.com');");
	            stmt.executeUpdate("INSERT INTO car_rent_Company VALUES (3, 'ķ�ΰ��', '��õ������ ������ ����1��  ', '032-456-7351', '�̿���', 'YJLee77@naver.com');");
	            stmt.executeUpdate("INSERT INTO car_rent_Company VALUES (4, '���ε� ķ��', '��⵵ ������ ������ ', '031-841-4258 ', '������ ', 'DoYun1357@naver.com');");
	            stmt.executeUpdate("INSERT INTO car_rent_Company VALUES (5, ' MoveCamping', '��⵵ ���� ������ ', '031-548-7523 ', '��ä�� ', 'Lee_CHone1@naver.com');");
	            stmt.executeUpdate("INSERT INTO car_rent_Company VALUES (6, '����ķ��ī �뿩��', '������ ���ֽ� �߾ӵ� ', '033-422-8852', '��μ�', 'KMSeo3231@naver.com');");
	            stmt.executeUpdate("INSERT INTO car_rent_Company VALUES (7, '������ķ��ī', '����Ư����ġ�� ������  ', '044-220-3020', '������', 'JJM0000@naver.com');");
	            stmt.executeUpdate("INSERT INTO car_rent_Company VALUES (8, '�λ�ķ��ī��Ʈ', '�λ걤���� ���屺 ����� ', '051-200-7301', '�輱��', 'SSunWoo2@naver.com');");
	            stmt.executeUpdate("INSERT INTO car_rent_Company VALUES (9, ' ����ķ��ī �뿩��', '���������� ����� �񷡵�  ', '042-859-9985', '������', 'HSHyunW@naver.com');");
	            stmt.executeUpdate("INSERT INTO car_rent_Company VALUES (10, '�뱸 ķ��ī �뿩��', '�뱸������ ������ ���̵� ', '053-705-9243', '�Ӵ��� ', 'DaEunL@naver.com');");
	            stmt.executeUpdate("INSERT INTO car_rent_Company VALUES (11, '����ķ��ī', '����Ư����ġ�� ���ֽ� ��㵿 ', '064-201-0210', '�̽ÿ�', 'LSWoo909@naver.com');");
	            stmt.executeUpdate("INSERT INTO car_rent_Company VALUES (12, '������������ķ��ī', '����Ư����ġ�� �������� ��ȫ��  ', '064-038-2022', '���ֿ�', 'Joo1Jung@naver.com');");
	            stmt.executeUpdate("INSERT INTO car_rent_Company VALUES (13, 'â������ķ��ī', '��󳲵� â���� ���걸 �����ڵ� ', '055-541-3207', '�̰ǿ�', 'gwLee333@naver.com');");
	            stmt.executeUpdate("INSERT INTO car_rent_Company VALUES (14, '������ķ��ī', '���ֱ����� �ϱ� ���ﵿ ', '062-804-6352', '�迹�� ', 'YesEun@naver.com');");
	            stmt.executeUpdate("INSERT INTO car_rent_Company VALUES (15, 'ToGoCampingCar', '����ϵ� ���ֽ� ������ ��Ƶ�  ', '063-120-9863', '���ϸ�', 'Haryn58@naver.com');");
	            
	            stmt.executeUpdate("INSERT INTO car VALUES (1, '�ٺ�ķ��ī', 1325, 6, 'Hondai', 2000, 554883, 50000, 6, STR_TO_DATE('2000-4-12','%Y-%m-%d'),6);");
	            stmt.executeUpdate("INSERT INTO car VALUES (2, '�ٰ���ķ��ī', 2185, 6, 'Daewon', 2005, 440124, 50000, 2, STR_TO_DATE('2006-3-27','%Y-%m-%d'),2);");
	            stmt.executeUpdate("INSERT INTO car VALUES (3, 'CampingCarseries1', 3207, 8, 'Hondai', 2006, 400223, 55000, 11, STR_TO_DATE('2006-9-21','%Y-%m-%d'),11);");
	            stmt.executeUpdate("INSERT INTO car VALUES (4, '����ķ��ī', 9582, 6, 'Daewon', 2008, 340124, 55000, 12, STR_TO_DATE('2008-12-10','%Y-%m-%d'),12);");
	            stmt.executeUpdate("INSERT INTO car VALUES (5, 'extremeCampCar', 6487, 8, 'Panda', 2010, 300012, 60000, 15, STR_TO_DATE('2010-1-24','%Y-%m-%d'),15);");
	            stmt.executeUpdate("INSERT INTO car VALUES (6, 'Yesssķ��Car', 2203, 6, 'Koogne', 2012, 270124, 60000, 4, STR_TO_DATE('2012-5-25','%Y-%m-%d'),4);");
	            stmt.executeUpdate("INSERT INTO car VALUES (7, 'Yesssķ��Car', 5489, 6, 'Koogne', 2012, 290154, 60000, 14, STR_TO_DATE('2012-5-25','%Y-%m-%d'),14);");
	            stmt.executeUpdate("INSERT INTO car VALUES (8, 'Niceķ��ī', 2367, 8, 'Koogne', 2013, 210489, 65000, 5, STR_TO_DATE('2014-4-21','%Y-%m-%d'),5);");
	            stmt.executeUpdate("INSERT INTO car VALUES (9, 'CampingCarSeries2', 1648, 6, 'Hondai', 2014, 201573, 65000, 10, STR_TO_DATE('2014-6-12','%Y-%m-%d'),10);");
	            stmt.executeUpdate("INSERT INTO car VALUES (10, 'ķ��ī��������', 3489, 6, 'Daewon', 2015, 187423, 65000, 1, STR_TO_DATE('2015-1-19','%Y-%m-%d'),1);");
	            stmt.executeUpdate("INSERT INTO car VALUES (11, 'CarForCamping', 4385, 8, 'Panda', 2015, 154682, 65000, 13, STR_TO_DATE('2015-4-8','%Y-%m-%d'),13);");
	            stmt.executeUpdate("INSERT INTO car VALUES (12, 'ķ��ī��������', 8632, 6, 'Daewon', 2015, 143287, 65000, 8, STR_TO_DATE('2015-8-27','%Y-%m-%d'),8);");
	            stmt.executeUpdate("INSERT INTO car VALUES (13, 'SpecialCampingCar', 7243, 8, 'Panda', 2016, 103425, 70000, 3, STR_TO_DATE('2016-7-10','%Y-%m-%d'),3);");
	            stmt.executeUpdate("INSERT INTO car VALUES (14, 'CampingCarSeries3', 6230, 6, 'Hondai', 2018, 82431, 80000, 9, STR_TO_DATE('2018-8-6','%Y-%m-%d'),9);");
	            stmt.executeUpdate("INSERT INTO car VALUES (15, 'CampingCarSeries3', 1564, 6, 'Hondai', 2018, 62147, 80000, 7, STR_TO_DATE('2018-9-17','%Y-%m-%d'),7);");
	            
	            stmt.executeUpdate("INSERT INTO car_check_info VALUES (1,4,\"���� �ļ�\",\"�̻� ��\",\"�̻� ��\",\"�̻� ��\",\"o\");");
	            stmt.executeUpdate("INSERT INTO car_check_info VALUES (2,9,\"�̻� ��\",\"�̻� ��\",\"�� Ÿ�̾� ��ũ\",\"�̻� ��\",\"o\");");
	            stmt.executeUpdate("INSERT INTO car_check_info VALUES (3,7,\"�̻� ��\",\"�� Ÿ�̾� ��ũ\",\"�̻� ��\",\"�̻� ��\",\"o\");");
	            stmt.executeUpdate("INSERT INTO car_check_info VALUES (4,10,\"���� �ļ�\",\"�̻� ��\",\"�̻� ��\",\"�̻� ��\",\"o\");");
	            stmt.executeUpdate("INSERT INTO car_check_info VALUES (5,15,\"�̻� ��\",\"��Ÿ�̾� ��ũ\",\"��Ÿ�̾� ��ũ\",\"�̻� ��\",\"o\");");
	            stmt.executeUpdate("INSERT INTO car_check_info VALUES (6,3,\"�̻� ��\",\"�̻� ��\",\"�̻� ��\",\"���� ������\",\"o\");");
	            stmt.executeUpdate("INSERT INTO car_check_info VALUES (7,14,\"�̻� ��\",\"�̻� ��\",\"�̻� ��\",\"��â�� �ļ�\",\"o\");");
	            stmt.executeUpdate("INSERT INTO car_check_info VALUES (8,11,\"�̻� ��\",\"���̵� �̷� �ļ�\",\"�̻� ��\",\"�̻� ��\",\"o\");");
	            stmt.executeUpdate("INSERT INTO car_check_info VALUES (9,5,\"�̻� ��\",\"��Ÿ�̾� ��ũ\",\"��Ÿ�̾� ��ũ\",\"�̻� ��\",\"o\");");
	            stmt.executeUpdate("INSERT INTO car_check_info VALUES (10,2,\"�̻� ��\",\"�̻� ��\",\"�̻� ��\",\"���� �ļ�\",\"o\");");
	            stmt.executeUpdate("INSERT INTO car_check_info VALUES (11,13,\"�̻� ��\",\"�� Ÿ�̾� ��ũ\",\"�̻� ��\",\"�̻� ��\",\"o\");");
	            stmt.executeUpdate("INSERT INTO car_check_info VALUES (12,1,\"�̻� ��\",\"�̻� ��\",\"��Ÿ�̾� ��ũ\",\"���� �ļ�\",\"o\");");
	            stmt.executeUpdate("INSERT INTO car_check_info VALUES (13,12,\"�̻� ��\",\"�̻� ��\",\"���̵� �̷� �ļ�\",\"�̻� ��\",\"o\");");
	            stmt.executeUpdate("INSERT INTO car_check_info VALUES (14,6,\"�����ļ�\",\"���̵� �̷� �ļ�\",\"�̻� ��\",\"�̻� ��\",\"o\");");
	            stmt.executeUpdate("INSERT INTO car_check_info VALUES (15,8,\"���� �ļ�\",\"�� Ÿ�̾� ��ũ\",\"�̻� ��\",\"�̻� ��\",\"o\");");
	            
	            stmt.executeUpdate("INSERT INTO RepairShop VALUES (1,\"����ī����\",\"����Ư���� ������ �߰\",\"02-452-1325\",\"�����\",\"WooJ123@naver.com\");");
	            stmt.executeUpdate("INSERT INTO RepairShop VALUES (2,\"����ī����\",\"��⵵ ������ ��ȱ� ���׵�\",\"031-215-5432\",\"�����\",\"JMKang@naver.com\");");
	            stmt.executeUpdate("INSERT INTO RepairShop VALUES (3,\"���������\",\"��õ������ ������ ������\",\"032-426-1231\",\"������\",\"Jone11@naver.com\");");
	            stmt.executeUpdate("INSERT INTO RepairShop VALUES (4,\"����ī��Ÿ\",\"��⵵ ������ ������\",\"031-813-1148\",\"�̴���\",\"LDH332412@naver.com\");");
	            stmt.executeUpdate("INSERT INTO RepairShop VALUES (5,\"����ī����\",\"��⵵ ���� ������\",\"031-543-8853\",\"������\",\"GoodJung@naver.com\");");
	            stmt.executeUpdate("INSERT INTO RepairShop VALUES (6,\"�̵�ī����\",\"������ ���ֽ� �߾ӵ�\",\"033-472-5132\",\"��ȣ��\",\"HHLee@naver.com\");");
	            stmt.executeUpdate("INSERT INTO RepairShop VALUES (7,\"Ư��ī����\",\"����Ư����ġ�� ������\",\"044-210-8541\",\"�����\",\"JDIDoit@naver.com\");");
	            stmt.executeUpdate("INSERT INTO RepairShop VALUES (8,\"���ī���������\",\"�λ걤���� ���屺 �����\",\"051-204-0214\",\"������\",\"BadBoy@naver.com\");");
	            stmt.executeUpdate("INSERT INTO RepairShop VALUES (9,\"����ī����\",\"���������� ����� �񷡵�\",\"042-853-1215\",\"������\",\"HTae@naver.com\");");
	            stmt.executeUpdate("INSERT INTO RepairShop VALUES (10,\"����ī����\",\"�뱸������ ������ ���̵�\",\"053-710-9555\",\"������\",\"Jisso@naver.com\");");
	            stmt.executeUpdate("INSERT INTO RepairShop VALUES (11,\"����ī����\",\"����Ư����ġ�� ���ֽ� ��㵿\",\"064-211-5275\",\"��â��\",\"CHang999@naver.com\");");
	            stmt.executeUpdate("INSERT INTO RepairShop VALUES (12,\"���ī����\",\"����Ư����ġ�� �������� ��ȫ��\",\"064-033-8578\",\"������\",\"SimJH@naver.com\");");
	            stmt.executeUpdate("INSERT INTO RepairShop VALUES (13,\"����ī����\",\"��󳲵� â���� ���걸 �����ڵ�\",\"055-541-1502\",\"�ڱ���\",\"Weatherman@naver.com\");");
	            stmt.executeUpdate("INSERT INTO RepairShop VALUES (14,\"���ī����\",\"���ֱ����� �ϱ� ���ﵿ\",\"062-803-4487\",\"����\",\"Go0H@naver.com\");");
	            stmt.executeUpdate("INSERT INTO RepairShop VALUES (15,\"���ī����\",\"����ϵ� ���ֽ� ������ ��Ƶ�\",\"063-120-9863\",\"����ö\",\"GCheol@naver.com\");");
	            
	            stmt.executeUpdate("INSERT INTO customers VALUES (762531,\"�Ӽ���\",\"����Ư���� ������ ��䵿\",\"010-8465-2046\",\"SH2046@naver.com\");");
	            stmt.executeUpdate("INSERT INTO customers VALUES (620154,\"������\",\"����Ư���� ���Ǳ� ��õ��\",\"010-2432-0125\",\"Yejun0125@naver.com\");");
	            stmt.executeUpdate("INSERT INTO customers VALUES (905432,\"�̵���\",\"��⵵ ������ �д籸 ������\",\"010-2105-9982\",\"DoYunLee2105@naver.com\");");
	            stmt.executeUpdate("INSERT INTO customers VALUES (320156,\"���ֿ�\",\"�λ걤���� �λ����� ������\",\"010-7523-2015\",\"KJone631@naver.com\");");
	            stmt.executeUpdate("INSERT INTO customers VALUES (426583,\"���ؼ�\",\"��󳲵� â���� ���걸 ������\",\"010-8542-0012\",\"KKJunSeo012@naver.com\");");
	            stmt.executeUpdate("INSERT INTO customers VALUES (201603,\"���ؿ�\",\"���󳲵� ��õ�� ���\",\"010-4352-0156\"   ,\"HandsomJW@naver.com\");");
	            stmt.executeUpdate("INSERT INTO customers VALUES (302154,\"������\",\"���󳲵� ������ ������\",\"010-4852-2356\",\"KangHW@naver.com\");");
	            stmt.executeUpdate("INSERT INTO customers VALUES (301264,\"������\",\"����ϵ� ���ֽ� �ϻ걸 ��ȭ��2��\",\"010-9421-0605\",\"JiiHun65@naver.com\");");
	            stmt.executeUpdate("INSERT INTO customers VALUES (532015,\"������\",\"��û���� ���ɽ� ��õ��\",\"010-3287-9182\",\"SuA32@naver.com\");");
	            stmt.executeUpdate("INSERT INTO customers VALUES (862513,\"�����\",\"��û���� �¾ȱ� �¾���\",\"010-0248-5347\",\"DAEUN2485347@naver.com\");");
	            stmt.executeUpdate("INSERT INTO customers VALUES (201543,\"������\",\"��⵵ �Ȼ�� �ܿ���\",\"010-9820-0031\",\"Lim0031@naver.com\");");
	            stmt.executeUpdate("INSERT INTO customers VALUES (753216,\"������\",\"����Ư����ġ�� ���ֽ� ����\",\"010-5103-0569\",\"SeoJinee@naver.com\");");
	            stmt.executeUpdate("INSERT INTO customers VALUES (102436,\"�迬��\",\"����Ư����ġ�� �������� �ۻ굿\",\"010-0504-0104\",\"KKKWoo@naver.com\");");
	            stmt.executeUpdate("INSERT INTO customers VALUES (510546,\"�迹��\",\"��û�ϵ� ���ֽ� ������\",\"010-7821-3582\",\"SkyCastle7821@naver.com\");");
	            stmt.executeUpdate("INSERT INTO customers VALUES (105468,\"�̰ǿ�\",\"��⵵ �����ν� �Ű\",\"010-7744-5521\",\"7744Gun@naver.com\");");
	            
	            stmt.executeUpdate("INSERT INTO car_rent VALUES (1,4,762531,12,\"2020-02-10\",\"2020-02-15\",55000*6,\"2020-02-09\",\"�� 2L * 12\",1000*12,762531,4,12);");
	            stmt.executeUpdate("INSERT INTO car_rent VALUES (2,9,620154,10,\"2020-02-14\",\"2020-02-20\",65000*7,\"2020-02-13\",\"�޴�� ��������, �� 2L * 14\",20000+1000*14,620154,9,10);");
	            stmt.executeUpdate("INSERT INTO car_rent VALUES (3,7,905432,14,\"2020-02-16\",\"2020-02-23\",60000*8,\"2020-02-15\",\"�޴�� ��������, ���� 3kg, �� 2L * 16\",20000+50000+1000*16,905432,7,14);");
	            stmt.executeUpdate("INSERT INTO car_rent VALUES (4,10,320156,1,\"2020-02-19\",\"2020-02-27\",65000*9,\"2020-02-18\",\"�� 2L * 18\",1000*18,320156,10,1);");
	            stmt.executeUpdate("INSERT INTO car_rent VALUES (5,15,426583,7,\"2020-02-20\",\"2020-02-28\",80000*9,\"2020-02-19\",\"�� 1kg, �� 2L * 18\",30000+1000*18,426583,15,7);");
	            stmt.executeUpdate("INSERT INTO car_rent VALUES (6,3,201603,11,\"2020-02-24\",\"2020-03-01\",55000*6,\"2020-02-23\",\"�� 2L * 12\",1000*12,201603,3,11);");
	            stmt.executeUpdate("INSERT INTO car_rent VALUES (7,14,302154,9,\"2020-03-10\",\"2020-03-16\",80000*7,\"2020-03-09\",\"�޴�� ��������, ���� 3kg, �� 2L * 14\",20000+50000+1000*14,302154,14,9);");
	            stmt.executeUpdate("INSERT INTO car_rent VALUES (8,11,301264,13,\"2020-03-12\",\"2020-03-16\",65000*5,\"2020-03-11\",\"�� 2L * 10\",1000*10,301264,11,13);");
	            stmt.executeUpdate("INSERT INTO car_rent VALUES (9,5,532015,15,\"2020-03-21\",\"2020-03-24\",60000*4,\"2020-03-20\",\"���ݷ� 8��, �� 2L * 8\",1000*8+1000*8,532015,5,15);");
	            stmt.executeUpdate("INSERT INTO car_rent VALUES (10,2,862513,2,\"2020-03-24\",\"2020-03-26\",50000*3,\"2020-03-23\",\"�� 2L * 6\",1000*6,862513,2,2);");
	            stmt.executeUpdate("INSERT INTO car_rent VALUES (11,13,201543,3,\"2020-04-02\",\"2020-04-05\",70000*4,\"2020-04-01\",\"�� 2L * 8\",1000*8,201543,13,3);");
	            stmt.executeUpdate("INSERT INTO car_rent VALUES (12,1,753216,6,\"2020-04-10\",\"2020-04-13\",50000*4,\"2020-04-09\",\"�� 2L * 8\",1000*8,753216,1,6);");
	            stmt.executeUpdate("INSERT INTO car_rent VALUES (13,12,102436,8,\"2020-04-16\",\"2020-04-19\",65000*3,\"2020-04-15\",\"���ݷ� 3��, �� 2L * 6\",1000*6+1000*3,102436,12,8);");
	            stmt.executeUpdate("INSERT INTO car_rent VALUES (14,6,510546,4,\"2020-04-25\",\"2020-04-27\",60000*3,\"2020-04-24\",\"�� 2L * 6\",1000*6,510546,6,4);");
	            stmt.executeUpdate("INSERT INTO car_rent VALUES (15,8,105468,5,\"2020-05-04\",\"2020-05-08\",65000*5,\"2020-05-03\",\"�� 2L * 10\",1000*10,105468,8,5);");
	           
	            stmt.executeUpdate("INSERT INTO repair_info VALUES (1,4,12,12,762531,\"�� ���� �ļ�\",\"2020-02-13\",100000,\"2020-02-17\",\"�� ����\",4,12,762531,12);");
	            stmt.executeUpdate("INSERT INTO repair_info VALUES (2,9,10,10,620154,\"������ �� Ÿ�̾� ��ũ\",\"2020-02-15\",200000,\"2020-02-22\",\"���� ���� ��ü\",9,10,620154,10);");
	            stmt.executeUpdate("INSERT INTO repair_info VALUES (3,7,14,14,905432,\"���� �� Ÿ�̾� ��ũ\",\"2020-02-17\",200000,\"2020-02-25\",\"�ð��� ��ü\",7,14,905432,14);");
	            stmt.executeUpdate("INSERT INTO repair_info VALUES (4,10,1,1,320156,\"�� ���� �ļ�\",\"2020-02-19\",100000,\"2020-03-01\",\"������ ��Ʈ ��ü\",10,1,320156,1);");
	            stmt.executeUpdate("INSERT INTO repair_info VALUES (5,15,7,7,426583,\"���� ��Ÿ�̾� ��ũ\",\"2020-02-20\",200000,\"2020-03-02\",\"���¼� ��Ʈ ��ü\",15,7,426583,7);");
	            stmt.executeUpdate("INSERT INTO repair_info VALUES (6,3,11,11,201603,\"�� ���� ������\",\"2020-02-21\",50000,\"2020-03-03\",\"������ ������Ʈ ����\",3,11,201603,11);");
	            stmt.executeUpdate("INSERT INTO repair_info VALUES (7,14,9,9,302154,\"��â�� �ļ�\",\"2020-03-01\",150000,\"2020-03-18\",\"�Ĺ̵� ��ü\",14,9,302154,9);");
	            stmt.executeUpdate("INSERT INTO repair_info VALUES (8,11,13,13,301264,\"���� ���̵� �̷� �ļ�\",\"2020-03-13\",180000,\"2020-03-18\",\"���� �� ����\",11,13,301264,13);");
	            stmt.executeUpdate("INSERT INTO repair_info VALUES (9,5,15,15,532015,\"���� ��Ÿ�̾� ��ũ ������ ��Ÿ�̾� ��ũ\",\"2020-03-15\",200000+200000,\"2020-03-26\",\"���� ���� ��ü\",5,15,532015,15);");
	            stmt.executeUpdate("INSERT INTO repair_info VALUES (10,2,2,2,862513,\"�� ���� �ļ�\",\"2020-03-17\",100000,\"2020-03-28\",\"������ ������Ʈ ����\",2,2,862513,2);");
	            stmt.executeUpdate("INSERT INTO repair_info VALUES (11,13,3,3,201543,\"���� �� Ÿ�̾� ��ũ\",\"2020-03-20\",200000,\"2020-04-07\",\"��̷� ��ü\",13,3,201543,3);");
	            stmt.executeUpdate("INSERT INTO repair_info VALUES (12,1,6,6,753216,\"������ ��Ÿ�̾� ��ũ �� ���� �ļ�\",\"2020-03-23\",200000+100000,\"2020-04-15\",\"�Ĺ̵� �� ���ε� ��ü\",1,6,753216,6);");
	            stmt.executeUpdate("INSERT INTO repair_info VALUES (13,12,8,8,102436,\"������ ���̵� �̷� �ļ�\",\"2020-04-13\",180000,\"2020-04-21\",\"������ ��Ʈ ��ü\",12,8,102436,8);");
	            stmt.executeUpdate("INSERT INTO repair_info VALUES (14,6,4,4,510546,\"�� ���� �ļ� ����  ���̵� �̷� �ļ�\",\"2020-04-17\",100000+180000,\"2020-04-29\",\"���� �� Ÿ�̾� ��ü\",6,4,510546,4);");
	            stmt.executeUpdate("INSERT INTO repair_info VALUES (15,8,5,5,105468,\"�� ���� �ļ� ���� �� Ÿ�̾� ��ũ\",\"2020-05-3\",100000+200000,\"2020-05-10\",\"�ð��� ��ü �� ���� ���� ��ü\",8,5,105468,5);");
	            
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
	           
	            System.out.println("DB �ʱ�ȭ �Ϸ�");
	            
	            //2020.06.11
	           
	       
	         } catch (Exception e4) {
	            System.out.println("DB �ʱ�ȭ ����" + e4);
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
	        	 
	        	 Search search = new Search(con_adm);//���� ������ 1��° �߰�
	        	 search.setVisible(true);//���� ������ ������ �߰�
	        	 //search
	        	 //RepairShop shop = new RepairShop(con_adm);
	        	 
	        	 //shop.setVisible(true);
	         }
	      } catch (Exception e2) {
	         System.out.println("���� �б� ���� :" + e2);
	         System.out.println("���� �߻�!"); 
	      }

   }
      
}