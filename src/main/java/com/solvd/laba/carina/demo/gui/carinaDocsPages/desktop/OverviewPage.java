package com.solvd.laba.carina.demo.gui.carinaDocsPages.desktop;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

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
	private static final String CARINA_HIGHLIGHTED_LINK_COLOR = "rgba(6, 194, 126, 1)";

	@FindBy(xpath = "/html/body/div[3]/main/div/div[3]/article/p[1]/img")
	private ExtendedWebElement carinaLog;
	
	@FindBy(xpath = "/html/body/header")
	private carinaHeader header;
	
	@FindBy(xpath = "/html/body/div[3]/main/div/div[1]/div/div/nav")
	private carinaSideNavigationBar navigationBar;
	
	public OverviewPage(WebDriver driver) {
		super(driver);
	}
	
	public ExtendedWebElement getCarinaLog() {
		return carinaLog;
	}

	public void setCarinaLog(ExtendedWebElement carinaLog) {
		this.carinaLog = carinaLog;
	}

	public carinaHeader getHeader() {
		return header;
	}

	public void setHeader(carinaHeader header) {
		this.header = header;
	}

	public carinaSideNavigationBar getNavigationBar() {
		return navigationBar;
	}

	public void setNavigationBar(carinaSideNavigationBar navigationBar) {
		this.navigationBar = navigationBar;
	}

	public String clickOverviewImageAndReturnURL() {
		this.header.clickOverViewImage();
		String browserURL = driver.getCurrentUrl(); 
		LOGGER.info("Getting current browser url : " + browserURL);
		return driver.getCurrentUrl();
	}
	
	public String getCarinaHeaderText() {
		String headerText = this.header.getCarinaTextSpan().getText();
		LOGGER.info("Getting Carina Header Text : " + headerText);
		return headerText;
	}
	
	public String getCarinaSearchBarPlaceHolderText() {
		String placeHolderText = this.header.getSearchBar().getAttribute("placeholder"); 
		LOGGER.info("Getting Carina Search Bar Placeholder Text : " + placeHolderText);
		return placeHolderText;
	}
	
	public String getCarinaHeaderGitHubLink() {
		String gitHubLinkURL = this.header.getGitHubLink().getAttribute("href"); 
		LOGGER.info("Getting Carina GitHub link URL : " + gitHubLinkURL);
		return gitHubLinkURL;
	}
	
	public void scrollToBottomOfPage() {
		LOGGER.info("Scrolling to bottom of page");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	public boolean checkVisibilityOfHeader() {
		LOGGER.info("Finding Header");
		WebElement header = driver.findElement(By.xpath("/html/body/header"));
		if (header.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public String getCarinaNavBarHeadingText() {
		String navBarHeadingText = this.navigationBar.getCarinaLabel().getText();
		LOGGER.info("Getting navigation bar heading text : " + navBarHeadingText);
		return navBarHeadingText; 
	}
	
	public boolean checkPresenceOfNavBarLinks() {
		LOGGER.info("Checking presence of navigation bar links");
		if (this.navigationBar.getNavBarLinks().size() > 0) {
			return true;
		}
		return false;
	}
	
	public boolean checkOverviewLinkHighlighted() {
		LOGGER.info("Checking if correct navigation bar link is highlighted"); 
		ArrayList<ExtendedWebElement> navBarLinks = new ArrayList<>(this.navigationBar.getNavBarLinks());
		if ((navBarLinks.get(0).getElement()).getCssValue("color") == CARINA_HIGHLIGHTED_LINK_COLOR) {
			return true;
		}
		return false;
	}
	
	public void clickParentNavBarLinks() {
		LOGGER.info("Clicking on parent nav bar links");
		this.navigationBar.getNavBarParentOfHiddenLinks().forEach((l) -> {
			l.click();
		});
	}
	
	public boolean checkVisibilityOfHiddenNavBarLinks() {
		LOGGER.info("Checking if hidden nav bar links are displayed"); 
		ArrayList<Boolean> displayedStatuses = new ArrayList<>();
		this.navigationBar.getNavBarHiddenLinks().forEach((hl) -> {
			displayedStatuses.add(hl.getElement().isDisplayed());
		});
		
		if (displayedStatuses.contains(false)) {
			return false;
		}
		return true; 
	}
	
	public boolean clickEveryNavLinkAndValidateColors() {
		ArrayList<Boolean> linkColors = new ArrayList<>();
		this.navigationBar.getNavBarLinks().forEach((l) -> {
			if (this.navigationBar.getNavBarParentOfHiddenLinks().contains(l)) {
			} else {
				l.click();
				if (l.getElement().getCssValue("color") == CARINA_HIGHLIGHTED_LINK_COLOR) {
					linkColors.add(true);
				} else {
					linkColors.add(false);
				}
			} 
		});
		if (linkColors.contains(false)) {
			return false;
		} else {
			return true;
		}
	}
}