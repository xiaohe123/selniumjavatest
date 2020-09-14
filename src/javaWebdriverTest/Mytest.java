package javaWebdriverTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;


import org.junit.*;

public class Mytest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		//String chromepath = "C:\\Users\\haiyang\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe"; //���������λ�ã������ͬ�����ʱʹ��
		String chromedrivepath= "mylib\\chromedriver.exe"; //chrome�������Ӧ���������򣬱�����������汾��Ӧ
		
		//System.setProperty("webdriver.chrome.bin", chromepath);	
		System.setProperty("webdriver.chrome.driver", chromedrivepath);
		
		
		//��������������Ķ˿ں� ������ʱÿ�������˿ںŶ���һ��
		/*ChromeDriverService.Builder builder = new ChromeDriverService.Builder();
		ChromeDriverService chromeservice = builder.usingDriverExecutable(new File(chromedrivepath)).usingPort(7002).build();
		try {
			chromeservice.start();
		}catch(IOException e){
			e.printStackTrace();
		}*/
				
		//WebDriver driver = new ChromeDriver(chromeservice);	
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://cn.student.com");
		
		System.out.println("open");
		Thread.sleep(5000);
		
		
			
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.MICROSECONDS);		
		WebElement inputelement = driver.findElement(By.xpath("//input[@placeholder='������С���ѧ��Ԣ��' and contains(@class,'banner')]"));
		System.out.println("input");
		
	    inputelement.click();		
	    inputelement.sendKeys("�׶�");
	    
	    List<WebElement> listElement = null;
	    
	    
	    try {
	    	
	    	//Thread.sleep(5000);
	    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.MICROSECONDS);
	    	WebElement formelement = driver.findElement(By.xpath("//form[contains(@class,'autocomplete__form--datepicker')]"));
	    	
	    	listElement = formelement.findElements(By.tagName("li"));    	
	    	System.out.println(String.valueOf(listElement.size()));
	    	
	    	///ul/li[3]/a/div/div[2]/span[1]/span
	    	for(WebElement we: listElement)
	        {
	    		WebElement element1 = we.findElement(By.xpath("a/div/div[2]/span[1]/span"));
	    		String str = element1.getText();
	            System.out.println(str);
	        } 
	    	
	    	driver.manage().timeouts().implicitlyWait(3, TimeUnit.MICROSECONDS);
	    	
	    	
	    	
	    	WebElement searchElement = driver.findElement(By.className("autocomplete__submit-btn"));
	    	searchElement.click();
	    	System.out.println("search click");
	    
	    }catch(Exception e)  {
	    	
	    	//Thread.sleep(5000);
	    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.MICROSECONDS);
	    	WebElement formelement = driver.findElement(By.xpath("//form[contains(@class,'hero-search__search-form')]"));
	    	
	    	
	    	listElement = formelement.findElements(By.xpath("//div[contains(@class,'search-list-desktop__item-container')]"));
	    	System.out.println(String.valueOf(listElement.size()));
	    	
	    	for(WebElement we: listElement)
	        {
	    		WebElement element1 = we.findElement(By.xpath("span/span[2]/span[1]/span[1]"));
	    		String str = element1.getText();
	            System.out.println(str);
	        } 
	    			
	    	driver.manage().timeouts().implicitlyWait(3, TimeUnit.MICROSECONDS);
	    	
	    	WebElement searchElement = null;
	    	try {
	    		searchElement = driver.findElement(By.className("hero-banner-white__search"));
	    	}catch(NoSuchElementException ee) {
	    		searchElement = driver.findElement(By.className("hero-banner-dark__search"));
	    	}
	    	
	    	searchElement.click();
	    	System.out.println("search click");
	    }
	   
	    Thread.sleep(5000);
		
		driver.quit();
		

	}

}
