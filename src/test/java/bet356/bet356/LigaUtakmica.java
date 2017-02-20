package bet356.bet356;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class LigaUtakmica {


	public WebDriver driver;
	public String baseUrl,liga,utakmica;
	public String  liga1,utakmica1;
	 
	@SuppressWarnings("unused")
	private StringBuffer verificationErrors = new StringBuffer();
	 
	@Before
	public void setUp() throws Exception {
		 
		baseUrl="https://www.bet365.com/?&cb=10325414067#/HO/";
		///liga1="Northern Ireland Reserve League";
		//utakmica1="Glentoran Reserves v Coleraine Reserves";
		
	}

	@Test
	public void test() { 
 
		File file = new File("C:/Users/Administrator/bet365.properties");
    	FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		
		//load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
			
	    	WebDriver driver = new FirefoxDriver();
	    	driver.get(baseUrl);
	    	Wait.seconds(10);
	    	driver.findElement(By.linkText("English")).click();
	    	Wait.seconds(10);
	     	driver.findElement(By.linkText("English")).click();
	    	Wait.seconds(10);
	    	driver.findElement(By.xpath("//*[text()='Soccer']")).click();
	    	//driver.findElement(By.cssSelector("div.wn-Classification")).click();
	    	//(//a[contains(text(),'Save')])[2]")).click();
	    	
	    	Wait.seconds(10);
	     	//Biras liga
	    	 driver.findElement(By.xpath("//*[text()='"+prop.getProperty("liga")+"']")).click();
	    	Wait.seconds(5);
	    	//Biras utakmica
	    	driver.findElement(By.xpath("//*[text()='"+prop.getProperty("utakmica")+"']")).click();
	    	Wait.seconds(5);

	    	String imaMain=driver.findElement(By.cssSelector("div.wl-NavBarScroller_HScroll")).getText();
	    	assertTrue("Ne Postoi Asian Lines za "+prop.getProperty("utakmica")+" ",imaMain.contains("Asian Lines"));
	    	System.out.println("Postoi Asian Lines za "+prop.getProperty("utakmica"));


}
}
