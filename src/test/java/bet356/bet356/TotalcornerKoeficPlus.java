package bet356.bet356;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TotalcornerKoeficPlus {

	public WebDriver driver;
	public String baseUrl,liga,utakmica;
	public String  liga1,utakmica1;
	private Double koeficientPlus;
	private Integer pogolemiOdKoeficineti=0;
	private Integer pomaliOdKoeficineti=0;
	private Integer pogolemiOdKoeficinetiGosti=0;
	private Integer pomaliOdKoeficinetiGosti=0;
	public String poraka;
	
	@SuppressWarnings("unused")
	private StringBuffer verificationErrors = new StringBuffer();
	 
	@Before
	public void setUp() throws Exception {
		baseUrl="http://www.totalcorner.com";
		liga1="Northern Ireland Reserve League";
		utakmica1="Glentoran Reserves v Coleraine Reserves";
		
	}

	@Test
	public void test() { 
 
	/*	File file = new File("C:/Users/User07/Desktop/totalCorner.properties");
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
		*/
			
	    	WebDriver driver = new FirefoxDriver();
	    	 driver.get(baseUrl);
	    	//driver.get(baseUrl + "/");
	    	Wait.seconds(10);
	    	 
	    	driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
	    	Wait.seconds(10);
	    	driver.findElement(By.id("inputEmail3")).clear();
	    	driver.findElement(By.id("inputEmail3")).sendKeys("mirce");
	    	driver.findElement(By.id("inputPassword3")).clear();
	    	driver.findElement(By.id("inputPassword3")).sendKeys("123456");
	    	driver.findElement(By.xpath("//button[@type='submit']")).click();
	    	Wait.seconds(10);
	    	driver.findElement(By.linkText("Today List")).click();
	    	driver.findElement(By.id("btn_only_show_corner")).click();
	    	Wait.seconds(15);
	    	int BrojNatprevari =driver.findElements(By.cssSelector(".text-right.match_home")).size();
	    	System.out.println("Broj na denesni natprevari "+BrojNatprevari);
	    	poraka ="Broj na denesni natprevari "+BrojNatprevari+"\n";
	    	
	      for (int i=1;i<=30;i++)
	     	{
	    	  WebElement element = driver.findElement(By.cssSelector("table#inplay_match_table tr:nth-child("+i+") td:nth-child(14) a:nth-child(1)"));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

	    		        
	    //	driver.findElement(By.cssSelector("table#inplay_match_table tr:nth-child("+i+") td:nth-child(14) a:nth-child(1)")).click();
	    		Wait.seconds(10);

	    		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    		driver.switchTo().window(tabs2.get(1));
		     	//////driver.findElement(By.xpath("(//button[@type='button'])[10]")).click();
	    		Wait.seconds(10);
	    		WebElement tabelaCorners=driver.findElement(By.id("bet_div"));
	    		WebElement tdCorners=tabelaCorners.findElement(By.xpath("//*[contains(@class,'panel-body')][1]"));
	    		String corners=tdCorners.getText();
	    		//System.out.println("--------- "+corners);
	    		 if (corners.contains("Asian Corners")){
	    		String corners1=StringUtils.substringBetween(corners, "Asian Corners", ")");
	    		///System.out.println("Asian Corners 1 "+ corners1 );
	    		 
	    		String koeficient= corners1.substring(corners1.lastIndexOf("(")+1);
	    	 	
	    		System.out.println("Koenficient "+ koeficient);
	    		 
	    		koeficientPlus=Double.parseDouble(koeficient);//+Double.parseDouble(prop.getProperty("koeficinetPlus"));
	    				//Integer.valueOf(koeficient)+Integer.valueOf(prop.getProperty("koeficinetPlus"));
	    		System.out.println("Koenficient plus "+ koeficientPlus);
	    		 }
	    		else
	    			System.out.println("Ne postoi Asian Corners"); 
	    					
	    		WebElement kolkuNatprevari=driver.findElement(By.xpath("//*[contains(@class,'home_stats_table')]"));
	    		WebElement trkolkuNatprevari=kolkuNatprevari.findElement(By.xpath("//tr/td[2]"));
	    		String tdKolkunatprevari=trkolkuNatprevari.getText();
	    	
	    		System.out.println("Broj na odigrani natprevari domasni "+tdKolkunatprevari);
	    		

	    		WebElement divElement=driver.findElement(By.xpath("//div[contains(@class,'moble_no_left_padding')]"));
	    		WebElement tablekolkuNatprevariGosti=divElement.findElement(By.xpath("//table[contains(@class,'away_stats_table')]"));
	    		String kolkunatprevariGosti=tablekolkuNatprevariGosti.getText();

	    		String  natprevariGosti=StringUtils.substringBetween(kolkunatprevariGosti, "Win Prob.", "%");
	    		System.out.println("natprevariGosti"+natprevariGosti); 
	    		
	    		String tocnoNatprevari;
	    		
	    			tocnoNatprevari=natprevariGosti.substring(7,9);
	    		if (tocnoNatprevari.contains(" "))
	    			tocnoNatprevari=natprevariGosti.substring(7,8);
	    		
	    		System.out.println("Broj na odigrani natprevari gosti "+tocnoNatprevari); 
	    		
	    		Integer BrojtocniNatprevariGosti=Integer.valueOf(tocnoNatprevari);
	    		
	    		//Koi se utakmicite 
	    		String tabelaKoiUtakmiciDomasni=driver.findElement(By.cssSelector("div#match_title_div a:nth-child(3)")).getText();
	    		String tabelaKoiUtakmiciGosti=driver.findElement(By.cssSelector("div#match_title_div a:nth-child(4)")).getText();
	    		System.out.println("tabelaKoiUtakmiciDomasni "+tabelaKoiUtakmiciDomasni); 
	    		System.out.println("tabelaKoiUtakmiciGosti "+tabelaKoiUtakmiciGosti); 
	    		
	    		//Koi se utakmicite Kraj
	    		//Domasni 
	    		pogolemiOdKoeficineti=0;
	    		pomaliOdKoeficineti=0;
	    		if (Integer.valueOf(tdKolkunatprevari)<20 ||  BrojtocniNatprevariGosti <20){
	    			System.out.println("Nema povejke od 20 natprevari odigrani");
	    		 poraka+= "Nema povejke od 20 natprevari odigrani"+tabelaKoiUtakmiciDomasni+"--"+tabelaKoiUtakmiciGosti+"\n";}
	    		else{
	    		for (int iii=1;iii<=12;iii++){
	    		WebElement tabelaKorenerNatprevarDomasni=driver.findElement(By.cssSelector("table#home_history_table tr:nth-child("+iii+") td:nth-child(12) span:nth-child(3)"));
	    	 	String kojNatprevar=tabelaKorenerNatprevarDomasni.getText();
	    	 	//////System.out.println("kojNatprevar "+kojNatprevar);  
	    	 	Double kojNatprevar1=Double.parseDouble(kojNatprevar);
	    	  
	    		 int retval = Double.compare( kojNatprevar1, koeficientPlus);
	    		    
	    	     if(retval > 0) {
	    	    	 pogolemiOdKoeficineti =pogolemiOdKoeficineti + 1;
	    	     }
	    	     else if(retval < 0) {
	    	    	 pomaliOdKoeficineti = pomaliOdKoeficineti + 1; 
	    	     }
	    		}
	    		System.out.println(tabelaKoiUtakmiciDomasni+" -Pogolemi od koeficent plus "+ pogolemiOdKoeficineti);
	    		poraka+="Broj na odigrani natprevari domasni "+tabelaKoiUtakmiciDomasni+"---"+tdKolkunatprevari+"\n";
	    		poraka+="Broj na odigrani natprevari gosti "+tabelaKoiUtakmiciGosti+"---"+tocnoNatprevari+"\n";
	    		poraka+=tabelaKoiUtakmiciDomasni+" -Pogolemi od koeficent plus "+ pogolemiOdKoeficineti + "\n";
	    		System.out.println(tabelaKoiUtakmiciDomasni+ " -Pomali od koeficent plus "+ pomaliOdKoeficineti);
	    		poraka+=tabelaKoiUtakmiciDomasni+ " -Pomali od koeficent plus "+ pomaliOdKoeficineti+"\n";
	    		//Domasni Kraj
	    		//Gosti
	    		pogolemiOdKoeficinetiGosti=0;
	    		pomaliOdKoeficinetiGosti=0;
	    		for (int ii=1;ii<=12;ii++){
		    		WebElement tabelaKorenerNatprevarDomasni=driver.findElement(By.cssSelector("table#away_history_table tr:nth-child("+ii+") td:nth-child(12) span:nth-child(3)"));
		    	 	String kojNatprevar=tabelaKorenerNatprevarDomasni.getText();
		    	 //////	System.out.println("kojNatprevar "+kojNatprevar);  
		    	 	Double kojNatprevar1=Double.parseDouble(kojNatprevar);
		    	  
		    		 int retval1 = Double.compare( kojNatprevar1, koeficientPlus);
		    		    
		    	     if(retval1 > 0) {
		    	    	 pogolemiOdKoeficinetiGosti =pogolemiOdKoeficinetiGosti + 1;
		    	     }
		    	     else if(retval1 < 0) {
		    	    	 pomaliOdKoeficinetiGosti = pomaliOdKoeficinetiGosti + 1; 
			     }
		    		}
		    		System.out.println(tabelaKoiUtakmiciGosti+" Gosti -Pogolemi od koeficent plus "+ pogolemiOdKoeficinetiGosti);
		    		poraka+=tabelaKoiUtakmiciGosti+" Gosti -Pogolemi od koeficent plus "+ pogolemiOdKoeficinetiGosti+"\n";
		    		System.out.println(tabelaKoiUtakmiciGosti+" Gosti- Pomali od koeficent plus "+ pomaliOdKoeficinetiGosti);
		    		poraka+=tabelaKoiUtakmiciGosti+" Gosti- Pomali od koeficent plus "+ pomaliOdKoeficinetiGosti+"\n";
		    	//Gosti Kraj


		    		int zbirPogelemiKoeficienti=pogolemiOdKoeficinetiGosti+pogolemiOdKoeficineti;
		    		///System.out.println("Zbir"+ zbirPogelemiKoeficienti);
		    			if (zbirPogelemiKoeficienti<=5){
		    			System.out.println( tabelaKoiUtakmiciDomasni+"---"+tabelaKoiUtakmiciGosti+"   -Go zadvoluva uslovot Komibinacija domasni "+pogolemiOdKoeficineti+"gosti"+pogolemiOdKoeficinetiGosti);
		    			poraka+=tabelaKoiUtakmiciDomasni+"---"+tabelaKoiUtakmiciGosti+"---Go zadvoluva uslovot Komibinacija domasni "+pogolemiOdKoeficineti+"gosti"+pogolemiOdKoeficinetiGosti;
		    			}
		    			else if(pogolemiOdKoeficineti==1&&pogolemiOdKoeficinetiGosti==5 )
		    			{
		    				System.out.println("Utakmicata go zadvoluva uslovot "+tabelaKoiUtakmiciDomasni+"---"+tabelaKoiUtakmiciGosti);
		    				poraka+="Utakmicata go zadvoluva uslovot "+tabelaKoiUtakmiciDomasni+"---"+tabelaKoiUtakmiciGosti+"\n";
		    			}
		    			else if(pogolemiOdKoeficineti==5&&pogolemiOdKoeficinetiGosti==1 )
		    			{
		    				System.out.println("Utakmicata Go zadvoluva uslovot "+tabelaKoiUtakmiciDomasni+"---"+tabelaKoiUtakmiciGosti);
		    				poraka+="Utakmicata go zadvoluva uslovot "+tabelaKoiUtakmiciDomasni+"---"+tabelaKoiUtakmiciGosti+"\\n";
				    		
		    			}
		    			else if(pogolemiOdKoeficineti==3&&pogolemiOdKoeficinetiGosti==3 )
		    			{
		    				System.out.println("Utakmicata go zadvoluva uslovot "+tabelaKoiUtakmiciDomasni+"---"+tabelaKoiUtakmiciGosti);
		    				poraka+="Utakmicata go zadvoluva uslovot "+tabelaKoiUtakmiciDomasni+"---"+tabelaKoiUtakmiciGosti+"\n";
				    		
		    			}
		    			
		    			int zbirPomaliKoeficienti=pomaliOdKoeficinetiGosti+pomaliOdKoeficineti;
			    	/////	System.out.println("Zbir"+ zbirPomaliKoeficienti);
			    			if (zbirPomaliKoeficienti<=5){
			    			System.out.println("Go zadvoluva uslovot kombinacijata "+tabelaKoiUtakmiciDomasni+"  "+pomaliOdKoeficineti+" "+" tabelaKoiUtakmiciGosti-"+pomaliOdKoeficinetiGosti);
			    			poraka+="Go zadvoluva uslovot kombinacijata "+tabelaKoiUtakmiciDomasni+"  "+pomaliOdKoeficineti+" "+" tabelaKoiUtakmiciGosti-"+pomaliOdKoeficinetiGosti+"\n";
			    			}
			    			else if(pomaliOdKoeficineti==1&&pomaliOdKoeficinetiGosti==5 )
			    			{
			    				System.out.println("Utakmicata go zadvoluva uslovot "+tabelaKoiUtakmiciDomasni+"---"+tabelaKoiUtakmiciGosti);
			    			poraka+=" Utakmicata go zadvoluva uslovot "+tabelaKoiUtakmiciDomasni+"---"+tabelaKoiUtakmiciGosti+"\n";
			    			}
			    			else if(pomaliOdKoeficineti==5&&pomaliOdKoeficinetiGosti==1 )
			    			{
			    				System.out.println("Utakmicata Go zadvoluva uslovot "+tabelaKoiUtakmiciDomasni+"---"+tabelaKoiUtakmiciGosti);
			    			poraka+="Utakmicata Go zadvoluva uslovot "+tabelaKoiUtakmiciDomasni+"---"+tabelaKoiUtakmiciGosti+"\n";
			    			}
			    			else if(pomaliOdKoeficineti==3&&pomaliOdKoeficinetiGosti==3 )
			    			{
			    				System.out.println("Utakmicata go zadvoluva uslovot "+tabelaKoiUtakmiciDomasni+"---"+tabelaKoiUtakmiciGosti);
			    			poraka+=" Utakmicata go zadvoluva uslovot "+tabelaKoiUtakmiciDomasni+"---"+tabelaKoiUtakmiciGosti+"\n";
			    			}
	    		}
	    		driver.switchTo().window(tabs2.get(1)).close();
	    		driver.switchTo().window(tabs2.get(0));
	    		 
	    		Wait.seconds(10);
	    }
	      final String username = "jenkinss4e@gmail.com";
	        final String password = "Passwords4e";
	        String to = "pecakova.bojana@gmail.com";
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
	            message.setSubject("A testing mail header total corner !!!");
	            message.setText(poraka);

	            Transport.send(message);

	            System.out.println("Done");

	        } 

	        catch (MessagingException e) 
	        {
	            // throw new RuntimeException(e);
	            System.out.println("Username or Password are incorrect ... exiting !");
	        }
}}


