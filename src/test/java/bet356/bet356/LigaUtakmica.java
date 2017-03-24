package bet356.bet356;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import javax.mail.PasswordAuthentication;
 

public class LigaUtakmica {


	public WebDriver driver;
	public String baseUrl,liga,utakmica;
	public String  liga1,utakmica1;
	public String poraka; 
	
	@SuppressWarnings("unused")
	private StringBuffer verificationErrors = new StringBuffer();
	
	@BeforeMethod
	public void setUp() throws Exception {
		 
		baseUrl="https://www.bet365.com/?&cb=10325414067#/HO/";
		///liga1="Northern Ireland Reserve League";
		//utakmica1="Glentoran Reserves v Coleraine Reserves";
		
	}

	@Test
	public void test() { 
 
		File file = new File("C:/Users/Administrator/Desktop/bet365.properties");
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
	    	if (imaMain.contains("Asian Lines")) 
	    	
	    	  poraka=("Postoi Asian Lines za "+prop.getProperty("utakmica"));
	    	else
	    		poraka=("Ne Postoi Asian Lines za "+prop.getProperty("utakmica"));	

	        final String username = "jenkinss4e@gmail.com";
	        final String password = "Passwords4e";
	        String to = "tomi.pocevski@gmail.com";
	        		
	        Properties props = new Properties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");

	        Session session = Session.getInstance(props,
	          new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(username, password);
	            }
	          });

	        try {

	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("pecakova.bojana@gmail.com"));
	            message.setRecipients(Message.RecipientType.TO,
	                InternetAddress.parse(to));
	            message.setSubject("A testing mail header !!!");
	            message.setText(poraka);

	            Transport.send(message);

	            System.out.println("Done");

	        } 

	        catch (MessagingException e) 
	        {
	            // throw new RuntimeException(e);
	            System.out.println("Username or Password are incorrect ... exiting !");
	        }
	}     
}
