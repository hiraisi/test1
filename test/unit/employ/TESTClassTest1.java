package unit.employ;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;






import unit.user.parts.*;
import user.bean.RegistrantInfo;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.File;
import java.io.FileReader;

import java.io.BufferedReader;


import javax.naming.Context;
import javax.naming.InitialContext;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import static org.junit.Assert.*;

import org.junit.Test;

public class TESTClassTest1 {

	//@BeforeClass
	//public static void setUpBeforeClass() throws Exception {
	//}
	static RegInfDAO regDAO;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		
		System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
		System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");
		InitialContext ic = new InitialContext();
		ic.createSubcontext("java:");
		ic.createSubcontext("java:comp");
		ic.createSubcontext("java:comp/env");
		ic.createSubcontext("java:comp/env/jdbc");
		MysqlDataSource ds = new MysqlDataSource();
	    ds.setUser("root");    
	    ds.setPassword("root");
	    ds.setURL("jdbc:mysql://localhost/Task");
	    ic.bind("java:comp/env/jdbc/Task", ds);
	    
	    
	    
		
	}
	
	@Test
	public void test3() {
		System.out.println("UT002-001 start");
		RegistrantInfo regInfo;
		RegistrantInfo a;
		RegistrantInfo b;
		ArrayList<RegistrantInfo> arrayRegInfo = new ArrayList<RegistrantInfo>();
		regInfo = new RegistrantInfo("001","鈴木太郎","35");
		arrayRegInfo.add(regInfo);
		regInfo = new RegistrantInfo("002","Tommy","25");
		arrayRegInfo.add(regInfo);
		regInfo = new RegistrantInfo("003","山田花子","30");
		arrayRegInfo.add(regInfo);
		regInfo = new RegistrantInfo("004","佐藤路未央","28");
		arrayRegInfo.add(regInfo);
        regDAO=new RegInfDAO();

        regDAO.insert("004","佐藤路未央","28");
       
        for (int i=0; i<arrayRegInfo.size();i++){
        	a=arrayRegInfo.get(i);
        	b=regDAO.getReglist().get(i);      	
        	assertEquals(a.getrId(),b.getrId());
        	assertEquals(a.getrName(),b.getrName());
        	assertEquals(a.getrAge(),b.getrAge());
        }
        regDAO.delete("004");
        System.out.println("UT002-001 end");
        
        System.out.println("UT002-002 start");
        a=null;
        b=null;
        arrayRegInfo.clear();
		regInfo = new RegistrantInfo("001","鈴木太郎","35");
		arrayRegInfo.add(regInfo);
		regInfo = new RegistrantInfo("002","Michael","29");
		arrayRegInfo.add(regInfo);
		regInfo = new RegistrantInfo("003","山田花子","30");
		arrayRegInfo.add(regInfo);
        regDAO.update("002","Michael","29");
        

        for (int i=0; i<arrayRegInfo.size();i++){
        	a=arrayRegInfo.get(i);
        	b=regDAO.getReglist().get(i);      	
        	assertEquals(a.getrId(),b.getrId());
        	assertEquals(a.getrName(),b.getrName());
        	assertEquals(a.getrAge(),b.getrAge());
        }
        regDAO.update("002","Tommy","25");
        System.out.println("UT002-002 end");
        
        System.out.println("UT002-003 start");
        arrayRegInfo.clear();
		regInfo = new RegistrantInfo("001","鈴木太郎","35");
		arrayRegInfo.add(regInfo);
		regInfo = new RegistrantInfo("002","Tommy","25");
		arrayRegInfo.add(regInfo);
		regDAO.delete("003");
		
        for (int i=0; i<arrayRegInfo.size();i++){
        	a=arrayRegInfo.get(i);
        	b=regDAO.getReglist().get(i);      	
        	assertEquals(a.getrId(),b.getrId());
        	assertEquals(a.getrName(),b.getrName());
        	assertEquals(a.getrAge(),b.getrAge());
        }
        
        regDAO.insert("003","山田花子","30");
        System.out.println("UT002-003 end");
        
        System.out.println("UT002-004 start");
		
        arrayRegInfo.clear();
		regInfo = new RegistrantInfo("001","鈴木太郎","35");
		arrayRegInfo.add(regInfo);
		regInfo = new RegistrantInfo("002","Tommy","25");
		arrayRegInfo.add(regInfo);
		regInfo = new RegistrantInfo("003","山田花子","30");
		arrayRegInfo.add(regInfo);
        for (int i=0; i<arrayRegInfo.size();i++){
        	a=arrayRegInfo.get(i);
        	b=regDAO.getReglist().get(i);      	
        	assertEquals(a.getrId(),b.getrId());
        	assertEquals(a.getrName(),b.getrName());
        	assertEquals(a.getrAge(),b.getrAge());
        }
        
        System.out.println("UT002-004 end");
        
        System.out.println("UT002-005 start");
        regDAO.delete("001");
        regDAO.delete("002");
        regDAO.delete("003");
        assertEquals("001",regDAO.getNextId());
        System.out.println("UT002-005 end");
	}

	
	
    @Test
    public void test2() {
    	System.out.println("UT001-001 start");
    	RegInfCheck obj1 =new RegInfCheck();
    	obj1.checkName("0123456789");
    	assertEquals("",obj1.getErrMsg());
    	System.out.println("UT001-001 end");
    	
    	System.out.println("UT001-002 start");
    	RegInfCheck obj2 =new RegInfCheck();
    	obj2.checkName("あいうえおかきくけこ");
    	assertEquals("",obj2.getErrMsg());
    	System.out.println("UT001-002 end");
    	
    	System.out.println("UT001-003 start");
    	RegInfCheck obj3 =new RegInfCheck();
    	obj3.checkName("01234567890");
    	assertEquals("名前は10桁以内で入力してください。<br />",obj3.getErrMsg());
    	System.out.println("UT001-003 end");
    	
    	System.out.println("UT001-004 start");
    	RegInfCheck obj4 =new RegInfCheck();
    	obj4.checkName("あいうえおかきくけこさ");
    	assertEquals("名前は10桁以内で入力してください。<br />",obj4.getErrMsg());
    	System.out.println("UT001-004 end");
    	
    	System.out.println("UT001-005 start");
    	RegInfCheck obj5 =new RegInfCheck();
    	obj5.checkAge("16");
    	assertEquals("",obj5.getErrMsg());
    	System.out.println("UT001-005 end");
    	
    	System.out.println("UT001-006 start");
    	RegInfCheck obj6 =new RegInfCheck();
    	obj6.checkAge("60");
    	assertEquals("",obj6.getErrMsg());
    	System.out.println("UT001-006 end");
    	
    	System.out.println("UT001-007 start");
    	RegInfCheck obj7 =new RegInfCheck();
    	obj7.checkAge("15");
    	assertEquals("年齢は(16-60)の範囲で入力してください。<br />",obj7.getErrMsg());
    	System.out.println("UT001-007 end");
    	
    	System.out.println("UT001-008 start");
    	RegInfCheck obj8 =new RegInfCheck();
    	obj8.checkAge("61");
    	assertEquals("年齢は(16-60)の範囲で入力してください。<br />",obj8.getErrMsg());
    	System.out.println("UT001-008 end");
    	
    	System.out.println("UT001-009 start");
    	RegInfCheck obj9 =new RegInfCheck();
    	obj9.checkAge("１６");
    	assertEquals("年齢は数値(半角)で入力してください。<br />",obj9.getErrMsg());
    	System.out.println("UT001-009 end");
    	
    	System.out.println("UT001-010 start");
    	RegInfCheck obj10 =new RegInfCheck();
    	obj10.checkAge("あいうえお");
    	assertEquals("年齢は数値(半角)で入力してください。<br />年齢は(16-60)の範囲で入力してください。<br />",obj10.getErrMsg());
    	System.out.println("UT001-010 end");
    	
    	System.out.println("UT001-011 start");
    	RegInfCheck obj11 =new RegInfCheck();
    	obj11.checkAge("16あいうえお");
    	assertEquals("年齢は数値(半角)で入力してください。<br />年齢は(16-60)の範囲で入力してください。<br />",obj11.getErrMsg());
    	System.out.println("UT001-011 end");
    	
    	System.out.println("UT001-012 start");
    	RegInfCheck obj12 =new RegInfCheck();
    	obj12.checkId("999");
    	assertEquals("",obj12.getErrMsg());
    	System.out.println("UT001-012 end");
    	
    	System.out.println("UT001-013 start");
    	RegInfCheck obj13 =new RegInfCheck();
    	obj13.checkId("1000");
    	assertEquals("登録可能なID（999）を超えています。管理者に問い合わせてください。<br />",obj13.getErrMsg());
    	System.out.println("UT001-013 end");
    }
	
	@Test
	public void test1() {
		String str=null;
		String STR=null;
		System.out.println("UT003-001 start");
		OutLog.outLogDmp("sample：サンプル");
		
		try{
		
		FileReader fr = new FileReader("C:\\test\\log\\log.txt");
		BufferedReader br = new BufferedReader(fr);

		do{
			STR=str;
			str=br.readLine();

			
		}while(str!=null);

		DateFormat YMD = new SimpleDateFormat("yyyy/mm/dd");
		DateFormat HMS = new SimpleDateFormat("hh:mm:ss");

		String[] str2Ary1 = STR.split(" ");
		String str1 = str2Ary1[1].replace(":sample：サンプル", "");
		String str2 = str2Ary1[1].replace(str1, "");

        assertEquals(":sample：サンプル",str2);

		YMD.setLenient(false);
		HMS.setLenient(false);

		try{

			YMD.parse(str2Ary1[0]);

			HMS.parse(str1);

		}catch (ParseException e){
			fail();
		}
        
		br.close();
		File file = new File("C:\\test\\log\\log.txt");
		
		file.delete();
		File newfile = new File("C:\\test\\log\\log.txt");
		newfile.createNewFile();
		
		System.out.println("UT003-001 end");
		
		System.out.println("UT003-002 start");
		
		FileReader fr1 = new FileReader("C:\\test\\log\\log.txt");
		BufferedReader br1 = new BufferedReader(fr1);
		OutLog.outLogDmp("12345");
		do{
			STR=str;
			str=br1.readLine();

			
		}while(str!=null);

		DateFormat YMD1 = new SimpleDateFormat("yyyy/mm/dd");
		DateFormat HMS1 = new SimpleDateFormat("hh:mm:ss");

		String[] str2Ary2 = STR.split(" ");
		String str3 = str2Ary2[1].replace(":12345", "");
		String str4 = str2Ary2[1].replace(str3, "");

        assertEquals(":12345",str4);

		YMD.setLenient(false);
		HMS.setLenient(false);

		try{

			YMD1.parse(str2Ary1[0]);

			HMS1.parse(str3);

		}catch (ParseException e){
			fail();
		}
		br1.close();
		System.out.println("UT003-002 end");
		
		}catch(FileNotFoundException e){
		    System.out.println(e);
		}catch(IOException e){
		    System.out.println(e);
		}
		
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception{
		regDAO.close();
	}


}
