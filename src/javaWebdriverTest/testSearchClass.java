package javaWebdriverTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class testSearchClass {
	
	public testSearchClass() {
		
	}
	
	public void navigateToUrl (WebDriver driver,String url) {
		
		//driver.navigate().to(url);
		driver.get(url);
	}
	

	public WebElement getElement(WebDriver driver,String byMethodName ,String elementpath) {
		
		WebElement myelement = null;
		
		try {
			
			switch(byMethodName) {			
			case "xpath":
				myelement = driver.findElement(By.xpath(elementpath));
				break;
			case "tagName":
				myelement = driver.findElement(By.tagName(elementpath));
				break;
			case "className":
				myelement = driver.findElement(By.className(elementpath));
				break;				
			}
			
			return myelement;
			
		}catch(NoSuchElementException e) {
			
			return null;
		}
	}
		
		
	public List<WebElement> getlistElement(WebDriver driver,String byMethodName ,String elementpath) {
			
		List<WebElement> mylistelement = null;
			
		try {
			switch(byMethodName) {
			case "xpath":
				mylistelement = driver.findElements(By.xpath(elementpath));
				break;
			case "tagName":
				mylistelement = driver.findElements(By.tagName(elementpath));
				break;
			case "className":
				mylistelement = driver.findElements(By.className(elementpath));
				break;			
			} 	
			return mylistelement;
				
		}catch (Exception e) {
				
			return mylistelement;
		}
	}
	
	public List<WebElement> getlistElement(WebElement element,String byMethodName ,String elementpath) {
		
		List<WebElement> mylistelement = null;
			
		try {
			switch(byMethodName) {
			case "xpath":
				mylistelement = element.findElements(By.xpath(elementpath));
				break;
			case "tagName":
				mylistelement = element.findElements(By.tagName(elementpath));
				break;
			case "className":
				mylistelement = element.findElements(By.className(elementpath));
				break;			
			} 	
			return mylistelement;
				
		}catch(NoSuchElementException e) {
				
			return null;
		}
	}
	
	
}
