package javaWebdriverTest;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class myJunitTest {
			
	
	static WebDriver driver;
	static testSearchClass testC ;
	String enterUrl = "https://cn.student.com";
	String sendStr = "伦敦";
	WebElement formelement = null;
	List<WebElement> listElement = null;
	List<WebElement> childlistElement = null;
	WebElement searchElement = null;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testC = new testSearchClass();
		String chromedrivepath= "mylib\\chromedriver.exe"; //chrome浏览器对应的驱动程序，必须与浏览器版本对应	
		System.setProperty("webdriver.chrome.driver", chromedrivepath);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.MICROSECONDS);
		//testC.navigateToUrl(driver, enterUrl);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		
		driver.quit(); //关闭所有页面
	}
	
	
	@Before
	public void setUp() throws Exception {
		
		
		
	}

	@After
	public void tearDown() throws Exception {
		
			
	}

	@Test
	public void testAOpenUrl() throws InterruptedException {
		
		driver.manage().window().maximize();//浏览器窗口放大
		
		testC.navigateToUrl(driver, enterUrl);	
		
		System.out.println("navigate to url");
		
	}

	@Test
	public void testBFindInputandSend() throws InterruptedException {
		
		WebElement inputelement = testC.getElement(driver, "xpath", "//input[@placeholder='输入城市、大学或公寓名' and contains(@class,'banner')]");
		if(inputelement != null) {
			inputelement.click();
			inputelement.sendKeys(sendStr);	
			System.out.println("have input text");
		}else {
			System.out.println("no find input element");
		}		
	}
	
	
	@Test
	public void testCFindFormAndList() throws InterruptedException {
		//获取数据表单
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.MICROSECONDS);
		formelement = testC.getElement(driver, "xpath", "//form[contains(@class,'autocomplete__form--datepicker')]");
		if(formelement != null) {
			
			System.out.println("find form of datalist");
			
			//获取数据列表
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.MICROSECONDS);
			listElement = testC.getlistElement(formelement, "tagName", "li");    	
			if(listElement.size() > 0) {
				System.out.println("find data of datalist");
				//获取数据列表带文本的子元素
				for(WebElement we: listElement)
		        {
					driver.manage().timeouts().implicitlyWait(3, TimeUnit.MICROSECONDS);
		    		WebElement element1 = we.findElement(By.xpath("a/div/div[2]/span[1]/span"));
		    		childlistElement.add(element1);
		        }
			}else {
				System.out.println("no find data of datalist");
			}	
			
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.MICROSECONDS);
			testC.getElement(driver, "className", "autocomplete__submit-btn");
		}else {
			//第一次获取失败后 尝试另外一种方法获取表单
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.MICROSECONDS);
			formelement = testC.getElement(driver, "xpath", "//form[contains(@class,'hero-search__search-form')]");
			
			if(formelement != null) {
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.MICROSECONDS);
				listElement = testC.getlistElement(formelement, "xpath", "//div[contains(@class,'search-list-desktop__item-container')]");
			    System.out.println("find form of datalist");
			    
			    //获取数据列表带文本的子元素
			    for(WebElement we: listElement)
		        {
			    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.MICROSECONDS);
		    		WebElement element1 = we.findElement(By.xpath("span/span[2]/span[1]/span[1]"));
		    		childlistElement.add(element1);
		        } 
			    
			    driver.manage().timeouts().implicitlyWait(3, TimeUnit.MICROSECONDS);
		    	searchElement = testC.getElement(driver, "className", "hero-banner-white__search");
		    	if(searchElement == null){
		    		driver.manage().timeouts().implicitlyWait(5, TimeUnit.MICROSECONDS);
		    		searchElement = testC.getElement(driver, "className", "hero-banner-dark__search");
		    	}
			}else {
				System.out.println("no find form of datalist");
			}
				    		
	    	
	    }
		
	}
		
	
	@Test
	public void testDListSize() throws InterruptedException {
		
		if(listElement != null) {
			
			System.out.println(String.valueOf(listElement.size()));
			assertTrue("列表内容不小于等于10",10>=listElement.size());
			
		}else {
		
			System.out.println("no find data list");
		}
				
	}

	@Test
	public void testEListText() throws InterruptedException {
		
		if(childlistElement != null) {
			
			for(WebElement child: childlistElement)
	        {
	    		String str = child.getText();
	    		System.out.println(str);
	    		assertThat(str,containsString(sendStr));
	    		//assertTrue(str.startsWith(sendStr));
	        }
						
		}else {
		
			System.out.println("no find Child data list");
		}
				
	}
		
		

	@Test
	public void testFSearchBtn() throws InterruptedException {
		
		if(searchElement != null) {
			
		    searchElement.click();
			System.out.println("search btn click");
		}else {
		    System.out.println("no find search btn");
	    }
		    	
	}					
		
	
	
}
