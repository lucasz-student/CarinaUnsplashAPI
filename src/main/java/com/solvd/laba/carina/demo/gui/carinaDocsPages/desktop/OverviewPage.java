package com.solvd.laba.carina.demo.gui.carinaDocsPages.desktop;

import java.lang.invoke.MethodHandles;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.solvd.laba.carina.demo.gui.carinaDocsPages.common.carinaHeader;
import com.solvd.laba.carina.demo.gui.carinaDocsPages.common.carinaSideNavigationBar;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public class OverviewPage extends AbstractPage {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@FindBy(xpath = "/html/body/div[3]/main/div/div[3]/article/p[1]/img")
	private ExtendedWebElement carinaLog;
	
	@FindBy(xpath = "/html/body/header")
	private carinaHeader header;
	
	@FindBy(xpath = "/html/body/div[3]/main/div/div[1]/div/div/nav")
	private carinaSideNavigationBar navigationBar;
	
	public OverviewPage(WebDriver driver) {
		super(driver);
	}


	public String clickOverviewImageAndReturnURL() {
		this.header.clickOverViewImage();
		String browserURL = driver.getCurrentUrl(); 
		LOGGER.info("Getting current browser url : " + browserURL);
		return driver.getCurrentUrl();
	}
	
	public String getCarinaHeaderText() {
		String headerText = header.getTextSpanText();
		LOGGER.info("Getting Carina Header Text : " + headerText);
		return headerText;
	}
	
	public String getCarinaSearchBarPlaceHolderText() {
		String placeHolderText = header.getSearchBarPlaceHolder(); 
		LOGGER.info("Getting Carina Search Bar Placeholder Text : " + placeHolderText);
		return placeHolderText;
	}
	
	public String getCarinaHeaderGitHubLink() {
		String gitHubLinkURL = header.getGitHubLinkhref(); 
		LOGGER.info("Getting Carina GitHub link URL : " + gitHubLinkURL);
		return gitHubLinkURL;
	}
	
	public void scrollToBottomOfPage() {
		LOGGER.info("Scrolling to bottom of page");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	public boolean checkVisibilityOfHeaderAfterSearch() {
		LOGGER.info("Finding Header Again");
		ExtendedWebElement headerAgain = header.findExtendedWebElement(By.xpath("/html/body/header"));
		if (headerAgain.getElement().isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public String getCarinaNavBarHeadingText() {
		String navBarHeadingText = this.navigationBar.getLabelText();
		LOGGER.info("Getting navigation bar heading text : " + navBarHeadingText);
		return navBarHeadingText; 
	}
	
	public boolean checkPresenceOfNavBarLinks() {
		LOGGER.info("Checking presence of navigation bar links");
		if (this.navigationBar.getNavBarLinksSize() > 0) {
			return true;
		}
		return false;
	}
	
	public boolean checkOverviewLinkHighlighted() {
		LOGGER.info("Checking if correct navigation bar link is highlighted"); 
		return this.navigationBar.checkIfFirstLinkisHighlighted();
	}
	
	public void clickParentNavBarLinks() {
		LOGGER.info("Clicking on parent nav bar links");
		this.navigationBar.clickParentLinks();
	}
	
	public boolean checkVisibilityOfHiddenNavBarLinks() {
		LOGGER.info("Checking if hidden nav bar links are displayed"); 
		return this.navigationBar.checkVisibilityOfHiddenLinks();
	}
	
	public boolean clickEveryNavLinkAndValidateColors() {
		LOGGER.info("Checking if Link Colors are correct"); 
		return this.navigationBar.clickLinksAndValidateColors();
	}
}